# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table account (
  acc_number                bigint auto_increment not null,
  glydel_crn                bigint,
  balance                   integer,
  last_trxn_date            datetime(6),
  is_active                 tinyint(1) default 0,
  constraint pk_account primary key (acc_number))
;

create table statement (
  trxn_id                   bigint auto_increment not null,
  account_acc_number        bigint,
  trxn_type                 varchar(255),
  amount                    integer,
  trxn_date                 datetime(6),
  constraint pk_statement primary key (trxn_id))
;

alter table statement add constraint fk_statement_account_1 foreign key (account_acc_number) references account (acc_number) on delete restrict on update restrict;
create index ix_statement_account_1 on statement (account_acc_number);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table account;

drop table statement;

SET FOREIGN_KEY_CHECKS=1;

