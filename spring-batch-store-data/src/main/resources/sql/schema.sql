drop table if exists spring_batch.rest_area_store;

create table spring_batch.rest_area_store
(
    id    bigint       not null primary key,
    store_name  varchar(255) null,
    location point     null
);
