-- tb_certification
insert into tb_certification values(nextval('tb_certification_seq'), 'Oracle SOA Suite 11g Essentials - Jun 2015');
insert into tb_certification values(nextval('tb_certification_seq'), 'Oracle Certified Professional, Java SE 7 Programmer - Aug 2014');
insert into tb_certification values(nextval('tb_certification_seq'), 'ITIL® Foundation Certificate Jun 2013');
insert into tb_certification values(nextval('tb_certification_seq'), 'Sun Certified Enterprise Architect for the Java Platform Oct 2010');
insert into tb_certification values(nextval('tb_certification_seq'), 'Sun Certified Business Component Developer for the Java Platform, EE 5 - Nov 2007');
insert into tb_certification values(nextval('tb_certification_seq'), 'Sun Certified Business Component Developer for the Java Platform, EE 1.3 - Feb 2007');
insert into tb_certification values(nextval('tb_certification_seq'), 'Sun Certified Web Component Developer for the Java Platform - Oct 2006');
insert into tb_certification values(nextval('tb_certification_seq'), 'Sun Certified Programmer for the Java Platform - Jun 2006');
insert into tb_certification values(nextval('tb_certification_seq'), 'Sun Certified Programmer for the Java Platform');

-- tb_project
insert into tb_project values(nextval('tb_project_seq'), 'Portal cadastro NET', 'NET', 1000000.00, '2016-11-05T08:15:30-05:00', '2017-11-05T08:15:30-05:00');
insert into tb_project values(nextval('tb_project_seq'), 'Fulfillment', 'Multiplus Fidelidade', 1000000.00, '2015-11-05T08:15:30-05:00', '2018-11-05T08:15:30-05:00');

-- tb_role
insert into tb_role values(nextval('tb_role_seq'), 'TI Architect');
insert into tb_role values(nextval('tb_role_seq'), 'Software Enginner');
insert into tb_role values(nextval('tb_role_seq'), 'Manager');

-- tb_skill
insert into tb_skill values(nextval('tb_skill_seq'), 'java');
insert into tb_skill values(nextval('tb_skill_seq'), 'javaee');
insert into tb_skill values(nextval('tb_skill_seq'), 'rest');
insert into tb_skill values(nextval('tb_skill_seq'), 'graphql');
insert into tb_skill values(nextval('tb_skill_seq'), 'microservice');
insert into tb_skill values(nextval('tb_skill_seq'), 'soa');
insert into tb_skill values(nextval('tb_skill_seq'), 'oracle soa suite');
insert into tb_skill values(nextval('tb_skill_seq'), 'DevOps');
insert into tb_skill values(nextval('tb_skill_seq'), 'gestao de pessoas');
insert into tb_skill values(nextval('tb_skill_seq'), 'PMI');
insert into tb_skill values(nextval('tb_skill_seq'), 'Scrum');
insert into tb_skill values(nextval('tb_skill_seq'), 'Agile');

