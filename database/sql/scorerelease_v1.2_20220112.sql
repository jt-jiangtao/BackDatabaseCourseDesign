-- phpMyAdmin SQL Dump
-- version 4.4.15.10
-- https://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 2022-01-12 19:18:40
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='权限';

--
-- 转存表中的数据 `access`
--

INSERT INTO `access` (`id`, `accessName`, `status`) VALUES
(1, 'STUDENT', '1'),
(2, 'TEACHER', '1'),
(3, 'GRANT_DEPT', '1'),
(4, 'GRANT_PRO', '1'),
(5, 'GRANT_GROUP', '1'),
(6, 'GRANT_CLASS', '1'),
(7, 'DEPT', '1'),
(8, 'PRO', '1'),
(9, 'GROUP', '1'),
(10, 'CLASS', '1'),
(11, 'GRANT_ALL', '1'),
(12, 'ALL', '1');

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
(1, '3725001', '第1周', '第16周', '4.5', 48, NULL, '专业必修课', 1, 4, 8, '1', '数据库', 1),
(2, '3725001', '第6周', '第10周', '1.0', 24, NULL, '专业必修课', 2, 3, 8, '1', '数据库实验', 1),
(3, '3725002', '第1周', '第16周', '2.5', 32, NULL, '专业必修课', 3, 2, 8, '1', 'c', 1),
(4, '3725002', '第1周', '第16周', '2.5', 32, NULL, '专业必修课', 4, 1, 8, '1', 'c++', 1);

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
,`score` decimal(3,1)
,`studentName` varchar(255)
,`property` varchar(255)
,`courseId` int(11)
,`teacherId` varchar(255)
,`proportion` decimal(3,2)
,`calculatedScore` decimal(6,3)
,`examinationId` int(11)
,`studentExaminationId` int(11)
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
(4, 1, '0.60', 'B', '1', NULL);

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
(1, 1, '0.40'),
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
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `ordinaryScoreItem`
--

INSERT INTO `ordinaryScoreItem` (`id`, `ordinaryScoreId`, `name`, `time`, `proportion`, `status`) VALUES
(1, 3, '考核', '2021-12-14', '0.30', 1),
(2, 3, '实验', '2022-01-03', '0.70', 1),
(3, 4, '考勤', '2022-01-10', '0.20', 1),
(4, 4, '实验报告', '2021-12-17', '0.80', 1),
(45, 1, '平时分1', '2021-11-26', '0.80', 1),
(46, 1, '实验2', '2021-11-04', '0.20', 1);

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
`calculatedOrdinaryScore` double
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='角色表';

--
-- 转存表中的数据 `role`
--

INSERT INTO `role` (`id`, `roleName`, `status`) VALUES
(1, 'STUDENT', '1'),
(2, 'TEACHER', '1'),
(3, 'SUPER_MANAGER', '1'),
(4, 'DEPT_MANAGER', '1'),
(5, 'PRO_MANAGER', '1'),
(6, 'GROUP_MANAGER', '1'),
(7, 'CLASS_MANAGER', '1');

-- --------------------------------------------------------

--
-- 表的结构 `roleAccess`
--

CREATE TABLE IF NOT EXISTS `roleAccess` (
  `id` int(11) NOT NULL COMMENT '编号;编号',
  `roleId` int(11) NOT NULL COMMENT '角色编号;角色编号',
  `accessId` int(11) NOT NULL COMMENT '权限编号;权限编号',
  `status` varchar(1) NOT NULL DEFAULT '1' COMMENT '状态;是否可用'
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='角色权限表';

--
-- 转存表中的数据 `roleAccess`
--

INSERT INTO `roleAccess` (`id`, `roleId`, `accessId`, `status`) VALUES
(1, 1, 1, '1'),
(2, 2, 2, '1'),
(3, 3, 11, '1'),
(4, 3, 12, '1'),
(5, 4, 3, '1'),
(6, 4, 7, '1'),
(7, 5, 4, '1'),
(8, 5, 8, '1'),
(9, 6, 5, '1'),
(10, 6, 9, '1'),
(11, 7, 6, '1'),
(12, 7, 10, '1');

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
,`grade` double
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='专业表';

--
-- 转存表中的数据 `specialty`
--

INSERT INTO `specialty` (`id`, `specialtyName`, `deptId`, `status`) VALUES
(1, '软件工程', 1, 1),
(2, '计算机科学与技术', 1, 1),
(3, '智能科学与技术', 1, 1),
(4, '网络工程', 1, 1);

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
  `score` decimal(3,1) NOT NULL COMMENT '考试成绩;考试成绩',
  `status` varchar(1) NOT NULL DEFAULT '1' COMMENT '状态;有效'
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='学生考试表（学生成绩表）';

--
-- 转存表中的数据 `studentExamination`
--

INSERT INTO `studentExamination` (`id`, `studentId`, `examinationId`, `score`, `status`) VALUES
(3, '201921098412', 1, '80.0', '1'),
(4, '201921098380', 1, '90.0', '1'),
(5, '201921098412', 2, '88.0', '1'),
(6, '201921098380', 2, '92.0', '1');

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
  `identityNumber` varchar(18) NOT NULL COMMENT '身份证号;身份证号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生信息表';

--
-- 转存表中的数据 `studentInfo`
--

INSERT INTO `studentInfo` (`number`, `classId`, `name`, `sex`, `birthday`, `entryTime`, `leaveTime`, `homeLocation`, `identityNumber`) VALUES
('201921098380', 4, '欧阳华清', '男', '2002-01-09', '2019-09-01', '2023-06-30', 'homeLocation_201921098380', '421302190503124527'),
('201921098412', 4, '蒋涛', '男', '2012-01-09', '2019-09-01', '2023-06-30', 'homeLocation_201921098412', '421302198503124536');

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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='学生平时成绩项表';

--
-- 转存表中的数据 `studentOrdinaryScoreItem`
--

INSERT INTO `studentOrdinaryScoreItem` (`id`, `score`, `status`, `studentId`, `ordinaryScoreItemId`) VALUES
(1, '20', '1', '201921098412', 1),
(2, '90', '1', '201921098380', 1),
(3, '90', '1', '201921098412', 2),
(4, '80', '1', '201921098380', 2),
(5, '100', '1', '201921098412', 3),
(6, '98', '1', '201921098380', 3),
(7, '75', '1', '201921098412', 4),
(8, '85', '1', '201921098380', 4),
(11, '20', '1', '201921098412', 5),
(24, '72.0', '1', '201921098412', 46),
(25, '99.0', '1', '201921098380', 46);

-- --------------------------------------------------------

--
-- 表的结构 `table_name`
--

CREATE TABLE IF NOT EXISTS `table_name` (
  `courseId` int(11) DEFAULT NULL,
  `studentId` varchar(255) DEFAULT NULL,
  `grade` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `table_name`
--

INSERT INTO `table_name` (`courseId`, `studentId`, `grade`) VALUES
(1, '8412', '98.25'),
(1, '8134', '98.25'),
(1, '8456', '21'),
(2, '8456', '56'),
(2, '8412', '35'),
(2, '8134', '98');

-- --------------------------------------------------------

--
-- 表的结构 `taskGroup`
--

CREATE TABLE IF NOT EXISTS `taskGroup` (
  `id` int(11) NOT NULL COMMENT '编号;编号',
  `groupName` varchar(255) NOT NULL COMMENT '课题组名称;课题组名称',
  `specialtyId` int(11) NOT NULL COMMENT '专业编号;专业编号',
  `status` varchar(1) NOT NULL DEFAULT '1' COMMENT '状态;有效'
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='课题组表';

--
-- 转存表中的数据 `taskGroup`
--

INSERT INTO `taskGroup` (`id`, `groupName`, `specialtyId`, `status`) VALUES
(1, '软工课题组1', 1, '1'),
(2, '软工课题组2', 1, '1'),
(3, '计科课题组1', 2, '1'),
(4, '计科课题组2', 2, '1');

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
('3725002', 1, '杨喜敏', '男', '1998-01-13', '2009-01-09', NULL, 'homeLocation_3725002', '421302200901093928', 'educationBackground_2', 'title_2');

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
('3725002', '3725001', 'TEACHER', '1');

-- --------------------------------------------------------

--
-- 表的结构 `userRole`
--

CREATE TABLE IF NOT EXISTS `userRole` (
  `id` int(11) NOT NULL COMMENT '编号;编号',
  `userId` varchar(255) NOT NULL COMMENT '用户编号;用户编号',
  `roleId` int(11) NOT NULL COMMENT '角色编号;角色编号',
  `status` varchar(1) NOT NULL DEFAULT '1' COMMENT '状态;该条数据是否可用'
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

--
-- 转存表中的数据 `userRole`
--

INSERT INTO `userRole` (`id`, `userId`, `roleId`, `status`) VALUES
(1, '201921098380', 1, '1'),
(2, '201921098412', 1, '1'),
(3, '3725001', 2, '1'),
(4, '3725002', 2, '1');

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
-- 视图结构 `courseExaminationScore`
--
DROP TABLE IF EXISTS `courseExaminationScore`;

CREATE ALGORITHM=UNDEFINED DEFINER=`ScoreRelease`@`localhost` SQL SECURITY DEFINER VIEW `courseExaminationScore` AS select `specialty`.`specialtyName` AS `specialtyName`,concat(`class`.`number`,'班') AS `className`,`studentExamination`.`studentId` AS `studentId`,`course`.`score` AS `courseScore`,`course`.`name` AS `courseName`,`examination`.`grade` AS `grade`,`studentExamination`.`score` AS `score`,`studentInfo`.`name` AS `studentName`,`course`.`property` AS `property`,`examination`.`courseId` AS `courseId`,`course`.`teacherId` AS `teacherId`,`examination`.`proportion` AS `proportion`,(`examination`.`proportion` * `studentExamination`.`score`) AS `calculatedScore`,`course`.`examinationId` AS `examinationId`,`studentExamination`.`id` AS `studentExaminationId` from (((((`studentExamination` join `examination` on((`examination`.`id` = `studentExamination`.`examinationId`))) join `course` on((`course`.`examinationId` = `examination`.`id`))) join `studentInfo` on((`studentExamination`.`studentId` = `studentInfo`.`number`))) join `class` on((`studentInfo`.`classId` = `class`.`id`))) join `specialty` on((`class`.`specialtyId` = `specialty`.`id`)));

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

CREATE ALGORITHM=UNDEFINED DEFINER=`ScoreRelease`@`%` SQL SECURITY DEFINER VIEW `ordinaryScoreViewCalculated` AS select sum(((`ordinaryScoreViewWithProportion`.`p1` * `ordinaryScoreViewWithProportion`.`p2`) * `ordinaryScoreViewWithProportion`.`score`)) AS `calculatedOrdinaryScore`,`ordinaryScoreViewWithProportion`.`courseId` AS `courseId`,`ordinaryScoreViewWithProportion`.`year` AS `year`,`ordinaryScoreViewWithProportion`.`term` AS `term`,`ordinaryScoreViewWithProportion`.`courseName` AS `courseName`,`ordinaryScoreViewWithProportion`.`studentId` AS `studentId`,`ordinaryScoreViewWithProportion`.`teacherId` AS `teacherId` from `ordinaryScoreViewWithProportion` group by `ordinaryScoreViewWithProportion`.`studentId`,`ordinaryScoreViewWithProportion`.`courseId`;

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
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `examination`
--
ALTER TABLE `examination`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `inputTime`
--
ALTER TABLE `inputTime`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ordinaryScore`
--
ALTER TABLE `ordinaryScore`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ordinaryScoreItem`
--
ALTER TABLE `ordinaryScoreItem`
  ADD PRIMARY KEY (`id`);

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
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `studentCourse`
--
ALTER TABLE `studentCourse`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `studentExamination`
--
ALTER TABLE `studentExamination`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `studentInfo`
--
ALTER TABLE `studentInfo`
  ADD PRIMARY KEY (`number`);

--
-- Indexes for table `studentOrdinaryScoreItem`
--
ALTER TABLE `studentOrdinaryScoreItem`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `studentOrdinaryScoreItem_ordinaryScoreItemId_studentId_uindex` (`ordinaryScoreItemId`,`studentId`);

--
-- Indexes for table `taskGroup`
--
ALTER TABLE `taskGroup`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `teacherInfo`
--
ALTER TABLE `teacherInfo`
  ADD PRIMARY KEY (`number`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`number`);

--
-- Indexes for table `userRole`
--
ALTER TABLE `userRole`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `yearTerm`
--
ALTER TABLE `yearTerm`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `access`
--
ALTER TABLE `access`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号;编号',AUTO_INCREMENT=13;
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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',AUTO_INCREMENT=47;
--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号;编号',AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `roleAccess`
--
ALTER TABLE `roleAccess`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号;编号',AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `specialty`
--
ALTER TABLE `specialty`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号;编号',AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `studentCourse`
--
ALTER TABLE `studentCourse`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号;编号',AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `studentExamination`
--
ALTER TABLE `studentExamination`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号;编号',AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `studentOrdinaryScoreItem`
--
ALTER TABLE `studentOrdinaryScoreItem`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号;编号',AUTO_INCREMENT=26;
--
-- AUTO_INCREMENT for table `taskGroup`
--
ALTER TABLE `taskGroup`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号;编号',AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `userRole`
--
ALTER TABLE `userRole`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号;编号',AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `yearTerm`
--
ALTER TABLE `yearTerm`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号;编号',AUTO_INCREMENT=13;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
