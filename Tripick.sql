Create DATABASE TRIPICK;
use TRIPICK;


create table TRAVEL
(
    travel_no int primary key,
    district varchar(100) not null,
    title    varchar(100) not null,
    description text not null,
    address  varchar(1000),
    phone    varchar(100) not null,
    sum      float default 0,
    count    int   default 0
);


Create table users
(
    user_no    int primary key auto_increment,
    id         varchar(50) not null UNIQUE,
    pw         varchar(50) not null,
    nickname   varchar(50) not null UNIQUE,
    age        int         not null,
    created_at date        not null,
    updated_at date
);

create table review
(
    review_no  int primary key auto_increment,
    user_no    int,
    travel_no  int,
    title      varchar(100) not null,
    content    text         not null,
    rate       float        not null,
    created_at date         not null,
    updated_at date ,
    CONSTRAINT fk_review_user_no FOREIGN key(user_no) REFERENCES users(user_no),
    CONSTRAINT fk_review_travel_no FOREIGN key (travel_no) REFERENCES TRAVEL(travel_no)
);

drop table user;
drop table travel;