package com.jw.backdatabasecoursedesign.service.impl;

import com.jw.backdatabasecoursedesign.core.UnifyResponse;
import com.jw.backdatabasecoursedesign.entity.course.CourseStudent;
import com.jw.backdatabasecoursedesign.entity.excel.ExcelParse;
import com.jw.backdatabasecoursedesign.entity.grade.OrdinaryScoreItem;
import com.jw.backdatabasecoursedesign.entity.grade.OrdinaryStudentScore;
import com.jw.backdatabasecoursedesign.mapper.TeacherGradeMapper;
import com.jw.backdatabasecoursedesign.service.TeacherOrdinaryService;
import com.jw.backdatabasecoursedesign.utils.StreamStringUtils;
import lombok.SneakyThrows;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: jiangtao
 * @Date: 2022/1/11 17:19
 */
@Service
public class TeacherOrdinaryServiceImpl implements TeacherOrdinaryService {

    SqlSessionFactory sqlSessionFactory = null;

    public TeacherOrdinaryServiceImpl() throws IOException {
        String resource = "config/mybatis-config.xml";
//         读取配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
//         获取session工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }


    @Override
    public Object getOrdinaryScoreByTeacher(Integer courseId, Integer itemId, String teacherId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherGradeMapper teacherGradeMapper = sqlSession.getMapper(TeacherGradeMapper.class);
        return teacherGradeMapper.selectOrdinaryScoreByTeacher(courseId, itemId, teacherId);
    }

    @Override
    public void downloadOrdinaryExcel(String id, Integer courseId, HttpServletResponse response) throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherGradeMapper teacherGradeMapper = sqlSession.getMapper(TeacherGradeMapper.class);
        List<OrdinaryScoreItem> items = teacherGradeMapper.selectOrdinaryItems(id, courseId);

        List<String> header = new ArrayList<>();
        header.add("姓名");
        header.add("学号（关键信息，勿修改）");
        header.add("专业");
        header.add("班级");
        for (OrdinaryScoreItem item : items) {
            header.add(item.getName() + "( " + item.getProportion() * 100 + "% )");
        }

        List<CourseStudent> students = teacherGradeMapper.selectStudentInfoInCourse(id, courseId);

        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();

        //生成一个表格，设置表格名称为"学生表"
        HSSFSheet sheet = workbook.createSheet("平时成绩表");

        //设置表格列宽度为10个字节
        sheet.setDefaultColumnWidth(20);

        //创建第一行表头
        HSSFRow headrow = sheet.createRow(0);

        //遍历添加表头(下面模拟遍历学生，也是同样的操作过程)
        for (int i = 0; i < header.size(); i++) {
            //创建一个单元格
            HSSFCell cell = headrow.createCell(i);

            //创建一个内容对象
            HSSFRichTextString text = new HSSFRichTextString(header.get(i));

            //将内容对象的文字内容写入到单元格中
            cell.setCellValue(text);
        }

        for (int i = 0; i < students.size(); i++) {
            HSSFRow row = sheet.createRow(i + 1);

            HSSFCell cell0 = row.createCell(0);
            HSSFRichTextString text0 = new HSSFRichTextString(students.get(i).getStudentName());
            cell0.setCellValue(text0);

            HSSFCell cell1 = row.createCell(1);
            HSSFRichTextString text1 = new HSSFRichTextString(students.get(i).getStudentId());
            cell1.setCellValue(text1);

            HSSFCell cell2 = row.createCell(2);
            HSSFRichTextString text2 = new HSSFRichTextString(students.get(i).getSpecialtyName());
            cell2.setCellValue(text2);

            HSSFCell cell3 = row.createCell(3);
            HSSFRichTextString text3 = new HSSFRichTextString(students.get(i).getClassName());
            cell3.setCellValue(text3);
        }

        // 校验信息表
        HSSFSheet sheetAuto = workbook.createSheet("自动生成项，请勿改动");
        sheetAuto.setDefaultColumnWidth(20);
        HSSFRow headRowAuto = sheetAuto.createRow(0);
        HSSFCell cell = headRowAuto.createCell(0);
        HSSFRichTextString text = new HSSFRichTextString("本表为校验信息，请勿改动（校验成绩项是否存在）");
        cell.setCellValue(text);
        HSSFRow row = sheetAuto.createRow(1);
        HSSFCell cell0 = row.createCell(0);
        HSSFRichTextString text0 = new HSSFRichTextString(String.valueOf(items.size()));
        cell0.setCellValue(text0);
        for (int i = 0; i < items.size(); i++) {
            HSSFRow rowA = sheetAuto.createRow(2 + i);
            HSSFCell cellA = rowA.createCell(0);
            HSSFRichTextString textA = new HSSFRichTextString(items.get(i).getId().toString());
            cellA.setCellValue(textA);
        }

        //准备将Excel的输出流通过response输出到页面下载
        //八进制输出流
        response.setContentType("application/octet-stream");

        String fileName = "";
        fileName += "OrdinaryScore_" + courseId + "_" + id;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date d = new Date();
        String str = sdf.format(d);
        fileName += "_" + str;

        if (items.isEmpty()) fileName = "wrong file";

        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");

        //刷新缓冲
        response.flushBuffer();

        //workbook将Excel写入到response的输出流中，供页面下载
        workbook.write(response.getOutputStream());
    }

    @Override
    public Object uploadOrdinaryExcel(Integer courseId, String teacherId, MultipartFile file) throws IOException, InvalidFormatException {
        Workbook workbook = WorkbookFactory.create(file.getInputStream());

        // 提取学分项校验项
        Sheet sheet = workbook.getSheetAt(1);
        Sheet sheetInfo = workbook.getSheetAt(0);
        Integer itemNumber = Integer.valueOf(sheet.getRow(1).getCell(0).toString());
        List<Integer> itemIdList = new ArrayList<>();
        for (int i = 0; i < itemNumber; i++) {
            itemIdList.add(Integer.valueOf(sheet.getRow(2 + i).getCell(0).toString()));
        }

        // 分数项名称
        List<String> itemName = new ArrayList<>();
        for (int i = 4; i <= 3 + itemNumber; i++) {
            itemName.add(sheetInfo.getRow(0).getCell(i).toString());
        }

        int rowLength = sheetInfo.getLastRowNum();
        List<OrdinaryStudentScore> info = new ArrayList<>();
        List<Map<String, Object>> success = new ArrayList<>();
        List<Map<String, Object>> error = new ArrayList<>();
        // 读取表格构造对象
        for (int i = 1; i <= rowLength; i++) {
            Row row = sheetInfo.getRow(i);
            for (int j = 4; j <= 3 + itemNumber; j++) {
                try {
                    info.add(new OrdinaryStudentScore(Double.valueOf(row.getCell(j).toString()), itemIdList.get(j - 4), row.getCell(1).toString()));
                    Map<String, Object> successItem = new HashMap<>();
                    successItem.put("score", Double.valueOf(row.getCell(j).toString()));
                    successItem.put("OrdinaryScoreItemId", itemIdList.get(j - 4));
                    successItem.put("OrdinaryScoreItemName", itemName.get(j - 4));
                    successItem.put("studentId", row.getCell(1).toString());
                    success.add(successItem);
                } catch (Exception e) {
                    Map<String, Object> errorItem = new HashMap<>();
                    errorItem.put("OrdinaryScoreItemId", itemIdList.get(j - 4));
                    errorItem.put("OrdinaryScoreItemName", itemName.get(j - 4));
                    errorItem.put("studentId", row.getCell(1).toString());
                    try{
                        errorItem.put("score", Double.valueOf(row.getCell(j).toString()));
                    }catch (Exception ex){
                        errorItem.put("tips", "请检查该项分数格式是否填写或格式是否正确");
                    }
                    error.add(errorItem);
                }
            }
        }
        ExcelParse excelParse = new ExcelParse(success, error);
        if (!error.isEmpty()) return new UnifyResponse(1805, excelParse);

        return checkWithCommit(itemName, itemIdList, info, courseId);
    }

    @Override
    public Object uploadOrdinarySQL(Integer courseId, String teacherId, MultipartFile file) throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherGradeMapper teacherGradeMapper = sqlSession.getMapper(TeacherGradeMapper.class);
        List<OrdinaryScoreItem> items = teacherGradeMapper.selectOrdinaryItems(teacherId, courseId);
        if (items.isEmpty()) return new UnifyResponse(1811);
        List<String> itemName = new ArrayList<>();
        List<Integer> itemIdList = new ArrayList<>();
        items.forEach(ordinaryScoreItem -> {
            itemName.add(ordinaryScoreItem.getName());
            itemIdList.add(ordinaryScoreItem.getId());
        });

        String content = StreamStringUtils.convertStreamToString(file);
        Pattern regx = Pattern.compile("INSERT INTO[\\s\\S]*?--");
        Matcher table = regx.matcher(content);
        int tableNumber = 0;
        String tableContent = "";
        while (table.find()){
            tableNumber ++;
            tableContent = table.group();
        }
        if (tableNumber >= 2) return new UnifyResponse(1808);
        Pattern dataRegx = Pattern.compile("\\((.*?)\\)");
        Matcher dataM = dataRegx.matcher(tableContent);
        List<OrdinaryStudentScore> info = new ArrayList<>();
        List<Map<String, Object>> success = new ArrayList<>();
        List<Map<String, Object>> error = new ArrayList<>();
        if (dataM.find()){
            List<String> header = new ArrayList<>();
            List<String> raw = List.of(dataM.group(1).split(","));
            for (int i = 0; i < raw.size(); i++) {
                header.add(raw.get(i).replaceAll(" ", "").replaceAll("`",""));
            }
            if (!header.contains("score") || !header.contains("studentId") || !header.contains("ordinaryScoreItemId")){
                return new UnifyResponse(1809);
            }
            int indexScore = header.indexOf("score");
            int indexStudentId = header.indexOf("studentId");
            int indexOrdinaryScoreItemId = header.indexOf("ordinaryScoreItemId");
            while (dataM.find()){
                List<String> rowRaw = List.of(dataM.group(1).split(","));
                List<String> row = new ArrayList<>();
                for (int i = 0; i < rowRaw.size(); i++) {
                    row.add(rowRaw.get(i).replaceAll(" ", "").replaceAll("'",""));
                }
                try{
                    if (!itemIdList.contains(Integer.valueOf(row.get(indexOrdinaryScoreItemId)))) continue;
                    info.add(new OrdinaryStudentScore(Double.valueOf(row.get(indexScore)), Integer.valueOf(row.get(indexOrdinaryScoreItemId)), row.get(indexStudentId)));
                    Map<String, Object> successItem = new HashMap<>();
                    successItem.put("score", Double.valueOf(row.get(indexScore)));
                    successItem.put("OrdinaryScoreItemId", Integer.valueOf(row.get(indexOrdinaryScoreItemId)));
                    successItem.put("OrdinaryScoreItemName", itemName.get(itemIdList.indexOf(Integer.valueOf(row.get(indexOrdinaryScoreItemId)))));
                    successItem.put("studentId", row.get(indexStudentId));
                    success.add(successItem);
                } catch (Exception e) {
                    Map<String, Object> errorItem = new HashMap<>();
                    errorItem.put("OrdinaryScoreItemId", Integer.valueOf(row.get(indexOrdinaryScoreItemId)));
                    errorItem.put("OrdinaryScoreItemName", itemName.get(itemIdList.indexOf(Integer.valueOf(row.get(indexOrdinaryScoreItemId)))));
                    errorItem.put("studentId", row.get(indexStudentId));
                    try{
                        errorItem.put("score", Double.valueOf(row.get(indexScore)));
                    }catch (Exception ex){
                        errorItem.put("tips", "请检查该项分数格式是否填写或格式是否正确");
                    }
                    error.add(errorItem);
                }
            }
        }
        ExcelParse sqlParse = new ExcelParse(success, error);
        if (!error.isEmpty()) return new UnifyResponse(1810, sqlParse);

        return checkWithCommit(itemName, itemIdList, info, courseId);
    }

    @Override
    public Object addOrdinaryScore(String id, Integer courseId, List<OrdinaryStudentScore> itemList) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherGradeMapper teacherGradeMapper = sqlSession.getMapper(TeacherGradeMapper.class);
        List<OrdinaryScoreItem> items = teacherGradeMapper.selectOrdinaryItems(id, courseId);
        if (items.isEmpty()) return new UnifyResponse(1812);
        List<String> itemName = new ArrayList<>();
        List<Integer> itemIdList = new ArrayList<>();
        items.forEach(ordinaryScoreItem -> {
            itemName.add(ordinaryScoreItem.getName());
            itemIdList.add(ordinaryScoreItem.getId());
        });
        List<OrdinaryStudentScore> success = new ArrayList<>();
        List<Map<String, Object>> error = new ArrayList<>();
        for (int i = 0; i < itemList.size(); i++) {
            OrdinaryStudentScore score = itemList.get(i);
            if (itemIdList.contains(score.getOrdinaryScoreItemId())){
                success.add(score);
            }else {
                Map<String, Object> map = new HashMap<>();
                map.put("score", score.getScore());
                map.put("studentId", score.getStudentId());
                map.put("ordinaryItemId", score.getOrdinaryScoreItemId());
                map.put("status", score.getOrdinaryScoreItemId() + " 不为当前教师课程");
                error.add(map);
            }
        }

        if (!error.isEmpty()) {
            Map<String, Object> res = new HashMap<>();
            res.put("success", success);
            res.put("error", error);
            return new UnifyResponse(1813, res);
        }

        return checkWithCommit(itemName, itemIdList, itemList, courseId);
    }

    @Override
    public Object deleteStudentOrdinaryScore(String id, List<Integer> studentOrdinaryScoreId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherGradeMapper teacherGradeMapper = sqlSession.getMapper(TeacherGradeMapper.class);
        int status = teacherGradeMapper.deleteOrdinaryStudentItem(id, studentOrdinaryScoreId);
        if (status < studentOrdinaryScoreId.size()) {
            sqlSession.rollback();
            return new UnifyResponse(1814);
        }
        sqlSession.commit();
        Map<String, Object> map = new HashMap<>();
        map.put("status", studentOrdinaryScoreId + " 删除成功");
        map.put("success", studentOrdinaryScoreId);
        return map;
    }

    @Override
    public Object updateStudentOrdinaryScore(String id, Integer studentOrdinaryScoreId, Double newScore) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherGradeMapper teacherGradeMapper = sqlSession.getMapper(TeacherGradeMapper.class);
        int status = teacherGradeMapper.updateOrdinaryStudentItem(id, studentOrdinaryScoreId, newScore);
        if (status <= 0 ){
            sqlSession.rollback();
            return new UnifyResponse(1815);
        }
        sqlSession.commit();
        Map<String, Object> map = new HashMap<>();
        map.put("studentOrdinaryScoreId", studentOrdinaryScoreId);
        map.put("newScore", newScore);
        return map;
    }

    private Object checkWithCommit(List<String> itemName, List<Integer> itemIdList, List<OrdinaryStudentScore> info, Integer courseId){
        // 检验分数的正确性
        boolean wrong = false;
        List<Map<String, Object>> wrongList = new ArrayList<>();
        for (OrdinaryStudentScore score : info) {
            if (score.getScore() > 100 || score.getScore() < 0) {
                wrong = true;
                Map<String, Object> map = new HashMap<>();
                map.put("studentId", score.getStudentId());
                map.put("score", score.getScore());
                map.put("errorItemName", itemName.get(itemIdList.indexOf(score.getOrdinaryScoreItemId())));
                wrongList.add(map);
            }
        }
        if (wrong) {
            return new UnifyResponse(1802, wrongList);
        }

        // 检验学分项编号是否正确
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherGradeMapper teacherGradeMapper = sqlSession.getMapper(TeacherGradeMapper.class);
        boolean wrongItem = false;
        List<String> wrongItemList = new ArrayList<>();
        for (int i = 0; i < info.size(); i++) {
            OrdinaryStudentScore score = info.get(i);
            int status = teacherGradeMapper.selectOrdinaryScoreIsTrue(info.get(i).getOrdinaryScoreItemId(), courseId);
            if (status <= 0) {
                wrongItem = true;
                wrongItemList.add(itemName.get(itemIdList.indexOf(score.getOrdinaryScoreItemId())));
            }
        }
        if (wrongItem) {
            Map<String, Object> map = new HashMap<>();
            map.put("wrongItem", wrongItemList);
            return new UnifyResponse(1803, map);
        }


        // 检验学号的正确性
        boolean wrongItemNum = false;
        List<String> wrongItemNumList = new ArrayList<>();
        for (int i = 0; i < info.size(); i++) {
            OrdinaryStudentScore score = info.get(i);
            int status = teacherGradeMapper.selectStudentIdIsTrue(info.get(i).getStudentId(), courseId);
            if (status <= 0) {
                wrongItemNum = true;
                wrongItemNumList.add(score.getStudentId());
            }
        }
        if (wrongItemNum) {
            Map<String, Object> map = new HashMap<>();
            map.put("wrongStudentId", wrongItemNumList);
            return new UnifyResponse(1804, map);
        }

        // 写入数据库
        for (int i = 0; i < info.size(); i++) {
            OrdinaryStudentScore score = info.get(i);
            int status = teacherGradeMapper.insertIntoStudentOrdinaryScoreItem(score.getScore(), score.getOrdinaryScoreItemId(), score.getStudentId());
            if (status <= 0){
                sqlSession.rollback();
                return new UnifyResponse(1806);
            }
        }
        sqlSession.commit();
        return new UnifyResponse(1020, info);
    }
}
