package com.jw.backdatabasecoursedesign.service.impl;

import com.jw.backdatabasecoursedesign.core.UnifyResponse;
import com.jw.backdatabasecoursedesign.entity.course.CourseStudent;
import com.jw.backdatabasecoursedesign.entity.excel.ExcelExaminationParse;
import com.jw.backdatabasecoursedesign.entity.excel.ExcelParse;
import com.jw.backdatabasecoursedesign.entity.grade.CourseGradeProportion;
import com.jw.backdatabasecoursedesign.entity.grade.ExaminationStudentScore;
import com.jw.backdatabasecoursedesign.entity.grade.OrdinaryScoreItem;
import com.jw.backdatabasecoursedesign.entity.grade.OrdinaryStudentScore;
import com.jw.backdatabasecoursedesign.mapper.TeacherGradeMapper;
import com.jw.backdatabasecoursedesign.service.TeacherExaminationService;
import com.jw.backdatabasecoursedesign.utils.StreamStringUtils;
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
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: jiangtao
 * @Date: 2022/1/12 17:24
 */
@Service
public class TeacherExaminationServiceImpl implements TeacherExaminationService {
    SqlSessionFactory sqlSessionFactory = null;

    public TeacherExaminationServiceImpl() throws IOException {
        String resource = "config/mybatis-config.xml";
//         读取配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
//         获取session工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Override
    public Object getFinalScore(Integer courseId, String id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherGradeMapper teacherGradeMapper = sqlSession.getMapper(TeacherGradeMapper.class);
        return teacherGradeMapper.getFinalScore(courseId, id);
    }

    @Override
    public Object getExaminationScore(Integer courseId, String id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherGradeMapper teacherGradeMapper = sqlSession.getMapper(TeacherGradeMapper.class);
        return teacherGradeMapper.getCourseExaminationScore(courseId, id);
    }

    @Override
    public void downloadExaminationExcel(String id, Integer courseId, HttpServletResponse response) throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherGradeMapper teacherGradeMapper = sqlSession.getMapper(TeacherGradeMapper.class);

        CourseGradeProportion gradeProportion = teacherGradeMapper.selectCourseProportion(courseId, id);

        List<String> header = new ArrayList<>();
        header.add("姓名");
        header.add("学号（关键信息，勿修改）");
        header.add("专业");
        header.add("班级");

        assert gradeProportion != null;
        header.add("考试成绩( " + gradeProportion.getExamProportion()*100 + "% )");

        List<CourseStudent> students = teacherGradeMapper.selectStudentInfoInCourse(id, courseId);

        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();

        //生成一个表格，设置表格名称为"学生表"
        HSSFSheet sheet = workbook.createSheet("期末成绩表");

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


        //准备将Excel的输出流通过response输出到页面下载
        //八进制输出流
        response.setContentType("application/octet-stream");

        String fileName = "";
        fileName += "ExaminationScore_" + courseId + "_" + id;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date d = new Date();
        String str = sdf.format(d);
        fileName += "_" + str;

        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");

        //刷新缓冲
        response.flushBuffer();

        //workbook将Excel写入到response的输出流中，供页面下载
        workbook.write(response.getOutputStream());
    }

    @Override
    public Object uploadExcel(String id, Integer courseId, MultipartFile file) throws IOException, InvalidFormatException {
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheetInfo = workbook.getSheetAt(0);
        int rowLength = sheetInfo.getLastRowNum();

        List<ExaminationStudentScore> info = new ArrayList<>();
        List<ExaminationStudentScore> success = new ArrayList<>();
        List<Map<String, Object>> error = new ArrayList<>();
        for (int i = 1; i <= rowLength; i++) {
            Row row = sheetInfo.getRow(i);
            try{
                String studentId = row.getCell(1).toString();
                Double score = Double.valueOf(row.getCell(4).toString());
                ExaminationStudentScore scoreItem = new ExaminationStudentScore(score, studentId);
                info.add(scoreItem);
                success.add(scoreItem);
            }catch (Exception e){
                Map<String, Object> errorItem = new HashMap<>();
                errorItem.put("studentId", row.getCell(1).toString());
                errorItem.put("courseId", courseId);
                try{
                    errorItem.put("score", Double.valueOf(row.getCell(4).toString()));
                }catch (Exception ex){
                    errorItem.put("tips", "请检查该项分数格式是否填写或格式是否正确");
                }
                error.add(errorItem);
            }
        }

        ExcelExaminationParse excelExaminationParse = new ExcelExaminationParse(success, error);
        if (!error.isEmpty()){
            return new UnifyResponse(1903, excelExaminationParse);
        }
        return checkWithCommit(info, courseId, id);
    }

    @Override
    public Object uploadSQL(String id, Integer courseId, MultipartFile file) throws IOException {
        String content = StreamStringUtils.convertStreamToString(file);
        Pattern regx = Pattern.compile("INSERT INTO[\\s\\S]*?;");
        Matcher table = regx.matcher(content);
        int tableNumber = 0;
        String tableContent = "";
        while (table.find()){
            tableNumber ++;
            tableContent = table.group();
        }
        if (tableNumber >= 2) return new UnifyResponse(1908);
        Pattern dataRegx = Pattern.compile("\\((.*?)\\)");
        Matcher dataM = dataRegx.matcher(tableContent);
        List<ExaminationStudentScore> info = new ArrayList<>();
        List<ExaminationStudentScore> success = new ArrayList<>();
        List<Map<String, Object>> error = new ArrayList<>();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherGradeMapper teacherGradeMapper = sqlSession.getMapper(TeacherGradeMapper.class);
        List<Integer> list = teacherGradeMapper.selectExaminationIdByCourse(id, courseId);
        if (list.size() > 2 ) return new UnifyResponse(1905);
        if (list.isEmpty()) return new UnifyResponse(1906);
        Integer examinationId = list.get(0);

        if (dataM.find()){
            List<String> header = new ArrayList<>();
            List<String> raw = List.of(dataM.group(1).split(","));
            for (int i = 0; i < raw.size(); i++) {
                header.add(raw.get(i).replaceAll(" ", "").replaceAll("`",""));
            }
            if (!header.contains("score") || !header.contains("studentId") || !header.contains("examinationId")){
                return new UnifyResponse(1909);
            }

            int indexScore = header.indexOf("score");
            int indexStudentId = header.indexOf("studentId");
            int indexExaminationId = header.indexOf("examinationId");
            while (dataM.find()){
                List<String> rowRaw = List.of(dataM.group(1).split(","));
                List<String> row = new ArrayList<>();
                for (int i = 0; i < rowRaw.size(); i++) {
                    row.add(rowRaw.get(i).replaceAll(" ", "").replaceAll("'",""));
                }
                try{
                    if (!Integer.valueOf(row.get(indexExaminationId)).equals(examinationId)) continue;
                    ExaminationStudentScore successItem = new ExaminationStudentScore(Double.valueOf(row.get(indexScore)) , row.get(indexStudentId));
                    info.add(successItem);
                    success.add(successItem);
                } catch (Exception e) {
                    Map<String, Object> errorItem = new HashMap<>();
                    errorItem.put("ExaminationScoreItemId", Integer.valueOf(row.get(indexExaminationId)));
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
        ExcelExaminationParse sqlParse = new ExcelExaminationParse(success, error);
        if (!error.isEmpty()) return new UnifyResponse(1910, sqlParse);

        return checkWithCommit(info, courseId, id);
    }

    @Override
    public Object checkWithCommit(List<ExaminationStudentScore> info, Integer courseId, String teacherId){
        // 检验分数的正确性
        boolean wrong = false;
        List<Map<String, Object>> wrongList = new ArrayList<>();
        for (ExaminationStudentScore score : info) {
            if (score.getScore() > 100 || score.getScore() < 0) {
                wrong = true;
                Map<String, Object> map = new HashMap<>();
                map.put("studentId", score.getStudentId());
                map.put("score", score.getScore());
                map.put("status", "分数范围错误");
                wrongList.add(map);
            }
        }
        if (wrong) {
            return new UnifyResponse(1904, wrongList);
        }

        // 检验是否有该门课程的考试
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherGradeMapper teacherGradeMapper = sqlSession.getMapper(TeacherGradeMapper.class);
        List<Integer> list = teacherGradeMapper.selectExaminationIdByCourse(teacherId, courseId);
        if (list.size() > 2 ) return new UnifyResponse(1905);
        if (list.isEmpty()) return new UnifyResponse(1906);
        Integer examinationId = list.get(0);

        // 检验学号的正确性
        boolean wrongItemNum = false;
        List<String> wrongItemNumList = new ArrayList<>();
        for (int i = 0; i < info.size(); i++) {
            ExaminationStudentScore score = info.get(i);
            int status = teacherGradeMapper.selectStudentIdIsTrue(info.get(i).getStudentId(), courseId);
            if (status <= 0) {
                wrongItemNum = true;
                wrongItemNumList.add(score.getStudentId());
            }
        }
        if (wrongItemNum) {
            Map<String, Object> map = new HashMap<>();
            map.put("wrongStudentId", wrongItemNumList);
            return new UnifyResponse(1907, map);
        }

        // 写入数据库
        for (int i = 0; i < info.size(); i++) {
            ExaminationStudentScore score = info.get(i);
            int status = teacherGradeMapper.insertIntoStudentExaminationScoreItem(score.getScore(), examinationId, score.getStudentId());
            if (status <= 0){
                sqlSession.rollback();
                return new UnifyResponse(1806);
            }
        }
        sqlSession.commit();
        return new UnifyResponse(1020, info);
    }

    @Override
    public Object deleteExaminationScore(String id, List<Integer> studentOrdinaryScoreId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherGradeMapper teacherGradeMapper = sqlSession.getMapper(TeacherGradeMapper.class);
        int status = teacherGradeMapper.deleteExaminationStudentItem(id, studentOrdinaryScoreId);
        if (status < studentOrdinaryScoreId.size()) {
            sqlSession.rollback();
            return new UnifyResponse(1912);
        }
        sqlSession.commit();
        Map<String, Object> map = new HashMap<>();
        map.put("status", studentOrdinaryScoreId + " 删除成功");
        map.put("success", studentOrdinaryScoreId);
        return map;
    }

    @Override
    public Object updateStudentExaminationScore(String id, Integer studentExaminationScoreId, Double newScore) {
        if (newScore > 100 || newScore < 0) return new UnifyResponse(1904);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TeacherGradeMapper teacherGradeMapper = sqlSession.getMapper(TeacherGradeMapper.class);
        int status = teacherGradeMapper.updateExaminationStudentItem(id, studentExaminationScoreId, newScore);
        if (status <= 0 ){
            sqlSession.rollback();
            return new UnifyResponse(1911);
        }
        sqlSession.commit();
        Map<String, Object> map = new HashMap<>();
        map.put("studentExaminationScoreId", studentExaminationScoreId);
        map.put("newScore", newScore);
        return map;
    }

}
