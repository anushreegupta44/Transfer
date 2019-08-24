CREATE SCHEMA IF NOT EXISTS BANK;
create table nim(
account_number uuid primary key,
balance numeric);
insert into account (account_number , balance) values (8, 3000.00);
insert into account (account_number , balance) values (GENERATE_UUID(), 4000.00);
