--ALTERS
CREATE TABLE CREDFISCAL.NCM(
    ID numeric NOT NULL,
    DESCRICAO character varying(200),
    UNIDADE character varying(5),
    CONSTRAINT PK_NCM PRIMARY KEY (ID)
);

CREATE TABLE CREDFISCAL.DOCUMENTO(
    ID BIGINT NOT NULL AUTO_INCREMENT,
    NUMERONF BIGINT,
    CNPJ BIGINT,
    NOTA MEDIUMTEXT,
    NOMENOTA MEDIUMTEXT,
    CONSTRAINT PK_DOCUMENTO PRIMARY KEY (ID)
);

CREATE TABLE CREDFISCAL.DOCUMENTOITEM(
    ID BIGINT NOT NULL AUTO_INCREMENT,
    ID_DOCUMENTO BIGINT,
    ID_NCM BIGINT,
    DESCRICAO MEDIUMTEXT,
    CONSTRAINT PK_DOCUMENTOITEM PRIMARY KEY (ID),
    FOREIGN KEY (ID_DOCUMENTO) REFERENCES CREDFISCAL.DOCUMENTO(ID)
);

CREATE TABLE CREDFISCAL.DOCUMENTOITEMRESULT(
    ID BIGINT NOT NULL AUTO_INCREMENT,
    ID_DOCUMENTOITEM BIGINT,
    DESCRICAO_ESPERADA MEDIUMTEXT,
    DESCRICAO_ENCONTRADA MEDIUMTEXT,
	DESCRICAO_NAO_ENCONTRADA MEDIUMTEXT,
	FL_DESCRICAO_NAO_ENCONTRADA BOOL,
    
    CONSTRAINT PK_DOCUMENTOITEMRESULT PRIMARY KEY (ID),
    FOREIGN KEY (ID_DOCUMENTOITEM) REFERENCES CREDFISCAL.DOCUMENTOITEM(ID)
);