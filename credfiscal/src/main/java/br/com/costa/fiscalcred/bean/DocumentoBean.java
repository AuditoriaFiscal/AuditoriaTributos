package br.com.costa.fiscalcred.bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Query;
import javax.xml.bind.JAXBException;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.costa.credfiscal.util.NFCompareUtils;
import br.com.costa.fiscalcred.model.Documento;
import br.com.costa.fiscalcred.model.DocumentoItem;
import br.com.costa.fiscalcred.model.DocumentoItemResult;
import br.com.costa.fiscalcred.model.NCM;
import br.com.costa.fiscalcred.model.nfe.Det;
import br.com.costa.fiscalcred.model.nfe.NfeProc;
import br.com.costa.fiscalcred.service.DocumentoService;
import br.com.samuelweb.nfe.util.XmlUtil;

@ManagedBean
@SessionScoped
public class DocumentoBean {
	
	@ManagedProperty("#{documentoService}")
	private DocumentoService documentoService;

	private UploadedFile arquivoEntrada;
	private UploadedFile arquivoSaida;
	
	private NfeProc nfeEntrada;
	private NfeProc nfeSaida;
	
	private String nomeArquivoEntrada;
	private String nomeArquivoSaida;
	
	private Documento documentoEntrada;
	private Documento documentoSaida;

	public UploadedFile getArquivoEntrada() {
		return arquivoEntrada;
	}

	public void setArquivoEntrada(UploadedFile arquivoEntrada) {
		this.arquivoEntrada = arquivoEntrada;
	}

	public UploadedFile getArquivoSaida() {
		return arquivoSaida;
	}

	public void setArquivoSaida(UploadedFile arquivoSaida) {
		this.arquivoSaida = arquivoSaida;
	}

	public DocumentoService getDocumentoService() {
		return documentoService;
	}

	public void setDocumentoService(DocumentoService documentoService) {
		this.documentoService = documentoService;
	}

	public NfeProc getNfeEntrada() {
		return nfeEntrada;
	}

	public void setNfeEntrada(NfeProc nfeEntrada) {
		this.nfeEntrada = nfeEntrada;
	}

	public NfeProc getNfeSaida() {
		return nfeSaida;
	}

	public void setNfeSaida(NfeProc nfeSaida) {
		this.nfeSaida = nfeSaida;
	}
	
	public String getNomeArquivoEntrada() {
		return nomeArquivoEntrada;
	}

	public void setNomeArquivoEntrada(String nomeArquivoEntrada) {
		this.nomeArquivoEntrada = nomeArquivoEntrada;
	}

	public String getNomeArquivoSaida() {
		return nomeArquivoSaida;
	}
	public void setNomeArquivoSaida(String nomeArquivoSaida) {
		this.nomeArquivoSaida = nomeArquivoSaida;
	}
	
	public Documento getDocumentoEntrada() {
		return documentoEntrada;
	}

	public void setDocumentoEntrada(Documento documentoEntrada) {
		this.documentoEntrada = documentoEntrada;
	}

	public Documento getDocumentoSaida() {
		return documentoSaida;
	}

	public void setDocumentoSaida(Documento documentoSaida) {
		this.documentoSaida = documentoSaida;
	}

	@PostConstruct
	private void doInit(){
		arquivoEntrada = null;
		arquivoSaida = null;
	}
	
	@SuppressWarnings("static-access")
	private static NfeProc convertStringToObject(String xmlStr) {
		XmlUtil util = new XmlUtil();
		try {
			String xml = util.replacesNfe(xmlStr);
			NfeProc nfe = util.xmlToObject(xml, NfeProc.class);
			return nfe;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("static-access")
	public void uploadEntrada() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(arquivoEntrada.getInputstream()));
			String line;

			StringBuffer xmlUpload = new StringBuffer();
			while ((line = in.readLine()) != null) {
				xmlUpload.append(line);
				System.out.println(line);
			}
			
			XmlUtil util = new XmlUtil();
			util.xmlToObject(xmlUpload.toString(), NfeProc.class);
			setNfeEntrada(convertStringToObject(xmlUpload.toString()));
			Documento documento = crateDocumento(xmlUpload.toString(), new Long(getNfeEntrada().getNFe().getInfNFe().getIde().getcNF()), new Long(getNfeEntrada().getNFe().getInfNFe().getEmit().getCNPJ()), arquivoEntrada.getFileName());
			documento.setItens(compararNotas(documento.getId()));
			
			documentoEntrada = documento;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload completo", "O arquivo " + arquivoEntrada.getFileName() + " foi salvo!"));
		} catch (IOException | JAXBException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", e.getMessage()));
		}

	}
	
	@SuppressWarnings("static-access")
	public void uploadSaida() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(arquivoSaida.getInputstream()));
			String line;

			StringBuffer xmlUpload = new StringBuffer();
			while ((line = in.readLine()) != null) {
				xmlUpload.append(line);
				System.out.println(line);
			}

			XmlUtil util = new XmlUtil();
			util.xmlToObject(xmlUpload.toString(), NfeProc.class);
			setNfeSaida(convertStringToObject(xmlUpload.toString()));
			
			Documento documento = crateDocumento(xmlUpload.toString(), new Long(getNfeSaida().getNFe().getInfNFe().getIde().getcNF()), new Long(getNfeSaida().getNFe().getInfNFe().getEmit().getCNPJ()), arquivoSaida.getFileName());
			documento.setItens(compararNotas(documento.getId()));
			
			documentoSaida = documento;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload completo", "O arquivo " + arquivoSaida.getFileName() + " foi salvo!"));

		} catch (IOException | JAXBException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", e.getMessage()));
		}

	}

	@SuppressWarnings("static-access")
	public void upload(FileUploadEvent event) {
		try {
			if(arquivoEntrada == null){
				arquivoEntrada = event.getFile();
				
				BufferedReader in = new BufferedReader(new InputStreamReader(arquivoEntrada.getInputstream()));
				String line;
	
				StringBuffer xmlUpload = new StringBuffer();
				while ((line = in.readLine()) != null) {
					xmlUpload.append(line);
					System.out.println(line);
				}
				
				XmlUtil util = new XmlUtil();
				util.xmlToObject(xmlUpload.toString(), NfeProc.class);
				setNfeEntrada(convertStringToObject(xmlUpload.toString()));
				Documento documento = crateDocumento(xmlUpload.toString(), new Long(getNfeEntrada().getNFe().getInfNFe().getIde().getcNF()), new Long(getNfeEntrada().getNFe().getInfNFe().getEmit().getCNPJ()), event.getFile().getFileName());
				documento.setItens(compararNotas(documento.getId()));
				
				documentoEntrada = documento;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload completo", "O arquivo " + event.getFile().getFileName() + " foi salvo!"));

			} else if (arquivoEntrada != null && arquivoSaida == null) {
				arquivoSaida = event.getFile();

				BufferedReader in = new BufferedReader(new InputStreamReader(arquivoSaida.getInputstream()));
				String line;

				StringBuffer xmlUpload = new StringBuffer();
				while ((line = in.readLine()) != null) {
					xmlUpload.append(line);
					System.out.println(line);
				}

				XmlUtil util = new XmlUtil();
				util.xmlToObject(xmlUpload.toString(), NfeProc.class);
				setNfeSaida(convertStringToObject(xmlUpload.toString()));
				
				Documento documento = crateDocumento(xmlUpload.toString(), new Long(getNfeSaida().getNFe().getInfNFe().getIde().getcNF()), new Long(getNfeSaida().getNFe().getInfNFe().getEmit().getCNPJ()), event.getFile().getFileName());
				documento.setItens(compararNotas(documento.getId()));
				
				documentoSaida = documento;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload completo", "O arquivo " + event.getFile().getFileName() + " foi salvo!"));
			}

		} catch (IOException | JAXBException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", e.getMessage()));
		}

	}
	
	@SuppressWarnings("unchecked")
	public List<DocumentoItem> compararNotas(Long idDocumento){

		List<DocumentoItem> itens = new ArrayList<DocumentoItem>();

		Det[] itensNota = getNfeEntrada().getNFe().getInfNFe().getDet();
		
		for(int i = 0; itensNota.length > i; i++){
			List<DocumentoItemResult> itensResult = new ArrayList<DocumentoItemResult>();
			
			DocumentoItem item = gravaDadosDocumento(itensNota[i].getProd().getxProd(), idDocumento, new Long(itensNota[i].getProd().getNCM()));

			Query q = documentoService.getEm()
					.createNativeQuery("SELECT n.* FROM NCM n WHERE n.id = ? ", NCM.class)
					.setParameter(1, itensNota[i].getProd().getNCM());

			List<NCM> lista = q.getResultList();
			
			if (lista.size() > 0) {
				
				DocumentoItemResult itemResult = new DocumentoItemResult();
				NCM ncm = lista.get(0);
				itensNota[i].getProd().setComparadorNCM(NFCompareUtils.compareNFDescription(ncm, itensNota[i]));
				if(!itensNota[i].getProd().isComparadorNCM()){
					 itemResult = gravaDadosDocumentoResult("Descrição incompatível com código NCM " + itensNota[i].getProd().getNCM() + 
											  " - Esperado : \"" + ncm.getDescricao() + "\"" + 
											  " - Obtido : \"" + itensNota[i].getProd().getxProd() + "\"", item.getId());
					itensResult.add(itemResult);
				}
			}else{
				DocumentoItemResult itemResult = gravaDadosDocumentoResult("Código NCM " + itensNota[i].getProd().getNCM() + " não localizado", item.getId());
				itensResult.add(itemResult);
			}
			
			item.setItensResult(itensResult);
			itens.add(item);
		}
		
		return itens;
	}
	
	public void compararNotasEntradaSaida() {
		if(null != documentoEntrada && null!= documentoSaida){
			for (DocumentoItem itemEntrada : documentoEntrada.getItens()) {
				itemEntrada.setItensResultNotasCompare(new ArrayList<>());
				boolean find = false;
				for (DocumentoItem itemSaida : documentoSaida.getItens()) {
					if(itemEntrada.getId().equals(itemSaida.getId())){
						if(!itemEntrada.getDescricao().equals(itemSaida.getDescricao())) {
							DocumentoItemResult result = new DocumentoItemResult();
							
							result.setId(itemEntrada.getId());
							result.setIdDocumentoItem(itemEntrada.getIdDocumento());
							
							StringBuilder resultadoCompare = new StringBuilder()
									.append("Descrição incompatível com código NCM " +itemEntrada.getId())
									.append(" - Esperado : \"" + itemEntrada.getDescricao() + "\"")
									.append(" - Obtido : \"" + itemEntrada.getDescricao() + "\"");
							result.setDescricao(resultadoCompare.toString());

							itemEntrada.getItensResultNotasCompare().add(result);
							find = true;
						}else {
							find = true;
						}
					}
				}
				if(!find) {
					DocumentoItemResult result = new DocumentoItemResult();
					
					result.setId(itemEntrada.getId());
					result.setIdDocumentoItem(itemEntrada.getIdDocumento());
					
					StringBuilder resultadoCompare = new StringBuilder()
							.append("Código NCM '" +itemEntrada.getId() + "' e descrição '")
							.append(itemEntrada.getDescricao() + "' não encontrados no documento de saida.");
					result.setDescricao(resultadoCompare.toString());

					itemEntrada.getItensResultNotasCompare().add(result);
				}
			}
		}
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public Documento crateDocumento(String xml, Long numeroNF, Long cnpj, String nomeArquivo){
		
		Documento documento = new Documento();
		
		documento.setNota(xml);
		documento.setCnpj(cnpj);
		documento.setNumeroNF(numeroNF);
		documento.setNomeNota(nomeArquivo);
		
		return documentoService.register(documento);
	}
	
	public DocumentoItem gravaDadosDocumento(String descricao, Long idDocumento, Long idNcm){
		DocumentoItem item = new DocumentoItem();
		
		item.setDescricao(descricao);
		item.setIdDocumento(idDocumento);
		item.setIdNcm(idNcm);
		
		return documentoService.registerItem(item);
	}
	
	public DocumentoItemResult gravaDadosDocumentoResult(String descricao, Long idDocumentoItem){
		DocumentoItemResult itemResult = new DocumentoItemResult();
		
		itemResult.setDescricao(descricao);
		itemResult.setIdDocumentoItem(idDocumentoItem);
		
		return documentoService.registerItemResult(itemResult);
	}
}
