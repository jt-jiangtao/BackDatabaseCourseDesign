package com.jw.backdatabasecoursedesign.mapper;

import com.jw.backdatabasecoursedesign.entity.course.Course;
import com.jw.backdatabasecoursedesign.entity.course.CourseStudent;
import com.jw.backdatabasecoursedesign.entity.grade.CourseGradeProportion;
import com.jw.backdatabasecoursedesign.entity.grade.OrdinaryScoreItem;
import com.jw.backdatabasecoursedesign.entity.grade.OrdinaryScoreWithStudentName;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

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

    @Select("SELECT * FROM ordinaryScoreItem AS osi WHERE osi.status = 1 AND osi.ordinaryScoreId IN\n" +
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

    @Select("SELECT COUNT(*) as count FROM ordinaryScoreItem AS osi\n" +
            "JOIN ordinaryScore AS os ON os.courseId = #{CourseId}\n" +
            "WHERE osi.id = #{id}")
    int selectOrdinaryScoreIsTrue(Integer id, Integer CourseId);

    @Select("SELECT COUNT(*) FROM studentCourse WHERE studentId = #{studentId}\n" +
            "AND courseId = #{CourseId}")
    int selectStudentIdIsTrue(String studentId, Integer CourseId);

    @Insert("INSERT INTO studentOrdinaryScoreItem(score, studentId, ordinaryScoreItemId)\n" +
            "VALUES(#{score}, #{studentId}, #{OrdinaryScoreItemId})\n" +
            "ON DUPLICATE KEY UPDATE score = #{score}")
    int insertIntoStudentOrdinaryScoreItem(Double score, Integer OrdinaryScoreItemId, String studentId);

    @Insert("INSERT INTO studentExamination(score, studentId, examinationId)\n" +
            "VALUES (#{score}, #{studentId}, #{examinationId})\n" +
            "ON DUPLICATE KEY UPDATE score = #{score};")
    int insertIntoStudentExaminationScoreItem(Double score, Integer examinationId, String studentId);

    int deleteOrdinaryStudentItem(String id, List<Integer> studentOrdinaryScoreId);

    @Update("UPDATE studentOrdinaryScoreItem\n" +
            "SET score = #{newScore}\n" +
            "WHERE id = #{studentOrdinaryScoreId}\n" +
            "  AND ordinaryScoreItemId\n" +
            "    IN (\n" +
            "          SELECT id AS ordinaryScoreItemId\n" +
            "          FROM ordinaryScoreItem\n" +
            "          WHERE ordinaryScoreId\n" +
            "                    IN (\n" +
            "                    SELECT id AS ordinaryScoreId\n" +
            "                    FROM course\n" +
            "                    WHERE teacherId = #{id}\n" +
            "                )\n" +
            "      );")
    int updateOrdinaryStudentItem(String id, Integer studentOrdinaryScoreId, Double newScore);

    @Select("SELECT year,\n" +
            "       term,\n" +
            "       courseName,\n" +
            "       property,\n" +
            "       score,\n" +
            "       studyMode,\n" +
            "       grade,\n" +
            "       studentId,\n" +
            "       courseId,\n" +
            "       studentInfo.name          AS studentName,\n" +
            "       specialtyName,\n" +
            "       CONCAT(class.number, '班') as className\n" +
            "FROM scoreCalculatedView\n" +
            "         JOIN studentInfo ON scoreCalculatedView.studentId = studentInfo.number\n" +
            "         JOIN class ON studentInfo.classId = class.id\n" +
            "         JOIN specialty ON class.specialtyId = specialty.id\n" +
            "WHERE courseId = #{courseId}\n" +
            "  AND scoreCalculatedView.teacherId = #{id};")
    List<Map<String, Object>> getFinalScore(Integer courseId, String id);

    @Select("SELECT *\n" +
            "FROM courseExaminationScore\n" +
            "WHERE courseId = #{courseId}\n" +
            "  AND teacherId = #{id};")
    List<Map<String, Object>> getCourseExaminationScore(Integer courseId, String id);

    @Select("SELECT examination.id AS id\n" +
            "FROM examination,\n" +
            "     course\n" +
            "WHERE examination.courseId = course.id\n" +
            "  AND teacherId = #{id}\n" +
            "  AND courseId = #{courseId};")
    List<Integer> selectExaminationIdByCourse(String id, Integer courseId);

    @Update("UPDATE studentExamination\n" +
            "SET score = #{newScore}\n" +
            "WHERE id = #{studentExaminationScoreId}\n" +
            "  AND examinationId\n" +
            "    IN (\n" +
            "          SELECT course.examinationId AS examinationId\n" +
            "          FROM course\n" +
            "          WHERE teacherId = #{id}\n" +
            "      );")
    int updateExaminationStudentItem(String id, Integer studentExaminationScoreId, Double newScore);

    int deleteExaminationStudentItem(String id, List<Integer> studentExaminationScoreId);
}
