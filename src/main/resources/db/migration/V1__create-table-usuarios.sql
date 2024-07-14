CREATE TABLE usuarios(
  id    BIGINT NOT NULL AUTO_INCREMENT,
  login VARCHAR(100) NOT NULL,
  clave VARCHAR(300) NOT NULL,
  PRIMARY KEY (ID)
  );
INSERT INTO `forohub`.`usuarios` (`login`,`clave`)
  VALUES ('poli.acho','$2a$10$VTp6S.j5j2mIOzXdxK1NuO6L9.uSWB62aAJgSOs0qRU7/bi/lXB92');
