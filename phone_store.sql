-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 06, 2025 lúc 04:48 AM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `phone_store`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customers`
--

CREATE TABLE `customers` (
  `CustomerID` int(11) NOT NULL,
  `FullName` varchar(100) NOT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `CreateDate` datetime NOT NULL DEFAULT current_timestamp(),
  `Gender` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `customers`
--

INSERT INTO `customers` (`CustomerID`, `FullName`, `Phone`, `Email`, `CreateDate`, `Gender`) VALUES
(1, 'Trần Hạnh Hương', '0878886423', 'huong@gmail.com', '2025-05-06 02:57:18', 'Nữ'),
(2, 'Nguyễn Xuân Mạnh', '0888664578', 'manh@gmail.com', '2025-05-06 09:47:41', 'Nam');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `employees`
--

CREATE TABLE `employees` (
  `EmployeeID` int(11) NOT NULL,
  `Username` varchar(50) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Phone` varchar(20) NOT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `JoinDate` date DEFAULT curdate(),
  `Address` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `employees`
--

INSERT INTO `employees` (`EmployeeID`, `Username`, `Password`, `Phone`, `Email`, `JoinDate`, `Address`) VALUES
(14, 'Hiu', '123123123', '0878985119', 'hiu@gmail.com', '2025-05-06', '36 Lê Lợi');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `products`
--

CREATE TABLE `products` (
  `ProductID` int(11) NOT NULL,
  `ProductName` varchar(255) NOT NULL,
  `Type` varchar(100) DEFAULT NULL,
  `Brand` varchar(100) DEFAULT NULL,
  `Stock` int(11) DEFAULT 0,
  `Status` varchar(255) DEFAULT NULL,
  `Prices` decimal(14,2) NOT NULL,
  `Date` date DEFAULT curdate(),
  `Image` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `products`
--

INSERT INTO `products` (`ProductID`, `ProductName`, `Type`, `Brand`, `Stock`, `Status`, `Prices`, `Date`, `Image`) VALUES
(1, 'iphone 6', 'Ios', 'Iphone', 117, 'Còn Hàng', 4200000.00, '2025-05-03', 'C:\\Users\\HELIOS 300\\OneDrive\\ドキュメント\\GitHub\\Web_ban_game\\admin\\assets\\icon\\MT3H.png'),
(2, 'realme neo 4', 'Android', 'Realme', 116, 'Còn Hàng', 1350000.00, '2025-05-04', 'C:\\Users\\HELIOS 300\\OneDrive\\ドキュメント\\GitHub\\Web_ban_game\\admin\\assets\\icon\\MT3H.png');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `purchaseinvoicedetails`
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
-- Cấu trúc bảng cho bảng `purchaseinvoices`
--

CREATE TABLE `purchaseinvoices` (
  `PurchaseID` int(11) NOT NULL,
  `SupplierID` int(11) DEFAULT NULL,
  `EmployeeID` int(11) DEFAULT NULL,
  `PurchaseDate` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `salesinvoicedetails`
--

CREATE TABLE `salesinvoicedetails` (
  `DetailID` int(11) NOT NULL,
  `InvoiceID` int(11) DEFAULT NULL,
  `ProductID` int(11) DEFAULT NULL,
  `Quantity` int(11) NOT NULL,
  `Prices` decimal(15,2) NOT NULL,
  `TotalPrices` decimal(15,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `salesinvoicedetails`
--

INSERT INTO `salesinvoicedetails` (`DetailID`, `InvoiceID`, `ProductID`, `Quantity`, `Prices`, `TotalPrices`) VALUES
(1, 2, 1, 1, 4200000.00, 4200000.00),
(2, 3, 1, 1, 4200000.00, 6900000.00),
(3, 3, 2, 2, 2700000.00, 6900000.00);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `salesinvoices`
--

CREATE TABLE `salesinvoices` (
  `InvoiceID` int(11) NOT NULL,
  `CustomerID` int(11) DEFAULT NULL,
  `EmployeeID` int(11) DEFAULT NULL,
  `SaleDate` datetime DEFAULT current_timestamp(),
  `TotalAmount` decimal(15,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `salesinvoices`
--

INSERT INTO `salesinvoices` (`InvoiceID`, `CustomerID`, `EmployeeID`, `SaleDate`, `TotalAmount`) VALUES
(1, 1, 14, '2025-05-06 09:43:28', 4200000.00),
(2, 1, 14, '2025-05-06 09:46:18', 4200000.00),
(3, 2, 14, '2025-05-06 09:48:06', 6900000.00);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `suppliers`
--

CREATE TABLE `suppliers` (
  `SupplierID` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`CustomerID`);

--
-- Chỉ mục cho bảng `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`EmployeeID`);

--
-- Chỉ mục cho bảng `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`ProductID`);

--
-- Chỉ mục cho bảng `purchaseinvoicedetails`
--
ALTER TABLE `purchaseinvoicedetails`
  ADD PRIMARY KEY (`DetailID`),
  ADD KEY `PurchaseID` (`PurchaseID`),
  ADD KEY `ProductID` (`ProductID`);

--
-- Chỉ mục cho bảng `purchaseinvoices`
--
ALTER TABLE `purchaseinvoices`
  ADD PRIMARY KEY (`PurchaseID`),
  ADD KEY `SupplierID` (`SupplierID`),
  ADD KEY `EmployeeID` (`EmployeeID`);

--
-- Chỉ mục cho bảng `salesinvoicedetails`
--
ALTER TABLE `salesinvoicedetails`
  ADD PRIMARY KEY (`DetailID`),
  ADD KEY `InvoiceID` (`InvoiceID`),
  ADD KEY `ProductID` (`ProductID`);

--
-- Chỉ mục cho bảng `salesinvoices`
--
ALTER TABLE `salesinvoices`
  ADD PRIMARY KEY (`InvoiceID`),
  ADD KEY `CustomerID` (`CustomerID`),
  ADD KEY `EmployeeID` (`EmployeeID`);

--
-- Chỉ mục cho bảng `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`SupplierID`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `customers`
--
ALTER TABLE `customers`
  MODIFY `CustomerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `employees`
--
ALTER TABLE `employees`
  MODIFY `EmployeeID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT cho bảng `products`
--
ALTER TABLE `products`
  MODIFY `ProductID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21313;

--
-- AUTO_INCREMENT cho bảng `salesinvoicedetails`
--
ALTER TABLE `salesinvoicedetails`
  MODIFY `DetailID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `salesinvoices`
--
ALTER TABLE `salesinvoices`
  MODIFY `InvoiceID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `purchaseinvoicedetails`
--
ALTER TABLE `purchaseinvoicedetails`
  ADD CONSTRAINT `fk_purchaseinvoicedetails_product` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_purchaseinvoicedetails_purchase` FOREIGN KEY (`PurchaseID`) REFERENCES `purchaseinvoices` (`PurchaseID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `purchaseinvoices`
--
ALTER TABLE `purchaseinvoices`
  ADD CONSTRAINT `fk_purchaseinvoices_employee` FOREIGN KEY (`EmployeeID`) REFERENCES `employees` (`EmployeeID`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_purchaseinvoices_supplier` FOREIGN KEY (`SupplierID`) REFERENCES `suppliers` (`SupplierID`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `salesinvoicedetails`
--
ALTER TABLE `salesinvoicedetails`
  ADD CONSTRAINT `fk_salesinvoicedetails_invoice` FOREIGN KEY (`InvoiceID`) REFERENCES `salesinvoices` (`InvoiceID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_salesinvoicedetails_product` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `salesinvoices`
--
ALTER TABLE `salesinvoices`
  ADD CONSTRAINT `fk_salesinvoices_customer` FOREIGN KEY (`CustomerID`) REFERENCES `customers` (`CustomerID`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_salesinvoices_employee` FOREIGN KEY (`EmployeeID`) REFERENCES `employees` (`EmployeeID`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
