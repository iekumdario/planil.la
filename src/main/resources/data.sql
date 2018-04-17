-- Insert role
insert into role (id,name) values (1,'ROLE_USER');
insert into role (id,name) values (2,'ROLE_ADMIN');

-- Insert one users (passwords are both 'password')
insert into usuarios (username,password,role_id,name) values ('admin','$2a$08$wgwoMKfYl5AUE9QtP4OjheNkkSDoqDmFGjjPE2XTPLDe9xso/hy7u',1,'admin');

-- BOOTSTRAP/CATALOG SAMPLE DATA

INSERT INTO ContractType (`id`, `description`) VALUES (1, "Permanente");
INSERT INTO ContractType (`id`, `description`) VALUES (2, "Temporal");

INSERT INTO Department (`id`, `description`) VALUES (1, "Gerencia");
INSERT INTO Department (`id`, `description`) VALUES (2, "Recursos Humanos");
INSERT INTO Department (`id`, `description`) VALUES (3, "Tecnologia");

INSERT INTO Position (`id`, `description`, `name`) VALUES (1, "Gerente ejecutivo", "CEO");
INSERT INTO Position (`id`, `description`, `name`) VALUES (2, "Encargado del diseño y desarrollo de software", "Ingeniero de Software");
INSERT INTO Position (`id`, `description`, `name`) VALUES (3, "Encargado de las pruebas funcionales y diseño de casos de prueba", "Ingeniero de Calidad");
INSERT INTO Position (`id`, `description`, `name`) VALUES (4, "Encargado de la ejecucion de la metodologia scrum en el equipo de desarrollo", "Scrum Master");
INSERT INTO Position (`id`, `description`, `name`) VALUES (5, "Encargado de el seguimiento y cumplimiento de proyectos", "Project Manager");
INSERT INTO Position (`id`, `description`, `name`) VALUES (6, "Adquisicion de talento y recurso humano", "Recursos Humanos");


-- dummy employees and contracts
INSERT INTO `Contract` VALUES
  (1,1000,NULL,6.25,'2018-01-22 00:02:00',40,1,NULL),
  (2,12000,'2018-01-18 00:04:00',75,'2018-01-02 00:02:00',40,2,NULL),
  (3,3500,NULL,21.875,'2018-01-25 00:04:00',123,1,NULL);

INSERT INTO `Employee` VALUES
  (1,'','panama','BHR','1993-01-05 00:01:00','juanperez@hola.com','Juan','MASCULINO','1234','Perez','123','123',1,3,3),
  (2,'','test','ASM','2000-01-09 00:02:00','test@test.com','Jose','MASCULINO','123267','Quintero','123','123',2,1,1),
  (3,'123','123','BWA','2000-01-05 00:02:00','123@123.com','Juana','FEMENINO','1231092','Perea','123','123',3,2,4);