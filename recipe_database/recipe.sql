-- phpMyAdmin SQL Dump
-- version 4.7.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Mar 16, 2018 at 10:48 AM
-- Server version: 5.7.21-0ubuntu0.16.04.1
-- PHP Version: 7.0.25-0ubuntu0.16.04.1

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
  `quantity` int(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ingredients_table`
--

INSERT INTO `ingredients_table` (`ingredient_id`, `recipe_id`, `ingredient_name`, `quantity`) VALUES
(1, 1, 'Maggie', 1),
(2, 1, 'tamato', 2);

-- --------------------------------------------------------

--
-- Table structure for table `recipe_steps`
--

CREATE TABLE `recipe_steps` (
  `step_id` int(20) NOT NULL,
  `recipe_id` int(20) NOT NULL,
  `step_number` int(20) NOT NULL,
  `step_description` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `recipe_steps`
--

INSERT INTO `recipe_steps` (`step_id`, `recipe_id`, `step_number`, `step_description`) VALUES
(1, 1, 1, 'chop tomato and carrot cabbage in equal portion.'),
(2, 1, 2, 'put a pan on gas and pour 250 ml of water in it.'),
(3, 1, 3, 'heat for 2 mins and boil the water.'),
(4, 1, 4, 'put Maggie and maggie masala.it it \r\nboil for 2 mins  ');

-- --------------------------------------------------------

--
-- Table structure for table `recipe_table`
--

CREATE TABLE `recipe_table` (
  `recipe_id` int(20) NOT NULL,
  `u_id` int(20) NOT NULL,
  `recipe_name` varchar(50) NOT NULL,
  `recipe_description` varchar(500) NOT NULL,
  `prep_time` time(6) NOT NULL,
  `cook_time` time(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `recipe_table`
--

INSERT INTO `recipe_table` (`recipe_id`, `u_id`, `recipe_name`, `recipe_description`, `prep_time`, `cook_time`) VALUES
(1, 1, 'maggie', '2 min food', '00:10:00.000000', '00:10:00.000000');

-- --------------------------------------------------------

--
-- Table structure for table `user_table`
--

CREATE TABLE `user_table` (
  `u_id` int(20) NOT NULL,
  `u_name` varchar(20) NOT NULL,
  `u_email` varchar(20) NOT NULL,
  `u_password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_table`
--

INSERT INTO `user_table` (`u_id`, `u_name`, `u_email`, `u_password`) VALUES
(1, 'dummy', 'd@d.com', '123456');

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
  ADD KEY `recipe_id` (`recipe_id`);

--
-- Indexes for table `recipe_table`
--
ALTER TABLE `recipe_table`
  ADD PRIMARY KEY (`recipe_id`),
  ADD KEY `u_id` (`u_id`);

--
-- Indexes for table `user_table`
--
ALTER TABLE `user_table`
  ADD PRIMARY KEY (`u_id`);

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
  MODIFY `step_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `recipe_table`
--
ALTER TABLE `recipe_table`
  MODIFY `recipe_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `user_table`
--
ALTER TABLE `user_table`
  MODIFY `u_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `ingredients_table`
--
ALTER TABLE `ingredients_table`
  ADD CONSTRAINT `ingredients_table_ibfk_1` FOREIGN KEY (`recipe_id`) REFERENCES `ingredients_table` (`ingredient_id`);

--
-- Constraints for table `recipe_steps`
--
ALTER TABLE `recipe_steps`
  ADD CONSTRAINT `recipe_steps_ibfk_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipe_steps` (`step_id`);

--
-- Constraints for table `recipe_table`
--
ALTER TABLE `recipe_table`
  ADD CONSTRAINT `recipe_table_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `recipe_table` (`recipe_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
