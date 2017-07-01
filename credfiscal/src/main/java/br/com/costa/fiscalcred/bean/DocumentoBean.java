package br.com.costa.fiscalcred.bean;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

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

    @SuppressWarnings("static-access")
	private static NfeProc convertStringToObject(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
        DocumentBuilder builder;
        XmlUtil util = new XmlUtil();
        try  
        {  
            builder = factory.newDocumentBuilder();  
            Document doc = builder.parse( new InputSource( new StringReader( xmlStr ) ) ); 
            
            TransformerFactory tranFactory = TransformerFactory.newInstance();
            Transformer aTransformer = tranFactory.newTransformer();
            Source src = new DOMSource(doc);
            Result dest = new StreamResult(new File("xmlFileName.xml"));
            aTransformer.transform(src, dest);
            
    		String xml = util.replacesNfe(util.leXml("xmlFileName.xml"));
    		NfeProc nfe = util.xmlToObject(xml, NfeProc.class);
            return nfe;
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
        return null;
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
			    
		    }else if(arquivoEntrada != null && arquivoSaida == null){
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
		    }

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload completo", "O arquivo " + event.getFile().getFileName() + " foi salvo!"));
		} catch (IOException | JAXBException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", e.getMessage()));
		}

	}
	
	public void handleFileUpload(FileUploadEvent event) {
		FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
    
}
