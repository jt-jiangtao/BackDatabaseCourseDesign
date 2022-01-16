-- phpMyAdmin SQL Dump
-- version 4.4.15.10
-- https://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 2022-01-16 16:16:34
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
-- 表的结构 `studentExamination`
--

CREATE TABLE IF NOT EXISTS `studentExamination` (
  `id` int(11) NOT NULL COMMENT '编号;编号',
  `studentId` varchar(255) NOT NULL COMMENT '学生编号;学生编号',
  `examinationId` int(11) NOT NULL COMMENT '考试编号;考试编号',
  `score` decimal(4,1) NOT NULL COMMENT '考试成绩;考试成绩',
  `status` varchar(1) NOT NULL DEFAULT '1' COMMENT '状态;有效'
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COMMENT='学生考试表（学生成绩表）';

--
-- 转存表中的数据 `studentExamination`
--

INSERT INTO `studentExamination` (`id`, `studentId`, `examinationId`, `score`, `status`) VALUES
(3, '201921098412', 1, '99.0', '1'),
(4, '201921098380', 1, '90.0', '1'),
(5, '201921098412', 2, '88.0', '1'),
(6, '201921098380', 2, '92.0', '1'),
(29, '201921098412', 4, '100.0', '1'),
(33, '201921098412', 3, '87.0', '1'),
(35, '201921098380', 4, '100.0', '1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `studentExamination`
--
ALTER TABLE `studentExamination`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `studentExamination_studentId_examinationId_uindex` (`studentId`,`examinationId`),
  ADD UNIQUE KEY `studentExamination_id_uindex` (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `studentExamination`
--
ALTER TABLE `studentExamination`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号;编号',AUTO_INCREMENT=36;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
