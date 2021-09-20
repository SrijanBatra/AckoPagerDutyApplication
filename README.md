# AckoPagerDutyApplication
This repository has the code for the PagerDutyApplication. This was the first round of interview conducted by BarRaiser.

The program is driven from the main function, through code changes.

Here are the SQL queries for creating the database to run this application:

create table team (
	id int UNSIGNED NOT NULL,
	 PRIMARY KEY (id), 
	name varchar(255)
);

create table developer (
	id int UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (id),
	phone_number varchar(255),
	name varchar(255),
	team_id int UNSIGNED NOT NULL,
	FOREIGN KEY (team_id) REFERENCES team(id)
);
