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
select  * from chapter;