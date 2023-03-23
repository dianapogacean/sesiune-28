-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: db
-- Generation Time: Mar 23, 2023 at 04:15 PM
-- Server version: 8.0.32
-- PHP Version: 8.0.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `university_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `courses`
--

CREATE TABLE `courses` (
  `id` int NOT NULL,
  `name` varchar(40) NOT NULL,
  `details` varchar(40) NOT NULL,
  `id_teacher` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`id`, `name`, `details`, `id_teacher`) VALUES
(1, 'java', 'java', 1),
(2, 'react', 'react', 1),
(3, 'react1', 'react1', 1),
(4, 'java1', 'java1', 1),
(5, 'java3', 'java3', 1),
(6, 'react3', 'react3', 3);

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `id` int NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `id_details` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`id`, `name`, `email`, `id_details`) VALUES
(1, 'name1', 'email1', 1),
(2, 'name2', 'email2', 2),
(3, 'hhhh', 'hhhh', 10),
(4, 'lkjh', 'iiiii', 12),
(5, 'mimi', 'mimi', 15),
(6, 'mnmn', 'mnmn', 16),
(7, 'bbb', 'bbb', 17),
(8, 'yup', 'yup', 18);

-- --------------------------------------------------------

--
-- Table structure for table `students_courses`
--

CREATE TABLE `students_courses` (
  `id_student` int NOT NULL,
  `id_course` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `students_courses`
--

INSERT INTO `students_courses` (`id_student`, `id_course`) VALUES
(7, 1),
(8, 1),
(7, 2),
(8, 2);

-- --------------------------------------------------------

--
-- Table structure for table `student_details`
--

CREATE TABLE `student_details` (
  `id` int NOT NULL,
  `college` varchar(50) NOT NULL,
  `date_enrolled` date NOT NULL,
  `nb_courses_enrolled` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `student_details`
--

INSERT INTO `student_details` (`id`, `college`, `date_enrolled`, `nb_courses_enrolled`) VALUES
(1, 'college', '2023-03-02', 0),
(2, 'college2', '2023-03-02', 0),
(3, 'mycollege', '2023-03-17', 10),
(4, 'ddddd', '2023-03-17', 10),
(6, 'abc', '2023-03-17', 10),
(7, 'abc', '2023-03-17', 10),
(8, 'anbc', '2023-03-17', 10),
(9, '', '2023-03-17', 1),
(10, 'hhhh', '2023-03-17', 4),
(12, 'popo', '2023-03-20', 50),
(13, 'yyyy', '2023-03-20', 66),
(14, 'pppp', '2023-03-20', 33),
(15, 'mimi', '2023-03-20', 10),
(16, 'mnmn', '2023-03-20', 5),
(17, 'bbb', '2023-03-20', 55),
(18, 'yup', '2023-03-20', 5);

-- --------------------------------------------------------

--
-- Table structure for table `teachers`
--

CREATE TABLE `teachers` (
  `id` int NOT NULL,
  `name` varchar(40) NOT NULL,
  `email` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `teachers`
--

INSERT INTO `teachers` (`id`, `name`, `email`) VALUES
(1, 'teacher1', 'email22'),
(3, 'teacher3', 'email33'),
(4, 'teacher3', 'email33'),
(7, 'teacher4', 'email44');

-- --------------------------------------------------------

--
-- Table structure for table `teacher_details`
--

CREATE TABLE `teacher_details` (
  `id` int NOT NULL,
  `college` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  `nb_classes` int NOT NULL,
  `subject` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `teacher_details`
--

INSERT INTO `teacher_details` (`id`, `college`, `address`, `nb_classes`, `subject`) VALUES
(1, 'college1', 'address1', 3, ''),
(7, 'college4', 'address4', 0, '');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int NOT NULL,
  `first_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `age` int NOT NULL,
  `birth_date` date NOT NULL,
  `active` tinyint(1) NOT NULL,
  `last_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `first_name`, `age`, `birth_date`, `active`, `last_name`) VALUES
(1, 'John', 33, '1990-01-01', 1, 'NEW LAST NAME'),
(2, 'Jane', 31, '1992-02-02', 0, 'Doe'),
(3, 'Alice', 38, '1985-03-03', 1, 'Smith'),
(4, 'Bob', 44, '1978-04-04', 1, 'Johnson'),
(5, 'Charlie', 27, '1995-05-05', 0, 'Brown'),
(6, 'David', 36, '1986-06-06', 1, 'Lee'),
(7, 'John', 22, '1980-07-07', 0, 'Wilson'),
(8, 'Frank', 29, '1993-08-08', 1, 'Miller'),
(9, 'Grace', 43, '1979-09-09', 1, 'Davis'),
(10, 'Henry', 31, '1991-10-10', 0, 'Thomas'),
(11, 'Isabella', 38, '1984-11-11', 1, 'Taylor'),
(12, 'Jack', 45, '1977-12-12', 1, 'White'),
(13, 'Kate', 41, '1982-01-13', 0, 'Green'),
(14, 'Liam', 29, '1994-02-14', 1, 'Hall'),
(15, 'John', 36, '1987-03-15', 0, 'Jones'),
(17, 'first name user new', 20, '2023-03-21', 0, 'my new user');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `courses`
--
ALTER TABLE `courses`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_c_t` (`id_teacher`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_stud_stud_details` (`id_details`);

--
-- Indexes for table `students_courses`
--
ALTER TABLE `students_courses`
  ADD PRIMARY KEY (`id_student`,`id_course`),
  ADD KEY `fk_sc_c1` (`id_course`);

--
-- Indexes for table `student_details`
--
ALTER TABLE `student_details`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `teachers`
--
ALTER TABLE `teachers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `teacher_details`
--
ALTER TABLE `teacher_details`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `courses`
--
ALTER TABLE `courses`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `students`
--
ALTER TABLE `students`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `student_details`
--
ALTER TABLE `student_details`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `teachers`
--
ALTER TABLE `teachers`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `courses`
--
ALTER TABLE `courses`
  ADD CONSTRAINT `fk_c_t` FOREIGN KEY (`id_teacher`) REFERENCES `teachers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `students`
--
ALTER TABLE `students`
  ADD CONSTRAINT `fk_stud_stud_details` FOREIGN KEY (`id_details`) REFERENCES `student_details` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `students_courses`
--
ALTER TABLE `students_courses`
  ADD CONSTRAINT `fk_sc_c1` FOREIGN KEY (`id_course`) REFERENCES `courses` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_sc_s1` FOREIGN KEY (`id_student`) REFERENCES `students` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `teacher_details`
--
ALTER TABLE `teacher_details`
  ADD CONSTRAINT `fk_td_t` FOREIGN KEY (`id`) REFERENCES `teachers` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
