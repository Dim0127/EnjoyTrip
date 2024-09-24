use enjoytrip;

select *
from member;

-- delete
-- from member
-- where member_id = 'admin';

select *
from hotplace;

select *
from review;

insert into review(hotplace_id, member_id, score, comment)
values("1", "dewchae1", 3, "good");

insert into member(member_id, member_password, member_name, member_email_id, member_email_domain)
values("dewchae3", "asdf1234", "dewchae", "dewchae", "naver.com");

insert into hotplace(hotplace_id, hotplace_name, hotplace_category, hotplace_lag, hotplace_lat, hotplace_address, hotplace_phone)
value("1", "용용", "음식점", 1, 1, "주소", "번호");

select h.hotplace_id, h.hotplace_name, h.hotplace_category, h.hotplace_lag, h.hotplace_lat, h.hotplace_address, h.hotplace_phone, h.hotplace_createdAt, h.hotplace_deletedAt
from hotplace h join review r
on h.hotplace_id = r.hotplace_id;

select *
from review
where member_id="dewchae1";

alter table review
add column helpful_count int;

update review
set helpful_count = helpful_count+1
where hotplace_id = "1" and member_id = "dewchae1";

select * from review;

SELECT 
            h.hotplace_id, 
            h.hotplace_name, 
            h.hotplace_category, 
            h.hotplace_lag, 
            h.hotplace_lat, 
            h.hotplace_address, 
            h.hotplace_phone, 
            h.hotplace_createdAt, 
            h.hotplace_deletedAt
        FROM hotplace h JOIN review r ON h.hotplace_id = r.hotplace_id
        where h.hotplace_id = #{hotplaceId} and r.member_id = #{memberId);
        
select h.hotplace_id, 
            h.hotplace_name, 
            h.hotplace_category, 
            h.hotplace_lag, 
            h.hotplace_lat, 
            h.hotplace_address, 
            h.hotplace_phone, 
            h.hotplace_createdAt, 
            h.hotplace_deletedAt
from hotplace h join
(select *
from review
where member_id = "admin") r
on h.hotplace_id = r.hotplace_id