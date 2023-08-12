create table candidates
(
    email      varchar(255) not null,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    resume     TEXT,
    id         bigint       not null,
    primary key (id)
);
create table employers
(
    email   varchar(255) not null,
    name    varchar(255) not null,
    website varchar(255),
    id      bigint       not null,
    primary key (id)
);
create table responses
(
    id            bigint generated by default as identity,
    cover_letter  TEXT,
    response_time timestamp not null,
    candidate_id  bigint    not null,
    vacancy_id    bigint    not null,
    primary key (id)
);
create table roles
(
    user_id bigint not null,
    roles   varchar(255)
);
create table users
(
    id       bigint not null,
    password varchar(255),
    username varchar(255),
    primary key (id)
);
create table vacancies
(
    id          bigint generated by default as identity,
    active      boolean      not null,
    description TEXT,
    title       varchar(255) not null,
    employer_id bigint       not null,
    primary key (id)
);
alter table if exists candidates
    drop constraint if exists UK_nm2ss73jii2hdupmpphl6agry;

alter table if exists candidates
    add constraint UK_nm2ss73jii2hdupmpphl6agry unique (email);
alter table if exists employers
    drop constraint if exists UK_qc5dry5qy5prsgul22acrt8am;

alter table if exists employers
    add constraint UK_qc5dry5qy5prsgul22acrt8am unique (email);
alter table if exists candidates
    add constraint FKpwx8qcbu3swnypnelf5b8db9j foreign key (id) references users;
alter table if exists employers
    add constraint FKnnl4ba0tc831e25ufip4ek2yq foreign key (id) references users;
alter table if exists responses
    add constraint FK667ojn3xgbfw2jl1gyuw66pu3 foreign key (candidate_id) references candidates;
alter table if exists responses
    add constraint FK9lipsp15yhtdsuyln7dr3k5hm foreign key (vacancy_id) references vacancies;
alter table if exists roles
    add constraint FK97mxvrajhkq19dmvboprimeg1 foreign key (user_id) references users;
alter table if exists vacancies
    add constraint FKoitkfsx04o9py3xgyvbtuxbqf foreign key (employer_id) references employers;
create sequence hibernate_sequence start 1 increment 1;
