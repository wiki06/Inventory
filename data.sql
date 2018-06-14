-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.60 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.5.0.5278
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for inventory
DROP DATABASE IF EXISTS `inventory`;
CREATE DATABASE IF NOT EXISTS `inventory` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `inventory`;

-- Dumping structure for table inventory.categories
DROP TABLE IF EXISTS `categories`;
CREATE TABLE IF NOT EXISTS `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(100) NOT NULL,
  `description` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Dumping data for table inventory.categories: ~5 rows (approximately)
DELETE FROM `categories`;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` (`id`, `type`, `description`) VALUES
	(1, 'Snacks', 'Eatable Items'),
	(2, 'Cosmetics', 'Body use, Body spray, Deodrant, Hair Oil'),
	(3, 'Eatable Food', 'Masala, Grains, Cereals, Rice, Groceries'),
	(4, 'Cool Drink', 'Pepsi, Coke, Sprite, Bovonto'),
	(5, 'Fruits', 'Apple, Orange, Lemon');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;

-- Dumping structure for table inventory.employees
DROP TABLE IF EXISTS `employees`;
CREATE TABLE IF NOT EXISTS `employees` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(100) NOT NULL,
  `lastname` varchar(100) NOT NULL,
  `username` varchar(40) NOT NULL,
  `password` varchar(100) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `address` varchar(200) NOT NULL,
  `type` enum('admin','employee') NOT NULL DEFAULT 'employee',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table inventory.employees: ~2 rows (approximately)
DELETE FROM `employees`;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` (`id`, `firstname`, `lastname`, `username`, `password`, `phone`, `address`, `type`) VALUES
	(1, 'admin', 'admin', 'admin', 'password', '0099887766', 'TN', 'admin'),
	(2, 'user', 'user', 'user', 'password', '123456789', 'TN', 'employee');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;

-- Dumping structure for table inventory.invoices
DROP TABLE IF EXISTS `invoices`;
CREATE TABLE IF NOT EXISTS `invoices` (
  `id` varchar(13) NOT NULL,
  `employeeId` int(11) DEFAULT NULL,
  `total` double NOT NULL,
  `cgst` double NOT NULL,
  `sgst` double NOT NULL,
  `discount` double NOT NULL,
  `payable` double NOT NULL,
  `paid` double NOT NULL,
  `returned` double NOT NULL,
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table inventory.invoices: ~13 rows (approximately)
DELETE FROM `invoices`;
/*!40000 ALTER TABLE `invoices` DISABLE KEYS */;
INSERT INTO `invoices` (`id`, `employeeId`, `total`, `cgst`, `sgst`, `discount`, `payable`, `paid`, `returned`, `datetime`) VALUES
	('201801010001', NULL, 1040, 0, 0, 0, 1040, 0, 1040, '2018-06-14 09:54:24'),
	('201801010002', NULL, 106431.3, 0, 0, 0, 106431.3, 0, 106431.3, '2018-06-14 10:18:11'),
	('201801010003', NULL, 116335.2, 0, 0, 0, 116335.2, 0, 116335.2, '2018-06-14 10:29:27'),
	('201801010004', NULL, 7155, 0, 0, 0, 7155, 0, 7155, '2018-06-14 10:45:07'),
	('201801010005', NULL, 7155, 0, 0, 0, 7155, 0, 7155, '2018-06-14 10:45:13'),
	('201801010006', NULL, 76992.3, 0, 0, 0, 76992.3, 0, 76992.3, '2018-06-14 10:49:52'),
	('201801010007', NULL, 218.4, 0, 0, 0, 218.4, 0, 218.4, '2018-06-14 10:51:56'),
	('201801010008', NULL, 156, 0, 0, 0, 156, 0, 156, '2018-06-14 10:54:39'),
	('201801010009', NULL, 57.2, 0, 0, 0, 57.2, 0, 57.2, '2018-06-14 10:55:35'),
	('201801010010', NULL, 286, 0, 0, 0, 286, 0, 286, '2018-06-14 10:56:16'),
	('201801010011', NULL, 972.8399999999999, 0, 0, 0, 972.8399999999999, 0, 972.8399999999999, '2018-06-14 11:05:35'),
	('201801010012', NULL, 972.8399999999999, 0, 0, 0, 972.8399999999999, 0, 972.8399999999999, '2018-06-14 11:05:38'),
	('201801010013', NULL, 6406, 0, 0, 0, 6406, 0, 6406, '2018-06-14 11:06:28');
/*!40000 ALTER TABLE `invoices` ENABLE KEYS */;

-- Dumping structure for table inventory.products
DROP TABLE IF EXISTS `products`;
CREATE TABLE IF NOT EXISTS `products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categoryId` int(11) NOT NULL,
  `supplierId` int(11) NOT NULL DEFAULT '0',
  `name` varchar(100) NOT NULL,
  `hsncode` varchar(50) NOT NULL,
  `price` double NOT NULL,
  `quantity` double NOT NULL,
  `description` varchar(200) NOT NULL,
  `cgst` double NOT NULL,
  `sgst` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `categoryId` (`categoryId`),
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`categoryId`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

-- Dumping data for table inventory.products: ~17 rows (approximately)
DELETE FROM `products`;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` (`id`, `categoryId`, `supplierId`, `name`, `hsncode`, `price`, `quantity`, `description`, `cgst`, `sgst`) VALUES
	(14, 1, 0, 'Good Day 100g', 'HSN001', 10, 100, '100g packet', 2, 2),
	(15, 2, 0, 'Hamam soap', 'HM001', 30, 100, '100g', 2, 2),
	(16, 2, 0, 'Pears soap', 'PR001', 45, 200, '200g', 2, 2),
	(17, 2, 0, 'Gokul Sandal Powder', 'GK001', 90, 65, '200g ', 3, 3),
	(18, 4, 0, 'Pepsi', 'PP001', 70, 222, '600ml', 2, 2),
	(19, 5, 0, 'Apple', 'AP001', 50, 93, '1 kg', 2, 2),
	(20, 3, 0, 'Moong Dhall', 'MH001', 30, 88, '20g', 2, 2),
	(21, 2, 0, 'Fogg body spray 250ml', 'FB001', 9999, 24, '250ml', 5, 5),
	(22, 2, 0, 'Lifeboy soap', 'LB001', 99, 300, '200g ', 3, 3),
	(23, 1, 0, 'Dairy milk 20g', 'DM001', 55, 32, '20g', 2, 2),
	(24, 1, 0, 'Dairy milk 40g', 'DM001', 95, 50, '40g', 2, 2),
	(25, 2, 0, 'Lifeboy soap', 'LB001', 75, 300, '150g', 3, 3),
	(26, 2, 0, 'Fogg body spray 50ml', 'FB001', 2999, 31, '50ml', 5, 5),
	(27, 4, 0, 'Pepsi', 'PP001', 50, 222, '200ml', 2, 2),
	(28, 2, 0, 'Dove cream', 'GK001', 259, 50, '200g ', 3, 3),
	(29, 2, 0, 'Medimix', 'PR001', 85, 200, '100g', 2, 2),
	(30, 1, 0, 'Good Day 700g', 'HSN001', 100, 100, '700g packet', 2, 2);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;

-- Dumping structure for table inventory.purchases
DROP TABLE IF EXISTS `purchases`;
CREATE TABLE IF NOT EXISTS `purchases` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productId` int(11) NOT NULL,
  `supplierId` int(11) NOT NULL,
  `quantity` double NOT NULL,
  `price` double NOT NULL,
  `total` double NOT NULL,
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `productId` (`productId`),
  CONSTRAINT `purchases_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping data for table inventory.purchases: ~0 rows (approximately)
DELETE FROM `purchases`;
/*!40000 ALTER TABLE `purchases` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchases` ENABLE KEYS */;

-- Dumping structure for table inventory.sales
DROP TABLE IF EXISTS `sales`;
CREATE TABLE IF NOT EXISTS `sales` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `invoiceId` varchar(13) NOT NULL,
  `productId` int(11) NOT NULL,
  `quantity` double NOT NULL,
  `price` int(11) NOT NULL,
  `total` double NOT NULL,
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `productId` (`productId`),
  KEY `invoiceId` (`invoiceId`),
  CONSTRAINT `sales_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `products` (`id`),
  CONSTRAINT `sales_ibfk_2` FOREIGN KEY (`invoiceId`) REFERENCES `invoices` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=225 DEFAULT CHARSET=latin1;

-- Dumping data for table inventory.sales: ~14 rows (approximately)
DELETE FROM `sales`;
/*!40000 ALTER TABLE `sales` DISABLE KEYS */;
INSERT INTO `sales` (`id`, `invoiceId`, `productId`, `quantity`, `price`, `total`, `datetime`) VALUES
	(211, '201801010001', 14, 100, 10, 1040, '2018-06-14 09:54:24'),
	(212, '201801010002', 21, 9, 9999, 98990.1, '2018-06-14 10:18:11'),
	(213, '201801010002', 17, 78, 90, 7441.2, '2018-06-14 10:18:11'),
	(214, '201801010003', 21, 10, 9999, 109989, '2018-06-14 10:29:27'),
	(215, '201801010003', 17, 7, 90, 667.8, '2018-06-14 10:29:27'),
	(216, '201801010003', 18, 78, 70, 5678.4, '2018-06-14 10:29:27'),
	(217, '201801010006', 21, 7, 9999, 76992.3, '2018-06-14 10:49:53'),
	(218, '201801010007', 20, 7, 30, 218.4, '2018-06-14 10:51:56'),
	(219, '201801010008', 20, 5, 30, 156, '2018-06-14 10:54:40'),
	(220, '201801010009', 23, 1, 55, 57.2, '2018-06-14 10:55:35'),
	(221, '201801010010', 23, 5, 55, 286, '2018-06-14 10:56:16'),
	(222, '201801010011', 23, 6, 55, 343.2, '2018-06-14 11:05:35'),
	(223, '201801010012', 23, 6, 55, 343.2, '2018-06-14 11:05:38'),
	(224, '201801010013', 19, 7, 50, 364, '2018-06-14 11:06:28');
/*!40000 ALTER TABLE `sales` ENABLE KEYS */;

-- Dumping structure for table inventory.suppliers
DROP TABLE IF EXISTS `suppliers`;
CREATE TABLE IF NOT EXISTS `suppliers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `address` varchar(200) NOT NULL,
  `saddress` varchar(200) NOT NULL,
  `gstnnumber` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table inventory.suppliers: ~3 rows (approximately)
DELETE FROM `suppliers`;
/*!40000 ALTER TABLE `suppliers` DISABLE KEYS */;
INSERT INTO `suppliers` (`id`, `name`, `phone`, `address`, `saddress`, `gstnnumber`) VALUES
	(1, 'Viveks', '9940038030', 'No 260 VOC Street TV Nagar Thirumangalam Chennai 600 040', 'No 260 VOC Street TV Nagar Thirumangalam Chennai 600 049', '1213435'),
	(2, 'Techno Soft', '8015409745', 'No 260 VOC Street TV Nagar Thirumangalam Chennai 600 040', 'No 260 VOC Street TV Nagar Thirumangalam Chennai 600 049', '3232223'),
	(3, 'ABC company', '565454544', 'No 261 VOC Street TV Nagar Thirumangalam Chennai, 600 049', 'No 265 VOC Street TV Nagar Thirumangalam Chennai 600 067', '987654321');
/*!40000 ALTER TABLE `suppliers` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
