/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/11/7 9:53:25                            */
/*==============================================================*/

DROP DATABASE IF EXISTS cityinsight;

CREATE DATABASE cityinsight default character set utf8 COLLATE utf8_general_ci;

use cityinsight;


drop table if exists career;

drop table if exists cibase_education;

drop table if exists cibase_files;

drop table if exists cibase_location;

drop table if exists cibase_svc_category;

drop table if exists cibase_svc_category_attr;

drop table if exists cibase_svc_category_type;

drop table if exists cibase_user;

drop table if exists cibase_userlables;

drop table if exists cidev_application;

drop table if exists cidev_application_version;

drop table if exists cidev_application_version_audit;

drop table if exists cidev_base_files_assoc;

drop table if exists cidev_co_channel;

drop table if exists cidev_co_market_category;

drop table if exists cidev_co_order;

drop table if exists cidev_co_tnt;

drop table if exists cidev_my_purchase_apps;

drop table if exists cidev_popular_apps;

drop table if exists cidev_project;

drop table if exists cidev_recommend_apps;

drop table if exists cidev_type_info;

drop table if exists cimobi_customescenario_service_assoc;

drop table if exists cimobi_myfootprint;

drop table if exists cimobi_recommend_services;

drop table if exists cimobi_svc;

drop table if exists cimobi_svc_avgscore;

drop table if exists cimobi_svc_invoke;

drop table if exists cimobi_svc_remark;

drop table if exists cimobi_svc_type;

drop table if exists cimobi_usercustom_scenario;

drop table if exists comobi_svc_loc_assoc;

/*==============================================================*/
/* Table: career                                                */
/*==============================================================*/
create table career
(
   career_id            varchar(64) not null,
   user_id              varchar(64),
   company              varchar(100),
   department           varchar(80),
   position             varchar(80),
   begin_date           date,
   end_date             date,
   description          text comment '工作内容的描述',
   created_at           timestamp DEFAULT CURRENT_TIMESTAMP,
   updated_at           timestamp DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table career comment '职业经历';

alter table career
   add primary key (career_id);

/*==============================================================*/
/* Table: cibase_education                                      */
/*==============================================================*/
create table cibase_education
(
   edu_id               varchar(64) not null,
   user_id              varchar(64) comment '用户id，外键，对应于user的id',
   school               varchar(255),
   major                varchar(255),
   degree               varchar(60),
   entered_time         date,
   graduated_time       date,
   description          varchar(500),
   created_at           timestamp DEFAULT CURRENT_TIMESTAMP,
   updated_at           timestamp DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

alter table cibase_education
   add primary key (edu_id);

/*==============================================================*/
/* Table: cibase_files                                          */
/*==============================================================*/
create table cibase_files
(
   file_id              varchar(64) not null,
   file_name            varchar(300) comment '文件名称',
   file_path            varchar(300) comment '文件在存储 介质上的存放路径',
   file_size            varchar(64) comment '文件大小',
   file_suffix          varchar(30) comment '文件后缀名',
   file_content_type    varchar(60) comment '文件MIME类型',
   file_url             text comment '文件的访问url',
   created_by           varchar(64) comment '创建人',
   created_at           timestamp DEFAULT CURRENT_TIMESTAMP comment '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

alter table cibase_files comment '开发者上传到云端的应用的产出物（一般是一个zip文件）、图片、logo等.';

alter table cibase_files
   add primary key (file_id);

/*==============================================================*/
/* Table: cibase_location                                       */
/*==============================================================*/
create table cibase_location
(
   loc_id               varchar(64) not null,
   loc_pid              varchar(64),
   loc_code             varchar(64),
   loc_name             varchar(255),
   loc_desc             varchar(255),
   created_at           timestamp DEFAULT CURRENT_TIMESTAMP,
   updated_at           timestamp DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

alter table cibase_location comment '地理位置区域表，需要新增初一条始化数据，表示全部
loc_id:0086;loc_code:0086;loc';

alter table cibase_location
   add primary key (loc_id);

/*==============================================================*/
/* Table: cibase_svc_category                                   */
/*==============================================================*/
create table cibase_svc_category
(
   svc_category_id      varchar(60) not null,
   svc_category_pid     varchar(60),
   category_name        varchar(60) comment '分类名称 or 街道名称 or 区域名称',
   category_type_id     varchar(20) comment '0：服务类别；1：街道',
   service_type         varchar(20) comment '其值是cso或者是co',
   description          varchar(255),
   created_at           timestamp DEFAULT CURRENT_TIMESTAMP,
   updated_at           timestamp DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

alter table cibase_svc_category comment '服务分类';

alter table cibase_svc_category
   add primary key (svc_category_id);

/*==============================================================*/
/* Table: cibase_svc_category_attr                              */
/*==============================================================*/
create table cibase_svc_category_attr
(
   svc_cat_attr_id      varchar(20) not null,
   svc_category_id      varchar(60),
   attr_desc            varchar(60),
   attr_value           varchar(60),
   created_at           timestamp DEFAULT CURRENT_TIMESTAMP,
   created_by           timestamp DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

alter table cibase_svc_category_attr comment '服务类目扩展属性，对于二级分类（道路）需要有初始化数据，其他可自由扩展。
1、attr_desc:左上角x坐';

alter table cibase_svc_category_attr
   add primary key (svc_cat_attr_id);

/*==============================================================*/
/* Table: cibase_svc_category_type                              */
/*==============================================================*/
create table cibase_svc_category_type
(
   category_type_id     varchar(20) not null comment '类目类型',
   type_desc            varchar(60) comment '类型描述',
   type_value           varchar(60) comment '类型值',
   created_at           timestamp DEFAULT CURRENT_TIMESTAMP,
   updated_at           timestamp DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

alter table cibase_svc_category_type comment '服务类型字典表，至少需要以下初始化数据
1、type_desc：顶级分类；type_value：top_le';

alter table cibase_svc_category_type
   add primary key (category_type_id);

/*==============================================================*/
/* Table: cibase_user                                           */
/*==============================================================*/
create table cibase_user
(
   user_id              varchar(64) not null comment '主键',
   username             varchar(60) not null comment '姓名',
   password             varchar(60),
   nickname             varchar(60),
   ID_no                varchar(18),
   indestry             varchar(255) comment '行业',
   position             varchar(255) comment '职位',
   workyears            numeric(8,0) comment '工作年限',
   address              varchar(255),
   company              varchar(255),
   mobile               varchar(20),
   email                varchar(60),
   birthday             date,
   gender               varchar(2) comment '性别：男/女',
   user_type            smallint comment '按位与计算标识：001=1最终用户，010=2开发者，100=4系统管理员
            011=3表示既是最终用户，又是开发者',
   created_at           timestamp DEFAULT CURRENT_TIMESTAMP,
   created_by           varchar(64),
   updated_at           timestamp DEFAULT CURRENT_TIMESTAMP,
   updated_by           varchar(64)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

alter table cibase_user
   add primary key (user_id);

/*==============================================================*/
/* Table: cibase_userlables                                     */
/*==============================================================*/
create table cibase_userlables
(
   lable_id             varchar(64) not null,
   content              varchar(300) comment 'json格式的字符串信息',
   user_id              varchar(64),
   created_at           timestamp DEFAULT CURRENT_TIMESTAMP,
   created_by           varchar(64),
   updated_at           timestamp DEFAULT CURRENT_TIMESTAMP,
   updated_by           varchar(64)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

alter table cibase_userlables comment '用户标签属性';

alter table cibase_userlables
   add primary key (lable_id);

/*==============================================================*/
/* Table: cidev_application                                     */
/*==============================================================*/
create table cidev_application
(
   app_id               varchar(64) not null,
   project_id           varchar(64) comment '项目ID',
   app_name             varchar(60) comment '应用名称',
   app_type             varchar(60) comment '应用发版后的类型：控件(co)、服务(cso)等',
   svc_category_id      varchar(64) comment '应用分类，co取自cidev_co_market_category表；cso取自cibase_svc_category表',
   is_onlined           tinyint comment '是否是上线的应用，0：上线，1：下线',
   published_channel    tinyint comment '发布渠道：1-发布到链城；2-链城合资渠道；3-自定义渠道',
   project_resource     tinyint comment '项目来源：1-编辑器；2-外包URL',
   outer_app_url        varchar(300) comment '发布服务到链城时，如果是外包URL，此字段记录URL的值',
   created_at           timestamp DEFAULT CURRENT_TIMESTAMP comment '创建时间',
   created_by           varchar(60) comment '创建者'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

alter table cidev_application comment '应用的信息';

alter table cidev_application
   add primary key (app_id);

/*==============================================================*/
/* Table: cidev_application_version                             */
/*==============================================================*/
create table cidev_application_version
(
   version_id           varchar(64) not null,
   app_id               varchar(64),
   app_version          varchar(20) comment '版本',
   description          varchar(256) comment '应用描述',
   version_info         varchar(256) comment '版本信息',
   keyword              varchar(256) comment '关键字',
   copyright            text comment '版权信息',
   link_man             varchar(60) comment '联系人姓名',
   mobile               varchar(20) comment '联系电话',
   address              text comment '联系地址',
   published_channel    tinyint comment '发布渠道：1-发布到链城；2-链城合资渠道；3-自定义渠道',
   co_channel_id        varchar(64) comment '当发布渠道为链城合作渠道时（published_channel=2）, 此字段不可为空，其值是合作渠道表中相应渠道的主键值',
   current_audit_status tinyint comment '当前版本的审核状态',
   is_onlined_version   tinyint comment '是否是在线提供功能服务的版本信息，目前实现是，每个应用是只有一个提供在线使用下载的版本。0：表示在线使用，1：不在线使用。只有一个版本的信息的值是0，其它都是1.',
   created_at           timestamp DEFAULT CURRENT_TIMESTAMP comment '创建时间',
   created_by           varchar(60) comment '创建者'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

alter table cidev_application_version comment '应用的版本信息';

alter table cidev_application_version
   add primary key (version_id);

/*==============================================================*/
/* Table: cidev_application_version_audit                       */
/*==============================================================*/
create table cidev_application_version_audit
(
   audit_id             varchar(64) not null comment '审核人id',
   version_id           varchar(64) comment '版本id',
   audit_info           text comment '审核结果说明',
   audit_status         tinyint default 1 comment '审核状态。0： ''待审核'',   1, ''审批通过'',    2, ''审批拒绝'',',
   audited_by           varchar(64) comment '由谁审核',
   created_at           timestamp DEFAULT CURRENT_TIMESTAMP comment '创建时间',
   updated_at           timestamp DEFAULT CURRENT_TIMESTAMP comment '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

alter table cidev_application_version_audit comment '版本审核信息';

alter table cidev_application_version_audit
   add primary key (audit_id);

/*==============================================================*/
/* Table: cidev_base_files_assoc                                */
/*==============================================================*/
create table cidev_base_files_assoc
(
   file_assoc_id        varchar(64) not null,
   version_id           varchar(64) not null comment '版本id或者是工程的id',
   file_id              varchar(64) not null comment '文件id',
   file_type            tinyint comment '文件类型。0-应用的图标；1-应用的截屏（5张图）；2－工程项目文件；3-二维码图片。',
   file_seq             tinyint default 1 comment '文件的排列顺序。其默认值是1。对于icon，1-详情页的图标；2-推荐用的图标；3-最新应用的图标；4-最受欢迎的图标。对于截屏来说，它表示了截屏的位置，比如有5个截屏，则其每个图片的对应顺序是1、2、3、4、5.'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

alter table cidev_base_files_assoc comment '版本和文件关联表';

alter table cidev_base_files_assoc
   add primary key (file_assoc_id);

/*==============================================================*/
/* Table: cidev_co_channel                                      */
/*==============================================================*/
create table cidev_co_channel
(
   channel_id           varchar(64) not null,
   channel_name         varchar(60) comment '工程名称',
   description          varchar(255) comment '文字描述',
   created_at           timestamp DEFAULT CURRENT_TIMESTAMP comment '创建时间',
   updated_at           timestamp DEFAULT CURRENT_TIMESTAMP comment '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

alter table cidev_co_channel comment '合作渠道信息表';

alter table cidev_co_channel
   add primary key (channel_id);

/*==============================================================*/
/* Table: cidev_co_market_category                              */
/*==============================================================*/
create table cidev_co_market_category
(
   svc_category_id      varchar(64) not null comment '版本id',
   category_name        varchar(300) comment '分类名称',
   category_desc        text comment '说明',
   category_status      tinyint default 1 comment '状态。0-启用；1-禁用'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

alter table cidev_co_market_category comment 'co市场分类表';

alter table cidev_co_market_category
   add primary key (svc_category_id);

/*==============================================================*/
/* Table: cidev_co_order                                        */
/*==============================================================*/
create table cidev_co_order
(
   order_id             varchar(64) not null comment '版本id',
   user_id              varchar(64) comment '购买者',
   version_id           varchar(64) comment '购买的应用版本',
   price                double comment '价格',
   order_curr_status    tinyint default 1 comment '订单状态。1.待付款2.付款成功3.已取消4.处理中5.完成 6.已生效 7.已删除
            '
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

alter table cidev_co_order comment 'co购买订单表';

alter table cidev_co_order
   add primary key (order_id);

/*==============================================================*/
/* Table: cidev_co_tnt                                          */
/*==============================================================*/
create table cidev_co_tnt
(
   tnt_id               varchar(64) not null,
   order_id             varchar(64),
   order_status         tinyint
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

alter table cidev_co_tnt comment '订单状态追踪表';

alter table cidev_co_tnt
   add primary key (tnt_id);

/*==============================================================*/
/* Table: cidev_my_purchase_apps                                */
/*==============================================================*/
create table cidev_my_purchase_apps
(
   purchase_id          varchar(64) not null comment '主键',
   user_id              varchar(64) comment '订阅者',
   app_id               varchar(64) comment '应用控件id',
   purchase_at          timestamp DEFAULT CURRENT_TIMESTAMP comment '订阅/购买时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

alter table cidev_my_purchase_apps comment '版本和文件关联表';

alter table cidev_my_purchase_apps
   add primary key (purchase_id);

/*==============================================================*/
/* Table: cidev_popular_apps                                    */
/*==============================================================*/
create table cidev_popular_apps
(
   popular_app_id       varchar(64) not null comment '主键',
   app_id               varchar(64) comment '应用控件id',
   created_at           timestamp DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

alter table cidev_popular_apps comment '推荐的服务';

alter table cidev_popular_apps
   add primary key (popular_app_id);

/*==============================================================*/
/* Table: cidev_project                                         */
/*==============================================================*/
create table cidev_project
(
   project_id           varchar(64) not null,
   project_name         varchar(60) comment '工程名称',
   project_type         varchar(60) comment '工程类型：co 或者cso',
   description          varchar(255) comment '文字描述',
   owner_id             varchar(64) comment '工程的所有者',
   created_at           timestamp DEFAULT CURRENT_TIMESTAMP comment '创建时间',
   updated_at           timestamp DEFAULT CURRENT_TIMESTAMP comment '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

alter table cidev_project comment '工程信息';

alter table cidev_project
   add primary key (project_id);

/*==============================================================*/
/* Table: cidev_recommend_apps                                  */
/*==============================================================*/
create table cidev_recommend_apps
(
   recomm_id            varchar(64) not null comment '主键',
   user_id              varchar(64) comment '目标用户',
   app_id               varchar(64) comment '应用控件id',
   recomm_at            timestamp DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

alter table cidev_recommend_apps comment '推荐的服务';

alter table cidev_recommend_apps
   add primary key (recomm_id);

/*==============================================================*/
/* Table: cidev_type_info                                       */
/*==============================================================*/
create table cidev_type_info
(
   type_info_id         varchar(64) not null,
   p_key                varchar(60) comment '映射键的值',
   p_value              varchar(60) comment '映射值',
   created_at           timestamp DEFAULT CURRENT_TIMESTAMP comment '创建时间',
   created_by           varchar(64) comment '创建人',
   updated_at           timestamp DEFAULT CURRENT_TIMESTAMP comment '更新时间',
   updated_by           varchar(64) comment '更新人'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

alter table cidev_type_info comment '类型信息映射表，目前收录了两项cso（轻应用/微服务）、co（控件）。';

alter table cidev_type_info
   add primary key (type_info_id);

/*==============================================================*/
/* Table: cimobi_customescenario_service_assoc                  */
/*==============================================================*/
create table cimobi_customescenario_service_assoc
(
   cus_serv_rel_id      varchar(64) not null,
   svc_id               varchar(64),
   scenario_id          varchar(64),
   created_at           timestamp DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

alter table cimobi_customescenario_service_assoc comment '自定义场景和服务关联表';

alter table cimobi_customescenario_service_assoc
   add primary key (cus_serv_rel_id);

/*==============================================================*/
/* Table: cimobi_myfootprint                                    */
/*==============================================================*/
create table cimobi_myfootprint
(
   footprint_id         varchar(64) not null,
   user_id              varchar(64),
   service_ids          varchar(300) comment '逗号分隔的service_id',
   created_at           timestamp DEFAULT CURRENT_TIMESTAMP,
   created_by           varchar(64)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

alter table cimobi_myfootprint
   add primary key (footprint_id);

/*==============================================================*/
/* Table: cimobi_recommend_services                             */
/*==============================================================*/
create table cimobi_recommend_services
(
   rec_serv_id          varchar(64) not null,
   user_id              varchar(64),
   svc_id               varchar(64),
   service_name         varchar(64),
   recommend_type       varchar(30),
   created_at           timestamp DEFAULT CURRENT_TIMESTAMP,
   created_by           varchar(64)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

alter table cimobi_recommend_services comment '根据用户标签推荐的（定制的/最新的）服务
最新的服务：by_latest
定制推荐的服务：by_';

alter table cimobi_recommend_services
   add primary key (rec_serv_id);

/*==============================================================*/
/* Table: cimobi_svc                                            */
/*==============================================================*/
create table cimobi_svc
(
   svc_id               varchar(64) not null,
   svc_name             varchar(60),
   svc_category_id      varchar(60) comment '上一级服务分类的id',
   owner                varchar(255) comment '软件的开发者',
   serial_no            varchar(20) comment '服务编号',
   keyword              varchar(255) comment '关键字，空格隔开',
   description          varchar(255) comment '文字描述',
   icon_url             varchar(300) comment '图标的url',
   created_at           timestamp DEFAULT CURRENT_TIMESTAMP,
   created_by           varchar(60),
   updated_at           timestamp DEFAULT CURRENT_TIMESTAMP,
   updated_by           varchar(60)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

alter table cimobi_svc comment '服务表，服务默认全国通用';

alter table cimobi_svc
   add primary key (svc_id);

/*==============================================================*/
/* Table: cimobi_svc_avgscore                                   */
/*==============================================================*/
create table cimobi_svc_avgscore
(
   cim_svc_id           varchar(64) not null,
   svc_avgscore_id      varchar(20) not null,
   svc_id               varchar(20),
   average_score        float,
   created_at           timestamp DEFAULT CURRENT_TIMESTAMP,
   updated_at           timestamp DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

alter table cimobi_svc_avgscore comment '服务的平均得分';

alter table cimobi_svc_avgscore
   add primary key (svc_avgscore_id);

/*==============================================================*/
/* Table: cimobi_svc_invoke                                     */
/*==============================================================*/
create table cimobi_svc_invoke
(
   svc_invoke_id        varchar(64) not null,
   svc_id               varchar(64),
   svc_name             varchar(64) comment '冗余',
   svc_version          varchar(60),
   endpoint             varchar(300) comment '服务访问的端点（ip/domain + port）',
   svc_type_id          varchar(64) comment '服务类型',
   invoke_desc          text comment '调用参数说明',
   created_at           timestamp DEFAULT CURRENT_TIMESTAMP,
   created_by           varchar(64),
   updated_at           timestamp DEFAULT CURRENT_TIMESTAMP,
   updated_by           varchar(64)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

alter table cimobi_svc_invoke comment '服务类型、调用及参数说明';

alter table cimobi_svc_invoke
   add primary key (svc_invoke_id);

/*==============================================================*/
/* Table: cimobi_svc_remark                                     */
/*==============================================================*/
create table cimobi_svc_remark
(
   svc_remark_id        varchar(20) not null,
   svc_id               varchar(64),
   user_id              varchar(64) comment '评分用户的id',
   user_name            varchar(30) comment '评分用户的姓名',
   score                float,
   comments             varchar(255),
   op_time              timestamp DEFAULT CURRENT_TIMESTAMP,
   created_at           timestamp DEFAULT CURRENT_TIMESTAMP,
   updated_at           timestamp DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

alter table cimobi_svc_remark
   add primary key (svc_remark_id);

/*==============================================================*/
/* Table: cimobi_svc_type                                       */
/*==============================================================*/
create table cimobi_svc_type
(
   svc_type_id          varchar(64) not null,
   type_desc            varchar(60) comment '服务类型说明',
   type_value           varchar(60) comment '类型值',
   created_at           timestamp DEFAULT CURRENT_TIMESTAMP,
   updated_at           timestamp DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

alter table cimobi_svc_type comment '服务类型字典表，需要初始化数据：
1、type_desc:平台自研服务;type_value:ci_svc
';

alter table cimobi_svc_type
   add primary key (svc_type_id);

/*==============================================================*/
/* Table: cimobi_usercustom_scenario                            */
/*==============================================================*/
create table cimobi_usercustom_scenario
(
   scenario_id          varchar(64) not null,
   name                 varchar(40),
   user_id              varchar(64),
   created_at           timestamp DEFAULT CURRENT_TIMESTAMP,
   created_by           varchar(64),
   updated_at           timestamp DEFAULT CURRENT_TIMESTAMP,
   updated_by           varchar(64)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

alter table cimobi_usercustom_scenario comment '用户自定义微场景';

alter table cimobi_usercustom_scenario
   add primary key (scenario_id);

/*==============================================================*/
/* Table: comobi_svc_loc_assoc                                  */
/*==============================================================*/
create table comobi_svc_loc_assoc
(
   svc_loc_id           varchar(64) not null,
   svc_id               varchar(64) not null,
   loc_id               varchar(64) not null,
   created_at           timestamp DEFAULT CURRENT_TIMESTAMP,
   updated_at           timestamp DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

alter table comobi_svc_loc_assoc comment '服务与地区对应关系表';

alter table comobi_svc_loc_assoc
   add primary key (svc_loc_id);

alter table career add constraint FK_职业关联 foreign key (user_id)
      references cibase_user (user_id) on delete restrict on update restrict;

alter table cibase_education add constraint FK_Relationship_2 foreign key (user_id)
      references cibase_user (user_id) on delete restrict on update restrict;

alter table cibase_svc_category add constraint FK_服务分类类型关联 foreign key (category_type_id)
      references cibase_svc_category_type (category_type_id) on delete restrict on update restrict;

alter table cibase_svc_category_attr add constraint FK_服务分类属性关联 foreign key (svc_category_id)
      references cibase_svc_category (svc_category_id) on delete restrict on update restrict;

alter table cibase_userlables add constraint FK_用户标签关联 foreign key (user_id)
      references cibase_user (user_id) on delete restrict on update restrict;

alter table cidev_application add constraint FK_Reference_project_application foreign key (project_id)
      references cidev_project (project_id) on delete restrict on update restrict;

alter table cidev_application_version add constraint FK_Reference_app_version foreign key (app_id)
      references cidev_application (app_id) on delete restrict on update restrict;

alter table cidev_application_version add constraint FK_Reference_version_channel foreign key (co_channel_id)
      references cidev_co_channel (channel_id) on delete restrict on update restrict;

alter table cidev_application_version_audit add constraint FK_审核关联 foreign key (version_id)
      references cidev_application_version (version_id) on delete restrict on update restrict;

alter table cidev_base_files_assoc add constraint FK_Reference_20 foreign key (version_id)
      references cidev_application_version (version_id) on delete restrict on update restrict;

alter table cidev_base_files_assoc add constraint FK_Reference_21 foreign key (file_id)
      references cibase_files (file_id) on delete restrict on update restrict;

alter table cidev_co_tnt add constraint FK_Reference_23 foreign key (order_id)
      references cidev_co_order (order_id) on delete restrict on update restrict;

alter table cidev_my_purchase_apps add constraint FK_Reference_订阅应用关联 foreign key (app_id)
      references cidev_application (app_id) on delete restrict on update restrict;

alter table cidev_popular_apps add constraint FK_Reference_popular_app_assoc foreign key (app_id)
      references cidev_application (app_id) on delete restrict on update restrict;

alter table cidev_recommend_apps add constraint FK_Reference_推荐服务关联 foreign key (app_id)
      references cidev_application (app_id) on delete restrict on update restrict;

alter table cimobi_customescenario_service_assoc add constraint FK_Reference_17 foreign key (scenario_id)
      references cimobi_usercustom_scenario (scenario_id) on delete restrict on update restrict;

alter table cimobi_customescenario_service_assoc add constraint FK_Relationship_14 foreign key (svc_id)
      references cimobi_svc (svc_id) on delete restrict on update restrict;

alter table cimobi_recommend_services add constraint FK_Relationship_15 foreign key (user_id)
      references cibase_user (user_id) on delete restrict on update restrict;

alter table cimobi_recommend_services add constraint FK_Relationship_16 foreign key (svc_id)
      references cimobi_svc (svc_id) on delete restrict on update restrict;

alter table cimobi_svc add constraint FK_服务所属分类关联 foreign key (svc_category_id)
      references cibase_svc_category (svc_category_id) on delete restrict on update restrict;

alter table cimobi_svc_avgscore add constraint FK_服务评分关联 foreign key (cim_svc_id)
      references cimobi_svc (svc_id) on delete restrict on update restrict;

alter table cimobi_svc_invoke add constraint FK_服务列表关联 foreign key (svc_type_id)
      references cimobi_svc_type (svc_type_id) on delete restrict on update restrict;

alter table cimobi_svc_invoke add constraint FK_服务版本及调用说明关联 foreign key (svc_id)
      references cimobi_svc (svc_id) on delete restrict on update restrict;

alter table cimobi_svc_remark add constraint FK_服务评价关联 foreign key (svc_id)
      references cimobi_svc (svc_id) on delete restrict on update restrict;

alter table comobi_svc_loc_assoc add constraint FK_地区与服务关系关联 foreign key (loc_id)
      references cibase_location (loc_id) on delete restrict on update restrict;

alter table comobi_svc_loc_assoc add constraint FK_服务与地区关系关联 foreign key (svc_id)
      references cimobi_svc (svc_id) on delete restrict on update restrict;

