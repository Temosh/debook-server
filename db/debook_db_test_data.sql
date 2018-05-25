-- -----------------------------------------------------
-- Data for table `debook_db`.`user`
-- Password for test users - 'test'
-- -----------------------------------------------------
START TRANSACTION;
USE `debook_db`;
INSERT INTO `user` VALUES (1, 'Test', 'First', 'Last', 'test@test.com', '1');
INSERT INTO `user` VALUES (2, 'NickFury', 'Nick', 'Fury', 'n.fury@shield.com', '1');
INSERT INTO `user_secret` VALUES (1, '$2a$10$3wuhm.O7XA1Cy/n76.Nw7OUuvhdQav0xVYpkgHmD05qYd7hJEQyiG');
INSERT INTO `user_secret` VALUES (2, '$2a$10$3VTgoqfKqI/d9rSnysTxgOZPh7cklucLYq7YaQHJ77v5YsmPFLrtG');

COMMIT;

-- ----------------------------------------
-- NOTE: 2 users should be addded manually!
-- ----------------------------------------

-- -----------------------------------------------------
-- Data for table `debook_db`.`local_user`
-- -----------------------------------------------------
START TRANSACTION;
USE `debook_db`;
INSERT INTO `person` VALUES (1, 1, null, 'Iron', 'Man');
INSERT INTO `person` VALUES (2, 1, null, 'Nat', 'Romanova');
INSERT INTO `person` VALUES (3, 2, null, 'Phil', 'Coulson');

COMMIT;


-- -----------------------------------------------------
-- Data for table `debook_db`.`local_debt`
-- -----------------------------------------------------
START TRANSACTION;
USE `debook_db`;
INSERT INTO `debt` VALUES (1, 1, 1, 1, 200.00);
INSERT INTO `debt` VALUES (1, 2, 2, 2, 10.00);
INSERT INTO `debt` VALUES (2, 3, 3, 1, 40.00);
INSERT INTO `debt` VALUES (2, 3, 4, 1, 10.00);

COMMIT;