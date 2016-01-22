-- -----------------------------------------------------
-- Data for table `debook_db`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `debook_db`;
INSERT INTO `user` VALUES (1, 'bux', 'Bux', 'Man', 'bux@summ.com', '1');

COMMIT;


-- -----------------------------------------------------
-- Data for table `debook_db`.`local_user`
-- -----------------------------------------------------
START TRANSACTION;
USE `debook_db`;
INSERT INTO `local_user` VALUES (1, 1, 'Lynx', 'Local');

COMMIT;


-- -----------------------------------------------------
-- Data for table `debook_db`.`local_debt`
-- -----------------------------------------------------
START TRANSACTION;
USE `debook_db`;
INSERT INTO `local_debt` VALUES (1, 1, 1, 1, 10.00);

COMMIT;