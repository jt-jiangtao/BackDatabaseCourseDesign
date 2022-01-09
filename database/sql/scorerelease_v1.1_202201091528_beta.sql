-- phpMyAdmin SQL Dump
-- version 4.4.15.10
-- https://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 2022-01-09 15:28:05
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
  `arrangeExamination` varchar(1) DEFAULT '0' COMMENT '是否编排考试;是否编排考试',
  `yearTermId` int(11) NOT NULL COMMENT '学年学期编号;学年学期编号',
  `status` varchar(1) NOT NULL DEFAULT '1' COMMENT '状态;有效',
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='课程表';

--
-- 转存表中的数据 `course`
--

INSERT INTO `course` (`id`, `teacherId`, `startTime`, `endTime`, `score`, `time`, `introduce`, `property`, `ordinaryScoreId`, `examinationId`, `arrangeExamination`, `yearTermId`, `status`, `name`) VALUES
(1, '3725001', '第1周', '第16周', '4.5', 48, NULL, '专业必修课', 1, 4, '0', 8, '1', '数据库'),
(2, '3725001', '第6周', '第10周', '1.0', 24, NULL, '专业必修课', 2, 3, '0', 8, '1', '数据库实验'),
(3, '3725002', '第1周', '第16周', '2.5', 32, NULL, '专业必修课', 3, 2, '0', 8, '1', 'c'),
(4, '3725002', '第1周', '第16周', '2.5', 32, NULL, '专业必修课', 4, 1, '0', 8, '1', 'c++');

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
  `maxScore` int(11) NOT NULL DEFAULT '100' COMMENT '分值;分值',
  `status` varchar(1) NOT NULL DEFAULT '1' COMMENT '状态;有效'
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='考试表';

--
-- 转存表中的数据 `examination`
--

INSERT INTO `examination` (`id`, `courseId`, `proportion`, `grade`, `maxScore`, `status`) VALUES
(1, 4, '0.40', 'A', 100, '1'),
(2, 3, '0.50', 'A', 100, '1'),
(3, 2, '0.60', 'A', 100, '1'),
(4, 1, '0.60', 'B', 100, '1');

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
(4, 4, '0.60');

-- --------------------------------------------------------

--
-- 表的结构 `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL COMMENT '编号;编号',
  `roleName` varchar(255) NOT NULL COMMENT '角色名称;角色名称',
  `staus` varchar(1) NOT NULL DEFAULT '1' COMMENT '状态;角色是否可用'
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='角色表';

--
-- 转存表中的数据 `role`
--

INSERT INTO `role` (`id`, `roleName`, `staus`) VALUES
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
  `score` int(11) DEFAULT NULL COMMENT '分数;分数',
  `status` varchar(1) NOT NULL DEFAULT '1' COMMENT '状态;有效'
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='学生课程表（学生选课表）';

--
-- 转存表中的数据 `studentCourse`
--

INSERT INTO `studentCourse` (`id`, `studentId`, `courseId`, `studyMode`, `score`, `status`) VALUES
(1, '201921098412', 1, '初修', NULL, '1'),
(2, '201921098412', 2, '初修', NULL, '1'),
(3, '201921098412', 3, '初修', 93, '1'),
(4, '201921098412', 4, '初修', NULL, '1'),
(5, '201921098380', 1, '初修', NULL, '1'),
(6, '201921098380', 2, '初修', NULL, '1'),
(7, '201921098380', 3, '初修', 86, '1'),
(8, '201921098380', 4, '初修', NULL, '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='学生考试表（学生成绩表）';

--
-- 转存表中的数据 `studentExamination`
--

INSERT INTO `studentExamination` (`id`, `studentId`, `examinationId`, `score`, `status`) VALUES
(1, '201921098412', 2, '94.0', '1'),
(2, '201921098380', 2, '90.0', '1');

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
-- 表的结构 `studentOrdinaryScore`
--

CREATE TABLE IF NOT EXISTS `studentOrdinaryScore` (
  `id` int(11) NOT NULL COMMENT '编号;编号',
  `studentId` varchar(255) NOT NULL COMMENT '学生编号;学生编号',
  `ordinaryScoreId` int(11) NOT NULL COMMENT '平时成绩编号;平时成绩编号',
  `status` varchar(1) NOT NULL DEFAULT '1' COMMENT '状态;有效'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='学生平时成绩表';

--
-- 转存表中的数据 `studentOrdinaryScore`
--

INSERT INTO `studentOrdinaryScore` (`id`, `studentId`, `ordinaryScoreId`, `status`) VALUES
(1, '201921098412', 3, '1'),
(2, '201921098380', 3, '1');

-- --------------------------------------------------------

--
-- 表的结构 `studentOrdinaryScoreItem`
--

CREATE TABLE IF NOT EXISTS `studentOrdinaryScoreItem` (
  `id` int(11) NOT NULL COMMENT '编号;编号',
  `itemName` varchar(255) NOT NULL COMMENT '单项名称;单项名称',
  `score` varchar(255) NOT NULL COMMENT '分数;分数',
  `proportion` decimal(3,2) NOT NULL COMMENT '占比;占比',
  `status` varchar(1) NOT NULL DEFAULT '1' COMMENT '状态;状态',
  `studentOrdinaryScoreId` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='学生平时成绩项表';

--
-- 转存表中的数据 `studentOrdinaryScoreItem`
--

INSERT INTO `studentOrdinaryScoreItem` (`id`, `itemName`, `score`, `proportion`, `status`, `studentOrdinaryScoreId`) VALUES
(1, '签到', '100', '0.30', '1', 1),
(2, '签到', '90', '0.30', '1', 2),
(3, '实验', '90', '0.70', '1', 1),
(4, '实验', '80', '0.70', '1', 2);

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
(3, '3725001', 6, '1'),
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
-- Indexes for table `studentOrdinaryScore`
--
ALTER TABLE `studentOrdinaryScore`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `studentOrdinaryScoreItem`
--
ALTER TABLE `studentOrdinaryScoreItem`
  ADD PRIMARY KEY (`id`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号;编号',AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `studentOrdinaryScore`
--
ALTER TABLE `studentOrdinaryScore`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号;编号',AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `studentOrdinaryScoreItem`
--
ALTER TABLE `studentOrdinaryScoreItem`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号;编号',AUTO_INCREMENT=5;
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
