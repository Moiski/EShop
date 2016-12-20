-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.1.41-community - MySQL Community Server (GPL)
-- ОС Сервера:                   Win32
-- HeidiSQL Версия:              9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Дамп структуры базы данных eshop
CREATE DATABASE IF NOT EXISTS `eshop` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `eshop`;


-- Дамп структуры для таблица eshop.carts
CREATE TABLE IF NOT EXISTS `carts` (
  `cartId` bigint(20) NOT NULL AUTO_INCREMENT,
  `productID` bigint(20) DEFAULT NULL,
  `userID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`cartId`),
  KEY `FK_scuvtr11s2yjk76jkmkipqa3f` (`productID`),
  KEY `FK_941l0fpcrr5w55ngbth7sheyc` (`userID`),
  CONSTRAINT `FK_941l0fpcrr5w55ngbth7sheyc` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`),
  CONSTRAINT `FK_scuvtr11s2yjk76jkmkipqa3f` FOREIGN KEY (`productID`) REFERENCES `products` (`productID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы eshop.carts: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `carts` DISABLE KEYS */;
/*!40000 ALTER TABLE `carts` ENABLE KEYS */;


-- Дамп структуры для таблица eshop.orderdetails
CREATE TABLE IF NOT EXISTS `orderdetails` (
  `orderDetailId` bigint(20) NOT NULL AUTO_INCREMENT,
  `count` int(11) DEFAULT NULL,
  `productID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`orderDetailId`),
  KEY `FK_m114gqppexh2yu0rd9klxtokh` (`productID`),
  CONSTRAINT `FK_m114gqppexh2yu0rd9klxtokh` FOREIGN KEY (`productID`) REFERENCES `products` (`productID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы eshop.orderdetails: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `orderdetails` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderdetails` ENABLE KEYS */;


-- Дамп структуры для таблица eshop.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `orderID` bigint(20) NOT NULL AUTO_INCREMENT,
  `amountPurchase` double DEFAULT NULL,
  `orderState` enum('OPEN','CLOSE') DEFAULT NULL,
  `userID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`orderID`),
  KEY `FK_fj9x12lojwenc6sac0hw57j6s` (`userID`),
  CONSTRAINT `FK_fj9x12lojwenc6sac0hw57j6s` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы eshop.orders: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;


-- Дамп структуры для таблица eshop.orders_orderdetail
CREATE TABLE IF NOT EXISTS `orders_orderdetail` (
  `orderId` bigint(20) NOT NULL,
  `orderDetailId` bigint(20) NOT NULL,
  UNIQUE KEY `UK_h7aditssnvgf9d53mk3byx9al` (`orderDetailId`),
  KEY `FK_7dfpfcquxknhhes5dolylwpdu` (`orderId`),
  CONSTRAINT `FK_7dfpfcquxknhhes5dolylwpdu` FOREIGN KEY (`orderId`) REFERENCES `orders` (`orderID`),
  CONSTRAINT `FK_h7aditssnvgf9d53mk3byx9al` FOREIGN KEY (`orderDetailId`) REFERENCES `orderdetails` (`orderDetailId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы eshop.orders_orderdetail: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `orders_orderdetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders_orderdetail` ENABLE KEYS */;


-- Дамп структуры для таблица eshop.productcategories
CREATE TABLE IF NOT EXISTS `productcategories` (
  `categoryID` bigint(20) NOT NULL AUTO_INCREMENT,
  `categoryname` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`categoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы eshop.productcategories: ~3 rows (приблизительно)
/*!40000 ALTER TABLE `productcategories` DISABLE KEYS */;
INSERT INTO `productcategories` (`categoryID`, `categoryname`) VALUES
	(1, 'Kindle E-readers'),
	(2, 'Tablets'),
	(3, 'Accessories');
/*!40000 ALTER TABLE `productcategories` ENABLE KEYS */;


-- Дамп структуры для таблица eshop.products
CREATE TABLE IF NOT EXISTS `products` (
  `productID` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `categoryID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`productID`),
  KEY `FK_n473p3mrgkota8k9uggoxf836` (`categoryID`),
  CONSTRAINT `FK_n473p3mrgkota8k9uggoxf836` FOREIGN KEY (`categoryID`) REFERENCES `productcategories` (`categoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы eshop.products: ~15 rows (приблизительно)
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` (`productID`, `description`, `image`, `name`, `price`, `categoryID`) VALUES
	(1, 'Amazon\'s 6" display technology with E Ink Pearl™, 167 ppi, optimized font technology, 16-level gray scale', 'kindle6.jpg', 'Kindle', 10.99, 1),
	(2, 'Amazon’s 6" Paperwhite display technology with E Ink Carta™ and built-in light, 300 ppi, optimized font technology, 16-level gray scale', 'paperwhite.jpg', 'Kindle Paperwhite', 20.99, 1),
	(3, 'Beautiful 6" HD display with over a million pixels (252 ppi / 1280 x 800)', 'Fire6.jpg', 'Fire HD 6', 30.99, 2),
	(4, '10.1" touchscreen, 1280 x 800 resolution at 149 ppi, HD video playback, with IPS (in-plane switching) technology and advanced polarizing filter, fully laminated HD display', 'Fire10.jpg', 'Fire HD 10', 40.99, 2),
	(5, 'Amazon’s 6" display technology with E Ink Carta™ and adaptive front light that adjusts automatically, 300 ppi, optimized font technology, 16-level gray scale', 'voyage.jpg', 'Kindle Voyage E-reader', 99.99, 1),
	(6, 'Amazon’s 6" Paperwhite display technology with E Ink Carta™ and built-in light, 300 ppi, optimized font technology, 16-level gray scale', 'kindleOasis.jpg', 'Kindle Oasis', 55.99, 1),
	(7, 'Kindle Paperwhite Essentials Bundle including Kindle Paperwhite 6" E-Reader, Black with Special Offers, Amazon Leather Cover - Onyx Black, and Power Adapter', 'paperwhiteBundle.jpg', 'Kindle Paperwhite Bundle', 155.45, 1),
	(8, '8" high definition touchscreen; 1280 x 800 resolution at 189 ppi, HD video playback, with IPS (in-plane switching) technology and advanced polarizing filter', 'Fire8.jpg', 'Fire HD 8', 130.56, 2),
	(9, '7" touchscreen, 1024 x 600 resolution at 171 ppi, SD video playback, with IPS (in-plane switching) technology and advanced polarizing filter', 'FireKidsEdition.jpg', 'Fire Kids Edition Tablet', 89.9, 2),
	(10, 'Fire Essentials Bundle including Fire Tablet, 7" Display, Wi-Fi, 8 GB - Includes Special Offers, Amazon Cover - Black and Screen Protector', 'FireEssentialsBundle.jpg', 'Fire Essentials Bundle', 120.1, 2),
	(11, 'Our genuine leather cover was engineered to be the lightest and thinnest protective cover for your Kindle Paperwhite.', 'PaperwhiteLeatherCover.jpg', 'Amazon Kindle Paperwhite Leather Case', 15.33, 3),
	(12, 'The Thinnest and Lightest Leather Cover With Auto Sleep / Wake for All-New Amazon Kindle Paperwhite (Fits All 2012, 2013, 2015 and 2016 Versions), Black', 'FintieSmartShell.jpg', 'Fintie SmartShell Case for Kindle Paperwhite', 36.33, 3),
	(13, 'Amazon Cover for All-New Kindle (8th Generation, 2016) - Black', 'AmazonCover.jpg', 'Amazon Cover for All-New Kindle (8th Generation, 2016)', 54.55, 3),
	(15, 'Amazon Kindle Voyage Leather Origami Case, Black', 'VoyageLeather.jpg', 'Amazon Kindle Voyage Leather Origami Case', 33.2, 3),
	(16, 'ddhdhdhdhdhdhdh', 'sgsdgg', 'safsdfsf', 11.11, 1);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;


-- Дамп структуры для таблица eshop.users
CREATE TABLE IF NOT EXISTS `users` (
  `userID` bigint(20) NOT NULL AUTO_INCREMENT,
  `isInBlackList` enum('Y','N') DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `registrDate` datetime DEFAULT NULL,
  `userT` enum('ADMIN','CLIENT') DEFAULT NULL,
  `shippingAddress` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы eshop.users: ~6 rows (приблизительно)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`userID`, `isInBlackList`, `email`, `firstName`, `lastName`, `login`, `password`, `registrDate`, `userT`, `shippingAddress`) VALUES
	(1, 'N', 'moiski@yandex.ru', 'Kostya', 'Moiski', 'lkpo', '123', '2016-11-23 16:18:14', 'ADMIN', 'Minsk'),
	(2, 'N', 'kat@yandex.ru', 'Kat', 'Stsiapanava', 'kat', '123', '2016-11-23 16:18:53', 'CLIENT', 'Mink');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
