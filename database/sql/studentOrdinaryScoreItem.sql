-- phpMyAdmin SQL Dump
-- version 4.4.15.10
-- https://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 2022-01-12 13:22:27
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
-- 表的结构 `studentOrdinaryScoreItem`
--

CREATE TABLE IF NOT EXISTS `studentOrdinaryScoreItem` (
  `id` int(11) NOT NULL COMMENT '编号;编号',
  `score` varchar(255) NOT NULL COMMENT '分数;分数',
  `status` varchar(1) NOT NULL DEFAULT '1' COMMENT '状态;状态',
  `studentId` varchar(255) NOT NULL COMMENT '学号',
  `ordinaryScoreItemId` int(11) NOT NULL COMMENT '平时成绩项编号'
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='学生平时成绩项表';

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
(12, '100.0', '1', '201921098412', 45),
(13, '55.0', '1', '201921098412', 46),
(14, '96.0', '1', '201921098380', 45),
(15, '78.0', '1', '201921098380', 46);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `studentOrdinaryScoreItem`
--
ALTER TABLE `studentOrdinaryScoreItem`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `studentOrdinaryScoreItem_ordinaryScoreItemId_studentId_uindex` (`ordinaryScoreItemId`,`studentId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `studentOrdinaryScoreItem`
--
ALTER TABLE `studentOrdinaryScoreItem`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号;编号',AUTO_INCREMENT=24;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
