create table candidates
(
    id         bigserial    not null,
    email      varchar(255) not null,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    resume     TEXT,
    primary key (id)
);

create table employers
(
    id      bigserial    not null,
    email   varchar(255) not null,
    name    varchar(255) not null,
    website varchar(255),
    primary key (id)
);
create table responses
(
    id            bigserial    not null,
    cover_letter  TEXT,
    response_time timestamp(6) not null,
    candidate_id  bigint       not null,
    vacancy_id    bigint       not null,
    primary key (id)
);
create table vacancies
(
    id          bigserial    not null,
    active      boolean      not null,
    description TEXT,
    title       varchar(255) not null,
    employer_id bigint       not null,
    primary key (id)
);
alter table if exists candidates drop constraint if exists UK_nm2ss73jii2hdupmpphl6agry;
alter table if exists candidates add constraint UK_nm2ss73jii2hdupmpphl6agry unique (email);
alter table if exists employers drop constraint if exists UK_qc5dry5qy5prsgul22acrt8am;
alter table if exists employers add constraint UK_qc5dry5qy5prsgul22acrt8am unique (email);
alter table if exists responses add constraint FK667ojn3xgbfw2jl1gyuw66pu3 foreign key (candidate_id) references candidates;
alter table if exists responses add constraint FK9lipsp15yhtdsuyln7dr3k5hm foreign key (vacancy_id) references vacancies;
alter table if exists vacancies add constraint FKoitkfsx04o9py3xgyvbtuxbqf foreign key (employer_id) references employers;
