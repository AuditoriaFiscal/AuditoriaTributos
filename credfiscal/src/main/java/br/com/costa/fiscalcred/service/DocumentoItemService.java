package br.com.costa.fiscalcred.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.costa.fiscalcred.model.DocumentoItem;

@Component
public class DocumentoItemService {

	@PersistenceContext
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Transactional
	public DocumentoItem register(DocumentoItem doc) {
		this.em.persist(doc);
		
		return doc;
	}
}
