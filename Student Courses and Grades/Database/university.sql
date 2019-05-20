-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Dec 05, 2018 at 11:22 PM
-- Server version: 5.7.21-log
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `university`
--

-- --------------------------------------------------------

--
-- Table structure for table `coursedescription`
--

DROP TABLE IF EXISTS `coursedescription`;
CREATE TABLE IF NOT EXISTS `coursedescription` (
  `courseNumber` varchar(15) NOT NULL,
  `courseTitle` varchar(15) NOT NULL,
  PRIMARY KEY (`courseNumber`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `studentcourses`
--

DROP TABLE IF EXISTS `studentcourses`;
CREATE TABLE IF NOT EXISTS `studentcourses` (
  `studentID` int(10) UNSIGNED NOT NULL,
  `courseNumber` varchar(15) NOT NULL,
  `semesterTaken` varchar(6) NOT NULL,
  `year` int(4) NOT NULL,
  `grade` int(3) NOT NULL,
  PRIMARY KEY (`studentID`),
  KEY `courseNumber` (`courseNumber`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `studentinfo`
--

DROP TABLE IF EXISTS `studentinfo`;
CREATE TABLE IF NOT EXISTS `studentinfo` (
  `studentID` int(10) UNSIGNED NOT NULL,
  `lastName` varchar(25) NOT NULL,
  `firstName` varchar(25) NOT NULL,
  `address` tinytext,
  `email` varchar(50) NOT NULL,
  `startingYear` int(4) UNSIGNED NOT NULL,
  `graduationYear` int(4) UNSIGNED NOT NULL,
  `graduationFlag` tinyint(1) NOT NULL,
  `studentOption` varchar(10) NOT NULL,
  `comments` text,
  PRIMARY KEY (`studentID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `studentinfo`
--

INSERT INTO `studentinfo` (`studentID`, `lastName`, `firstName`, `address`, `email`, `startingYear`, `graduationYear`, `graduationFlag`, `studentOption`, `comments`) VALUES
(201703130, 'asd', 'aaaaa', 'sads', 'asdd@mail.aub.edu', 2016, 2020, 1, 'courses', 'adasd'),
(201703125, 'aaaa', 'aaaaa', 'aaaaa', 'aaaa@mail.aub.edu', 2015, 2020, 0, 'project', 'aaa'),
(201703126, 'bbbbbb', 'bbbbbb', 'bbbbbb', 'bbbbbb@mail.aub.edu', 2014, 2020, 0, 'courses', 'bbbbbbbbb'),
(201703127, 'cccccccc', 'cccccccc', 'ccccccccccc', 'ccccc@mail.aub.edu', 2016, 2020, 0, 'courses', 'cccccccccccc'),
(201702222, 'TEST', 'TEST', 'TEST', 'TEST@mail.aub.edu', 2014, 2018, 1, 'thesis', 'TEST');

-- --------------------------------------------------------

--
-- Table structure for table `studentthesisproject`
--

DROP TABLE IF EXISTS `studentthesisproject`;
CREATE TABLE IF NOT EXISTS `studentthesisproject` (
  `studentID` int(10) UNSIGNED NOT NULL,
  `title` tinytext NOT NULL,
  `advisor` varchar(20) NOT NULL,
  `member1` varchar(20) DEFAULT NULL,
  `member2` varchar(20) DEFAULT NULL,
  `comments` text,
  PRIMARY KEY (`studentID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
