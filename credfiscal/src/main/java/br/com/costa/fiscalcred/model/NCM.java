package br.com.costa.fiscalcred.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CREDFISCAL.NCM")
public class NCM {

	@Id
	@Column(name="ID")
	private long id;
	
	@Column(name="DESCRICAO")
	private String descricao;
	
	@Column(name="UNIDADE")
	private String unidade;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

}