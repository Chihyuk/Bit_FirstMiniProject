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

--카트 회원가입 userid 외래키 설정
alter table cart add constraint cart_userid_fk
foreign key(userid) references signup(userid);

--카트 아이템 item_num 외래키 설정x 기본키가 아님
alter table cart add constraint cart_item_num_fk
foreign key(item_num) references items(item_num);

-- cart, singup, item 조인
 select username, title,price,amount,price*amount money
-- 회원의 이름과, 상품의 이름,물건 한개의 가격, 
-- 장바구니에 담은 개수, 총 물건의 가격을 검색한다.
   from signup s, items i, cart c
-- member테이블과, product테이블과, cart테이블로부터 검색
   where s.userid = c.userid and i.item_num = c.item_num;
-- signup의 userid와 cart의 userid가 같고, 
-- items의 items_num와 cart의 items_num가 같을때
-- 회원의 아이디와 상품을 사려는 회원이 같고, 
-- 상품의 번호와 카트의 번호가 같을때 

SELECT  cart_id, c.item_num as at, i.item_num as bt, userid, amount*price money
from cart c, items i
where c.item_num = i.item_num;

select cart_id, c.item_num, i.item_num, userid, price, amount*price money
FROM cart c, items i
where c.item_num = i.item_num(+);

SELECT cart_id, i.item_num, userid, price 
FROM cart c, items i
where c.item_num = i.item_num(+);