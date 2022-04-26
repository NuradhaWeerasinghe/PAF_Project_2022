-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 26, 2022 at 06:58 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `notification_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `interruptions`
--

CREATE TABLE `interruptions` (
  `Interruption_id` int(100) NOT NULL,
  `subject` varchar(100) NOT NULL,
  `description` varchar(100) NOT NULL,
  `area` varchar(100) NOT NULL,
  `time` varchar(100) NOT NULL,
  `date` varchar(100) NOT NULL,
  `created_date` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `interruptions`
--

INSERT INTO `interruptions` (`Interruption_id`, `subject`, `description`, `area`, `time`, `date`, `created_date`) VALUES
(3, 'Interruption notice', 'Interruption on the said date', 'Godigamuwa', '01.00p.m. - 05.00p.m.', '2022/05/02', '2022/04/30'),
(4, 'Interuption Notice', 'There will be a shedueled maintainance in the relevant area. ', 'Boralesgamuwa', '2.30P.M. - 5.30P.M.', '2022/05/03', '2021/04/30'),
(6, 'Interuption Notice', 'There will be a shedueled maintainance in the relevant area. ', 'Pannipitiya', '2.30P.M. - 5.30P.M.', '2022/05/03', '2021/04/30'),
(7, 'Interuption Notice', 'There will be a shedueled maintainance in the relevant area. ', 'Nawinna', '2.30P.M. - 5.30P.M.', '2022/05/10', '2021/05/02');

-- --------------------------------------------------------

--
-- Table structure for table `promotions`
--

CREATE TABLE `promotions` (
  `PromotionId` int(11) NOT NULL,
  `subject` varchar(100) NOT NULL,
  `description` varchar(100) NOT NULL,
  `fromDate` varchar(100) NOT NULL,
  `toDate` varchar(100) NOT NULL,
  `conditions` varchar(100) NOT NULL,
  `createdDate` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `promotions`
--

INSERT INTO `promotions` (`PromotionId`, `subject`, `description`, `fromDate`, `toDate`, `conditions`, `createdDate`) VALUES
(4, 'Promotion Notice', 'There will be 5% discount for cash payments', '2022/05/01', '2022/05/25', 'Amount must be greater than 5000 LKR', '2022/04/26'),
(7, 'Promotion Notice', 'There will be 3% discount for online payments', '2022/04/25', '2022/05/25', 'Amount must be greater than 2000lkr', '2022/04/26'),
(8, 'Promotion Notice', 'There will be 8% discount for online payments', '2022/04/25', '2022/05/25', 'Amount must be greater than 2000lkr', '2022/04/26'),
(9, 'Promotion Notice', 'There will be a 8% discount for HNB and BOC credit card payments', '2022/05/01', '2022/05/30', 'All payments must be done by credit cards', '2011/04/20');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `interruptions`
--
ALTER TABLE `interruptions`
  ADD PRIMARY KEY (`Interruption_id`);

--
-- Indexes for table `promotions`
--
ALTER TABLE `promotions`
  ADD PRIMARY KEY (`PromotionId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `interruptions`
--
ALTER TABLE `interruptions`
  MODIFY `Interruption_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `promotions`
--
ALTER TABLE `promotions`
  MODIFY `PromotionId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
