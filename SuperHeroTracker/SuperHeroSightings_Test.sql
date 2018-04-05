drop database SuperHeroTracker_Test;

create database if not exists SuperHeroTracker_Test;

use SuperHeroTracker_Test;

create table if not exists `Power` (
	PowerID int not null auto_increment,
    Description varchar(50) not null,
    primary key (PowerID)
);

create table if not exists `Super` (
	SuperID int not null auto_increment,
    `Name` varchar(50),
    Description varchar(1200) not null,
    PowerID int,
    iconFile varchar(200),
    primary key (SuperID)
);

alter table `Super`
	add constraint fk_Super_PowerID foreign key (PowerID)
    references `Power`(PowerID) on delete no action;
    
create table if not exists Headquarters (
	HeadquartersID int not null auto_increment,
    Address varchar(75),
    Planet varchar(30),
    primary key (HeadquartersId)
);

create table if not exists Organization (
	OrganizationID int not null auto_increment,
    `Name` varchar(50) not null,
    Description varchar(1200) not null,
    Email varchar(50) not null,
    Alignment bool,
    HeadquartersID int,
    primary key (OrganizationID)
);

alter table Organization
	add constraint fk_Organization_HeadquartersID foreign key (HeadquartersID)
    references Headquarters(HeadquartersID) on delete no action;

create table if not exists SuperOrganization (
	SuperOrganizationID int not null auto_increment,
    SuperID int not null,
    OrganizationID int not null,
    primary key (SuperOrganizationID)
);

alter table SuperOrganization
	add constraint fk_SuperOrganization_SuperID foreign key (SuperID)
    references `Super`(SuperID) on delete no action;
alter table SuperOrganization
	add constraint fk_SuperOrganization_OrganizationID foreign key (OrganizationID)
    references Organization(OrganizationID) on delete no action;

create table if not exists Location (
	LocationID int not null auto_increment,
    `Name` varchar(60) not null,
    Description varchar(1000) not null,
    Address varchar(75) not null,
    Latitude decimal(9, 6),
    Longitude decimal(9, 6),
    primary key (LocationID)
);

create table if not exists Sighting (
	SightingID int not null auto_increment,
    `DateTime` datetime not null,
    LocationID int not null,
    primary key (SightingID)
);

alter table Sighting
	add constraint fk_Sighting_LocationID foreign key (LocationID)
    references Location(LocationID) on delete no action;

create table if not exists SuperSighting (
	SuperSightingID int not null auto_increment,
    SuperID int not null,
    SightingID int not null,
    primary key (SuperSightingID)
);

alter table SuperSighting
	add constraint fk_SuperSighting_SuperID foreign key (SuperID)
    references `Super`(SuperID) on delete no action;
alter table SuperSighting
	add constraint fk_SuperSighting_SightingID foreign key (SightingID)
    references Sighting(SightingID) on delete no action;
