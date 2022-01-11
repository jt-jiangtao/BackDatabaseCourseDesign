package com.jw.backdatabasecoursedesign.mapper;

import com.jw.backdatabasecoursedesign.entity.course.Course;
import com.jw.backdatabasecoursedesign.entity.course.CourseStudent;
import com.jw.backdatabasecoursedesign.entity.grade.CourseGradeProportion;
import com.jw.backdatabasecoursedesign.entity.grade.OrdinaryScoreItem;
import com.jw.backdatabasecoursedesign.entity.grade.OrdinaryScoreWithStudentName;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author: jiangtao
 * @Date: 2022/1/10 21:48
 */
@Mapper
public interface TeacherGradeMapper {

    @Select("SELECT c.name ,o.proportion AS normalProportion, e.proportion AS examProportion, e.courseId, teacherId  FROM course AS c, ordinaryScore AS o, examination AS e WHERE c.id = o.courseId AND c.id = e.courseId AND e.courseId = #{courseId} AND teacherId = #{id};")
    CourseGradeProportion selectCourseProportion(Integer courseId, String id);

    @Update("UPDATE ordinaryScore SET proportion = #{normalProportion} WHERE courseId = #{courseId} AND id IN (SELECT ordinaryScoreId AS id FROM course AS c WHERE c.id = #{courseId} AND c.teacherId = #{id})")
    Integer updateOrdinaryScore(String id, Integer courseId, Double normalProportion);

    @Update("UPDATE examination SET proportion = #{examProportion} WHERE courseId = #{courseId} AND id IN (SELECT examinationId AS id FROM course AS c WHERE c.id = #{courseId} AND c.teacherId = #{id})")
    Integer updateExaminationScore(String id, Integer courseId, Double examProportion);

    int addOrdinaryItem(String id, Integer courseId, List<OrdinaryScoreItem> items);

    int deleteOrdinaryItem(List<Integer> list);

    @Select("SELECT * FROM ordinaryScoreItem AS osi WHERE osi.ordinaryScoreId IN\n" +
            "(\n" +
            "    SELECT os.id AS id FROM ordinaryScore AS os WHERE os.courseId IN\n" +
            "    (\n" +
            "        SELECT c.id AS id FROM course AS c WHERE c.id = #{courseId} AND c.teacherId = #{id} AND c.status = 1\n" +
            "        )\n" +
            "    )")
    List<OrdinaryScoreItem> selectOrdinaryItems(String id, Integer courseId);

    List<OrdinaryScoreWithStudentName> selectOrdinaryScoreByTeacher(Integer courseId, Integer itemId, String teacherId);

    @Select("SELECT studentId, st.name as studentName, specialtyName, CONCAT(c.number, '班') as className, course.name as courseName\n" +
            "FROM studentCourse AS sc\n" +
            "JOIN studentInfo AS st ON sc.studentId = st.number\n" +
            "JOIN class AS c ON st.classId = c.id\n" +
            "JOIN specialty AS s ON s.id = c.specialtyId\n" +
            "JOIN course ON course.id = sc.courseId AND course.teacherId = #{id}\n" +
            "WHERE sc.courseId = #{courseId}")
    List<CourseStudent> selectStudentInfoInCourse(String id, Integer courseId);
}
