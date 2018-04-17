-- tables
drop sequence if exists tb_certification_seq;
drop sequence if exists tb_employee_seq;
drop sequence if exists tb_project_seq;
drop sequence if exists tb_skill_seq;

-- sequences
drop table if exists tb_certification cascade;
drop table if exists tb_employee cascade;
drop table if exists tb_employee_certification cascade;
drop table if exists tb_employee_project cascade;
drop table if exists tb_employee_skill cascade;
drop table if exists tb_skill cascade;
drop table tb_employee_certification cascade;
