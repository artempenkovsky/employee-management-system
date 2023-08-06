create table users
(
    id       bigserial,
    password varchar(255),
    username varchar(255),
    primary key (id)
);
create table users_roles
(
    id      bigserial,
    name    varchar(255),
    user_id bigserial not null,
    role_id bigserial not null,
    primary key (id)
);
alter table if exists users_roles
    add constraint FKaxm251aosah2cgsmksb5xy7if foreign key (role_id) references users_roles;
alter table if exists users_roles
    add constraint FK2o0jvgh89lemvvo17cbqvdxaa foreign key (user_id) references users