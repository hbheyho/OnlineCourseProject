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


/*课程章节 - 小节*/
drop table if exists `section`;
create table `section`(
    `id` char(8) not null default '' comment 'id',
    `title` varchar(50) not null comment '标题',
    `course_id` char(8) comment '课程ID',
    `chapter_id` char (8) comment '大章ID',
    `video` varchar (200) comment '视频',
    `time` int comment '时长/单位秒',
    `charge` char (1) comment '收费 - C; 免费 - F',
    `sort` int comment '顺序',
    `created_at` datetime(3) comment '创建时间',
    `updated_at` datetime(3) comment '修改时间',
    primary key (`id`)
) engine=innodb default charset=utf8mb4 comment '小节';
insert into `section`(id, title, course_id, chapter_id, video, time, charge, sort, created_at, updated_at) VALUES
('00000001','测试小节','00000001','00000006','',500,'F',1,now(), now());


/*课程章节 - 小节*/
drop table if exists `course`;
create table `course`(
    `id` char(8) not null default '' comment 'id',
    `name` varchar(50) not null comment '名称',
    `summary` varchar(2000) comment '概述',
    `time` int default 0 comment '时长/单位秒',
    `price` decimal (8,2) not null default 0.00 comment '价格(元)',
    `image` varchar(100) comment '封面',
    `level` char(1) not null comment '级别|枚举[CourseLevelEnum]: ONE("1", "初级"),TWO("2", "中级"),THREE("3", "高级")',
    `charge` char (1) comment '收费|枚举[CourseChargeEnum]: CHARGE("C", "收费"), FREE("F", "免费")',
    `status` char (1) comment '状态|枚举[CourseStatusEnum]: PUBLISH("P", "发布"), DRAFT("D", "草稿")',
    `enroll` int default  0 comment '报名数',
    `sort` int comment '顺序',
    `created_at` datetime(3) comment '创建时间',
    `updated_at` datetime(3) comment '修改时间',
    primary key (`id`)
) engine=innodb default charset=utf8mb4 comment '课程';

insert into `course`(id, name, summary, time, price, image, level, charge,status, enroll, sort, created_at, updated_at) VALUES
('00000001','测试课程','测试测试','999','99.9',"图片",'1',"F","P",999,9,now(), now());
