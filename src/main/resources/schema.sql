DROP TABLE IF EXISTS ORGN;

create table orgn (
    orgn_no integer not null,
    orgn_id varchar(255),
    orgn_nm varchar(255),
    orgn_dsc varchar(1000),
    primary key (orgn_no)
);

DROP TABLE IF EXISTS ATHRZTN;

create table athrztn (
    athrztn_no integer not null,
    athrztn_nm varchar(255),
    athrztn_dsc varchar(1000),
    first_rgst_tm timestamp,
    primary key (athrztn_no)
);

drop table if exists USERLIST;

create table USERLIST (
    user_id varchar(255) not null,
    user_nm varchar(255),
    dsc varchar(1000),
    tel varchar(30),
    email varchar(40),
    orgn_no integer,
    athrztn_no integer,
    access_allow_ip varchar(255),
    dplct_lgin_allow_yn varchar(1),
    lgin_lmt_cd varchar(20),
    user_stts_cd varchar(20),
    first_rgstr_id varchar(255),
    first_rgst_tm timestamp,
    last_updtr_id varchar(255),
    last_updt_tm timestamp,
    primary key (user_id)
);

alter table USERLIST
   add constraint FKbx1pumoe6vlxrlslidwffsbi0
   foreign key (athrztn_no)
   references athrztn
;

alter table USERLIST
   add constraint FK7ffvvfsc7341k128yk64use4v
   foreign key (orgn_no)
   references orgn
;