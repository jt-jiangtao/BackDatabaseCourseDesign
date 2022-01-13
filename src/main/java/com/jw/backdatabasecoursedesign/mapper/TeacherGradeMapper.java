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
            "  AND scoreCalculatedView.teacherId = #{id} ORDER BY grade DESC")
    List<Map<String, Object>> getFinalScore(Integer courseId, String id);

    @Select("SELECT *\n" +
            "FROM courseExaminationScore\n" +
            "WHERE courseId = #{courseId}\n" +
            "  AND teacherId = #{id} ORDER BY score DESC")
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

    @Select("SELECT * FROM totalScoreView as t\n" +
            "WHERE t.courseId = #{courseId} AND t.teacherId = #{id} AND (t.examinationScore < 43 OR t.score < 60)")
    List<Map<String, Object>> selectCourseFailStudent(String id, Integer courseId);

    @Select("SELECT CAST(MAX(t.score) AS DECIMAL(5, 2)) AS max,\n" +
            "       CAST(AVG(t.score) AS DECIMAL(5, 2)) AS avg,\n" +
            "       CAST(MIN(t.score) AS DECIMAL(5, 2)) AS min\n" +
            "FROM totalScoreView as t\n" +
            "WHERE t.courseId = #{courseId}\n" +
            "  AND t.teacherId = #{id};")
    Map<String, Object> selectMinMaxAvg(String id, Integer courseId);

    @Select("<script>SELECT studentId,\n" +
            "        course.id        AS courseId,\n" +
            "        studyMode,\n" +
            "        teacherId,\n" +
            "        startTime,\n" +
            "        endTime,\n" +
            "        score,\n" +
            "        time,\n" +
            "        introduce,\n" +
            "        property,\n" +
            "        examinationId,\n" +
            "        course.name      AS courseName,\n" +
            "        proportion,\n" +
            "        grade,\n" +
            "        studentInfo.name AS studentName,\n" +
            "        classNumber\n" +
            "        FROM studentCourse\n" +
            "        JOIN course ON studentCourse.courseId = course.id\n" +
            "        JOIN examination ON examination.id = course.examinationId\n" +
            "        JOIN studentInfo ON studentId = studentInfo.number\n" +
            "        WHERE (studentId, examinationId) NOT IN (\n" +
            "        SELECT studentId, examinationId\n" +
            "        FROM studentExamination\n" +
            "        )\n" +
            "        AND course.status = 1\n" +
            "        <if test=\"courseId != -1\">\n" +
            "            AND examination.courseId = #{courseId}\n" +
            "        </if>\n" +
            "        AND teacherId = #{id};</script>")
    List<Map<String, Object>> teacherUndoExaminationScore(String id, Integer courseId);

    @Select("<script>SELECT studentId,\n" +
            "               ordinaryScore.courseId       AS courseId,\n" +
            "               studyMode,\n" +
            "               teacherId,\n" +
            "               startTime,\n" +
            "               endTime,\n" +
            "               score,\n" +
            "               introduce,\n" +
            "               property,\n" +
            "               examinationId,\n" +
            "               course.name                  AS courseName,\n" +
            "               ordinaryScoreItem.id         AS ordianryScoreItemId,\n" +
            "               ordinaryScore.proportion     AS ordinaryScoreProportion,\n" +
            "               ordinaryScoreItem.proportion AS ordinaryScoreItemProportion,\n" +
            "               ordinaryScoreItem.name       AS ordinaryScoreItemName,\n" +
            "               studentInfo.name             AS studentName,\n" +
            "               classNumber\n" +
            "        FROM studentCourse\n" +
            "                 JOIN course ON studentCourse.courseId = course.id\n" +
            "                 JOIN ordinaryScore ON course.ordinaryScoreId = ordinaryScore.id\n" +
            "                 JOIN ordinaryScoreItem ON ordinaryScore.id = ordinaryScoreItem.ordinaryScoreId\n" +
            "                 JOIN studentInfo ON studentId = studentInfo.number\n" +
            "        WHERE (studentId, ordinaryScoreItem.id) NOT IN (\n" +
            "            SELECT studentId, ordinaryScoreItemId\n" +
            "            FROM studentOrdinaryScoreItem\n" +
            "        )\n" +
            "          AND course.status = 1\n" +
            "          <if test=\"courseId != -1\">\n" +
            "              AND ordinaryScore.courseId = #{courseId}\n" +
            "          </if>\n" +
            "          AND teacherId = #{id};</script>")
    List<Map<String, Object>> teacherUndoStudentOrdinaryScore(String id, Integer courseId);

    @Select("<script>SELECT ordinaryScoreId,\n" +
            "               courseId,\n" +
            "               proportion AS ordinaryProportion,\n" +
            "               startTime,\n" +
            "               endTime,\n" +
            "               score,\n" +
            "            time,\n" +
            "            introduce,\n" +
            "            property,\n" +
            "            name,\n" +
            "            classNumber\n" +
            "        FROM ordinaryScore\n" +
            "            JOIN course ON course.ordinaryScoreId = ordinaryScore.id\n" +
            "        WHERE ordinaryScoreId NOT IN (\n" +
            "            SELECT ordinaryScoreItem.ordinaryScoreId\n" +
            "            FROM ordinaryScoreItem\n" +
            "            )\n" +
            "          AND status = 1\n" +
            "          AND teacherId = #{id}\n" +
            "          AND courseId = #{courseId};</script>")
    List<Map<String, Object>> teacherUndoOrdinaryItemScore(String id, Integer courseId);
}
