/* Populate tables */
INSERT INTO estado (estado) VALUES('En Curso');
INSERT INTO estado (estado) VALUES('Pendiente de Validación');
INSERT INTO estado (estado) VALUES('Recibido');

/* Creamos algunos usuarios con sus roles */

INSERT INTO users (authority,username, password, enabled) VALUES ('ADMINISTRADOR','administrador','$2a$10$kxiwSZBRBacsKRqcPsPBlOvImvmNGOHfnrv3AvFw46eGALgokZOzq',1);


INSERT INTO authorities (user_id, authority) VALUES (1,'ROLE_ADMIN');
