CREATE TABLE Profile (
	id int PRIMARY KEY auto_increment,
	email varchar(255),
	password varchar(255),
	name varchar(255),
	surname varchar(255),
	phone varchar(255)
	
);

INSERT INTO PROFILE (email, password, name, surname, phone) VALUES('gustavo@email.com','$2a$12$48Gv3FhuIdSshXz.UWatxuHpTbDfxV0r.6WwmpPfXd8fd2X.hYweS', 'Gustavo', 'Melo', '11991234567');
INSERT INTO PROFILE (email, password, name, surname, phone) VALUES('henrique@email.com','$2a$12$48Gv3FhuIdSshXz.UWatxuHpTbDfxV0r.6WwmpPfXd8fd2X.hYweS', 'Henrique', 'Lima', '11991234567');
INSERT INTO PROFILE (email, password, name, surname, phone) VALUES('vinicius@email.com','$2a$12$48Gv3FhuIdSshXz.UWatxuHpTbDfxV0r.6WwmpPfXd8fd2X.hYweS', 'Vinicius', 'Santos', '11991234567');

CREATE TABLE Crm (
	id int PRIMARY KEY auto_increment,
	crm varchar(45),
	uf varchar(2),
	specialty varchar(255),	
	user_id int
);

INSERT INTO Crm (crm, uf, specialty, user_id) VALUES('1234', 'SP','RADIOLOGIA E DIAGNÓSTICO POR IMAGEM', 1);
INSERT INTO Crm (crm, uf, specialty, user_id) VALUES('2334', 'RJ','CLINICO GERAL', 3);
INSERT INTO Crm (crm, uf, specialty, user_id) VALUES('3456','MG','CIRURGIÃO', 2);

