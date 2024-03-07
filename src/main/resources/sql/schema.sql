create table user
(
    id       bigint auto_increment
        primary key,
    email    varchar(255) not null,
    username varchar(255) not null,
    password varchar(255) not null,
    avator   varchar(255) null,
    constraint user_pk
        unique (username)
);

create table star
(
    id          bigint auto_increment
        primary key,
    user        bigint       not null,
    name        varchar(255) not null,
    image       varchar(255) null,
    description varchar(255) null,
    constraint star_user_id_fk
        foreign key (user) references user (id)
            on delete cascade
);

create table video
(
    id          bigint auto_increment
        primary key,
    user        bigint       not null,
    title       varchar(255) null,
    video_url   varchar(255) null,
    url         varchar(255) not null,
    description varchar(255) null,
    constraint video_user_id_fk
        foreign key (user) references user (id)
            on delete cascade
);

create table website
(
    id          bigint auto_increment
        primary key,
    user        bigint       not null,
    title       varchar(255) null,
    url         varchar(255) not null,
    description varchar(255) null,
    constraint website_user_id_fk
        foreign key (user) references user (id)
            on delete cascade
);


