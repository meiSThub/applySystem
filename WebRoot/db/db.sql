/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     2013/8/29 10:21:42                           */
/*==============================================================*/


alter table "ApplyItem"
   drop constraint FK_APPLYITE_RELATIONS_INDIVIDU;

alter table "ApplyItem"
   drop constraint FK_APPLYITE_RELATIONS_DECLAREI;

alter table "ItemHits"
   drop constraint FK_ITEMHITS_RELATIONS_DECLAREI;

alter table "declareItem"
   drop constraint FK_DECLAREI_APPLY_TEAM;

drop table "ApplyItem" cascade constraints;

drop table "ItemHits" cascade constraints;

drop table "declareItem" cascade constraints;

drop table "individual" cascade constraints;

drop table "manager" cascade constraints;

drop table "team" cascade constraints;

/*==============================================================*/
/* Table: "ApplyItem"                                           */
/*==============================================================*/
create table "ApplyItem" 
(
   "ApplyItem_ID"       NUMBER(9,0)          not null,
   "declareItem_ID"     NUMBER(9,0),
   "individual_ID"      NUMBER(12,0),
   "ApplyItemResult"    CHAR(1),
   "ApplySysDate"       DATE,
   constraint PK_APPLYITEM primary key ("ApplyItem_ID")
);

comment on table "ApplyItem" is
'报名事项';

comment on column "ApplyItem"."ApplyItem_ID" is
'报名事项编号';

comment on column "ApplyItem"."declareItem_ID" is
'申报事项编号';

comment on column "ApplyItem"."individual_ID" is
'个人账号';

comment on column "ApplyItem"."ApplyItemResult" is
'报名结果';

comment on column "ApplyItem"."ApplySysDate" is
'报名系统时间';

/*==============================================================*/
/* Table: "ItemHits"                                            */
/*==============================================================*/
create table "ItemHits" 
(
   "declareItem_ID"     NUMBER(9,0)          not null,
   "hitNumbers"         NUMBER(5,0),
   constraint PK_ITEMHITS primary key ("declareItem_ID")
);

comment on table "ItemHits" is
'事项点击量
';

comment on column "ItemHits"."declareItem_ID" is
'申报事项编号';

comment on column "ItemHits"."hitNumbers" is
'点击数量';

/*==============================================================*/
/* Table: "declareItem"                                         */
/*==============================================================*/
create table "declareItem" 
(
   "declareItem_ID"     NUMBER(9,0)          not null,
   "team_ID"            NUMBER(6,0),
   "declareItem_title"  VARCHAR2(60),
   "declareItem_intro"  VARCHAR2(1000),
   "ItemShowItem"       DATE,
   "ItemStopApplyTime"  DATE,
   "declareItemStartTime" DATE,
   "declareItemStopTime" DATE,
   "declareItemApplyMax" NUMBER(3,0),
   "college"            SMALLINT,
   "Graduate"           SMALLINT,
   "staff"              SMALLINT,
   "Attentions"         VARCHAR2(400),
   "ItemOrganizer"      VARCHAR2(40),
   "ItemResult"         CHAR(1),
   "ItemSysDate"        DATE,
   constraint PK_DECLAREITEM primary key ("declareItem_ID")
);

comment on table "declareItem" is
'申报事项编号';

comment on column "declareItem"."declareItem_ID" is
'申报事项编号';

comment on column "declareItem"."declareItem_title" is
'申报事项标题';

comment on column "declareItem"."declareItem_intro" is
'申报事项的简介';

comment on column "declareItem"."ItemShowItem" is
'申报事项通知发布时间';

comment on column "declareItem"."ItemStopApplyTime" is
'申报事项停止报名时间';

comment on column "declareItem"."declareItemStartTime" is
'事项开始举办时间';

comment on column "declareItem"."declareItemStopTime" is
'事项举办结束时间';

comment on column "declareItem"."declareItemApplyMax" is
'申报事项报名上线';

comment on column "declareItem"."college" is
'本科生';

comment on column "declareItem"."Graduate" is
'研究生';

comment on column "declareItem"."staff" is
'教职工';

comment on column "declareItem"."Attentions" is
'注意事项';

comment on column "declareItem"."ItemOrganizer" is
'事项承办方';

comment on column "declareItem"."ItemResult" is
'事项申报结果';

comment on column "declareItem"."ItemSysDate" is
'事项申报系统时间';

/*==============================================================*/
/* Table: "individual"                                          */
/*==============================================================*/
create table "individual" 
(
   "individual_ID"      NUMBER(12,0)         not null,
   "individual_pass"    VARCHAR2(16),
   "individual_name"    VARCHAR2(8),
   "IndDepartment"      VARCHAR2(30),
   "Indclass"           VARCHAR2(30),
   "individual_Te"      NUMBER(11,0),
   "individual_Email"   VARCHAR2(32),
   constraint PK_INDIVIDUAL primary key ("individual_ID")
);

comment on table "individual" is
'个人';

comment on column "individual"."individual_ID" is
'个人账号';

comment on column "individual"."IndDepartment" is
'个人所属院系';

comment on column "individual"."Indclass" is
'个人所属专业班级';

comment on column "individual"."individual_Te" is
'l个人的联系电话';

comment on column "individual"."individual_Email" is
'个人的电子邮箱';

/*==============================================================*/
/* Table: "manager"                                             */
/*==============================================================*/
create table "manager" 
(
   "manager_ID"         NUMBER(6,0)          not null,
   "manager_Pass"       VARCHAR2(16),
   "manager_name"       VARCHAR2(8),
   constraint PK_MANAGER primary key ("manager_ID")
);

comment on table "manager" is
'管理方';

comment on column "manager"."manager_ID" is
'管理方账号';

comment on column "manager"."manager_Pass" is
'管理方密码';

comment on column "manager"."manager_name" is
'管理方用户名';

/*==============================================================*/
/* Table: "team"                                                */
/*==============================================================*/
create table "team" 
(
   "team_ID"            NUMBER(6,0)          not null,
   "team_pass"          VARCHAR2(16),
   "team_name"          VARCHAR2(50),
   "team_principal"     VARCHAR2(8),
   "team_tel"           NUMBER(11,0),
   "team_address"       VARCHAR2(60),
   "team_email"         VARCHAR2(32),
   constraint PK_TEAM primary key ("team_ID")
);

comment on column "team"."team_ID" is
'团队账号';

comment on column "team"."team_pass" is
'密码';

comment on column "team"."team_name" is
'团队名称';

comment on column "team"."team_principal" is
'团队负责人';

comment on column "team"."team_address" is
'联系地址';

alter table "ApplyItem"
   add constraint FK_APPLYITE_RELATIONS_INDIVIDU foreign key ("individual_ID")
      references "individual" ("individual_ID");

alter table "ApplyItem"
   add constraint FK_APPLYITE_RELATIONS_DECLAREI foreign key ("declareItem_ID")
      references "declareItem" ("declareItem_ID");

alter table "ItemHits"
   add constraint FK_ITEMHITS_RELATIONS_DECLAREI foreign key ("declareItem_ID")
      references "declareItem" ("declareItem_ID");

alter table "declareItem"
   add constraint FK_DECLAREI_APPLY_TEAM foreign key ("team_ID")
      references "team" ("team_ID");

