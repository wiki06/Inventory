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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping data for table inventory.categories: ~4 rows (approximately)
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` (`id`, `type`, `description`) VALUES
	(1, 'Shampoo', 'Hair Product'),
	(2, 'Gel', 'Hair styling'),
	(3, 'Cosmetics', 'Grooming products'),
	(4, 'Food', 'Eatable');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;

-- Dumping structure for table inventory.customer
DROP TABLE IF EXISTS `customer`;
CREATE TABLE IF NOT EXISTS `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `saddress` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table inventory.customer: ~0 rows (approximately)
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;

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
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` (`id`, `firstname`, `lastname`, `username`, `password`, `phone`, `address`, `type`) VALUES
	(1, 'john', 'cena', 'admin', 'password', '0099887766', 'New York, USA', 'admin'),
	(2, 'Martha', 'Jones', 'user', 'password', '123456789', 'Seattle', 'employee');
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

-- Dumping data for table inventory.invoices: ~20 rows (approximately)
/*!40000 ALTER TABLE `invoices` DISABLE KEYS */;
INSERT INTO `invoices` (`id`, `employeeId`, `total`, `cgst`, `sgst`, `discount`, `payable`, `paid`, `returned`, `datetime`) VALUES
	('1491729973342', 2, 760, 19, 0, 5, 774, 800, 26, '2017-01-09 20:56:13'),
	('1491730560516', 2, 370, 9.25, 0, 5, 374.25, 375, 0.75, '2017-01-09 21:06:00'),
	('1492165305284', 2, 270, 6.75, 0, 5, 271.75, 280, 8.25, '2017-01-14 21:51:45'),
	('1492189349464', 2, 490, 12.25, 0, 5, 497.25, 500, 2.75, '2017-02-15 04:32:29'),
	('1492189946488', 2, 190, 4.75, 0, 5, 189.75, 200, 10.25, '2017-02-15 04:42:26'),
	('1492190099626', 2, 120, 3, 0, 5, 118, 120, 2, '2017-04-15 04:44:59'),
	('1492190341116', 2, 65, 1.625, 0, 5, 61.625, 62, 0.375, '2017-04-15 04:49:01'),
	('1492191099328', 2, 190, 4.75, 0, 5, 189.75, 190, 0.25, '2017-04-15 05:01:39'),
	('1492192975710', 2, 770, 19.25, 0, 5, 784.25, 1000, 215.75, '2017-04-15 05:32:55'),
	('1492193361407', 2, 865, 21.625, 0, 5, 881.625, 900, 18.375, '2017-03-15 05:39:21'),
	('1492313070538', 2, 275, 6.875, 0, 5, 276.875, 300, 23.125, '2017-03-16 14:54:30'),
	('1493699328760', 2, 70, 1.75, 0, 5, 66.75, 70, 3.25, '2017-05-02 15:58:48'),
	('1493699482352', 2, 190, 4.75, 0, 5, 189.75, 190, 0.25, '2017-05-02 16:01:22'),
	('1528552067600', NULL, 26.4, 0, 0, 0, 26.4, 26.4, 0, '2018-06-09 06:47:47'),
	('1528552267427', NULL, 184.8, 0, 0, 0, 184.8, 184.8, 0, '2018-06-09 06:51:07'),
	('1528552904119', NULL, 316.8, 0, 0, 0, 316.8, 316.8, 0, '2018-06-09 07:02:16'),
	('1528554546575', NULL, 26.4, 0, 0, 0, 26.4, 26.4, 0, '2018-06-09 07:29:06'),
	('1528556221317', NULL, 26.4, 0, 0, 0, 26.4, 26.4, 0, '2018-06-09 07:57:01'),
	('1528561981364', NULL, 26.4, 0, 0, 0, 26.4, 26.4, 0, '2018-06-09 09:33:01'),
	('1528562288212', NULL, 26.4, 0, 0, 0, 26.4, 26.4, 0, '2018-06-09 09:38:08');
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- Dumping data for table inventory.products: ~3 rows (approximately)
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` (`id`, `categoryId`, `supplierId`, `name`, `hsncode`, `price`, `quantity`, `description`, `cgst`, `sgst`) VALUES
	(10, 4, 0, 'Milk 1 Lit', '56GDF', 24, 176, 'Food & Dairy Product', 5, 5),
	(11, 4, 0, 'Coko', '65GDF', 10, 200, 'Food', 4, 4),
	(12, 2, 0, 'saravana', 'SARAVANA001', 500, 10, 'Beard Oil', 10, 10);
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

-- Dumping data for table inventory.purchases: ~4 rows (approximately)
/*!40000 ALTER TABLE `purchases` DISABLE KEYS */;
INSERT INTO `purchases` (`id`, `productId`, `supplierId`, `quantity`, `price`, `total`, `datetime`) VALUES
	(1, 10, 1, 5, 165, 825, '2017-03-14 05:30:00'),
	(2, 10, 2, 6, 120, 720, '2017-03-09 05:30:00'),
	(3, 10, 1, 1, 24, 24, '2017-05-02 15:32:47'),
	(4, 10, 1, 2, 20, 40, '2017-05-02 15:40:37');
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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;

-- Dumping data for table inventory.sales: ~28 rows (approximately)
/*!40000 ALTER TABLE `sales` DISABLE KEYS */;
INSERT INTO `sales` (`id`, `invoiceId`, `productId`, `quantity`, `price`, `total`, `datetime`) VALUES
	(1, '1491729973342', 7, 2, 300, 600, '2017-04-09 20:56:13'),
	(2, '1491729973342', 6, 1, 160, 160, '2017-04-09 20:56:13'),
	(3, '1491730560516', 2, 2, 120, 240, '2017-04-09 21:06:00'),
	(4, '1491730560516', 4, 2, 65, 130, '2017-04-09 21:06:00'),
	(5, '1492165305284', 5, 2, 70, 140, '2017-04-14 21:51:45'),
	(6, '1492165305284', 4, 2, 65, 130, '2017-04-14 21:51:45'),
	(7, '1492189349464', 1, 2, 165, 330, '2017-01-15 04:32:29'),
	(8, '1492189349464', 6, 1, 160, 160, '2017-04-15 04:32:29'),
	(9, '1492189946488', 3, 1, 190, 190, '2017-04-15 04:42:26'),
	(10, '1492190099626', 2, 1, 120, 120, '2017-04-15 04:44:59'),
	(11, '1492190341116', 4, 1, 65, 65, '2017-04-15 04:49:01'),
	(12, '1492191099328', 3, 1, 190, 190, '2017-04-15 05:01:39'),
	(13, '1492192975710', 6, 2, 160, 320, '2017-04-15 05:32:55'),
	(14, '1492192975710', 2, 1, 120, 120, '2017-04-15 05:32:55'),
	(15, '1492192975710', 1, 2, 165, 330, '2017-02-15 05:32:55'),
	(16, '1492193361407', 3, 2, 190, 380, '2017-04-15 05:39:21'),
	(17, '1492193361407', 1, 1, 165, 165, '2017-03-15 05:39:21'),
	(18, '1492193361407', 6, 2, 160, 320, '2017-04-15 05:39:21'),
	(19, '1492313070538', 5, 3, 70, 210, '2017-04-16 14:54:30'),
	(20, '1492313070538', 4, 1, 65, 65, '2017-04-16 14:54:30'),
	(21, '1493699482352', 3, 1, 190, 190, '2017-05-02 16:01:22'),
	(22, '1528552067600', 10, 1, 24, 26.4, '2018-06-09 06:47:47'),
	(23, '1528552267427', 10, 7, 24, 184.8, '2018-06-09 06:51:07'),
	(24, '1528552904119', 10, 12, 24, 316.8, '2018-06-09 07:03:31'),
	(25, '1528554546575', 10, 1, 24, 26.4, '2018-06-09 07:29:07'),
	(26, '1528556221317', 10, 1, 24, 26.4, '2018-06-09 07:57:01'),
	(27, '1528561981364', 10, 1, 24, 26.4, '2018-06-09 09:33:03'),
	(28, '1528562288212', 10, 1, 24, 26.4, '2018-06-09 09:38:10');
/*!40000 ALTER TABLE `sales` ENABLE KEYS */;

-- Dumping structure for table inventory.suppliers
DROP TABLE IF EXISTS `suppliers`;
CREATE TABLE IF NOT EXISTS `suppliers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `address` varchar(200) NOT NULL,
  `saddress` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- Dumping data for table inventory.suppliers: ~1 rows (approximately)
/*!40000 ALTER TABLE `suppliers` DISABLE KEYS */;
INSERT INTO `suppliers` (`id`, `name`, `phone`, `address`, `saddress`) VALUES
	(1, 'Viveks', '9940038030', 'No 260 VOC Street TV Nagar Thirumangalam Chennai 600 040', ''),
	(10, 'Techno Soft', '8015409745', 'No 260	', 'No 299');
/*!40000 ALTER TABLE `suppliers` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
