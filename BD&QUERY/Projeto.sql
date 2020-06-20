
CREATE TABLE News (idNews int primary key NOT NULL, title varchar(100) NULL, txt_news varchar(100))

CREATE TABLE Ads (idAds int primary key NOT NULL, title_ads varchar(100) NULL, txt_ads varchar(500))

drop table Ads

CREATE TABLE Venues (idVenues int primary key NOT NULL, name_venue varchar(100) NULL, location_venues varchar(100))

CREATE TABLE Tournaments (idTournaments int primary key NOT NULL, "name" varchar(100) NULL, prize int NULL, 
						initial_date varchar(100) NULL, last_date varchar(100) NULL)

drop table Tournaments

CREATE TABLE tipoUtilizadores (designacao varchar(100) primary key NOT NULL)

CREATE TABLE Games (idGame int primary key NOT NULL, idTeamOne int NULL,idTeamTwo int NULL,TeamOneName varchar(100) NULL, TeamTwoName varchar(100) NULL, GoalsTeamOne int NULL,GoalsTeamTwo int NULL, idTournaments int NULL)

DROP TABLE Games

CREATE TABLE Players (idPlayer int primary key NOT NULL, first_name varchar(100) NULL, last_name varchar(100) NULL, age int NULL, height float NULL,
						country varchar(100) NULL, position varchar(100) NULL,idTeam int NULL)

drop table Players

CREATE TABLE Teams (idTeam int primary key NOT NULL, "name" varchar(100) NULL, initials varchar(100) NULL, "location" varchar(100),
						coach varchar(100) NULL, idTournaments int NULL)

DROP TABLE Teams


ALTER TABLE Users
	ADD UNIQUE (username);

