package br.com.costa.fiscalcred.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="CREDFISCAL.DOCUMENTOITEMRESULT")
public class DocumentoItemResult {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="ID")
	private long id;
	
	@Column(name="ID_DOCUMENTOITEM")
	private Long idDocumentoItem;
	
	@Column(name="DESCRICAO")
	@Type(type="text")
	private String descricao;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getIdDocumentoItem() {
		return idDocumentoItem;
	}

	public void setIdDocumentoItem(Long idDocumentoItem) {
		this.idDocumentoItem = idDocumentoItem;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}