use enjoytrip;

insert into 
member(member_id, member_name, member_password, member_email_id, member_email_domain)
values ("admin", "admin", "1234", "ssafy",  "ssafy");

select *
from member;

delete
from hotplace
where hotplace_id = "1676926090";

select *
from hotplace;