drop database HotelCalifornia;

create database if not exists HotelCalifornia;

use HotelCalifornia;

create table if not exists Amenity (
	AmenityID int not null auto_increment,
    `Name` varchar(30) not null,
    Quantity int,
    primary key (AmenityID)
);

create table if not exists RoomType (
	RoomTypeID int not null auto_increment,
    `Name` varchar(30) not null,
    primary key (RoomTypeID)
);

create table if not exists RoomTypeAmenity (
	RoomTypeAmenityID int not null auto_increment,
    RoomTypeID int,
    AmenityID int not null,
    primary key (RoomTypeAmenityID)
);

alter table RoomTypeAmenity
	add constraint fk_RoomTypeAmenity_RoomTypeID foreign key (RoomTypeID)
    references RoomType(RoomTypeID) on delete no action;
alter table RoomTypeAmenity
	add constraint fk_RoomTypeAmenity_AmenityID foreign key (AmenityID)
    references Amenity(AmenityID) on delete no action;
    
create table if not exists Rate (
	RateID int not null auto_increment,
    Value decimal(10,2) not null,
    StartDate date not null,
    EndDate date,
    primary key (RateID)
);

create table if not exists Room (
	RoomID int not null auto_increment,
    Occupancy tinyint not null,
    RoomNumber tinyint not null,
    Floor int not null,
    Branch varchar(30) not null,
    RoomTypeID int not null,
    primary key (RoomID)
);

alter table Room
	add constraint fk_RoomTypeID foreign key (RoomTypeID) 
    references RoomType(RoomTypeID) on delete no action;
    
create table if not exists RoomAmenity (
	RoomAmenityID int not null auto_increment,
    RoomID int,
    AmenityID int not null,
    primary key (RoomAmenityID)
);

alter table RoomAmenity
	add constraint fk_RoomAmenity_AmenityID foreign key (AmenityID)
    references Amenity(AmenityID) on delete no action;
alter table RoomAmenity
	add constraint fk_RoomAmenity_RoomID foreign key (RoomID)
	references Room(RoomID) on delete no action;
    
create table if not exists RoomRate (
	RoomRateID int not null auto_increment,
    RoomID int not null,
    RateID int not null,
    primary key (RoomRateID)
);

alter table RoomRate
	add constraint fk_RoomRate_RateID foreign key (RateID)
    references Rate(RateID) on delete no action;
alter table RoomRate
	add constraint fk_RoomRate_RoomID foreign key (RoomID)
    references Room(RoomID) on delete no action;
    
create table if not exists Customer (
	CustomerID int not null auto_increment,
    FirstName varchar(30),
    LastName varchar(30) not null,
    Phone char(10),
    Email varchar(50),
    primary key (CustomerID)
);

create table if not exists Bill (
	BillID int not null auto_increment,
    Total decimal(10,2) not null,
    Tax decimal(10,2) not null,
    DateTimeIssued datetime,
    CustomerID int,
    primary key (BillID)
);

alter table Bill
	add constraint fk_Bill_CustomerID foreign key (CustomerID)
	references Customer(CustomerID) on delete no action;

create table if not exists Reservation (
	ReservationID int not null auto_increment,
    Occupants varchar(500),
    StartDate date not null,
    EndDate date not null,
    CustomerID int,
    primary key (ReservationID)
);

alter table Reservation
	add constraint fk_Reservation_CustomerID foreign key (CustomerID)
    references Customer(CustomerID) on delete no action;
    
create table if not exists RoomReservation (
	RoomReservationID int not null auto_increment,
    ReservationID int,
    RoomID int not null,
    BillID int,
    primary key (RoomReservationID)
);

alter table RoomReservation
	add constraint fk_RoomReservation_ReservationID foreign key (ReservationID)
    references Reservation(ReservationID) on delete no action;
alter table RoomReservation
    add constraint fk_RoomReservation_RoomID foreign key (RoomID)
    references Room(RoomID) on delete no action;
alter table RoomReservation
	add constraint fk_RoomReservation_BillID foreign key (BillID)
    references Bill(BillID) on delete no action;
    
create table if not exists AddOn (
	AddOnID int not null auto_increment,
    `Name` varchar(30) not null,
    `Value` int not null,
    StartDate date not null,
    EndDate date,
    primary key (AddOnID)
);

create table if not exists Price (
	PriceID int not null auto_increment,
    Value decimal(10,2) not null,
    StartDate date not null,
    EndDate date,
    primary key (PriceID)
);

create table if not exists AddOnPrice (
	AddOnPriceID int not null auto_increment,
    AddOnID int not null,
    PriceID int not null,
    primary key (AddOnPriceID)
);

alter table AddOnPrice
	add constraint fk_AddOnPrice_AddOnID foreign key (AddOnID)
    references AddOn(AddOnID) on delete no action;
alter table AddOnPrice
	add constraint fk_AddOnPrice_PriceID foreign key (PriceID)
    references Price(PriceID) on delete no action;

create table if not exists Promo (
	PromoID int not null auto_increment,
    `Name` varchar(30) not null,
    `Value` int not null,
    PromoType binary(1) not null,
    StartDate date not null,
    EndDate date,
    primary key (PromoID)
);

create table if not exists LineItem (
	LineItemID int not null auto_increment,
    AddOnID int,
    RoomReservationID int not null,
    BillID int not null,
    primary key (LineItemID)
);

alter table LineItem
	add constraint fk_LineItem_AddOnID foreign key (AddOnID)
    references AddOn(AddOnID) on delete no action;
alter table LineItem
	add constraint fk_LineItem_RoomReservationID foreign key (RoomReservationID)
    references RoomReservation(RoomReservationID) on delete no action;
alter table LineItem
	add constraint fk_LineItem_BillID foreign key (BillID)
    references Bill(BillID) on delete no action;
    
create table LineItemPromo (
	LineItemPromoID int not null auto_increment,
    LineItemID int,
    PromoID int not null,
    primary key (LineItemPromoID)
);

alter table LineItemPromo
	add constraint fk_LineItemPromo_LineItemID foreign key (LineItemID)
    references LineItem(LineItemID) on delete no action;
alter table LineItemPromo
	add constraint fk_LineItemPromo_PromoID foreign key (PromoID)
    references Promo(PromoID) on delete no action;