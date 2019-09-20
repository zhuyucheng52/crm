drop database if exists crm;

create database crm character set = utf8mb4;

use crm;

create table if not exists tbl_order (
    id bigint not null auto_increment,
    online_id varchar(255) comment '线上订单ID',
    product_id bigint comment '商品ID',
    product_num int comment '商品件数',
    payment decimal(12,2) comment '实付金额',
    status int not null default 0 comment '订单状态:0. 等待买家付款; 1. 交易成功',
    address varchar(255) comment '地址',
    remark text comment '备注信息',
    customer_id bigint comment '客户ID',
    customer_name varchar(255) comment '客户名称',
    customer_mobile varchar(15) comment '客户电话',

    approve_id bigint not null comment '审批人',
    approve_status int not null default 0 comment '审批状态:0. 未审批; 1. 拒绝; 2. 审批通过',
    approve_remark text comment '审批人备注',

    user_id bigint not null comment '记录人',

    tenant_id bigint not null comment '租户ID',

    create_time datetime comment '录入时间',
    update_time datetime comment '更新时间',

    primary key(id),
    key `idx_product_id`(product_id),
    key `idx_online_id`(online_id)
) comment = '订单';

create table if not exists tbl_user (
    id bigint not null auto_increment,
    name varchar(255) comment '姓名',
    sex varchar(2) comment '性别',
    birthday date comment '生日',
    mobile varchar(15) comment '电话',
    address varchar(255) comment '地址',
    email varchar(255) comment '邮件',
    remark text comment '备注',

    account varchar(64) not null comment '账户',
    password varchar(64) not null comment '密码',

    user_id bigint not null comment '记录人',

    tenant_id bigint not null comment '租户ID',

    create_time datetime comment '录入时间',
    update_time datetime comment '更新时间',

    primary key(id),
    unique index `idx_account`(account),
    key `idx_name`(name),
    key `idx_mobile`(mobile),
    key `idx_birthday`(birthday)
) comment = '用户';

create table if not exists tbl_product (
    id bigint not null auto_increment,
    name varchar(255) comment '名称',
    pic_url varchar(255) comment '图片',
    disabled int not null default 0 comment '状态: 1. 禁用; 0. 启用',
    remark text comment '备注',

    user_id bigint not null comment '记录人',

    tenant_id bigint not null comment '租户ID',

    create_time datetime comment '录入时间',
    update_time datetime comment '更新时间',

    primary key(id),
    key `idx_name`(name)
) comment = '商品';

create table if not exists tbl_purchase (
    id bigint not null auto_increment,
    name varchar(255) comment '名称',
    product_id bigint comment '商品ID',
    product_num int comment '数量',
    payment decimal(12, 2) comment '采购费用',
    remark text comment '备注',
    purcase_user_id bigint comment '采购人',

    approve_id bigint not null comment '审批人',
    approve_status int not null default 0 comment '审批状态:0. 未审批; 1. 拒绝; 2. 审批通过',
    approve_remark text comment '审批人备注',

    user_id bigint not null comment '录入人',

    tenant_id bigint not null comment '租户ID',

    create_time datetime comment '录入时间',
    update_time datetime comment '更新时间',

    primary key(id),
    key `idx_name`(name),
    key `idx_product_id`(product_id),
    key `idx_payment`(payment),
    key `idx_purcase_user_id`(purcase_user_id)
) comment = '采购';

create table if not exists tbl_finance (
    id bigint not null auto_increment,
    name varchar(255) comment '名称',
    finance_type int not null comment '1. 收入; 2. 支出',
    payment decimal(12, 2) not null default 0 comment '金额',
    finance_user_id bigint not null comment '用户ID',
    remark text comment '备注',

    approve_id bigint not null comment '审批人',
    approve_status int not null default 0 comment '审批状态:0. 未审批; 1. 拒绝; 2. 审批通过',
    approve_remark text comment '审批人备注',

    user_id bigint not null comment '录入人',

    tenant_id bigint not null comment '租户ID',

    create_time datetime comment '录入时间',
    update_time datetime comment '更新时间',

    primary key(id),
    key `idx_name`(name),
    key `idx_payment`(payment),
    key `idx_finance_user_id`(finance_user_id)
) comment = '财务';

create table if not exists tbl_after_sale (
    id bigint not null auto_increment,
    order_id bigint not null comment '订单ID',
    name varchar(255) comment '名称',
    payment decimal(12, 2) not null default 0 comment '费用',
    remark text comment '备注',

    approve_id bigint not null comment '审批人',
    approve_status int not null default 0 comment '审批状态:0. 未审批; 1. 拒绝; 2. 审批通过',
    approve_remark text comment '审批人备注',

    user_id bigint not null comment '录入人',

    tenant_id bigint not null comment '租户ID',

    create_time datetime comment '录入时间',
    update_time datetime comment '更新时间',

    primary key(id),
    index `idx_payment`(payment)
) comment = '售后';

create table if not exists tbl_tenant (
    id bigint not null auto_increment,
    name varchar(64) comment '租户名称',
    end_time datetime not null comment '到租时间',

    linkman varchar(32) comment '联系人',
    mobile varchar(32) comment '联系人电话',

    create_time datetime comment '录入时间',
    update_time datetime comment '更新时间',

    primary key(id),
    unique index `idx_name`(name)
) comment = '租户';

create table if not exists tbl_role(
    id bigint not null auto_increment,
    name varchar(64) not null comment '角色名称',

    primary key(id)
) comment = '角色';

create table if not exists tbl_user_role(
    user_id bigint not null comment '用户ID',
    role_id bigint not null comment '角色ID',

    primary key(user_id, role_id)
) comment '用户角色';
