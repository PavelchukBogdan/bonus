create table client
(
    id         uuid primary key,
    name       varchar(255) not null ,
    blocked    boolean not null default false,
    blocked_at      timestamp with time zone,
    deleted    boolean default false not null,
    deleted_at timestamp with time zone,
    idempotency_key uuid        not null

);

create table bonus_card
(
    id              uuid primary key,
    code            varchar(3)  not null,
    number          varchar(20) not null,
    blocked    boolean not null default false,
    blocked_at      timestamp with time zone,
    deleted         boolean default false not null,
    deleted_at      timestamp with time zone,
    client_id       uuid        not null,
    idempotency_key uuid        not null

);

create table bonus
(
    id            uuid primary key,
    bonus_card_id uuid not null,
    amount        numeric not null,
    deleted       boolean default false not null,
    deleted_at    timestamp with time zone,
    idempotency_key uuid    not null,
    created_at    timestamp with time zone not null
);

alter table bonus_card
    add foreign key (client_id) references client (id);

alter table bonus
    add foreign key (bonus_card_id) references bonus_card (id);

insert into client(id, name, idempotency_key) values
('dadc0ef0-7b46-48b5-981f-8a5d622eb44a','John', '5161d4ea-8f5b-4003-98ed-0add9929318d'),
('56024305-4e06-498f-8613-67a340a0fe99','David', '04482801-c214-4d6e-b646-73af049ed051');

insert into bonus_card(id, code, number, client_id, idempotency_key) values
('8fe729aa-9885-46d0-ba7f-4bf04453569e', 'USD', '1111111111', 'dadc0ef0-7b46-48b5-981f-8a5d622eb44a','be7824f2-0035-4c83-8a04-73108ed4071d'),
('338dae93-4468-45ab-ae83-9d85c837277a', 'USD', '2222222222', 'dadc0ef0-7b46-48b5-981f-8a5d622eb44a','90c337d2-8a15-4dbf-9e76-619aa81dd568'),
('c13a9e38-6f93-4ee4-9588-f8b66c7c0bab', 'RUB', '3333333333', 'dadc0ef0-7b46-48b5-981f-8a5d622eb44a','fd12a77a-92b6-4b34-9d17-b9942bf79630'),
('d72cfb5b-7d87-4769-95ab-b7f430d98a31', 'RUB', '4444444444', 'dadc0ef0-7b46-48b5-981f-8a5d622eb44a','647b31a4-4da8-45f7-8f5e-19ce21403a12'),
('2edc5b9b-f9c7-4d60-a7b3-6999f64a88d1', 'USD', '5555555555', '56024305-4e06-498f-8613-67a340a0fe99','06a7bbca-70e2-489a-b909-0dbd758ac238'),
('ad23f67a-1a27-49df-b896-c9f4bb65e10a', 'USD', '6666666666', '56024305-4e06-498f-8613-67a340a0fe99','79bbfd4b-e6b8-4778-bb5b-1e8f038cb3ff'),
('acd1e4c5-6396-4317-8dd9-468a0ab9a8a4', 'RUB', '7777777777', '56024305-4e06-498f-8613-67a340a0fe99','2539defc-f275-4e2b-9327-cbdf7499b709'),
('f2e5db86-e33d-46d1-853b-48c47ac73286', 'RUB', '8888888888', '56024305-4e06-498f-8613-67a340a0fe99','149cbd29-9db5-42b4-bc3b-76ab9b4b74df');



insert into bonus(id, bonus_card_id, amount, idempotency_key, created_at) values
('2b7a4436-1b81-44a1-a0d5-009c33831398', '8fe729aa-9885-46d0-ba7f-4bf04453569e', 300, '08e5530c-6253-4681-89da-62f22262e02b', '2021-12-19 10:24:54+02'),
('2d02c5ae-85e8-4483-9607-917dd52a7009', '8fe729aa-9885-46d0-ba7f-4bf04453569e', 230, '1b5a470c-db43-4e38-abd2-abde2bf11d27', '2021-12-22 10:32:11+02'),
('5c2b2ef3-debc-45e1-957e-75aec4f2e188', '8fe729aa-9885-46d0-ba7f-4bf04453569e', 100, '29fdadf0-38dc-4b0b-ad84-639d7d392206', '2021-12-24 11:42:45+02'),
('0c11e884-f5e4-42a9-a4db-e00545ea94cc', '8fe729aa-9885-46d0-ba7f-4bf04453569e', 146, '7e49caaf-5e73-40ce-b6e4-f14bcc7c97b6', '2021-11-20 10:27:51+02'),
('5f938b67-efeb-4546-8bb9-ffd58f1913fb', '8fe729aa-9885-46d0-ba7f-4bf04453569e', 532, '7b87b4e0-dd8c-48fe-b31c-23b3b5dde219', '2021-11-27 11:14:12+02'),
('bf8f7f86-add9-4130-9955-d60cad28966e', 'c13a9e38-6f93-4ee4-9588-f8b66c7c0bab', 532, 'ae6cabb5-20b8-434a-ad45-dd550271d98b', '2021-10-28 14:14:12+02');
