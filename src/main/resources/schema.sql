create table if not exists Ingredient
(
    id   varchar(4)  not null primary key,
    name varchar(25) not null,
    type varchar(10) not null
);

create table if not exists Taco
(
    id        identity primary key,
    name      varchar(50) not null,
    createdAt timestamp   not null
);

create table if not exists Taco_Ingredients
(
    taco       bigint     not null references Taco,
    ingredient varchar(4) not null references Ingredient
);

create table if not exists Taco_Order
(
    id             identity primary key,
    deliveryName   varchar(50) not null,
    deliveryStreet varchar(50) not null,
    deliveryCity   varchar(50) not null,
    deliveryState  varchar(2)  not null,
    deliveryZip    varchar(10) not null,
    ccNumber       varchar(16) not null,
    ccExpiration   varchar(5)  not null,
    ccCVV          varchar(3)  not null,
    placedAt       timestamp   not null
);

create table if not exists Taco_Order_Tacos
(
    tacoOrder bigint not null references Taco_Order,
    taco      bigint not null references Taco
);
