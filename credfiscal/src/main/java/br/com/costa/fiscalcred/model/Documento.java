package br.com.costa.fiscalcred.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="CREDFISCAL.DOCUMENTO")
public class Documento {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="ID")
	private long id;
	
	@Column(name="NOTA")
	@Type(type="text")
	private String nota;
	
	@Column(name="NUMERONF")
	private Long numeroNF;
	
	@Column(name="CNPJ")
	private Long cnpj;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public Long getNumeroNF() {
		return numeroNF;
	}

	public void setNumeroNF(Long numeroNF) {
		this.numeroNF = numeroNF;
	}

	public Long getCnpj() {
		return cnpj;
	}

	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}

}