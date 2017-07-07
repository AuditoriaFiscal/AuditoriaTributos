package br.com.costa.fiscalcred.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

@Entity
@Table(name="CREDFISCAL.DOCUMENTOITEM")
public class DocumentoItem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="ID")
	private long id;
	
	@Column(name="ID_DOCUMENTO")
	private Long idDocumento;
	
	@Column(name="ID_NCM")
	private Long idNcm;
	
	@Column(name="DESCRICAO")
	@Type(type="text")
	private String descricao;
	
	@Transient
	private List<DocumentoItemResult> itensResult;

	@Transient
	private List<DocumentoItemResult> itensResultNotasCompare;
	
	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}

	public Long getIdNcm() {
		return idNcm;
	}

	public void setIdNcm(Long idNcm) {
		this.idNcm = idNcm;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<DocumentoItemResult> getItensResult() {
		return itensResult;
	}

	public void setItensResult(List<DocumentoItemResult> itensResult) {
		this.itensResult = itensResult;
	}

	public List<DocumentoItemResult> getItensResultNotasCompare() {
		return itensResultNotasCompare;
	}

	public void setItensResultNotasCompare(List<DocumentoItemResult> itensResultNotasCompare) {
		this.itensResultNotasCompare = itensResultNotasCompare;
	}
	
}