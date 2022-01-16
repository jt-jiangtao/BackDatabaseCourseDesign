-- phpMyAdmin SQL Dump
-- version 4.4.15.10
-- https://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 2022-01-16 17:14:50
-- 服务器版本： 5.6.50-log
-- PHP Version: 5.6.40

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `scorerelease`
--

-- --------------------------------------------------------

--
-- 表的结构 `access`
--

CREATE TABLE IF NOT EXISTS `access` (
  `id` int(11) NOT NULL COMMENT '编号;编号',
  `accessName` varchar(255) NOT NULL COMMENT '权限名称;权限名称（根据权限名称来确定是否可以访问某一资源）',
  `status` varchar(1) NOT NULL DEFAULT '1' COMMENT '状态;是否可用'
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='权限';

--
-- 转存表中的数据 `access`
--

INSERT INTO `access` (`id`, `accessName`, `status`) VALUES
(1, 'STUDENT', '1'),
(2, 'TEACHER', '1'),
(3, 'DEPT', '1'),
(4, 'SCHOOL', '1');

-- --------------------------------------------------------

--
-- 表的结构 `class`
--

CREATE TABLE IF NOT EXISTS `class` (
  `id` int(11) NOT NULL COMMENT '编号;编号',
  `number` int(11) NOT NULL COMMENT '班级号;班级号',
  `specialtyId` int(11) NOT NULL COMMENT '专业编号;专业编号',
  `status` varchar(1) NOT NULL DEFAULT '1' COMMENT '状态;有效',
  `teacherId` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='班级表';

--
-- 转存表中的数据 `class`
--

INSERT INTO `class` (`id`, `number`, `specialtyId`, `status`, `teacherId`) VALUES
(1, 1901, 1, '1', NULL),
(2, 1902, 1, '1', NULL),
(3, 1903, 1, '1', NULL),
(4, 1904, 1, '1', NULL),
(5, 1901, 2, '1', NULL),
(6, 1902, 2, '1', NULL),
(7, 1903, 2, '1', NULL),
(8, 1904, 2, '1', NULL);

-- --------------------------------------------------------

--
-- 替换视图以便查看 `classWithDepartment`
--
CREATE TABLE IF NOT EXISTS `classWithDepartment` (
`specialtyId` int(11)
,`specialtyName` varchar(255)
,`deptId` int(11)
,`deptName` varchar(255)
,`classId` int(11)
,`number` int(11)
,`classTeacher` int(11)
);

-- --------------------------------------------------------

--
-- 表的结构 `course`
--

CREATE TABLE IF NOT EXISTS `course` (
  `id` int(11) NOT NULL COMMENT '课程编号;课程编号',
  `teacherId` varchar(255) NOT NULL COMMENT '教师编号;教师编号',
  `startTime` varchar(90) NOT NULL COMMENT '开课时间;开课时间',
  `endTime` varchar(90) NOT NULL COMMENT '结课时间;结课时间',
  `score` decimal(2,1) NOT NULL COMMENT '学分;学分',
  `time` int(11) NOT NULL COMMENT '学时;学时',
  `introduce` varchar(255) DEFAULT NULL COMMENT '简介;简介',
  `property` varchar(255) NOT NULL COMMENT '性质;性质',
  `ordinaryScoreId` int(11) DEFAULT NULL COMMENT '平时成绩编号;平时成绩编号',
  `examinationId` int(11) DEFAULT NULL COMMENT '考试编号;考试编号',
  `yearTermId` int(11) NOT NULL COMMENT '学年学期编号;学年学期编号',
  `status` varchar(1) NOT NULL DEFAULT '1' COMMENT '状态;有效',
  `name` varchar(255) NOT NULL,
  `classNumber` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='课程表';

--
-- 转存表中的数据 `course`
--

INSERT INTO `course` (`id`, `teacherId`, `startTime`, `endTime`, `score`, `time`, `introduce`, `property`, `ordinaryScoreId`, `examinationId`, `yearTermId`, `status`, `name`, `classNumber`) VALUES
(1, '3725001', '第1周', '第16周', '4.5', 48, NULL, '专业必修课', 1, 4, 7, '1', '数据库', 1),
(2, '3725001', '第6周', '第10周', '1.0', 24, NULL, '专业必修课', 2, 3, 7, '1', '软件工程导论', 1),
(3, '3725002', '第1周', '第16周', '2.5', 32, NULL, '专业必修课', 3, 2, 7, '1', 'c', 1),
(4, '3725002', '第1周', '第16周', '2.5', 32, NULL, '专业必修课', 4, 1, 7, '1', 'c++', 1);

-- --------------------------------------------------------

--
-- 替换视图以便查看 `courseExaminationScore`
--
CREATE TABLE IF NOT EXISTS `courseExaminationScore` (
`specialtyName` varchar(255)
,`className` varchar(12)
,`studentId` varchar(255)
,`courseScore` decimal(2,1)
,`courseName` varchar(255)
,`grade` varchar(1)
,`score` decimal(4,1)
,`studentName` varchar(255)
,`property` varchar(255)
,`courseId` int(11)
,`teacherId` varchar(255)
,`proportion` decimal(3,2)
,`calculatedScore` decimal(7,3)
,`examinationId` int(11)
,`studentExaminationId` int(11)
,`studentSpecialtyId` int(11)
,`studentClassId` int(11)
);

-- --------------------------------------------------------

--
-- 替换视图以便查看 `courseWithTeacherAndDepartment`
--
CREATE TABLE IF NOT EXISTS `courseWithTeacherAndDepartment` (
`teacherId` varchar(255)
,`teacherName` varchar(255)
,`taskGroupId` int(11)
,`groupName` varchar(255)
,`specialtyId` int(11)
,`specialtyName` varchar(255)
,`deptId` int(11)
,`deptName` varchar(255)
,`courseId` int(11)
,`startTime` varchar(90)
,`endTime` varchar(90)
,`score` decimal(2,1)
,`time` int(11)
,`introduce` varchar(255)
,`property` varchar(255)
,`ordinaryScoreId` int(11)
,`examinationId` int(11)
,`courseName` varchar(255)
,`classNumber` int(11)
,`year` varchar(255)
,`term` varchar(255)
);

-- --------------------------------------------------------

--
-- 表的结构 `department`
--

CREATE TABLE IF NOT EXISTS `department` (
  `id` int(11) NOT NULL COMMENT '编号;编号',
  `deptName` varchar(255) NOT NULL COMMENT '系名称;系名称',
  `status` varchar(1) NOT NULL DEFAULT '1' COMMENT '状态;有效'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系部表';

--
-- 转存表中的数据 `department`
--

INSERT INTO `department` (`id`, `deptName`, `status`) VALUES
(1, '计算机科学学院', '1'),
(2, '数学与统计学院', '1');

-- --------------------------------------------------------

--
-- 表的结构 `examination`
--

CREATE TABLE IF NOT EXISTS `examination` (
  `id` int(11) NOT NULL COMMENT '编号;编号',
  `courseId` int(11) NOT NULL COMMENT '课程编号;课程编号',
  `proportion` decimal(3,2) NOT NULL COMMENT '占比;占比',
  `grade` varchar(1) NOT NULL COMMENT '等级;等级',
  `status` varchar(1) NOT NULL DEFAULT '1' COMMENT '状态;有效',
  `examinationArrange` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='考试表';

--
-- 转存表中的数据 `examination`
--

INSERT INTO `examination` (`id`, `courseId`, `proportion`, `grade`, `status`, `examinationArrange`) VALUES
(1, 4, '0.80', 'A', '1', NULL),
(2, 3, '0.50', 'A', '1', NULL),
(3, 2, '0.60', 'A', '1', NULL),
(4, 1, '0.80', 'B', '1', NULL);

-- --------------------------------------------------------

--
-- 表的结构 `inputTime`
--

CREATE TABLE IF NOT EXISTS `inputTime` (
  `id` int(11) NOT NULL COMMENT '编号;编号',
  `yearTermId` int(11) NOT NULL COMMENT '学年学期编号;学年学期编号',
  `startTime` date NOT NULL COMMENT '录入开始时间;录入开始时间',
  `endTime` date NOT NULL COMMENT '录入结束;录入结束',
  `status` varchar(1) NOT NULL DEFAULT '1' COMMENT '状态;状态'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='录入时间表';

--
-- 转存表中的数据 `inputTime`
--

INSERT INTO `inputTime` (`id`, `yearTermId`, `startTime`, `endTime`, `status`) VALUES
(1, 8, '2021-12-20', '2022-01-20', '1');

-- --------------------------------------------------------

--
-- 表的结构 `ordinaryScore`
--

CREATE TABLE IF NOT EXISTS `ordinaryScore` (
  `id` int(11) NOT NULL COMMENT '编号;编号',
  `courseId` int(11) NOT NULL COMMENT '课程编号;课程编号',
  `proportion` decimal(3,2) NOT NULL COMMENT '占比;占比'
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='平时成绩表';

--
-- 转存表中的数据 `ordinaryScore`
--

INSERT INTO `ordinaryScore` (`id`, `courseId`, `proportion`) VALUES
(1, 1, '0.20'),
(2, 2, '0.40'),
(3, 3, '0.50'),
(4, 4, '0.20');

-- --------------------------------------------------------

--
-- 表的结构 `ordinaryScoreItem`
--

CREATE TABLE IF NOT EXISTS `ordinaryScoreItem` (
  `id` int(11) NOT NULL COMMENT '编号',
  `ordinaryScoreId` int(11) NOT NULL COMMENT '平时成绩编号',
  `name` varchar(255) NOT NULL COMMENT '平时成绩项名称',
  `time` date NOT NULL COMMENT '平时成绩项考核时间',
  `proportion` decimal(3,2) NOT NULL COMMENT '平时成绩项占比',
  `status` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `ordinaryScoreItem`
--

INSERT INTO `ordinaryScoreItem` (`id`, `ordinaryScoreId`, `name`, `time`, `proportion`, `status`) VALUES
(1, 3, '考核', '2021-12-14', '0.30', 1),
(2, 3, '实验', '2022-01-03', '0.70', 1),
(3, 4, '考勤', '2022-01-10', '0.20', 1),
(4, 4, '实验报告', '2021-12-17', '0.80', 1),
(48, 1, '实验3', '2021-11-03', '1.00', 1);

-- --------------------------------------------------------

--
-- 替换视图以便查看 `ordinaryScoreView`
--
CREATE TABLE IF NOT EXISTS `ordinaryScoreView` (
`courseId` int(11)
,`year` varchar(255)
,`term` varchar(255)
,`courseName` varchar(255)
,`time` date
,`name` varchar(255)
,`score` varchar(255)
,`studentId` varchar(255)
);

-- --------------------------------------------------------

--
-- 替换视图以便查看 `ordinaryScoreViewCalculated`
--
CREATE TABLE IF NOT EXISTS `ordinaryScoreViewCalculated` (
`calculatedOrdinaryScore` decimal(5,2)
,`courseId` int(11)
,`year` varchar(255)
,`term` varchar(255)
,`courseName` varchar(255)
,`studentId` varchar(255)
,`teacherId` varchar(255)
);

-- --------------------------------------------------------

--
-- 替换视图以便查看 `ordinaryScoreViewWithProportion`
--
CREATE TABLE IF NOT EXISTS `ordinaryScoreViewWithProportion` (
`teacherId` varchar(255)
,`courseId` int(11)
,`year` varchar(255)
,`term` varchar(255)
,`courseName` varchar(255)
,`time` date
,`name` varchar(255)
,`score` varchar(255)
,`studentId` varchar(255)
,`p1` decimal(3,2)
,`p2` decimal(3,2)
,`itemId` int(11)
,`studentOrdinaryScoreItemId` int(11)
);

-- --------------------------------------------------------

--
-- 表的结构 `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL COMMENT '编号;编号',
  `roleName` varchar(255) NOT NULL COMMENT '角色名称;角色名称',
  `status` varchar(1) NOT NULL DEFAULT '1' COMMENT '状态;角色是否可用'
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='角色表';

--
-- 转存表中的数据 `role`
--

INSERT INTO `role` (`id`, `roleName`, `status`) VALUES
(1, 'STUDENT', '1'),
(2, 'TEACHER', '1'),
(3, 'DEPT_MANAGER', '1'),
(4, 'SCHOOL_MANAGER', '1');

-- --------------------------------------------------------

--
-- 表的结构 `roleAccess`
--

CREATE TABLE IF NOT EXISTS `roleAccess` (
  `id` int(11) NOT NULL COMMENT '编号;编号',
  `roleId` int(11) NOT NULL COMMENT '角色编号;角色编号',
  `accessId` int(11) NOT NULL COMMENT '权限编号;权限编号',
  `status` varchar(1) NOT NULL DEFAULT '1' COMMENT '状态;是否可用'
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='角色权限表';

--
-- 转存表中的数据 `roleAccess`
--

INSERT INTO `roleAccess` (`id`, `roleId`, `accessId`, `status`) VALUES
(1, 1, 1, '1'),
(2, 2, 2, '1'),
(3, 3, 1, '1'),
(4, 3, 2, '1'),
(5, 3, 3, '1'),
(6, 4, 1, '1'),
(7, 4, 2, '1'),
(8, 4, 3, '1'),
(9, 4, 4, '1');

-- --------------------------------------------------------

--
-- 替换视图以便查看 `scoreCalculatedView`
--
CREATE TABLE IF NOT EXISTS `scoreCalculatedView` (
`year` varchar(255)
,`term` varchar(255)
,`courseName` varchar(255)
,`property` varchar(255)
,`score` decimal(2,1)
,`studyMode` varchar(255)
,`grade` decimal(8,3)
,`studentId` varchar(255)
,`courseId` int(11)
,`teacherId` varchar(255)
);

-- --------------------------------------------------------

--
-- 表的结构 `specialty`
--

CREATE TABLE IF NOT EXISTS `specialty` (
  `id` int(11) NOT NULL COMMENT '编号;编号',
  `specialtyName` varchar(255) NOT NULL COMMENT '专业名称;专业名称',
  `deptId` int(11) NOT NULL COMMENT '所属系部;所属系部',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态;有效'
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='专业表';

--
-- 转存表中的数据 `specialty`
--

INSERT INTO `specialty` (`id`, `specialtyName`, `deptId`, `status`) VALUES
(1, '软件工程', 1, 1),
(2, '计算机科学与技术', 1, 1),
(3, '智能科学与技术', 1, 1),
(4, '网络工程', 1, 1),
(5, '数统', 2, 1);

-- --------------------------------------------------------

--
-- 替换视图以便查看 `specialtyWithDepartment`
--
CREATE TABLE IF NOT EXISTS `specialtyWithDepartment` (
`specialtyId` int(11)
,`specialtyName` varchar(255)
,`deptId` int(11)
,`deptName` varchar(255)
);

-- --------------------------------------------------------

--
-- 表的结构 `studentCourse`
--

CREATE TABLE IF NOT EXISTS `studentCourse` (
  `id` int(11) NOT NULL COMMENT '编号;编号',
  `studentId` varchar(255) NOT NULL COMMENT '学生编号;学生编号',
  `courseId` int(11) NOT NULL COMMENT '课程编号;课程编号',
  `studyMode` varchar(255) NOT NULL COMMENT '修读方式;修读方式',
  `status` varchar(1) NOT NULL DEFAULT '1' COMMENT '状态;有效'
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='学生课程表（学生选课表）';

--
-- 转存表中的数据 `studentCourse`
--

INSERT INTO `studentCourse` (`id`, `studentId`, `courseId`, `studyMode`, `status`) VALUES
(1, '201921098412', 1, '初修', '1'),
(2, '201921098412', 2, '初修', '1'),
(3, '201921098412', 3, '初修', '1'),
(4, '201921098412', 4, '初修', '1'),
(5, '201921098380', 1, '初修', '1'),
(6, '201921098380', 2, '初修', '1'),
(7, '201921098380', 3, '初修', '1'),
(8, '201921098380', 4, '初修', '1');

-- --------------------------------------------------------

--
-- 表的结构 `studentExamination`
--

CREATE TABLE IF NOT EXISTS `studentExamination` (
  `id` int(11) NOT NULL COMMENT '编号;编号',
  `studentId` varchar(255) NOT NULL COMMENT '学生编号;学生编号',
  `examinationId` int(11) NOT NULL COMMENT '考试编号;考试编号',
  `score` decimal(4,1) NOT NULL COMMENT '考试成绩;考试成绩',
  `status` varchar(1) NOT NULL DEFAULT '1' COMMENT '状态;有效'
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='学生考试表（学生成绩表）';

--
-- 转存表中的数据 `studentExamination`
--

INSERT INTO `studentExamination` (`id`, `studentId`, `examinationId`, `score`, `status`) VALUES
(3, '201921098412', 1, '99.0', '1'),
(4, '201921098380', 1, '90.0', '1'),
(5, '201921098412', 2, '88.0', '1'),
(6, '201921098380', 2, '92.0', '1'),
(33, '201921098412', 3, '87.0', '1'),
(35, '201921098380', 4, '42.0', '1');

-- --------------------------------------------------------

--
-- 表的结构 `studentInfo`
--

CREATE TABLE IF NOT EXISTS `studentInfo` (
  `number` varchar(255) NOT NULL COMMENT '学号;学号',
  `classId` int(11) NOT NULL COMMENT '班级编号;班级编号',
  `name` varchar(255) NOT NULL COMMENT '姓名;姓名',
  `sex` varchar(255) NOT NULL COMMENT '性别;性别',
  `birthday` date NOT NULL COMMENT '出生日期;出生日期',
  `entryTime` date NOT NULL COMMENT '入学时间;入学时间',
  `leaveTime` date NOT NULL COMMENT '毕业时间;毕业时间',
  `homeLocation` varchar(255) NOT NULL COMMENT '家庭住址;家庭住址',
  `identityNumber` varchar(18) NOT NULL COMMENT '身份证号;身份证号',
  `nowGrade` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生信息表';

--
-- 转存表中的数据 `studentInfo`
--

INSERT INTO `studentInfo` (`number`, `classId`, `name`, `sex`, `birthday`, `entryTime`, `leaveTime`, `homeLocation`, `identityNumber`, `nowGrade`) VALUES
('201921098380', 5, '欧阳华清', '男', '2002-01-09', '2019-09-01', '2023-06-30', 'homeLocation_201921098380', '421302190503124527', 2019),
('201921098412', 4, '蒋涛', '男', '2012-01-09', '2019-09-01', '2023-06-30', 'homeLocation_201921098412', '421302198503124536', 2019),
('201921098427', 4, '张三', '男', '2022-01-14', '2022-01-14', '2022-01-14', 'lo', '421302187604126745', 2018);

-- --------------------------------------------------------

--
-- 表的结构 `studentOrdinaryScoreItem`
--

CREATE TABLE IF NOT EXISTS `studentOrdinaryScoreItem` (
  `id` int(11) NOT NULL COMMENT '编号;编号',
  `score` varchar(255) NOT NULL COMMENT '分数;分数',
  `status` varchar(1) NOT NULL DEFAULT '1' COMMENT '状态;状态',
  `studentId` varchar(255) NOT NULL COMMENT '学号',
  `ordinaryScoreItemId` int(11) NOT NULL COMMENT '平时成绩项编号'
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='学生平时成绩项表';

--
-- 转存表中的数据 `studentOrdinaryScoreItem`
--

INSERT INTO `studentOrdinaryScoreItem` (`id`, `score`, `status`, `studentId`, `ordinaryScoreItemId`) VALUES
(1, '99', '1', '201921098412', 1),
(2, '90', '1', '201921098380', 1),
(3, '90', '1', '201921098412', 2),
(4, '80', '1', '201921098380', 2),
(5, '100', '1', '201921098412', 3),
(6, '98', '1', '201921098380', 3),
(8, '85', '1', '201921098380', 4),
(26, '89', '1', '201921098380', 47),
(28, '100.0', '1', '201921098380', 48);

-- --------------------------------------------------------

--
-- 替换视图以便查看 `studentWithDepartment`
--
CREATE TABLE IF NOT EXISTS `studentWithDepartment` (
`studentId` varchar(255)
,`classId` int(11)
,`name` varchar(255)
,`sex` varchar(255)
,`birthday` date
,`entryTime` date
,`leaveTime` date
,`homeLocation` varchar(255)
,`identityNumber` varchar(18)
,`classNumber` int(11)
,`specialtyId` int(11)
,`classTeacherId` int(11)
,`specialtyName` varchar(255)
,`deptId` int(11)
,`deptName` varchar(255)
,`nowGrade` int(11)
);

-- --------------------------------------------------------

--
-- 表的结构 `taskGroup`
--

CREATE TABLE IF NOT EXISTS `taskGroup` (
  `id` int(11) NOT NULL COMMENT '编号;编号',
  `groupName` varchar(255) NOT NULL COMMENT '课题组名称;课题组名称',
  `specialtyId` int(11) NOT NULL COMMENT '专业编号;专业编号',
  `status` varchar(1) NOT NULL DEFAULT '1' COMMENT '状态;有效'
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='课题组表';

--
-- 转存表中的数据 `taskGroup`
--

INSERT INTO `taskGroup` (`id`, `groupName`, `specialtyId`, `status`) VALUES
(1, '软工课题组1', 1, '1'),
(2, '软工课题组2', 1, '1'),
(3, '计科课题组1', 2, '1'),
(4, '计科课题组2', 2, '1'),
(5, '数统', 5, '1');

-- --------------------------------------------------------

--
-- 表的结构 `teacherInfo`
--

CREATE TABLE IF NOT EXISTS `teacherInfo` (
  `number` varchar(255) NOT NULL COMMENT '工号;工号',
  `taskGroupId` int(11) NOT NULL COMMENT '课题组编号;课题组编号',
  `name` varchar(255) NOT NULL COMMENT '姓名;姓名',
  `sex` varchar(255) NOT NULL COMMENT '性别;性别',
  `birthday` date NOT NULL COMMENT '出生日期;出生日期',
  `entryTime` date NOT NULL COMMENT '入职时间;入学时间',
  `leaveTime` date DEFAULT NULL COMMENT '离职时间;毕业时间',
  `homeLocation` varchar(255) NOT NULL COMMENT '家庭住址;家庭住址',
  `identityNumber` varchar(18) NOT NULL COMMENT '身份证号;身份证号',
  `educationBackground` varchar(255) NOT NULL COMMENT '学历;学历',
  `professionalTitle` varchar(255) NOT NULL COMMENT '职称;职称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师信息表';

--
-- 转存表中的数据 `teacherInfo`
--

INSERT INTO `teacherInfo` (`number`, `taskGroupId`, `name`, `sex`, `birthday`, `entryTime`, `leaveTime`, `homeLocation`, `identityNumber`, `educationBackground`, `professionalTitle`) VALUES
('3725001', 1, '曾广平', '男', '1992-01-09', '2013-01-09', NULL, 'homeLocation_3725001', '421302201301094528', 'educationBackground_1', 'title_1'),
('3725002', 1, '杨喜敏', '男', '1998-01-13', '2009-01-09', NULL, 'homeLocation_3725002', '421302200901093928', 'educationBackground_2', 'title_2'),
('3725003', 5, '1', '1', '2022-01-15', '2022-01-15', NULL, '1', '1', '1', '1'),
('3725004', 1, '2', '2', '2022-01-15', '2022-01-15', NULL, '2', '2', '2', '2');

-- --------------------------------------------------------

--
-- 替换视图以便查看 `teacherInfoWithDepartment`
--
CREATE TABLE IF NOT EXISTS `teacherInfoWithDepartment` (
`teacherId` varchar(255)
,`name` varchar(255)
,`taskGroupId` int(11)
,`sex` varchar(255)
,`birthday` date
,`entryTime` date
,`leaveTime` date
,`homeLocation` varchar(255)
,`identityNumber` varchar(18)
,`educationBackground` varchar(255)
,`professionalTitle` varchar(255)
,`groupName` varchar(255)
,`specialtyId` int(11)
,`specialtyName` varchar(255)
,`deptId` int(11)
,`deptName` varchar(255)
);

-- --------------------------------------------------------

--
-- 替换视图以便查看 `totalScoreView`
--
CREATE TABLE IF NOT EXISTS `totalScoreView` (
`year` varchar(255)
,`term` varchar(255)
,`courseName` varchar(255)
,`property` varchar(255)
,`courseScore` decimal(2,1)
,`studyMode` varchar(255)
,`score` decimal(8,3)
,`studentId` varchar(255)
,`courseId` int(11)
,`teacherId` varchar(255)
,`specialtyName` varchar(255)
,`className` varchar(12)
,`studentSpecialtyId` int(11)
,`studentClassId` int(11)
,`grade` varchar(1)
,`examinationScore` decimal(4,1)
,`ordinaryScoreProportion` decimal(4,2)
,`studentName` varchar(255)
,`examinationProportion` decimal(3,2)
,`examinationId` int(11)
,`studentExaminationId` int(11)
,`ordinaryScore` decimal(5,2)
);

-- --------------------------------------------------------

--
-- 替换视图以便查看 `totalScoreWithDepartmentView`
--
CREATE TABLE IF NOT EXISTS `totalScoreWithDepartmentView` (
`year` varchar(255)
,`term` varchar(255)
,`courseName` varchar(255)
,`property` varchar(255)
,`courseScore` decimal(2,1)
,`studyMode` varchar(255)
,`score` decimal(8,3)
,`studentId` varchar(255)
,`courseId` int(11)
,`teacherId` varchar(255)
,`specialtyName` varchar(255)
,`className` varchar(12)
,`studentSpecialtyId` int(11)
,`studentClassId` int(11)
,`grade` varchar(1)
,`examinationScore` decimal(4,1)
,`ordinaryScoreProportion` decimal(4,2)
,`studentName` varchar(255)
,`examinationProportion` decimal(3,2)
,`examinationId` int(11)
,`studentExaminationId` int(11)
,`ordinaryScore` decimal(5,2)
,`teacherDeptId` int(11)
);

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `number` varchar(255) NOT NULL COMMENT '学号/工号;学号/工号',
  `password` varchar(255) NOT NULL COMMENT '密码;登录密码',
  `identity` varchar(255) NOT NULL COMMENT '身份;身份（教师或学生）',
  `status` varchar(1) NOT NULL DEFAULT '1' COMMENT '状态;账号是否有效'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表（常用数据）（其它详细数据被分表）';

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`number`, `password`, `identity`, `status`) VALUES
('201921098380', '201921098380', 'STUDENT', '1'),
('201921098412', '201921098412', 'STUDENT', '1'),
('3725001', '3725001', 'TEACHER', '1'),
('3725002', '3725001', 'TEACHER', '1'),
('3725003', '3725003', 'TEACHER', '1'),
('3725004', 'jiangtao3725004', 'TEACGER', '1');

-- --------------------------------------------------------

--
-- 表的结构 `userRole`
--

CREATE TABLE IF NOT EXISTS `userRole` (
  `id` int(11) NOT NULL COMMENT '编号;编号',
  `userId` varchar(255) NOT NULL COMMENT '用户编号;用户编号',
  `roleId` int(11) NOT NULL COMMENT '角色编号;角色编号',
  `status` varchar(1) NOT NULL DEFAULT '1' COMMENT '状态;该条数据是否可用'
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

--
-- 转存表中的数据 `userRole`
--

INSERT INTO `userRole` (`id`, `userId`, `roleId`, `status`) VALUES
(1, '201921098380', 1, '1'),
(2, '201921098412', 1, '1'),
(3, '3725001', 2, '1'),
(4, '3725002', 2, '1'),
(5, '3725003', 3, '1'),
(6, '3725004', 4, '1'),
(8, '3725003', 2, '1');

-- --------------------------------------------------------

--
-- 表的结构 `yearTerm`
--

CREATE TABLE IF NOT EXISTS `yearTerm` (
  `id` int(11) NOT NULL COMMENT '编号;编号',
  `year` varchar(255) NOT NULL COMMENT '学年;学年',
  `term` varchar(255) NOT NULL COMMENT '学期;学期'
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='学年学期表';

--
-- 转存表中的数据 `yearTerm`
--

INSERT INTO `yearTerm` (`id`, `year`, `term`) VALUES
(1, '2018-2019', '第一学期'),
(2, '2018-2019', '第二学期'),
(3, '2019-2020', '第一学期'),
(4, '2019-2020', '第二学期'),
(5, '2020-2021', '第一学期'),
(6, '2020-2021', '第二学期'),
(7, '2021-2022', '第一学期'),
(8, '2021-2022', '第二学期'),
(9, '2022-2023', '第一学期'),
(10, '2022-2023', '第二学期'),
(11, '2023-2024', '第一学期'),
(12, '2023-2024', '第二学期');

-- --------------------------------------------------------

--
-- 视图结构 `classWithDepartment`
--
DROP TABLE IF EXISTS `classWithDepartment`;

CREATE ALGORITHM=UNDEFINED DEFINER=`ScoreRelease`@`%` SQL SECURITY DEFINER VIEW `classWithDepartment` AS select `specialtyWithDepartment`.`specialtyId` AS `specialtyId`,`specialtyWithDepartment`.`specialtyName` AS `specialtyName`,`specialtyWithDepartment`.`deptId` AS `deptId`,`specialtyWithDepartment`.`deptName` AS `deptName`,`class`.`id` AS `classId`,`class`.`number` AS `number`,`class`.`teacherId` AS `classTeacher` from (`class` join `specialtyWithDepartment` on(((`class`.`specialtyId` = `specialtyWithDepartment`.`specialtyId`) and (`class`.`status` = 1))));

-- --------------------------------------------------------

--
-- 视图结构 `courseExaminationScore`
--
DROP TABLE IF EXISTS `courseExaminationScore`;

CREATE ALGORITHM=UNDEFINED DEFINER=`ScoreRelease`@`localhost` SQL SECURITY DEFINER VIEW `courseExaminationScore` AS select `specialty`.`specialtyName` AS `specialtyName`,concat(`class`.`number`,'班') AS `className`,`studentExamination`.`studentId` AS `studentId`,`course`.`score` AS `courseScore`,`course`.`name` AS `courseName`,`examination`.`grade` AS `grade`,`studentExamination`.`score` AS `score`,`studentInfo`.`name` AS `studentName`,`course`.`property` AS `property`,`examination`.`courseId` AS `courseId`,`course`.`teacherId` AS `teacherId`,`examination`.`proportion` AS `proportion`,(`examination`.`proportion` * `studentExamination`.`score`) AS `calculatedScore`,`course`.`examinationId` AS `examinationId`,`studentExamination`.`id` AS `studentExaminationId`,`class`.`specialtyId` AS `studentSpecialtyId`,`studentInfo`.`classId` AS `studentClassId` from (((((`studentExamination` join `examination` on((`examination`.`id` = `studentExamination`.`examinationId`))) join `course` on((`course`.`examinationId` = `examination`.`id`))) join `studentInfo` on((`studentExamination`.`studentId` = `studentInfo`.`number`))) join `class` on((`studentInfo`.`classId` = `class`.`id`))) join `specialty` on((`class`.`specialtyId` = `specialty`.`id`)));

-- --------------------------------------------------------

--
-- 视图结构 `courseWithTeacherAndDepartment`
--
DROP TABLE IF EXISTS `courseWithTeacherAndDepartment`;

CREATE ALGORITHM=UNDEFINED DEFINER=`ScoreRelease`@`%` SQL SECURITY DEFINER VIEW `courseWithTeacherAndDepartment` AS select `course`.`teacherId` AS `teacherId`,`teacherInfoWithDepartment`.`name` AS `teacherName`,`teacherInfoWithDepartment`.`taskGroupId` AS `taskGroupId`,`teacherInfoWithDepartment`.`groupName` AS `groupName`,`teacherInfoWithDepartment`.`specialtyId` AS `specialtyId`,`teacherInfoWithDepartment`.`specialtyName` AS `specialtyName`,`teacherInfoWithDepartment`.`deptId` AS `deptId`,`teacherInfoWithDepartment`.`deptName` AS `deptName`,`course`.`id` AS `courseId`,`course`.`startTime` AS `startTime`,`course`.`endTime` AS `endTime`,`course`.`score` AS `score`,`course`.`time` AS `time`,`course`.`introduce` AS `introduce`,`course`.`property` AS `property`,`course`.`ordinaryScoreId` AS `ordinaryScoreId`,`course`.`examinationId` AS `examinationId`,`course`.`name` AS `courseName`,`course`.`classNumber` AS `classNumber`,`yearTerm`.`year` AS `year`,`yearTerm`.`term` AS `term` from ((`course` join `teacherInfoWithDepartment` on((`course`.`teacherId` = `teacherInfoWithDepartment`.`teacherId`))) join `yearTerm` on((`yearTerm`.`id` = `course`.`yearTermId`)));

-- --------------------------------------------------------

--
-- 视图结构 `ordinaryScoreView`
--
DROP TABLE IF EXISTS `ordinaryScoreView`;

CREATE ALGORITHM=UNDEFINED DEFINER=`ScoreRelease`@`%` SQL SECURITY DEFINER VIEW `ordinaryScoreView` AS select `ordinaryScore`.`courseId` AS `courseId`,`yearTerm`.`year` AS `year`,`yearTerm`.`term` AS `term`,`course`.`name` AS `courseName`,`ordinaryScoreItem`.`time` AS `time`,`ordinaryScoreItem`.`name` AS `name`,`studentOrdinaryScoreItem`.`score` AS `score`,`studentOrdinaryScoreItem`.`studentId` AS `studentId` from ((((`course` join `ordinaryScore` on(((`course`.`id` = `ordinaryScore`.`courseId`) and (`course`.`status` = 1)))) join `ordinaryScoreItem` on(((`ordinaryScore`.`id` = `ordinaryScoreItem`.`ordinaryScoreId`) and (`ordinaryScoreItem`.`status` = 1)))) join `studentOrdinaryScoreItem` on(((`ordinaryScoreItem`.`id` = `studentOrdinaryScoreItem`.`ordinaryScoreItemId`) and (`studentOrdinaryScoreItem`.`status` = 1)))) join `yearTerm` on((`yearTerm`.`id` = `course`.`yearTermId`)));

-- --------------------------------------------------------

--
-- 视图结构 `ordinaryScoreViewCalculated`
--
DROP TABLE IF EXISTS `ordinaryScoreViewCalculated`;

CREATE ALGORITHM=UNDEFINED DEFINER=`ScoreRelease`@`%` SQL SECURITY DEFINER VIEW `ordinaryScoreViewCalculated` AS select cast(sum(((`ordinaryScoreViewWithProportion`.`p1` * `ordinaryScoreViewWithProportion`.`p2`) * `ordinaryScoreViewWithProportion`.`score`)) as decimal(5,2)) AS `calculatedOrdinaryScore`,`ordinaryScoreViewWithProportion`.`courseId` AS `courseId`,`ordinaryScoreViewWithProportion`.`year` AS `year`,`ordinaryScoreViewWithProportion`.`term` AS `term`,`ordinaryScoreViewWithProportion`.`courseName` AS `courseName`,`ordinaryScoreViewWithProportion`.`studentId` AS `studentId`,`ordinaryScoreViewWithProportion`.`teacherId` AS `teacherId` from `ordinaryScoreViewWithProportion` group by `ordinaryScoreViewWithProportion`.`studentId`,`ordinaryScoreViewWithProportion`.`courseId`;

-- --------------------------------------------------------

--
-- 视图结构 `ordinaryScoreViewWithProportion`
--
DROP TABLE IF EXISTS `ordinaryScoreViewWithProportion`;

CREATE ALGORITHM=UNDEFINED DEFINER=`ScoreRelease`@`localhost` SQL SECURITY DEFINER VIEW `ordinaryScoreViewWithProportion` AS select `course`.`teacherId` AS `teacherId`,`ordinaryScore`.`courseId` AS `courseId`,`yearTerm`.`year` AS `year`,`yearTerm`.`term` AS `term`,`course`.`name` AS `courseName`,`ordinaryScoreItem`.`time` AS `time`,`ordinaryScoreItem`.`name` AS `name`,`studentOrdinaryScoreItem`.`score` AS `score`,`studentOrdinaryScoreItem`.`studentId` AS `studentId`,`ordinaryScore`.`proportion` AS `p1`,`ordinaryScoreItem`.`proportion` AS `p2`,`ordinaryScoreItem`.`id` AS `itemId`,`studentOrdinaryScoreItem`.`id` AS `studentOrdinaryScoreItemId` from ((((`course` join `ordinaryScore` on(((`course`.`id` = `ordinaryScore`.`courseId`) and (`course`.`status` = 1)))) join `ordinaryScoreItem` on(((`ordinaryScore`.`id` = `ordinaryScoreItem`.`ordinaryScoreId`) and (`ordinaryScoreItem`.`status` = 1)))) join `studentOrdinaryScoreItem` on(((`ordinaryScoreItem`.`id` = `studentOrdinaryScoreItem`.`ordinaryScoreItemId`) and (`studentOrdinaryScoreItem`.`status` = 1)))) join `yearTerm` on((`yearTerm`.`id` = `course`.`yearTermId`)));

-- --------------------------------------------------------

--
-- 视图结构 `scoreCalculatedView`
--
DROP TABLE IF EXISTS `scoreCalculatedView`;

CREATE ALGORITHM=UNDEFINED DEFINER=`ScoreRelease`@`localhost` SQL SECURITY DEFINER VIEW `scoreCalculatedView` AS select `yearTerm`.`year` AS `year`,`yearTerm`.`term` AS `term`,`course`.`name` AS `courseName`,`course`.`property` AS `property`,`course`.`score` AS `score`,`studentCourse`.`studyMode` AS `studyMode`,(`courseExaminationScore`.`calculatedScore` + `ordinaryScoreViewCalculated`.`calculatedOrdinaryScore`) AS `grade`,`courseExaminationScore`.`studentId` AS `studentId`,`courseExaminationScore`.`courseId` AS `courseId`,`course`.`teacherId` AS `teacherId` from ((((((`course` join `examination` on(((`course`.`id` = `examination`.`courseId`) and (`examination`.`status` = 1) and (`course`.`status` = 1)))) join `studentExamination` on(((`examination`.`id` = `studentExamination`.`examinationId`) and (`studentExamination`.`status` = 1)))) join `courseExaminationScore` on(((`course`.`id` = `courseExaminationScore`.`courseId`) and (`studentExamination`.`studentId` = `courseExaminationScore`.`studentId`)))) join `ordinaryScoreViewCalculated` on(((`course`.`id` = `ordinaryScoreViewCalculated`.`courseId`) and (`studentExamination`.`studentId` = `ordinaryScoreViewCalculated`.`studentId`)))) join `yearTerm` on((`yearTerm`.`id` = `course`.`yearTermId`))) join `studentCourse` on(((`studentCourse`.`studentId` = `studentExamination`.`studentId`) and (`studentCourse`.`courseId` = `examination`.`courseId`) and (`studentCourse`.`status` = 1))));

-- --------------------------------------------------------

--
-- 视图结构 `specialtyWithDepartment`
--
DROP TABLE IF EXISTS `specialtyWithDepartment`;

CREATE ALGORITHM=UNDEFINED DEFINER=`ScoreRelease`@`%` SQL SECURITY DEFINER VIEW `specialtyWithDepartment` AS select `specialty`.`id` AS `specialtyId`,`specialty`.`specialtyName` AS `specialtyName`,`specialty`.`deptId` AS `deptId`,`department`.`deptName` AS `deptName` from (`specialty` join `department` on(((`department`.`id` = `specialty`.`deptId`) and (`specialty`.`status` = 1) and (`department`.`status` = 1))));

-- --------------------------------------------------------

--
-- 视图结构 `studentWithDepartment`
--
DROP TABLE IF EXISTS `studentWithDepartment`;

CREATE ALGORITHM=UNDEFINED DEFINER=`ScoreRelease`@`%` SQL SECURITY DEFINER VIEW `studentWithDepartment` AS select `sf`.`number` AS `studentId`,`sf`.`classId` AS `classId`,`sf`.`name` AS `name`,`sf`.`sex` AS `sex`,`sf`.`birthday` AS `birthday`,`sf`.`entryTime` AS `entryTime`,`sf`.`leaveTime` AS `leaveTime`,`sf`.`homeLocation` AS `homeLocation`,`sf`.`identityNumber` AS `identityNumber`,`c`.`number` AS `classNumber`,`c`.`specialtyId` AS `specialtyId`,`c`.`teacherId` AS `classTeacherId`,`s`.`specialtyName` AS `specialtyName`,`s`.`deptId` AS `deptId`,`d`.`deptName` AS `deptName`,`sf`.`nowGrade` AS `nowGrade` from (((`studentInfo` `sf` left join `class` `c` on(((`sf`.`classId` = `c`.`id`) and (`c`.`status` = 1)))) left join `specialty` `s` on(((`c`.`specialtyId` = `s`.`id`) and (`s`.`status` = 1)))) left join `department` `d` on(((`s`.`deptId` = `d`.`id`) and (`d`.`status` = 1))));

-- --------------------------------------------------------

--
-- 视图结构 `teacherInfoWithDepartment`
--
DROP TABLE IF EXISTS `teacherInfoWithDepartment`;

CREATE ALGORITHM=UNDEFINED DEFINER=`ScoreRelease`@`%` SQL SECURITY DEFINER VIEW `teacherInfoWithDepartment` AS select `teacherInfo`.`number` AS `teacherId`,`teacherInfo`.`name` AS `name`,`teacherInfo`.`taskGroupId` AS `taskGroupId`,`teacherInfo`.`sex` AS `sex`,`teacherInfo`.`birthday` AS `birthday`,`teacherInfo`.`entryTime` AS `entryTime`,`teacherInfo`.`leaveTime` AS `leaveTime`,`teacherInfo`.`homeLocation` AS `homeLocation`,`teacherInfo`.`identityNumber` AS `identityNumber`,`teacherInfo`.`educationBackground` AS `educationBackground`,`teacherInfo`.`professionalTitle` AS `professionalTitle`,`taskGroup`.`groupName` AS `groupName`,`taskGroup`.`specialtyId` AS `specialtyId`,`specialtyWithDepartment`.`specialtyName` AS `specialtyName`,`specialtyWithDepartment`.`deptId` AS `deptId`,`specialtyWithDepartment`.`deptName` AS `deptName` from ((`teacherInfo` join `taskGroup` on(((`taskGroup`.`id` = `teacherInfo`.`taskGroupId`) and (`taskGroup`.`status` = 1)))) join `specialtyWithDepartment` on((`specialtyWithDepartment`.`specialtyId` = `taskGroup`.`specialtyId`)));

-- --------------------------------------------------------

--
-- 视图结构 `totalScoreView`
--
DROP TABLE IF EXISTS `totalScoreView`;

CREATE ALGORITHM=UNDEFINED DEFINER=`ScoreRelease`@`localhost` SQL SECURITY DEFINER VIEW `totalScoreView` AS select `scoreCalculatedView`.`year` AS `year`,`scoreCalculatedView`.`term` AS `term`,`scoreCalculatedView`.`courseName` AS `courseName`,`scoreCalculatedView`.`property` AS `property`,`courseExaminationScore`.`courseScore` AS `courseScore`,`scoreCalculatedView`.`studyMode` AS `studyMode`,`scoreCalculatedView`.`grade` AS `score`,`scoreCalculatedView`.`studentId` AS `studentId`,`scoreCalculatedView`.`courseId` AS `courseId`,`scoreCalculatedView`.`teacherId` AS `teacherId`,`courseExaminationScore`.`specialtyName` AS `specialtyName`,`courseExaminationScore`.`className` AS `className`,`courseExaminationScore`.`studentSpecialtyId` AS `studentSpecialtyId`,`courseExaminationScore`.`studentClassId` AS `studentClassId`,`courseExaminationScore`.`grade` AS `grade`,`courseExaminationScore`.`score` AS `examinationScore`,(1.0 - `courseExaminationScore`.`proportion`) AS `ordinaryScoreProportion`,`courseExaminationScore`.`studentName` AS `studentName`,`courseExaminationScore`.`proportion` AS `examinationProportion`,`courseExaminationScore`.`examinationId` AS `examinationId`,`courseExaminationScore`.`studentExaminationId` AS `studentExaminationId`,cast((`ordinaryScoreViewCalculated`.`calculatedOrdinaryScore` / (1 - `courseExaminationScore`.`proportion`)) as decimal(5,2)) AS `ordinaryScore` from ((`scoreCalculatedView` join `courseExaminationScore` on(((`scoreCalculatedView`.`studentId` = `courseExaminationScore`.`studentId`) and (`scoreCalculatedView`.`courseId` = `courseExaminationScore`.`courseId`)))) join `ordinaryScoreViewCalculated` on(((`scoreCalculatedView`.`studentId` = `ordinaryScoreViewCalculated`.`studentId`) and (`scoreCalculatedView`.`courseId` = `ordinaryScoreViewCalculated`.`courseId`))));

-- --------------------------------------------------------

--
-- 视图结构 `totalScoreWithDepartmentView`
--
DROP TABLE IF EXISTS `totalScoreWithDepartmentView`;

CREATE ALGORITHM=UNDEFINED DEFINER=`ScoreRelease`@`%` SQL SECURITY DEFINER VIEW `totalScoreWithDepartmentView` AS select `totalScoreView`.`year` AS `year`,`totalScoreView`.`term` AS `term`,`totalScoreView`.`courseName` AS `courseName`,`totalScoreView`.`property` AS `property`,`totalScoreView`.`courseScore` AS `courseScore`,`totalScoreView`.`studyMode` AS `studyMode`,`totalScoreView`.`score` AS `score`,`totalScoreView`.`studentId` AS `studentId`,`totalScoreView`.`courseId` AS `courseId`,`totalScoreView`.`teacherId` AS `teacherId`,`totalScoreView`.`specialtyName` AS `specialtyName`,`totalScoreView`.`className` AS `className`,`totalScoreView`.`studentSpecialtyId` AS `studentSpecialtyId`,`totalScoreView`.`studentClassId` AS `studentClassId`,`totalScoreView`.`grade` AS `grade`,`totalScoreView`.`examinationScore` AS `examinationScore`,`totalScoreView`.`ordinaryScoreProportion` AS `ordinaryScoreProportion`,`totalScoreView`.`studentName` AS `studentName`,`totalScoreView`.`examinationProportion` AS `examinationProportion`,`totalScoreView`.`examinationId` AS `examinationId`,`totalScoreView`.`studentExaminationId` AS `studentExaminationId`,`totalScoreView`.`ordinaryScore` AS `ordinaryScore`,`teacherInfoWithDepartment`.`deptId` AS `teacherDeptId` from (`totalScoreView` left join `teacherInfoWithDepartment` on((`totalScoreView`.`teacherId` = `teacherInfoWithDepartment`.`teacherId`)));

--
-- Indexes for dumped tables
--

--
-- Indexes for table `access`
--
ALTER TABLE `access`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `class`
--
ALTER TABLE `class`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `class_id_uindex` (`id`);

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `course_id_uindex` (`id`);

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `department_id_uindex` (`id`);

--
-- Indexes for table `examination`
--
ALTER TABLE `examination`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `examination_id_uindex` (`id`);

--
-- Indexes for table `inputTime`
--
ALTER TABLE `inputTime`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ordinaryScore`
--
ALTER TABLE `ordinaryScore`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `ordinaryScore_id_uindex` (`id`);

--
-- Indexes for table `ordinaryScoreItem`
--
ALTER TABLE `ordinaryScoreItem`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `ordinaryScoreItem_id_uindex` (`id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `roleAccess`
--
ALTER TABLE `roleAccess`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `specialty`
--
ALTER TABLE `specialty`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `specialty_id_uindex` (`id`);

--
-- Indexes for table `studentCourse`
--
ALTER TABLE `studentCourse`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `studentCourse_id_uindex` (`id`);

--
-- Indexes for table `studentExamination`
--
ALTER TABLE `studentExamination`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `studentExamination_studentId_examinationId_uindex` (`studentId`,`examinationId`),
  ADD UNIQUE KEY `studentExamination_id_uindex` (`id`);

--
-- Indexes for table `studentInfo`
--
ALTER TABLE `studentInfo`
  ADD PRIMARY KEY (`number`),
  ADD UNIQUE KEY `studentInfo_number_uindex` (`number`);

--
-- Indexes for table `studentOrdinaryScoreItem`
--
ALTER TABLE `studentOrdinaryScoreItem`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `studentOrdinaryScoreItem_ordinaryScoreItemId_studentId_uindex` (`ordinaryScoreItemId`,`studentId`),
  ADD UNIQUE KEY `studentOrdinaryScoreItem_id_uindex` (`id`);

--
-- Indexes for table `taskGroup`
--
ALTER TABLE `taskGroup`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `taskGroup_id_uindex` (`id`);

--
-- Indexes for table `teacherInfo`
--
ALTER TABLE `teacherInfo`
  ADD PRIMARY KEY (`number`),
  ADD UNIQUE KEY `teacherInfo_number_uindex` (`number`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`number`),
  ADD UNIQUE KEY `user_number_uindex` (`number`);

--
-- Indexes for table `userRole`
--
ALTER TABLE `userRole`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `yearTerm`
--
ALTER TABLE `yearTerm`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `yearTerm_id_uindex` (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `access`
--
ALTER TABLE `access`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号;编号',AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `class`
--
ALTER TABLE `class`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号;编号',AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `course`
--
ALTER TABLE `course`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '课程编号;课程编号',AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `department`
--
ALTER TABLE `department`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号;编号',AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `examination`
--
ALTER TABLE `examination`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号;编号',AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `inputTime`
--
ALTER TABLE `inputTime`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号;编号',AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `ordinaryScore`
--
ALTER TABLE `ordinaryScore`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号;编号',AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `ordinaryScoreItem`
--
ALTER TABLE `ordinaryScoreItem`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',AUTO_INCREMENT=49;
--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号;编号',AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `roleAccess`
--
ALTER TABLE `roleAccess`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号;编号',AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `specialty`
--
ALTER TABLE `specialty`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号;编号',AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `studentCourse`
--
ALTER TABLE `studentCourse`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号;编号',AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `studentExamination`
--
ALTER TABLE `studentExamination`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号;编号',AUTO_INCREMENT=40;
--
-- AUTO_INCREMENT for table `studentOrdinaryScoreItem`
--
ALTER TABLE `studentOrdinaryScoreItem`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号;编号',AUTO_INCREMENT=32;
--
-- AUTO_INCREMENT for table `taskGroup`
--
ALTER TABLE `taskGroup`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号;编号',AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `userRole`
--
ALTER TABLE `userRole`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号;编号',AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `yearTerm`
--
ALTER TABLE `yearTerm`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号;编号',AUTO_INCREMENT=13;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
