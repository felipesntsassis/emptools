-- The essencial tables
create sequence tb_certification_seq start 1;
create table tb_certification(
	certification_id bigint not null default nextval('tb_certification_seq'),
	description varchar(250) not null,
	constraint pk_tb_certification primary key (certification_id)
);
create index idx_tb_certification on tb_certification using btree (certification_id, description);
comment on table tb_certification is 'Table to registry the Employee Certifications';
comment on column tb_certification.certification_id is 'The certification primary key'; 
comment on column tb_certification.description is 'The certification description';

create sequence tb_role_seq start 1;
create table tb_role(
	role_id bigint not null default nextval('tb_role_seq'),
	description varchar(250) not null,
	constraint pk_tb_role primary key (role_id)
);
create index idx_tb_role on tb_role using btree (role_id, description);
comment on table tb_role is 'Table to registry the Employee roles';
comment on column tb_role.role_id is 'The role primary key'; 
comment on column tb_role.description is 'The role description';

create sequence tb_skill_seq start 1;
create table tb_skill(
	skill_id bigint not null default nextval('tb_skill_seq'),
	description varchar(250) not null,
	constraint pk_tb_skill primary key (skill_id)
);
create index idx_tb_skill on tb_skill using btree (skill_id, description);
comment on table tb_skill is 'Table to registry the Employee skills';
comment on column tb_skill.skill_id is 'The skill primary key'; 
comment on column tb_skill.description is 'The skill description';

create sequence tb_project_seq start 1;
create table tb_project(
	project_id bigint not null default nextval('tb_project_seq'),
	name varchar(100) not null,
	customer varchar(250) not null,
	value numeric(10,2) not null,
	dt_begin timestamp not null,
	dt_end timestamp not null,
	constraint pk_tb_project primary key (project_id)
);
create index idx_tb_project on tb_project using btree (project_id, customer, value, dt_begin, dt_end);
comment on table tb_project is 'Table to registry the Employee projects';
comment on column tb_project.project_id is 'The project primary key'; 
comment on column tb_project.name is 'The project name';
comment on column tb_project.customer is 'The project customer';
comment on column tb_project.value is 'The project value';
comment on column tb_project.dt_begin is 'The date and hour when project begun';
comment on column tb_project.dt_begin is 'The date and hour project ended';

create sequence tb_employee_seq start 1;
create table tb_employee(
	employee_id bigint not null default nextval('tb_employee_seq'),
	name varchar(100) not null,
	role_id bigint not null,
	manager_id bigint,
	gcm varchar(25) not null,
	salary numeric(10,2) not null,
	constraint pk_tb_employee primary key (employee_id),
	constraint fk_tb_employee_tb_role foreign key (role_id) references tb_role(role_id),
	constraint fk_tb_employee_manager foreign key (manager_id) references tb_employee(employee_id)
);
create index idx_tb_employee on tb_employee using btree (employee_id, name, role_id, manager_id, gcm, salary);
comment on table tb_employee is 'Table to registry the Employees';
comment on column tb_employee.employee_id is 'The employee primary key'; 
comment on column tb_employee.name is 'The employee name';
comment on column tb_employee.role_id is 'Foreign key that identifies the employee role';
comment on column tb_employee.manager_id is 'Foreign key that identifies the employee manager';
comment on column tb_employee.gcm is 'The employee GCM data';
comment on column tb_employee.salary is 'The employee salary';

-- The associative tables
create table tb_employee_project(
	employee_id bigint not null,
	project_id bigint not null,
	constraint fk_tb_employee_project primary key(employee_id, project_id),
	constraint fk_tb_employee_project_tb_employee foreign key(employee_id) references tb_employee(employee_id),
	constraint fk_tb_employee_project_tb_project foreign key(project_id) references tb_project(project_id)
);
create index idx_tb_employee_project on tb_employee_project using btree(employee_id, project_id);
comment on table tb_employee_project is 'Table to associate Employees and Projects';
comment on column tb_employee_project.employee_id is 'Part of key that represents the Employee';
comment on column tb_employee_project.project_id is 'Part of key that represents the Project';

create table tb_employee_skill(
	employee_id bigint not null,
	skill_id bigint not null,
	constraint fk_tb_employee_skill primary key(employee_id, skill_id),
	constraint fk_tb_employee_skill_tb_employee foreign key(employee_id) references tb_employee(employee_id),
	constraint fk_tb_employee_skill_tb_skill foreign key(skill_id) references tb_skill(skill_id)
);
create index idx_tb_employee_skill on tb_employee_skill using btree(employee_id, skill_id);
comment on table tb_employee_skill is 'Table to associate Employees and Skills';
comment on column tb_employee_skill.employee_id is 'Part of key that represents the Employee';
comment on column tb_employee_skill.skill_id is 'Part of key that represents the Skill';

create table tb_employee_certification(
	employee_id bigint not null,
	certification_id bigint not null,
	constraint fk_tb_employee_certification primary key(employee_id, certification_id),
	constraint fk_tb_employee_certification_tb_employee foreign key(employee_id) references tb_employee(employee_id),
	constraint fk_tb_employee_certification_tb_certification foreign key(certification_id) references tb_certification(certification_id)
);
create index idx_tb_employee_certification on tb_employee_certification using btree(employee_id, certification_id);
comment on table tb_employee_certification is 'Table to associate Employees and Certifications';
comment on column tb_employee_certification.employee_id is 'Part of key that represents the Employee';
comment on column tb_employee_certification.certification_id is 'Part of key that represents the Certification';