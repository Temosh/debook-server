-- -----------------------------------------------------
-- Data for table `debook_db`.`currency`
-- -----------------------------------------------------
START TRANSACTION;
USE `debook_db`;
INSERT INTO `debook_db`.`currency` (`currency_id`, `code`, `sign`) VALUES (1, 'UAH', '₴');
INSERT INTO `debook_db`.`currency` (`currency_id`, `code`, `sign`) VALUES (2, 'USD', '$');
INSERT INTO `debook_db`.`currency` (`currency_id`, `code`, `sign`) VALUES (3, 'EUR', '€');
INSERT INTO `debook_db`.`currency` (`currency_id`, `code`, `sign`) VALUES (4, 'GBP', '£');
INSERT INTO `debook_db`.`currency` (`currency_id`, `code`, `sign`) VALUES (5, 'TRY', '₺');
INSERT INTO `debook_db`.`currency` (`currency_id`, `code`, `sign`) VALUES (6, 'THB', '฿');

COMMIT;


-- -----------------------------------------------------
-- Data for table `debook_db`.`credit_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `debook_db`;
INSERT INTO `debook_db`.`credit_type` (`credit_type_id`, `type`) VALUES (1, 'LOAN');
INSERT INTO `debook_db`.`credit_type` (`credit_type_id`, `type`) VALUES (2, 'DEBT');

COMMIT;


-- -----------------------------------------------------
-- Data for table `debook_db`.`event_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `debook_db`;
INSERT INTO `debook_db`.`event_type` (`event_type_id`, `type`, `subtype`) VALUES (1, 'CONNECTION', 'REQUEST');
INSERT INTO `debook_db`.`event_type` (`event_type_id`, `type`, `subtype`) VALUES (2, 'CONNECTION', 'APPROVE');
INSERT INTO `debook_db`.`event_type` (`event_type_id`, `type`, `subtype`) VALUES (3, 'CONNECTION', 'REJECT');
INSERT INTO `debook_db`.`event_type` (`event_type_id`, `type`, `subtype`) VALUES (4, 'DEBT', 'REQUEST');
INSERT INTO `debook_db`.`event_type` (`event_type_id`, `type`, `subtype`) VALUES (5, 'DEBT', 'APPROVE');
INSERT INTO `debook_db`.`event_type` (`event_type_id`, `type`, `subtype`) VALUES (6, 'DEBT', 'REJECT');

COMMIT;