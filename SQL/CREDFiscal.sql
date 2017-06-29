--ALTERS
CREATE TABLE CREDFISCAL.NOTAFISCAL
(
  ID numeric NOT NULL,
  ID
  NUMERONOTAFISCAL character varying(50),
  senha character varying(50),
  datacadastro date,
  CONSTRAINT usuario_pk PRIMARY KEY (id)
);