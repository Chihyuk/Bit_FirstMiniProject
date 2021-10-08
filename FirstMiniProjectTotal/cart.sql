select * from signup;
select * from orders;
select * from cart;
select * from items;
select * from orders;

--DROP TABLE cart purge;

create table cart
(
  cart_id number(20) primary key,
  userid varchar(20) not null,
  item_num number not null,
  amount number not null
);

--īƮ ȸ������ userid �ܷ�Ű ����
alter table cart add constraint cart_userid_fk
foreign key(userid) references signup(userid);

--īƮ ������ item_num �ܷ�Ű ����x �⺻Ű�� �ƴ�
alter table cart add constraint cart_item_num_fk
foreign key(item_num) references items(item_num);

-- cart, singup, item ����
 select username, title,price,amount,price*amount money
-- ȸ���� �̸���, ��ǰ�� �̸�,���� �Ѱ��� ����, 
-- ��ٱ��Ͽ� ���� ����, �� ������ ������ �˻��Ѵ�.
   from signup s, items i, cart c
-- member���̺��, product���̺��, cart���̺�κ��� �˻�
   where s.userid = c.userid and i.item_num = c.item_num;
-- signup�� userid�� cart�� userid�� ����, 
-- items�� items_num�� cart�� items_num�� ������
-- ȸ���� ���̵�� ��ǰ�� ����� ȸ���� ����, 
-- ��ǰ�� ��ȣ�� īƮ�� ��ȣ�� ������ 

SELECT  cart_id, c.item_num as at, i.item_num as bt, userid, amount*price money
from cart c, items i
where c.item_num = i.item_num;

select cart_id, c.item_num, i.item_num, userid, price, amount*price money
FROM cart c, items i
where c.item_num = i.item_num(+);

SELECT cart_id, i.item_num, userid, price 
FROM cart c, items i
where c.item_num = i.item_num(+);