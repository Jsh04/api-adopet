CREATE TABLE tb_tutor(
	ID BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	NOME VARCHAR(250) NOT NULL,
	EMAIL VARCHAR(250) NOT NULL UNIQUE,
	SENHA VARCHAR(250) NOT NULL
)