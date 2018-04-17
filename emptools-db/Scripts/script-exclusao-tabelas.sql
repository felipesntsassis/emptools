-- tables
drop sequence if exists tb_certification_seq cascade;
drop sequence if exists tb_employee_seq cascade;
drop sequence if exists tb_project_seq cascade;
drop sequence if exists tb_role_seq cascade;
drop sequence if exists tb_skill_seq cascade;

-- sequences
drop table if exists tb_certification cascade;
drop table if exists tb_employee cascade;
drop table if exists tb_employee_certification cascade;
drop table if exists tb_employee_project cascade;
drop table if exists tb_employee_skill cascade;
drop table if exists tb_project cascade;
drop table if exists tb_skill cascade;
drop table if exists tb_role cascade;
