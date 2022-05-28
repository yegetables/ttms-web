-- auto-generated definition
create table if not exists area
(
    id        int          null comment '区的id',
    area_name varchar(100) null comment '区的名字',
    city_id   int          null comment '市的id'
);
create table if not exists area_cinemas
(
    id             int auto_increment comment '主键id'
        primary key,
    area_id        int              not null comment '区的id',
    cinema_name    varchar(200)     null comment '电影院的名称',
    cinema_picture varchar(200)     null comment '电影院的照片',
    cinema_address varchar(200)     not null comment '影院的地址',
    cinema_money   double default 0 null comment '电影院赚的钱',
    cinema_number  varchar(200)     null comment '电影院的电话号码',
    cinema_email   varchar(100)     null comment '电影院的email'
);
create table if not exists cinema_movies
(
    id                 int auto_increment comment '主键'
        primary key,
    cinema_id          int                       not null comment '电影院的id',
    movie_id           int                       not null comment '电影id',
    movie_lowMoney     float(4, 2)  default 0.00 null comment '电影的最低价格',
    day_money          float(4, 2)  default 0.00 null comment '电影院这部电影当天赚得钱',
    cinema_movie_money float(10, 2) default 0.00 null comment '电影院这部电影上映时间总钱数'
);
create table if not exists city
(
    id          int         null comment '城市的id',
    city_name   varchar(50) null comment '城市的名字',
    province_id int         null comment '省的Id'
);
create table if not exists hall_seat
(
    id            int auto_increment comment '主键'
        primary key,
    seat_line     int not null comment '座位的行',
    seat_column   int not null comment '座位的列',
    movie_plan_id int null comment '某一个电影院的某个电影的某个时间段的id',
    order_id      int not null comment '订单id',
    ticket_status int null comment '票是否已售'
);
create table if not exists movie
(
    id           int auto_increment
        primary key,
    movie_name   varchar(30)             not null comment '电影名字',
    movie_status int                     not null comment '电影状态(正在热映/即将上映/热播电影)',
    movie_start  datetime                not null comment '上映时间',
    movie_minute int                     not null comment '电影的时长',
    movie_brief  varchar(5000)           not null comment '电影的简介',
    movie_score  float(2, 1) default 0.0 null comment '电影的总评分',
    movie_money  double      default 0   null comment '电影的票房',
    movie_area   varchar(20)             not null comment '电影的出产地',
    movie_type   varchar(20)             not null comment '电影的类型',
    movie_head   varchar(100)            not null comment '电影的首页',
    day_money    double                  null comment '当天的票房',
    want_look    bigint                  null comment '想看的数目'
);
create table if not exists movie_actor
(
    id            int auto_increment
        primary key,
    actor_picture varchar(200) null comment '电影的人物图片',
    movie_id      int          not null comment '电影的id',
    actor_name    varchar(100) null comment '真名',
    role_name     varchar(100) null comment '角色的名字'
);
create table if not exists movie_comment
(
    id              int auto_increment comment '主键id'
        primary key,
    movie_id        int          not null comment '电影的id',
    people_comment  varchar(500) null comment '电影的评论',
    people_nickname varchar(100) not null comment '评论的人的昵称',
    people_icon     varchar(200) null comment '图像',
    people_score    float(3, 1)  not null comment '对电影的个人评分'
);
create table if not exists movie_director
(
    id               int auto_increment comment '主键'
        primary key,
    director_name    varchar(30)  not null comment '导演的名字',
    director_picture varchar(100) null comment '导演的照片',
    movie_id         int          null comment '电影的id'
);
create table if not exists movie_hall
(
    id          int auto_increment comment '主键'
        primary key,
    cinema_id   int          not null comment '电影院的id',
    seat_line   int          not null comment '座位行数',
    seat_column int          not null comment '座位列数',
    hall_name   varchar(100) not null comment '演出厅的名字',
    movie_type  varchar(20)  not null comment '影厅的类型'
);
create table if not exists movie_picture
(
    id            int auto_increment
        primary key,
    movie_id      int          not null,
    movie_picture varchar(500) null
);
create table if not exists movie_plan
(
    id               int auto_increment comment '主键'
        primary key,
    movie_start_time datetime    not null comment '电影的开始时间',
    movie_end_time   datetime    not null comment '电影的结束时间',
    plan_date        date        not null comment '演出计划的时间',
    cinema_movie_id  int         not null comment '电影院里面对应的电影id',
    hall_id          int         null comment '该时间段对应的演出厅id',
    ticket_money     float(4, 2) not null comment '某个时间段的钱'
);
create table if not exists movie_producer
(
    id               int auto_increment comment '主键'
        primary key,
    movie_id         int          not null comment '电影的id',
    producer_name    varchar(30)  null comment '出品人的名字',
    producer_picture varchar(200) null comment '出品人的照片'
);
create table if not exists movie_role
(
    role_id   int         null,
    role_name varchar(20) null
);
create table if not exists movie_sell
(
    id         int auto_increment
        primary key,
    sell_id    int                      null,
    sell_money float(4, 2) default 0.00 null comment '售票员赚得钱'
);
create table if not exists movie_user
(
    id                     int auto_increment
        primary key,
    accounts               varchar(64)  not null comment '用户名',
    password               varchar(64)  not null comment '密码',
    nickname               varchar(64)  null comment '昵称',
    create_time            datetime     null comment '注册时间',
    icon                   varchar(500) null comment '头像',
    gender                 varchar(5)   null comment '性别',
    birthday               date         null comment '生日',
    city                   varchar(64)  null comment '所在城市',
    job                    varchar(100) null comment '职业',
    personalized_signature varchar(200) null comment '个性签名',
    cinema_id              int          null,
    sell_id                int          null,
    constraint movie_user_accounts_uindex
        unique (accounts)
)
    comment '用户表';
-- auto-generated definition
create table if not exists movie_user_roles
(
    id      int auto_increment
        primary key,
    user_id int not null,
    role_id int not null
);
create table if not exists movie_video
(
    id          int auto_increment
        primary key,
    movie_id    int          not null,
    movie_video varchar(300) not null,
    video_title varchar(100) null comment '视频的标题'
);
create table if not exists movie_writer
(
    id                   int auto_increment comment '主键'
        primary key,
    movie_id             int          not null comment '电影的id',
    screenwriter_picture varchar(200) null comment '编剧的照片',
    screenwriter_name    varchar(30)  null comment '编剧的名字'
);
create table if not exists people_want
(
    id       int          null comment '主键id',
    movie_id int          null comment '电影的id',
    accounts varchar(100) null comment '使用者的账户'
);
create table if not exists province
(
    id            int         null comment '省的ID',
    province_name varchar(50) null comment '省的名字'
);
-- auto-generated definition
create table if not exists user_order
(
    id               int auto_increment comment '主键也是订单号'
        primary key,
    user_id          int          not null comment '使用者的id',
    cinema_name      varchar(100) not null comment '电影院的名字',
    movie_name       varchar(100) not null comment '电影的名字',
    order_money      double(4, 2) not null comment '订单的总钱数',
    cinema_id        int          not null comment '电影院的id',
    ticket_money     float(4, 2)  not null comment '一张票的钱',
    movie_start_time datetime     not null comment '电影的开始时间',
    order_status     varchar(10)  not null comment '订单状态/未支付or已支付/退款',
    hall_name        varchar(20)  not null comment '演出厅的名字',
    movie_type       varchar(50)  null comment '电影类型',
    movie_time       int          null comment '电影时长',
    movie_id         int          null comment '电影的id',
    movie_head       varchar(200) null,
    plan_id          int          null comment '计划Id',
    pay_time         datetime     null
);

