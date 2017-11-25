DROP TABLE IF EXISTS customer;

create table customer (
    ID          serial,
    first_name  varchar(40),
    last_name   varchar(40),
    company     varchar(40),
    created_at  timestamp NOT NULL DEFAULT now(),
    updated_at  timestamp,
    PRIMARY KEY (ID)
);