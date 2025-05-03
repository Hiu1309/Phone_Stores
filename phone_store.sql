-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 03, 2025 at 03:48 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `phone_store`
--

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `CustomerID` int(11) NOT NULL,
  `FullName` varchar(100) NOT NULL,
  `BirthDate` date DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL,
  `CreatedAt` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `EmployeeID` int(11) NOT NULL,
  `Username` varchar(50) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `FullName` varchar(100) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `Role` enum('admin','staff') DEFAULT 'staff'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`EmployeeID`, `Username`, `Password`, `FullName`, `Phone`, `Role`) VALUES
(1, 'abc', '123123', 'abc', '123', 'staff'),
(2, 'd', '123123', 'f', '123', 'staff'),
(3, 'hason', '123123', 'f', '123', 'staff'),
(4, 'haha', '123123', 'fg', '123', 'staff'),
(5, 'h', '123123', 'hieu', '123123', 'staff');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `ProductID` int(11) NOT NULL,
  `ProductName` varchar(255) NOT NULL,
  `Type` varchar(100) DEFAULT NULL,
  `Brand` varchar(100) DEFAULT NULL,
  `Stock` int(11) DEFAULT 0,
  `Prices` decimal(10,2) NOT NULL,
  `Status` enum('Available','Out of Stock') DEFAULT 'Available',
  `Date` date DEFAULT curdate(),
  `Image` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `purchaseinvoicedetails`
--

CREATE TABLE `purchaseinvoicedetails` (
  `DetailID` int(11) NOT NULL,
  `PurchaseID` int(11) DEFAULT NULL,
  `ProductID` int(11) DEFAULT NULL,
  `Quantity` int(11) NOT NULL,
  `UnitPrice` decimal(15,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `purchaseinvoices`
--

CREATE TABLE `purchaseinvoices` (
  `PurchaseID` int(11) NOT NULL,
  `SupplierID` int(11) DEFAULT NULL,
  `EmployeeID` int(11) DEFAULT NULL,
  `PurchaseDate` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `salesinvoicedetails`
--

CREATE TABLE `salesinvoicedetails` (
  `DetailID` int(11) NOT NULL,
  `InvoiceID` int(11) DEFAULT NULL,
  `ProductID` int(11) DEFAULT NULL,
  `Quantity` int(11) NOT NULL,
  `UnitPrice` decimal(15,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `salesinvoices`
--

CREATE TABLE `salesinvoices` (
  `InvoiceID` int(11) NOT NULL,
  `CustomerID` int(11) DEFAULT NULL,
  `EmployeeID` int(11) DEFAULT NULL,
  `SaleDate` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Stand-in structure for view `salesreport`
-- (See below for the actual view)
--
CREATE TABLE `salesreport` (
`CustomerName` varchar(100)
,`ProductName` varchar(255)
,`SaleDate` datetime
,`EmployeeName` varchar(100)
,`Quantity` int(11)
,`UnitPrice` decimal(15,2)
,`Total` decimal(25,2)
);

-- --------------------------------------------------------

--
-- Table structure for table `suppliers`
--

CREATE TABLE `suppliers` (
  `SupplierID` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure for view `salesreport`
--
DROP TABLE IF EXISTS `salesreport`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `salesreport`  AS SELECT `c`.`FullName` AS `CustomerName`, `p`.`ProductName` AS `ProductName`, `si`.`SaleDate` AS `SaleDate`, `eid`.`FullName` AS `EmployeeName`, `sid`.`Quantity` AS `Quantity`, `sid`.`UnitPrice` AS `UnitPrice`, `sid`.`Quantity`* `sid`.`UnitPrice` AS `Total` FROM ((((`salesinvoices` `si` join `customers` `c` on(`si`.`CustomerID` = `c`.`CustomerID`)) join `employees` `eid` on(`si`.`EmployeeID` = `eid`.`EmployeeID`)) join `salesinvoicedetails` `sid` on(`si`.`InvoiceID` = `sid`.`InvoiceID`)) join `products` `p` on(`sid`.`ProductID` = `p`.`ProductID`)) ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`CustomerID`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`EmployeeID`),
  ADD UNIQUE KEY `Username` (`Username`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`ProductID`);

--
-- Indexes for table `purchaseinvoicedetails`
--
ALTER TABLE `purchaseinvoicedetails`
  ADD PRIMARY KEY (`DetailID`),
  ADD KEY `PurchaseID` (`PurchaseID`),
  ADD KEY `ProductID` (`ProductID`);

--
-- Indexes for table `purchaseinvoices`
--
ALTER TABLE `purchaseinvoices`
  ADD PRIMARY KEY (`PurchaseID`),
  ADD KEY `SupplierID` (`SupplierID`),
  ADD KEY `EmployeeID` (`EmployeeID`);

--
-- Indexes for table `salesinvoicedetails`
--
ALTER TABLE `salesinvoicedetails`
  ADD PRIMARY KEY (`DetailID`),
  ADD KEY `InvoiceID` (`InvoiceID`),
  ADD KEY `ProductID` (`ProductID`);

--
-- Indexes for table `salesinvoices`
--
ALTER TABLE `salesinvoices`
  ADD PRIMARY KEY (`InvoiceID`),
  ADD KEY `CustomerID` (`CustomerID`),
  ADD KEY `EmployeeID` (`EmployeeID`);

--
-- Indexes for table `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`SupplierID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `CustomerID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
  MODIFY `EmployeeID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `ProductID` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
