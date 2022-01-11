package com.jw.backdatabasecoursedesign.service.impl;

import com.jw.backdatabasecoursedesign.entity.course.CourseStudent;
import com.jw.backdatabasecoursedesign.entity.grade.OrdinaryScoreItem;
import com.jw.backdatabasecoursedesign.mapper.TeacherGradeMapper;
import com.jw.backdatabasecoursedesign.service.TeacherOrdinaryService;
import lombok.SneakyThrows;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        for (OrdinaryScoreItem item: items) {
            header.add(item.getName() + "( " + item.getProportion()*100 + "% )");
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

        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");

        //刷新缓冲
        response.flushBuffer();

        //workbook将Excel写入到response的输出流中，供页面下载
        workbook.write(response.getOutputStream());
    }
}
