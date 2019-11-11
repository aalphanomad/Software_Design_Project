-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 06, 2019 at 11:37 AM
-- Server version: 5.5.38-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `d1601745`
--

-- --------------------------------------------------------

--
-- Table structure for table `BOOKINGS`
--

CREATE TABLE IF NOT EXISTS `BOOKINGS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) NOT NULL,
  `STUDENT_NUM` varchar(10) NOT NULL,
  `COURSE` varchar(10) DEFAULT NULL,
  `DATE` varchar(20) NOT NULL,
  `VENUE` varchar(50) DEFAULT NULL,
  `START_TIME` time DEFAULT NULL,
  `END_TIME` time NOT NULL,
  `ACTIVITY` varchar(20) NOT NULL,
  `VALID` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=802 ;

--
-- Dumping data for table `BOOKINGS`
--

INSERT INTO `BOOKINGS` (`ID`, `NAME`, `STUDENT_NUM`, `COURSE`, `DATE`, `VENUE`, `START_TIME`, `END_TIME`, `ACTIVITY`, `VALID`) VALUES
(72, 'Tutor', '1', 'COMS2002', '25 Mar 2019', 'fjff', '09:15:00', '12:15:00', 'Tutoring', 1),
(162, 'Test', '1611352', 'COMS1015', '04 Apr 2019', 'MSL', '15:25:00', '17:20:00', 'Tutoring', 0),
(214, 'Innocent', '011', 'COMS2014', '05 Apr 2019', 'hehe', '08:51:00', '08:58:00', 'Invigilating', 0),
(245, 'Tutor', '1', 'COMS2014', '19 Apr 2019', 'done', '15:41:00', '15:45:00', 'Invigilating', 0),
(247, '10', '10', 'COMS1016', '21 Apr 2019', 'ppp', '07:23:00', '07:30:00', 'Tutoring', 0),
(248, 'Tutor', '1', 'COMS3006', '06 May 2019', 'MSL004', '15:01:00', '15:26:00', 'Other', 0),
(274, 'Tutor', '1', 'COMS2002', '30 May 2019', 'bfsg', '10:33:00', '10:45:00', 'Tutoring', 0),
(275, 'Tutor', '1', 'COMS1016', '30 May 2019', 'Msl', '12:31:00', '13:31:00', 'Invigilating', 0),
(276, 'Tutor', '1', 'COMS3007', '30 May 2019', 'Msl', '16:35:00', '17:36:00', 'Tutoring', 1),
(278, 'Tutor', '1', 'COMS3007', '30 May 2019', 'Pandors house', '20:59:00', '21:59:00', 'Tutoring', 0),
(279, 'Tutor', '1', 'COMS3007', '01 Jun 2019', 'hvdd', '10:59:00', '12:59:00', 'Tutoring', 0),
(280, 'Tutor', '1', 'COMS3007', '01 Jun 2019', 'nbg', '20:21:00', '20:25:00', 'Tutoring', 0),
(295, 'Mayur', '31', 'COMS1016', '08 Aug 2019', 'ppp', '18:07:00', '18:10:00', 'Tutoring', 0),
(301, 'Tutor', '1', 'COMS3007', '30 May 2019', 'Pandors house', '20:21:00', '20:25:00', 'Tutoring', 0),
(752, 'Tutor', '1', 'COMS3007', '25 Sep 2019', 'gjherghjre', '07:00:00', '07:05:00', 'Tutoring', 1),
(753, 'Tutor', '1', 'COMS3007', '25 Sep 2019', 'gjhgh', '07:30:00', '07:35:00', 'Tutoring', 1),
(755, 'Tutor', '1', 'COMS3007', '27 Sep 2019', 'sig', '08:10:00', '08:20:00', 'Tutoring', 0),
(756, 'Tutor', '1', 'COMS3007', '27 Sep 2019', 'etyuu', '16:00:00', '16:05:00', 'Invigilating', 0),
(757, 'Tutor', '1', 'COMS3007', '27 Sep 2019', 'sig', '08:30:00', '08:35:00', 'Tutoring', 0),
(758, 'Tutor', '1', 'COMS3007', '27 Sep 2019', 'hjvhsgsh', '16:10:00', '16:15:00', 'Tutoring', 0),
(759, 'Tutor', '1', 'COMS3007', '27 Sep 2019', 'gjhghj', '17:00:00', '17:05:00', 'Tutoring', 0),
(760, 'Tutor', '1', 'COMS3007', '27 Sep 2019', 'sig', '08:45:00', '08:50:00', 'Tutoring', 0),
(761, 'Tutor', '1', 'COMS3007', '27 Sep 2019', 'iere', '17:20:00', '17:25:00', 'Tutoring', 0),
(762, 'Tutor', '1', 'COMS3007', '27 Sep 2019', 'yruieyr', '17:30:00', '17:35:00', 'Tutoring', 0),
(763, 'Tutor', '1', 'COMS3007', '27 Sep 2019', 'sig', '08:55:00', '09:00:00', 'Tutoring', 0),
(764, 'Tutor', '1', 'COMS3007', '27 Sep 2019', 'kjehwjkeh', '17:40:00', '17:45:00', 'Tutoring', 0),
(765, 'Tutor', '1', 'COMS3007', '27 Sep 2019', 'wweiwu', '18:00:00', '18:05:00', 'Tutoring', 0),
(766, 'Tutor', '1', 'COMS3007', '27 Sep 2019', 'sig', '09:05:00', '09:10:00', 'Tutoring', 0),
(767, 'Tutor', '1', 'COMS3007', '27 Sep 2019', 'yuiy23ui2', '18:30:00', '18:35:00', 'Tutoring', 0),
(768, 'Tutor', '1', 'COMS3007', '27 Sep 2019', 'uyeiuwei', '19:00:00', '19:05:00', 'Invigilating', 0),
(769, 'Tutor', '1', 'COMS3007', '27 Sep 2019', 'uyiu2y', '19:20:00', '19:30:00', 'Invigilating', 0),
(770, 'Tutor', '1', 'COMS3007', '27 Sep 2019', 'sig', '09:15:00', '09:20:00', 'Tutoring', 0),
(771, 'Tutor', '1', 'COMS3007', '27 Sep 2019', 'ghghj', '20:10:00', '20:15:00', 'Tutoring', 0),
(772, 'Tutor', '1', 'COMS3007', '27 Sep 2019', 'sig', '09:25:00', '09:30:00', 'Tutoring', 0),
(773, 'Tutor', '1', 'COMS3007', '27 Sep 2019', 'sig', '09:35:00', '09:40:00', 'Tutoring', 0),
(774, 'Tutor', '1', 'COMS3007', '27 Sep 2019', 'sig', '09:50:00', '09:55:00', 'Tutoring', 0),
(775, 'Tutor', '1', 'COMS3007', '27 Sep 2019', 'yuiy', '19:40:00', '19:45:00', 'Tutoring', 0),
(776, 'Tutor', '1', 'COMS3007', '27 Sep 2019', 'sig', '10:05:00', '10:10:00', 'Tutoring', 0),
(777, 'Tutor', '1', 'COMS3007', '27 Sep 2019', 'uiyiuyi`', '21:20:00', '21:25:00', 'Tutoring', 0),
(778, 'Tutor', '1', 'COMS3007', '27 Sep 2019', 'sig', '10:20:00', '10:30:00', 'Tutoring', 0),
(779, 'Tutor', '1', 'COMS3007', '27 Sep 2019', 'iuiou', '21:50:00', '21:55:00', 'Tutoring', 0),
(780, 'Tutor', '1', 'COMS3007', '27 Sep 2019', 'hhwkjehwjk', '22:00:00', '22:05:00', 'Tutoring', 0),
(781, 'Tutor', '1', 'COMS3007', '27 Sep 2019', 'sig', '11:05:00', '11:15:00', 'Tutoring', 0),
(782, 'Tutor', '1', 'COMS3007', '27 Sep 2019', 'uyiuyui', '22:40:00', '22:45:00', 'Tutoring', 0),
(783, 'Tutor', '1', 'COMS3007', '27 Sep 2019', 'sig', '11:30:00', '11:55:00', 'Tutoring', 0),
(784, 'Tutor', '1', 'COMS2015', '27 Sep 2019', 'hhgjhghj', '23:00:00', '23:05:00', 'Tutoring', 0),
(785, 'Tutor', '1', 'COMS3007', '27 Sep 2019', '01hhiu', '01:00:00', '01:05:00', 'Tutoring', 0),
(786, 'Tutor', '1', 'COMS1017', '27 Sep 2019', 'sig', '12:00:00', '12:05:00', 'Invigilating', 0),
(787, 'Tutor', '1', 'COMS3007', '27 Sep 2019', '12', '01:20:00', '01:25:00', 'Tutoring', 0),
(788, 'Tutor', '1', 'COMS3007', '27 Sep 2019', 'hwkjew', '02:30:00', '02:35:00', 'Tutoring', 0),
(789, 'Tutor', '1', 'COMS3007', '27 Sep 2019', 'uiwew', '02:40:00', '02:45:00', 'Tutoring', 0),
(790, 'Tutor', '1', 'COMS1017', '27 Sep 2019', 'sig', '12:10:00', '12:20:00', 'Marking', 0),
(791, 'Tutor', '1', 'COMS3007', '27 Sep 2019', 'ytytuy', '03:30:00', '03:45:00', 'Tutoring', 0),
(792, 'Tutor', '1', 'COMS1017', '27 Sep 2019', 'sig', '12:35:00', '12:45:00', 'Tutoring', 0),
(793, 'Tutor', '1', 'COMS1017', '27 Sep 2019', 'rwew', '04:30:00', '04:35:00', 'Other', 0),
(794, 'Tutor', '1', 'COMS1017', '27 Sep 2019', 'sig', '13:00:00', '13:05:00', 'Marking', 0),
(795, 'Tutor', '1', 'COMS2015', '27 Sep 2019', 'sig', '13:10:00', '13:15:00', 'Marking', 0),
(796, 'Elgoni', '10', 'COMS1016', '27 Sep 2019', 'kjhj', '10:10:00', '10:15:00', 'Tutoring', 0),
(797, 'Zack Hemsey', '000', 'COMS1016', '27 Sep 2019', 'hjkhj', '10:00:00', '10:05:00', 'Tutoring', 0),
(798, 'Rush', '83', 'COMS1017', '27 Sep 2019', 'hjghjew', '01:00:00', '01:15:00', 'Tutoring', 1),
(799, 'Sleep', '2019', 'COMS2015', '27 Sep 2019', 'hdksj', '01:00:00', '01:05:00', 'Marking', 0),
(800, 'new', '140', 'COMS3003', '28 Sep 2019', 'hjhgjh', '01:00:00', '01:05:00', 'Tutoring', 1),
(801, 'Marubini', '1622535', 'Software D', 'MSL', '12-09-2019', '08:00:00', '09:00:00', 'LoginView', 0);

-- --------------------------------------------------------

--
-- Table structure for table `COURSES`
--

CREATE TABLE IF NOT EXISTS `COURSES` (
  `COURSE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `COURSE_CODE` varchar(10) NOT NULL,
  `COURSE_NAME` varchar(50) NOT NULL,
  PRIMARY KEY (`COURSE_ID`),
  UNIQUE KEY `COURSE_CODE` (`COURSE_CODE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=88 ;

--
-- Dumping data for table `COURSES`
--

INSERT INTO `COURSES` (`COURSE_ID`, `COURSE_CODE`, `COURSE_NAME`) VALUES
(1, 'COMS1016', 'Discrete Computational Structures'),
(2, 'COMS2002', 'Database Fundamentals'),
(3, 'COMS2013', 'Mobile Computing'),
(6, 'COMS2014', 'Computer Networks'),
(7, 'COMS2015', 'Analysis Of Algorithms'),
(11, 'COMS3005', 'Advanced Analysis Of Algorithms'),
(12, 'COMS3003', 'Formal Languages And Automata'),
(14, 'COMS3009', 'Software Design'),
(15, 'COMS3010', 'Operating Systems & System Programming'),
(18, 'COMS3007', 'Machine Learning'),
(19, 'COMS3006', 'Computer Graphics & Visualization'),
(20, 'COMS3008', 'Parallel Computing'),
(22, 'COMS3011', 'Software Design Project'),
(75, 'COMS1015', 'Basic Computer Organisation'),
(76, 'COMS1017', 'Introduction To Data Structures And Algorithms'),
(77, 'COMS1018', 'Introduction To Algorithms And Programming');

-- --------------------------------------------------------

--
-- Table structure for table `LECT_ALLOC`
--

CREATE TABLE IF NOT EXISTS `LECT_ALLOC` (
  `USER` varchar(50) DEFAULT NULL,
  `COURSE` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `USER_COURSE_ALLOC`
--

CREATE TABLE IF NOT EXISTS `USER_COURSE_ALLOC` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) NOT NULL,
  `STUDENT_NUM` varchar(10) NOT NULL,
  `ROLE` varchar(5) NOT NULL,
  `COURSE` varchar(50) NOT NULL,
  `CONFIRMED` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=152 ;

--
-- Dumping data for table `USER_COURSE_ALLOC`
--

INSERT INTO `USER_COURSE_ALLOC` (`ID`, `NAME`, `STUDENT_NUM`, `ROLE`, `COURSE`, `CONFIRMED`) VALUES
(10, '', '010', '', 'COMS3011', 0),
(11, '', '010', '', 'COMS3008', 1),
(12, '', '010', '', 'COMS3006', 1),
(13, '', '050', '', 'COMS1017', 1),
(14, '', '050', '', 'COMS2014', 1),
(15, '', '050', '', 'COMS2015', 1),
(18, '', '1', '', 'COMS1017', 1),
(19, '', '10', '', 'COMS1016', 1),
(20, '', '10', '', 'COMS1017', 1),
(21, '', '10', '', 'COMS2014', 1),
(22, '', '100', '', 'COMS3007', 0),
(23, '', '100', '', 'COMS3010', 0),
(24, '', '1000', '', 'COMS2002', 0),
(25, '', '1000', '', 'COMS2013', 0),
(26, '', '1000', '', 'COMS1016', 1),
(27, '', '1000', '', 'COMS1017', 0),
(28, '', '1000', '', 'COMS1018', 1),
(29, '', '101', '', 'COMS3003', 1),
(30, '', '101', '', ' COMS2014', 1),
(31, '', '101', '', ' COMS2013', 2),
(32, '', '10101', '', 'COMS2014', 0),
(33, '', '10101', '', 'COMS2002', 0),
(34, '', '10101', '', 'COMS1016', 2),
(35, '', '11', '', 'COMS1017', 0),
(36, '', '11', '', 'COMS2014', 0),
(37, '', '11', '', 'COMS2013', 0),
(38, '', '11', '', 'COMS1016', 0),
(39, '', '11', '', 'COMS2015', 0),
(40, '', '111', '', 'COMS3003', 1),
(41, '', '12', '', 'COMS1016', 0),
(42, '', '12', '', ' COMS2013', 0),
(43, '', '12', '', ' COMS2002', 0),
(44, '', '12', '', ' COMS2014', 0),
(45, '', '12', '', ' COMS2015', 0),
(46, '', '123', '', 'COMS2013', 0),
(47, '', '12345', '', 'COMS1015', 0),
(48, '', '12345', '', 'COMS1018', 0),
(49, '', '123653', '', 'COMS2014', 0),
(50, '', '123653', '', 'COMS1017', 0),
(51, '', '153', '', 'COMS3006', 0),
(52, '', '153', '', 'COMS3009', 0),
(53, '', '153', '', 'COMS2015', 0),
(54, '', '153', '', 'COMS2013', 0),
(55, '', '153', '', 'COMS1016', 0),
(56, '', '1601745', '', 'COMS2002', 0),
(57, '', '1601745', '', 'COMS2015', 0),
(58, '', '161', '', 'COMS3003', 0),
(59, '', '162', '', 'COMS2014', 0),
(60, '', '162', '', 'COMS2002', 0),
(61, '', '162', '', 'COMS3009', 0),
(62, '', '162', '', 'COMS2015', 0),
(63, '', '163', '', 'COMS2014', 0),
(64, '', '163', '', 'COMS1016', 0),
(65, '', '1676', '', 'COMS2002', 0),
(66, '', '1676', '', 'COMS1017', 0),
(67, '', '170', '', 'COMS2014', 0),
(68, '', '170', '', 'COMS2015', 0),
(69, '', '170', '', 'COMS3011', 0),
(70, '', '2', '', 'COMS2013', 1),
(71, '', '2', '', 'COMS1018', 1),
(73, '', '271', '', 'COMS1016', 0),
(74, '', '271', '', ' COMS2002', 0),
(75, '', '271', '', ' COMS2013', 0),
(76, '', '3', '', 'COMS3003', 1),
(77, '', '3', '', 'COMS3007', 0),
(78, '', '31', '', 'COMS1016', 0),
(79, '', '31', '', ' COMS2002', 0),
(80, '', '4', '', 'COMS3007', 2),
(81, '', '4', '', 'COMS3006', 0),
(82, '', '4', '', 'COMS3011', 1),
(87, '', '1', '', 'COMS3005', 1),
(88, '', '1', '', 'COMS3007', 1),
(89, '', '1', '', 'COMS3008', 1),
(90, '', '23', '', 'COMS3003', 0),
(91, '', '999', '', 'COMS2013', 0),
(92, '', '780', '', 'COMS2013', 0),
(99, '', '140', '', 'COMS2014', 0),
(100, '', '140', '', 'COMS3005', 0),
(101, '', '140', '', 'COMS3003', 0),
(102, '', '140', '', 'COMS2013', 0),
(103, '', '140', '', 'COMS1017', 0),
(104, 'new test', '072', '0', 'COMS2014', 0),
(105, 'new test', '072', '0', 'COMS2002', 0),
(106, 'new test', '072', '0', 'COMS1018', 0),
(107, 'new_test', '071', '1', 'COMS3003', 0),
(108, 'new_test', '071', '1', 'COMS2015', 0),
(109, 'new_test', '071', '1', 'COMS2014', 0),
(114, 'fjkdshksfdhksd', '859308340', '0', 'COMS2015', 0),
(115, 'fjkdshksfdhksd', '859308340', '0', 'COMS2014', 0),
(116, 'fjkdshksfdhksd', '859308340', '0', 'COMS2002', 0),
(117, 'fjkdshksfdhksd', '859308340', '0', 'COMS1016', 0),
(118, '', '011852', '', 'COMS2014', 0),
(121, 'bob', '852727', '0', 'one', 0),
(122, 'bob', '852727', '0', 'two', 0),
(123, 'opop', '6464', '0', 'COMS2002', 0),
(124, 'opop', '6464', '0', 'COMS2013', 0),
(125, 'qwert', '191919', '0', 'COMS1015', 0),
(126, 'qwert', '191919', '0', 'COMS1016', 0),
(127, 'heiwheuw', '129019', '0', 'COMS2014', 0),
(128, 'heiwheuw', '129019', '0', 'COMS2002', 0),
(129, 'hewhewk', '101010101', '0', 'COMS2015', 0),
(130, 'hewhewk', '101010101', '0', 'COMS2014', 0),
(131, 'jkhjhkh', '79433', '0', 'COMS1015', 0),
(132, 'jkhjhkh', '79433', '0', 'COMS2014', 0),
(133, 'jkhjhkh', '79433', '0', 'COMS3005', 0),
(134, 'jkhjhkh', '79433', '0', 'COMS2015', 0),
(141, 'hkjhkj', '829032', '0', 'COMS2015', 0),
(142, 'hkjhkj', '829032', '0', 'COMS2014', 0),
(143, 'hkjhkj', '829032', '0', 'COMS2013', 0),
(144, 'hkjhkj', '829032', '0', 'COMS3005', 0),
(145, 'gugigi', '75578578', '0', 'COMS2014', 0),
(146, 'gugigi', '75578578', '0', 'COMS1017', 0),
(147, 'gugigi', '75578578', '0', 'COMS1015', 0),
(148, 'gugigi', '75578578', '0', 'COMS3005', 0),
(149, 'New_test', '732891', '0', 'COMS1016', 0),
(150, 'kjeljlq', '83092', '0', 'COMS2013', 0),
(151, 'dfhsk', '3840', '0', 'COMS2015', 0);

-- --------------------------------------------------------

--
-- Table structure for table `USER_INFORMATION`
--

CREATE TABLE IF NOT EXISTS `USER_INFORMATION` (
  `NAME` varchar(50) DEFAULT NULL,
  `STUDENT_NUM` varchar(10) NOT NULL,
  `EMAIL_ADDRESS` varchar(50) DEFAULT NULL,
  `USER_PASSWORD` varchar(50) NOT NULL,
  `ROLE` tinyint(1) DEFAULT NULL,
  `COURSE_1` varchar(20) DEFAULT NULL,
  `COURSE_2` varchar(20) DEFAULT NULL,
  `COURSE_3` varchar(20) DEFAULT NULL,
  `COURSE_4` varchar(20) DEFAULT NULL,
  `COURSE_5` varchar(20) DEFAULT NULL,
  `TRANSCRIPT` varchar(500) DEFAULT NULL,
  UNIQUE KEY `STUDENT_NUMBER` (`STUDENT_NUM`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `USER_INFORMATION`
--

INSERT INTO `USER_INFORMATION` (`NAME`, `STUDENT_NUM`, `EMAIL_ADDRESS`, `USER_PASSWORD`, `ROLE`, `COURSE_1`, `COURSE_2`, `COURSE_3`, `COURSE_4`, `COURSE_5`, `TRANSCRIPT`) VALUES
('DUMMY', '-1', '-1@students.wits.ac.za', 'test', 0, 'COMS2013', 'yiuyuyiu', 'yuiyuiy', 'uiyuiyuiy', 'yiyiy', 'http://lamp.ms.wits.ac.za/~s1601745/pdfs/75578578.pdf'),
('Zack Hemsey', '000', '437898@students.wits.ac.za', 'test', 0, 'COMS1016', 'COMS2002', 'COMS2014', 'COMS3003', 'COMS3007', NULL),
('Ryan Wood', '009', '009@students.wits.ac.za', 'test', 1, 'COMS2013', 'COMS3003', NULL, NULL, NULL, NULL),
('Omer', '011852', '011852@students.wits.ac.za', 'test', 0, 'COMS2015', 'COMS2013', 'COMS1018', 'COMS1016', NULL, NULL),
('Tutor', '1', '1@students.wits.ac.za', 'test', 0, 'COMS3007', 'COMS2015', 'COMS1017', NULL, NULL, NULL),
('Elgoni', '10', '10@students.wits.ac.za', 'test', 0, 'COMS1016', 'COMS1017', 'COMS2014', NULL, NULL, NULL),
('Juno Reactor', '10000090', '10000090@students.wits.ac.za', 'test', 0, 'COMS3005', 'COMS3003', 'COMS2015', 'COMS2014', 'COMS2013', NULL),
('Mayur', '101', '101@students.wits.ac.za', 'test', 1, 'COMS3003', 'COMS2014', 'COMS2013', "COMS3005", "COMS1017", NULL),
('Innocent', '11', '11@students.wits.ac.za', 'test', 0, 'COMS1017', 'COMS2014', 'COMS2013', 'COMS1016', 'COMS2015', NULL),
('oupa', '12345', '12345@students.wits.ac.za', '12345', 0, 'COMS1015', 'COMS1018', NULL, NULL, NULL, NULL),
('Mayur', '1601745', '1601745@students.wits.ac.za', 'test', 3, 'COMS2002', 'COMS2015', NULL, NULL, NULL, NULL),
('Lecturer', '2', '2@students.wits.ac.za', 'test', 1, 'COMS2013', 'COMS1018', NULL, NULL, NULL, NULL),
('Admin', '3', '3@students.wits.ac.za', 'test', 2, 'COMS3003', 'COMS3007', NULL, NULL, NULL, NULL),
('dfhsk', '3840', '3840@students.wits.ac.za', 'test', 0, 'COMS3009', NULL, NULL, NULL, NULL, 'http://lamp.ms.wits.ac.za/~s1601745/pdfs/75578578.pdf'),
('Lecturer_Admin', '4', '4@students.wits.ac.za', 'test', 3, 'COMS3007', 'COMS3006', 'COMS3011', NULL, NULL, NULL),
('super_admin', '5', '5@students.wits.ac.za', 'test', 4, NULL, NULL, NULL, NULL, NULL, NULL),
('New_test', '732891', '732891@students.wits.ac.za', 'test', 0, 'COMS1016', NULL, NULL, NULL, NULL, 'http://lamp.ms.wits.ac.za/~s1601745/pdfs/75578578.pdf'),
('gugigi', '75578578', '75578578@students.wits.ac.za', 'test', 0, 'COMS2014', 'COMS1017', 'COMS1015', 'COMS3005', NULL, 'http://lamp.ms.wits.ac.za/~s1601745/pdfs/75578578.pdf'),
('kjeljlq', '83092', '83092@students.wits.ac.za', 'test', 0, 'COMS2013', NULL, NULL, NULL, NULL, 'http://lamp.ms.wits.ac.za/~s1601745/pdfs/75578578.pdf'),
('mitchell Broom', '9374983794', '93749837948@students.wits.ac.za', 'test', 0, 'COMS1016', 'COMS2014', 'COMS2015', 'COMS3005', 'COMS3006', NULL);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
