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
	private Long id;
	
	@Column(name="ID_NCM")
	private Long idNcm;
	
	@Column(name="ID_DOCUMENTOITEM")
	private Long idDocumentoItem;
	
	@Column(name="DESCRICAO_ESPERADA")
	@Type(type="text")
	private String descricaoEsperada;

	@Column(name="DESCRICAO_ENCONTRADA")
	@Type(type="text")
	private String descricaoEncontrada;

	@Column(name="DESCRICAO_NAO_ENCONTRADA")
	@Type(type="text")
	private String descricaoNaoEncontrada;

	@Column(name="FL_DESCRICAO_NAO_ENCONTRADA")
	private boolean flDescricaoNaoEncontrada;
	
	
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

	public Long getIdNcm() {
		return idNcm;
	}

	public void setIdNcm(Long idNcm) {
		this.idNcm = idNcm;
	}

	public String getDescricaoEsperada() {
		return descricaoEsperada;
	}

	public void setDescricaoEsperada(String descricaoEsperada) {
		this.descricaoEsperada = descricaoEsperada;
	}

	public String getDescricaoEncontrada() {
		return descricaoEncontrada;
	}

	public void setDescricaoEncontrada(String descricaoEncontrada) {
		this.descricaoEncontrada = descricaoEncontrada;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoNaoEncontrada() {
		return descricaoNaoEncontrada;
	}

	public void setDescricaoNaoEncontrada(String descricaoNaoEncontrada) {
		this.descricaoNaoEncontrada = descricaoNaoEncontrada;
	}

	public boolean isFlDescricaoNaoEncontrada() {
		return flDescricaoNaoEncontrada;
	}

	public void setFlDescricaoNaoEncontrada(boolean flDescricaoNaoEncontrada) {
		this.flDescricaoNaoEncontrada = flDescricaoNaoEncontrada;
	}

	
}