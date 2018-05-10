-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 04, 2018 at 06:20 AM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `recipe`
--

-- --------------------------------------------------------

--
-- Table structure for table `ingredients_table`
--

CREATE TABLE `ingredients_table` (
  `ingredient_id` int(20) NOT NULL,
  `recipe_id` int(20) NOT NULL,
  `ingredient_name` varchar(40) NOT NULL,
  `quantity` int(40) NOT NULL,
  `unit` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ingredients_table`
--

INSERT INTO `ingredients_table` (`ingredient_id`, `recipe_id`, `ingredient_name`, `quantity`, `unit`) VALUES
(1, 1, 'Maggie', 1, ''),
(2, 1, 'tamato', 2, '');

-- --------------------------------------------------------

--
-- Table structure for table `recipe_steps`
--

CREATE TABLE `recipe_steps` (
  `step_id` int(20) NOT NULL,
  `recipe_id` int(20) NOT NULL,
  `step_number` int(20) DEFAULT NULL,
  `step_description` varchar(500) DEFAULT NULL,
  `step_time` int(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `recipe_steps`
--

INSERT INTO `recipe_steps` (`step_id`, `recipe_id`, `step_number`, `step_description`, `step_time`) VALUES
(1, 4, 1, 'step1', 10);

-- --------------------------------------------------------

--
-- Table structure for table `recipe_table`
--

CREATE TABLE `recipe_table` (
  `recipe_id` int(20) NOT NULL,
  `recipe_name` varchar(50) NOT NULL,
  `recipe_description` varchar(500) NOT NULL,
  `prep_time` int(6) DEFAULT NULL,
  `cook_time` int(6) DEFAULT NULL,
  `image_url` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `recipe_table`
--

INSERT INTO `recipe_table` (`recipe_id`, `recipe_name`, `recipe_description`, `prep_time`, `cook_time`, `image_url`) VALUES
(1, 'mmm', 'mmmmm', 20, 16, 0x696d616765732f6d6d6d2e6a7067),
(2, 'Maggie', 'Maggie', 10, 2, 0x696d616765732f4d6167676965),
(3, 'magie2', 'maggie2', 12, 5, 0x696d616765732f6d61676965322e6a7067),
(4, 'hj', 'uho', 8, 8, 0x696d616765732f686a2e6a7067),
(5, 'jim', 'jguyttefxvcbbhkjuuifc', 30, 40, 0x696d616765732f6a696d2e6a7067),
(6, 'qwygydgiaan', 'lkjuhytredfcvgbnm,', 30, 12, 0x696d616765732f717779677964676961616e2e6a7067);

-- --------------------------------------------------------

--
-- Table structure for table `user_table`
--

CREATE TABLE `user_table` (
  `id` int(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_table`
--

INSERT INTO `user_table` (`id`, `username`, `email`, `password`, `created_at`, `updated_at`) VALUES
(1, 'dummy', 'd@d.com', '123456', '0000-00-00 00:00:00', NULL),
(2, 'kanu1234', 'pkanu95@gmail.com', 'kanu1234', '2018-03-24 11:11:49', '2018-03-24 11:11:49'),
(3, 'dimpi234', 'd@gmail.com', 'dimpi123', '2018-03-26 15:40:14', '2018-03-26 15:40:14'),
(4, 'pkq12', 'pkq@yugs.com', 'pkq1234', '2018-04-02 14:31:58', '2018-04-02 14:31:58'),
(5, 'hgydrs123', 'pkq@gjhg.com', 'jjguydrc', '2018-04-02 14:48:20', '2018-04-02 14:48:20');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ingredients_table`
--
ALTER TABLE `ingredients_table`
  ADD PRIMARY KEY (`ingredient_id`),
  ADD KEY `recipe_id` (`recipe_id`);

--
-- Indexes for table `recipe_steps`
--
ALTER TABLE `recipe_steps`
  ADD PRIMARY KEY (`step_id`),
  ADD UNIQUE KEY `step_id` (`step_id`,`recipe_id`,`step_number`,`step_description`,`step_time`),
  ADD KEY `recipe_id` (`recipe_id`);

--
-- Indexes for table `recipe_table`
--
ALTER TABLE `recipe_table`
  ADD PRIMARY KEY (`recipe_id`),
  ADD UNIQUE KEY `recipe_name` (`recipe_name`);

--
-- Indexes for table `user_table`
--
ALTER TABLE `user_table`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ingredients_table`
--
ALTER TABLE `ingredients_table`
  MODIFY `ingredient_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `recipe_steps`
--
ALTER TABLE `recipe_steps`
  MODIFY `step_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `recipe_table`
--
ALTER TABLE `recipe_table`
  MODIFY `recipe_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `user_table`
--
ALTER TABLE `user_table`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `ingredients_table`
--
ALTER TABLE `ingredients_table`
  ADD CONSTRAINT `ingredients_table_ibfk_1` FOREIGN KEY (`recipe_id`) REFERENCES `ingredients_table` (`ingredient_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
