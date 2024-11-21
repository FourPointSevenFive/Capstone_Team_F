-- schema.sql
drop table if exists proof_shot;
drop table if exists stamp;
drop table if exists image;
drop table if exists favorite;
drop table if exists location;
drop table if exists content;
drop table if exists users;

create table users
(
    id                  bigint not null auto_increment,
    username            varchar(255),
    password            varchar(255),
    nickname            varchar(255),
    created_at          datetime(6),
    primary key (id)
) engine = InnoDB;

create table content
(
    id                  bigint not null auto_increment,
    category            enum('K_POP', 'DRAMA', 'MOVIE', 'NOVEL'),
    title               varchar(255),
    description         text,
    content_image_url   varchar(255),
    created_at          datetime(6),
    updated_at          datetime(6),
    hashtag             text,
    primary key (id)
) engine = InnoDB;

create table location
(
    id                  bigint not null auto_increment,
    content_id          bigint,
    title               varchar(255),
    latitude            decimal(15, 8),
    longitude           decimal(15, 8),
    description         text,
    video_link          varchar(255),
    favorite_cnt        bigint default 0,
    pose                text,
    created_at          datetime(6),
    updated_at          datetime(6),
    primary key (id),
    foreign key (content_id) references content(id)
) engine = InnoDB;

create table image
(
    id                  bigint not null auto_increment,
    location_id         bigint,
    image_url           varchar(255),
    description         text,
    created_at          datetime(6),
    primary key (id),
    foreign key (location_id) references location(id)
) engine = InnoDB;

create table stamp
(
    id                  bigint not null auto_increment,
    user_id             bigint,
    location_id         bigint,
    created_at          datetime(6),
    primary key (id),
    foreign key (user_id) references users(id),
    foreign key (location_id) references location(id)
) engine = InnoDB;

create table proof_shot
(
    id                  bigint not null auto_increment,
    user_id             bigint,
    location_id         bigint,
    image_url           varchar(255),
    description         text,
    created_at          datetime(6),
    primary key (id),
    foreign key (user_id) references users(id),
    foreign key (location_id) references location(id)
) engine = InnoDB;

create table favorite
(
    id                  bigint not null auto_increment,
    user_id             bigint,
    entity_id           bigint,
    entity_type         enum('CONTENT', 'LOCATION'),
    created_at          datetime(6),
    primary key (id),
    foreign key (user_id) references users(id)
) engine = InnoDB;
