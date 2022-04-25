create TABLE t_owner(
    id bigint not null,
    first_name VARCHAR(255),
    last_name VARCHAR(255)
);

create TABLE t_pet(
    id bigint not null,
    name VARCHAR(255),
    birth_date date,
    owner_id bigint
);

alter table t_owner add CONSTRAINT CONSTRAINT_1 PRIMARY KEY(id);
alter table t_pet add CONSTRAINT CONSTRAINT_2 PRIMARY KEY(id);

alter table t_pet add CONSTRAINT CONSTRAINT_3 foreign key(owner_id) REFERENCES t_owner(id);

create SEQUENCE petclinic_sequence start with 100;