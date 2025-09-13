-- Table structure for table `product_ratings`
CREATE TABLE `product_ratings` (
  `Rating_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Product_Name` varchar(100) NOT NULL,
  `Average_Rating` decimal(3,1) NOT NULL,
  `Total_Review` int(11) NOT NULL,
  `Comments` text DEFAULT NULL,
  PRIMARY KEY (`Rating_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Sample data for table `product_ratings`
INSERT INTO `product_ratings` (`Product_Name`, `Average_Rating`, `Total_Review`, `Comments`) VALUES
('EGG PIE CARAMEL', 4.2, 15, 'Yummy'),
('PASTEL DELIGHT ROUND CAKE', 3.4, 9, 'wow yummy');
