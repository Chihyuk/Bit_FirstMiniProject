create table signup
(
  userid varchar2(10) primary key,
  passwd varchar2(10) not null,
  username varchar2(10) not null,
  birth varchar2(6),
  phone varchar2(11) not null
);