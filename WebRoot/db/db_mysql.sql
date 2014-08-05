/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2013/8/28 20:21:59                           */
/*==============================================================*/


drop table if exists ApplyItem;

drop table if exists ItemHits;

drop table if exists declareItem;

drop table if exists individual;

drop table if exists manager;

drop table if exists team;

/*==============================================================*/
/* Table: ApplyItem                                             */
/*==============================================================*/
create table ApplyItem
(
   ApplyItem_ID         numeric(9,0) not null comment '报名事项编号',
   declareItem_ID       numeric(9,0) comment '申报事项编号',
   individual_ID        numeric(12,0) comment '个人账号',
   ApplyItemResult      char(1) comment '报名结果',
   ApplySysDate         date comment '报名系统时间',
   primary key (ApplyItem_ID)
);

alter table ApplyItem comment '报名事项';

/*==============================================================*/
/* Table: ItemHits                                              */
/*==============================================================*/
create table ItemHits
(
   declareItem_ID       numeric(9,0) not null comment '申报事项编号',
   hitNumbers           numeric(5,0) comment '点击数量',
   primary key (declareItem_ID)
);

alter table ItemHits comment '事项点击量
';

/*==============================================================*/
/* Table: declareItem                                           */
/*==============================================================*/
create table declareItem
(
   declareItem_ID       numeric(9,0) not null comment '申报事项编号',
   team_ID              numeric(6,0),
   declareItem_title    varchar(60) comment '申报事项标题',
   declareItem_intro    varchar(1000) comment '申报事项的简介',
   ItemShowItem         date comment '申报事项通知发布时间',
   ItemStopApplyTime    date comment '申报事项停止报名时间',
   declareItemStartTime date comment '事项开始举办时间',
   declareItemStopTime  date comment '事项举办结束时间',
   declareItemApplyMax  numeric(3,0) comment '申报事项报名上线',
   college              bool comment '本科生',
   Graduate             bool comment '研究生',
   staff                bool comment '教职工',
   Attentions           varchar(400) comment '注意事项',
   ItemOrganizer        varchar(40) comment '事项承办方',
   ItemResult           char(1) comment '事项申报结果',
   ItemSysDate          date comment '事项申报系统时间',
   primary key (declareItem_ID)
);

alter table declareItem comment '申报事项编号';

/*==============================================================*/
/* Table: individual                                            */
/*==============================================================*/
create table individual
(
   individual_ID        numeric(12,0) not null comment '个人账号',
   individual_pass      varchar(16),
   individual_name      varchar(8),
   IndDepartment        varchar(30) comment '个人所属院系',
   Indclass             varchar(30) comment '个人所属专业班级',
   individual_Te        numeric(11,0) comment '个人的联系电话',
   individual_Email     varchar(32) comment '个人的电子邮箱',
   primary key (individual_ID)
);

alter table individual comment '个人';

/*==============================================================*/
/* Table: manager                                               */
/*==============================================================*/
create table manager
(
   manager_ID           numeric(6,0) not null comment '管理方账号',
   manager_Pass         varchar(16) comment '管理方密码',
   manager_name         varchar(8) comment '管理方用户名',
   primary key (manager_ID)
);

alter table manager comment '管理方';

/*==============================================================*/
/* Table: team                                                  */
/*==============================================================*/
create table team
(
   team_ID              numeric(6,0) not null comment '团队账号',
   team_pass            varchar(16) comment '密码',
   team_name            varchar(50) comment '团队名称',
   team_principal       varchar(8) comment '团队负责人',
   team_tel             numeric(11,0),
   team_address         varchar(60) comment '联系地址',
   team_email           varchar(32),
   primary key (team_ID)
);

alter table ApplyItem add constraint FK_Relationship_2 foreign key (individual_ID)
      references individual (individual_ID) on delete restrict on update restrict;

alter table ApplyItem add constraint FK_Relationship_3 foreign key (declareItem_ID)
      references declareItem (declareItem_ID) on delete restrict on update restrict;

alter table ItemHits add constraint FK_Relationship_4 foreign key (declareItem_ID)
      references declareItem (declareItem_ID) on delete restrict on update restrict;

alter table declareItem add constraint FK_apply foreign key (team_ID)
      references team (team_ID) on delete restrict on update restrict;

