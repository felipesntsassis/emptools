-- Database: emptools

-- DROP DATABASE emptools;

CREATE DATABASE emptools
    WITH 
    OWNER = emptools
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

COMMENT ON DATABASE emptools
    IS 'EmpTools Application database.';

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