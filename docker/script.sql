 CREATE DATABASE LBS;
 CREATE TABLE Buildings(
 	id int8 not null PRIMARY KEY,
 	city_type varchar(100),
 	city_name varchar(100),
 	regeon_name varchar(100),
 	full_addr varchar(1000),
 	name varchar(500),
 	geo_lat float8,
 	geo_lon float8,
 	med_care_name varchar(255),
 	last_update timestamp
 );
