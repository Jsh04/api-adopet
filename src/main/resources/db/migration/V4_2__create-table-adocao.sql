CREATE TABLE tb_adocao(
	ID BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	NOME VARCHAR(250) NOT NULL,
	TELEFONE VARCHAR(15) NOT NULL,
	MENSAGEM VARCHAR(300),
	PET_ID BIGINT NOT NULL,
	TUTOR_ID BIGINT NOT NULL,
	FOREIGN KEY(PET_ID) REFERENCES tb_pet(ID),
	FOREIGN KEY(TUTOR_ID) REFERENCES tb_tutor(ID)
)