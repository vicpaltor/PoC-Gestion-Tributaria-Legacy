-- Configuración inicial del usuario para PDB (Pluggable Database)
ALTER SESSION SET CONTAINER = XEPDB1;

-- Borrar usuario previo si existiera (Opcional)
-- DROP USER gestion_tributaria CASCADE;

-- Crear el usuario
CREATE USER gestion_tributaria IDENTIFIED BY admin123;

-- Dar permisos necesarios
GRANT CREATE SESSION TO gestion_tributaria;
GRANT RESOURCE TO gestion_tributaria;
GRANT CREATE VIEW TO gestion_tributaria;
GRANT UNLIMITED TABLESPACE TO gestion_tributaria;

COMMIT;