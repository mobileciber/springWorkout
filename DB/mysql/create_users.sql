	INSERT INTO `comics`.`address` (`idAddress`, `street`, `houseNumber`, `postalCode`, `town`, `careOf`, `company`)
	VALUES ('1', 'Adminstr.', '1337', '31337', 'Adminstadt', '-', 'Admins Inc.');

	INSERT INTO `comics`.`contactdetails` (`idContactDetails`, `telephoneNumber`, `mobileNumber`, `fax`, `email`, `skypeName`)
	VALUES ('1', '12345', '12345', '12345', 'admin@admins.com', 'admin_a');

	INSERT INTO `comics`.`registrationdetails` (`idRegistrationDetails`, `ip`, `date`)
	VALUES ('1', '127.0.0.1', '1');

	INSERT INTO `comics`.`address` (`idAddress`, `street`, `houseNumber`, `postalCode`, `town`, `careOf`, `company`)
	VALUES ('2', 'Userstr.', '1337', '31337', 'Userstadt', '-', 'Users Inc.');

	INSERT INTO `comics`.`contactdetails` (`idContactDetails`, `telephoneNumber`, `mobileNumber`, `fax`, `email`, `skypeName`)
	VALUES ('2', '12345', '12345', '12345', 'user@users.com', 'user_u');

	INSERT INTO `comics`.`registrationdetails` (`idRegistrationDetails`, `ip`, `date`)
	VALUES ('2', '127.0.0.1', '1');
	
	commit;
	
	INSERT INTO `comics`.`user` (`idUser`, `Address_idAddress`, `RegistrationDetails_idRegistrationDetails`, `ContactDetails_idContactDetails`, `firstname`, `lastname`, `title`, `password`, `role`, `username`, `status`)
<<<<<<< HEAD
	VALUES ('1', '1', '1', '1', 'Admin', 'Adminsson', 'Prof.', 'zrTzIyXtphQr1lIV9MDzcQ==', 'ROLE_ADMIN', 'admin', 'ACTIVE');
=======
	VALUES ('1', '1', '1', '1', 'Admin', 'Adminsson', 'Prof.', md5('admin'), 'ROLE_ADMIN', 'admin', 'ACTIVE');
>>>>>>> branch 'master' of git@github.com:mobileciber/springWorkout.git

	INSERT INTO `comics`.`user` (`idUser`, `Address_idAddress`, `RegistrationDetails_idRegistrationDetails`, `ContactDetails_idContactDetails`, `firstname`, `lastname`, `title`, `password`, `role`, `username`, `status`) 
<<<<<<< HEAD
	VALUES ('2', '2', '2', '2', 'User', 'Usersson', 'Dr.', 'R6cz1gmYxxnPNSaufRBtEw==', 'ROLE_USER', 'user', 'ACTIVE');
=======
	VALUES ('2', '2', '2', '2', 'User', 'Usersson', 'Dr.', md5('user'), 'ROLE_USER', 'user', 'ACTIVE');
>>>>>>> branch 'master' of git@github.com:mobileciber/springWorkout.git

	commit;
