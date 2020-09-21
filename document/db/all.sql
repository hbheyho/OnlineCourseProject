/*课程章节 - 大章节*/
drop table if exists `chapter`;
create table `chapter`(
    `id` char(8) not null comment 'id',
    `course_id` char(8) comment '课程ID',
    `name` varchar(50) comment '名称',
    primary key (`id`)
) engine=innodb default charset=utf8mb4 comment '大章';

insert into `chapter`(id, course_id, name) values ('00000001', '0000001', '测试大章1');
insert into `chapter`(id, course_id, name) values ('00000002', '0000001', '测试大章2');
insert into `chapter`(id, course_id, name) values ('00000003', '0000001', '测试大章3');
insert into `chapter`(id, course_id, name) values ('00000004', '0000001', '测试大章4');
insert into `chapter`(id, course_id, name) values ('00000005', '0000001', '测试大章1');
insert into `chapter`(id, course_id, name) values ('00000006', '0000001', '测试大章2');
insert into `chapter`(id, course_id, name) values ('00000007', '0000001', '测试大章1');
insert into `chapter`(id, course_id, name) values ('00000008', '0000001', '测试大章2');
insert into `chapter`(id, course_id, name) values ('00000009', '0000001', '测试大章1');
insert into `chapter`(id, course_id, name) values ('00000010', '0000001', '测试大章2');
insert into `chapter`(id, course_id, name) values ('00000011', '0000001', '测试大章1');
insert into `chapter`(id, course_id, name) values ('00000012', '0000001', '测试大章2');
insert into `chapter`(id, course_id, name) values ('00000013', '0000001', '测试大章1');
insert into `chapter`(id, course_id, name) values ('00000014', '0000001', '测试大章2');
select  * from chapter;