###########################
#Initial data for project #
###########################

################Clients
insert into client (first_name, last_name, patronymic, phone_number, age, password)
values ('Ivan','Kovalchuk', 'Ivanovich', '+3806799933', 20, 'III'),
		('Nick', 'Peret', 'Sergiovich', '+3809733399', 22,'NNN'),
        ('Igor', 'Mart', 'Bobovich', '+3806677799', 25,'III'),
        ('Katerina', 'Siri', 'Ivanova', '+3809799333', 20, 'KKK'),
        ('Inna', 'Bodnack', 'Bogdanovna', '+380965533', 26,'III'),
        ('Nikolya', 'Saurcozi', 'Mikholaiovich', '+3806722211', 30,'NNN'),
        ('Karina', 'Dymel', 'Nikitovna', '+38093888822', 21,'KKK'),
        ('Nina', 'Ivanovika', 'Andreivna', '+38064553335', 18,'NNN'),
        ('Anton', 'Kok', 'Alecsiovich', '+3806633777', 32,'AAA'),
        ('Vasia', 'Pupkin', 'Kekovich', '+380999988', 35,'VVV');
        
############################ Instructors
insert into instructor(first_name, last_name, patronymic, phone_number, age, password)
values ('Karl', 'Marks', 'Ivanovich', '+3809995566', 30, 'KKK'),
		('Faust', 'Sin', 'Godovich', '+38063555993', 28, 'FFF'),
        ('Jinee', 'Grey', 'Ostapovna', '+38056223322', 25, 'JJJ'),
        ('Dmitriy', 'Klok', 'Antonovich', '+38055233399', 30, 'DDD'),
        ('Iliya', 'Illiyn', 'Illich', '+38066222222', 28, 'III'),
        ('Menges', 'Kurat', 'Kuratovich', '+38066778899', 29, 'MMM'),
        ('Anna', 'Valet', 'Victorovna', '+3809955666', 27, 'AAA'),
        ('Lidia', 'Kytatna', 'Valentinovna', '+38067111111', 30, 'LLL');
        
################################Disciplines
insert into discipline(name, max_visitors)
values ('Fitness', 12),
	('Weightlifting', 3),
    ('Powerlifting', 5),
    ('CrossFit', 3),
    ('Bodybuilding', 1),
    ('Yoga', 10),
    ('Aerobica', 8);

####################################Instructing 
insert into instruct(discipline_id, instructor_id, price)
values (4, 1, 370),
		(6, 2, 350),
        (7, 3, 300),
        (1, 3, 270),
        (2, 4, 420),
        (3, 4, 400),
        (2, 5, 430),
        (5, 6, 500),
        (6, 7, 340),
        (1, 7, 280),
        (4, 8, 400);

################################Days
insert into day(name)
values ('Monday'),
		('Thuesday'),
        ('Wednesday'),
        ('Thursday'),
        ('Friday');

##################################Times
insert into time(start, finish)
values('9:00', '10:30'),
		('10:00', '11:30'),
        ('12:00', '13:30'),
        ('15:00', '16:30'),
        ('16:00', '17:30'),
        ('17:00', '18:30'),
        ('18:00', '19:30'),
        ('19:00', '20:30'),
        ('20:00', '21:00');

#################################################Periods##separeted by days, the same program for all odd days and the same for the even days
insert into period(day_id, time_id)
values(1, 1),
	  (1, 2),
      ( 1, 4),
      (1, 5),
      (1, 7),
      (1, 8),
#################
      (2, 2),
      (2, 3),
      (2, 4),
      (2, 6),
      (2, 8),
      (2, 9),
###################
	  (3, 1),
      (3, 2),
      (3, 4),
      (3, 5),
      (3, 7),
      (3, 8),  
####################
	  (4, 2),
      (4, 3),
      (4, 4),
      (4, 6),
      (4, 8),
      (4, 9),
####################  
      (5, 1),
      (5, 2),
      (5, 4),
      (5, 5),
      (5, 7),
      (5, 8);
      
#################################################Schedules##also separeted by days
insert into schedule(instruct_id, period_id)
values(4, 1),
	  (1, 2),
      (9, 3),
      (5, 4),
      (8, 5),
      (3, 6),
      (6, 6),
########################
	  (2, 7),
      (3, 8),
      (7, 9),
      (11, 10),
      (6, 11),
      (10, 11),
      (8, 12),
########################
      (4, 13),
      (1, 14),
      (9, 15),
      (5, 16),
      (8, 17),
      (3, 18),
      (6, 18),
########################
      (2, 19),
      (3, 20),
      (7, 21),
      (11, 22),
      (6, 23),
      (10, 23),
      (8, 24),
#########################
	  (4, 25),
      (1, 26),
      (9, 27),
      (5, 28),
      (8, 29),
      (3, 30),
      (6, 30);

#######################################################
insert into subscription (duration_end, client_id, instruct_id)
value ('2019-12-06', 2, 5);
insert into visits (schedule_id, subscription_id)
values (4,1),
	   (18,1),
	   (32,1);
insert into subscription (duration_end, client_id, instruct_id)
value ('2019-05-06', 5, 9);
insert into visits (schedule_id, subscription_id)
values (3,2),
	   (17,2),
	    (31,2);

########################################################
insert into admin(name, password)
value ('admin', 'AAA');
########################################################