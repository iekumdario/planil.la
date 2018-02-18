-- Insert role
insert into role (name) values ('ROLE_USER');
insert into role (name) values ('ROLE_ADMIN');

Insert two users (passwords are both 'password')
insert into usuarios (username,password,role_id,name) values ('admin','$2a$08$wgwoMKfYl5AUE9QtP4OjheNkkSDoqDmFGjjPE2XTPLDe9xso/hy7u',1,'admin');

-- BOOTSTRAP/CATALOG SAMPLE DATA

INSERT INTO `planilla`.`ContractType` (`id`, `description`) VALUES (1, "Permanente");
INSERT INTO `planilla`.`ContractType` (`id`, `description`) VALUES (2, "Temporal");

INSERT INTO `planilla`.`Department` (`id`, `description`) VALUES (1, "Gerencia");
INSERT INTO `planilla`.`Department` (`id`, `description`) VALUES (2, "Recursos Humanos");
INSERT INTO `planilla`.`Department` (`id`, `description`) VALUES (3, "Tecnologia");

INSERT INTO `planilla`.`Position` (`id`, `description`, `name`) VALUES (1, "Gerente ejecutivo", "CEO");
INSERT INTO `planilla`.`Position` (`id`, `description`, `name`) VALUES (2, "Encargado del diseño y desarrollo de software", "Ingeniero de Software");
INSERT INTO `planilla`.`Position` (`id`, `description`, `name`) VALUES (3, "Encargado de las pruebas funcionales y diseño de casos de prueba", "Ingeniero de Calidad");
INSERT INTO `planilla`.`Position` (`id`, `description`, `name`) VALUES (4, "Encargado de la ejecucion de la metodologia scrum en el equipo de desarrollo", "Scrum Master");
INSERT INTO `planilla`.`Position` (`id`, `description`, `name`) VALUES (5, "Encargado de el seguimiento y cumplimiento de proyectos", "Project Manager");
INSERT INTO `planilla`.`Position` (`id`, `description`, `name`) VALUES (6, "Adquisicion de talento y recurso humano", "Recursos Humanos");