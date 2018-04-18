-- Database: emptools
-- DROP DATABASE emptools;

CREATE DATABASE emptools
    WITH 
    OWNER = emptools
    ENCODING = 'UTF8'
    LC_COLLATE = 'pt_BR.UTF-8'
    LC_CTYPE = 'pt_BR.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

COMMENT ON DATABASE emptools IS 'Database to Emptools Application';

-- User: emptools
-- DROP USER emptools;

CREATE USER emptools WITH
  LOGIN
  SUPERUSER
  INHERIT
  CREATEDB
  CREATEROLE
  NOREPLICATION;

COMMENT ON ROLE emptools IS 'Role to admin the EmpTools database project.';