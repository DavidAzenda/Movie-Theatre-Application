
CREATE DATABASE IF NOT EXISTS ENSF614PROJECT;
USE ENSF614PROJECT;

CREATE TABLE IF NOT EXISTS PAYMENTINFO
	(infoID				int 			NOT NULL UNIQUE DEFAULT 0,
	 fName				VARCHAR(30)		NOT NULL,
     lName				VARCHAR(30)		NOT NULL,
     address			VARCHAR(30)		NOT NULL,
     cardNumber			int				NOT NULL,
     expiry				datetime		NOT NULL,
     cvv				int				NOT NULL,
     PRIMARY KEY (infoID));
     
CREATE TABLE IF NOT EXISTS USER
	(userID				int 			NOT NULL AUTO_INCREMENT,
	 username			VARCHAR(30)		NOT NULL,
     password			VARCHAR(30)		NOT NULL,
     email				VARCHAR(30)		NOT NULL,
     feepaid			datetime,
     infoID				int 			NOT NULL,
     PRIMARY KEY (userID),
     foreign key(infoID) references PAYMENTINFO(infoID) ON DELETE CASCADE);
     
     
CREATE TABLE IF NOT EXISTS THEATRE
	(theatreID			INT 			NOT NULL AUTO_INCREMENT,
	 name				VARCHAR(30) 	NOT NULL,
     address			VARCHAR(30)		NOT NULL,
     PRIMARY KEY (theatreID)); 
     
      
CREATE TABLE IF NOT EXISTS MOVIE	
	(movieID			int				NOT NULL AUTO_INCREMENT,
     title				VARCHAR(30)		NOT NULL,
     news				VARCHAR(30)		NOT NULL,
     earlyRelease	    boolean			NOT NULL,

     PRIMARY KEY (movieID));
     
     
CREATE TABLE IF NOT EXISTS SHOWTIME
	(showtimeID		    int 			NOT NULL AUTO_INCREMENT,
	 movieID			int				NOT NULL,
     date				DATE			NOT NULL,
     time				TIME			NOT NULL,

	 PRIMARY KEY (showtimeID), 
     foreign key(movieID) references MOVIE(movieID) ON DELETE CASCADE);
     
CREATE TABLE IF NOT EXISTS SEAT
	(number				int 			NOT NULL AUTO_INCREMENT,
	 reserved			BOOL			NOT NULL,
     showtimeID			int 			NOT NULL,
     price				int				NOT NULL,
     CONSTRAINT PK_seat PRIMARY KEY (number,showtimeID),
     foreign key(showtimeID) references SHOWTIME(showtimeID) ON DELETE CASCADE);
     

CREATE TABLE IF NOT EXISTS TICKET
	(ticketID		    int 			NOT NULL,
	 showtimeID			int				NOT NULL,
     theatreID			int				NOT NULL,
     seat				int				NOT NULL,
     userID				int,

     PRIMARY KEY (ticketID), 
     foreign key(showtimeID) references SHOWTIME(showtimeID) ON DELETE CASCADE,
     foreign key(theatreID) references THEATRE(theatreID) ON DELETE CASCADE,	
     foreign key(seat) references SEAT(number) ON DELETE CASCADE,
     foreign key(userID) references USER(userID)ON DELETE CASCADE);	
     
CREATE TABLE IF NOT EXISTS RECEIPT
	(receiptID		    int 			NOT NULL,
	 ticketID			int				NOT NULL,
     price				int				NOT NULL,
     userID				int				NOT NULL,

     PRIMARY KEY (ticketID), 
     foreign key(ticketID) references TICKET(ticketID) ON DELETE CASCADE,	
     foreign key(userID) references USER(userID)ON DELETE CASCADE);
     
CREATE TABLE IF NOT EXISTS CREDIT
	(creditID		    int 			NOT NULL AUTO_INCREMENT,
	 expiry				date			NOT NULL,
     value				double			NOT NULL,
     userID				int,

     PRIMARY KEY (creditID), 
     foreign key(userID) references USER(userID)ON DELETE CASCADE);
     
     
     
INSERT INTO PAYMENTINFO (infoID,fName,lName,address,cardNumber,expiry,cvv)
VALUES (1,"Sarah",		"W",	"4 driveway road",		456456465,	'2025-10-1',	456),
		(2,"Pang",		"C",	"1010 pillar ave",		456645456,	'2026-10-1',	234),
        (3,"Drew",		"B",	"90 clearwater cres",	456834447,	'2026-10-1',	565),
		(4,"David",		"A",	"3 vista park",			457023437,	'2026-10-1',	674),
		(5,"Michelle",	"A",	"56 flyaway drive",		457212428,	'2026-10-1',	865),
        ('6','Mike','Z','34 rando way','988438934','2026-10-01 00:00:00','987'),
		('7','Derek','G','1 rich lane clove','543542553','2026-10-01 00:00:00','145');

        
INSERT INTO USER(username,password,email,feepaid,infoID)
VALUES ("sarw",	"sar123",	"sarahW@hotmail.com",	'2021-12-01',	1),
		("pangc",	"pang123",	"pangC@hotmail.com",	'2021-12-02',	2),
		("drewb",	"drew123",	"drewB@hotmail.com",	'2021-12-03',	3),
		("davida",	"david123",	"davidA@hotmail.com",	'2021-12-04',	4),
		("michellea",	"michelle123",	"michelleA@hotmail.com",	'2021-12-05',	5);
        

        
INSERT INTO THEATRE(name,address)
VALUES ("chinook","macleod trail");
		
        

INSERT INTO MOVIE(title,news,earlyRelease)
VALUES ("Eternal",	"Another superhero movie", 0),
		("House of Gucci", "Who likes fashion?",	0),
		("Dune", "Very confusing",	0),
		("Spiderman-Lost in a web","Spiderman Spiderman get stuck",	1);


INSERT INTO SHOWTIME (movieID,date,time)
VALUES (1,	'2022-01-01',	'19:30:00'),
		(1,	'2022-01-01',	"20:30:00"),
		(1,	'2022-01-01',	"21:30:00"),
		(2,	'2022-01-01',	"22:30:00"),
		(2,	'2022-01-02',	"23:30:00"),
		(3,	'2022-01-01',	"12:30:00"),
		(3,	'2022-01-02',	"01:30:00"),
		(1,	'2022-01-03',	"02:30:00"),
		(2,	'2022-01-04',	"03:30:00"),
		(3,	'2022-01-05',	"04:30:00"),
        (4, '2021-12-29',   "22:30:00"),
        ('1','2021-12-09','20:00:00');


INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (1,1,1,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (1,0,2,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (1,0,3,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (1,0,4,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (1,0,5,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (1,0,6,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (1,0,7,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (1,0,8,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (1,0,9,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (1,0,10,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (1,0,11,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (1,0,12,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (2,1,1,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (2,0,2,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (2,0,3,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (2,0,4,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (2,0,5,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (2,0,6,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (2,0,7,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (2,0,8,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (2,0,9,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (2,0,10,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (2,0,11,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (2,0,12,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (3,1,1,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (3,0,2,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (3,0,3,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (3,0,4,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (3,0,5,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (3,0,6,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (3,0,7,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (3,0,8,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (3,0,9,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (3,0,10,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (3,0,11,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (3,0,12,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (4,1,1,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (4,0,2,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (4,0,3,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (4,0,4,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (4,0,5,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (4,0,6,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (4,0,7,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (4,0,8,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (4,0,9,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (4,0,10,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (4,0,11,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (4,0,12,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (5,1,1,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (5,0,2,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (5,0,3,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (5,0,4,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (5,0,5,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (5,0,6,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (5,0,7,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (5,0,8,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (5,0,9,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (5,0,10,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (5,0,11,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (5,0,12,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (6,0,1,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (6,0,2,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (6,0,3,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (6,0,4,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (6,0,5,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (6,0,6,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (6,0,7,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (6,0,8,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (6,0,9,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (6,0,10,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (6,0,11,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (6,0,12,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (7,0,1,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (7,0,2,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (7,0,3,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (7,0,4,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (7,0,5,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (7,0,6,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (7,0,7,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (7,0,8,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (7,0,9,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (7,0,10,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (7,0,11,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (7,0,12,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (8,0,1,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (8,0,2,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (8,0,3,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (8,0,4,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (8,0,5,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (8,0,6,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (8,0,7,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (8,0,8,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (8,0,9,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (8,0,10,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (8,0,11,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (8,0,12,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (9,0,1,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (9,0,2,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (9,0,3,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (9,0,4,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (9,0,5,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (9,0,6,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (9,0,7,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (9,0,8,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (9,0,9,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (9,0,10,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (9,0,11,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (9,0,12,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (10,0,1,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (10,0,2,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (10,0,3,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (10,0,4,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (10,0,5,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (10,0,6,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (10,0,7,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (10,0,8,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (10,0,9,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (10,0,10,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (10,0,11,10);
INSERT INTO `seat` (`number`,`reserved`,`showtimeID`,`price`) VALUES (10,0,12,10);

        

INSERT INTO TICKET(ticketID,showtimeID,theatreID,seat,userID)
VALUES (1, 1,	1,	1,	1),
		(2, 1, 1,	2,	1),
		(3, 4,	1,	3,	3),
		(4, 6,	1,	4,	2),
		(5, 7,	1,	5,	2);


INSERT INTO RECEIPT(receiptID,ticketID,price,userID)
VALUES (1,	1,	10,	1),
		(1,	2,	10,	1),
		(2,	4,	10,	2),
		(2,	5,	10,	2),
		(3,	3,	10,	3);

INSERT INTO CREDIT(expiry,value,userID)
VALUES ('2022-11-28',	20,	1),
		('2022-11-29',	10,	3);
