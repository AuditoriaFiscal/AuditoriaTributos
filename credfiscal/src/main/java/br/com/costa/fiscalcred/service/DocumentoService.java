package br.com.costa.fiscalcred.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.costa.fiscalcred.model.Documento;
import br.com.costa.fiscalcred.model.DocumentoItem;
import br.com.costa.fiscalcred.model.DocumentoItemResult;

@Component
public class DocumentoService {

	@PersistenceContext
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Transactional
	public Documento register(Documento doc) {
		this.em.persist(doc);
		
		return doc;
	}
	
	@Transactional
	public DocumentoItem registerItem(DocumentoItem doc) {
		this.em.persist(doc);
		
		return doc;
	}
	
	@Transactional
	public DocumentoItemResult registerItemResult(DocumentoItemResult doc) {
		this.em.persist(doc);
		
		return doc;
	}
}
