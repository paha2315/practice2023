 CREATE DATABASE LBS;
 CREATE TABLE Buildings(
 	id int not null PRIMARY KEY,
 	city_type varchar(100),
 	city_name varchar(100),
 	regeon_name varchar(100),
 	full_addr varchar(1000),
 	name varchar(500),
 	geo_lat Double,
 	geo_lon Double,
 	med_care_name varchar(255),
 	last_update long
 );
