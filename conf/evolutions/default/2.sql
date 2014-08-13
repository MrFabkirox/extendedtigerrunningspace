# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table quote (
  id                        varchar(255) not null,
  name                      varchar(255),
  constraint pk_quote primary key (id))
;

create sequence quote_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists quote;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists quote_seq;

