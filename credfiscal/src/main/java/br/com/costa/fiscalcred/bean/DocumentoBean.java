package br.com.costa.fiscalcred.bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import br.com.costa.fiscalcred.model.Documento;
import br.com.costa.fiscalcred.service.DocumentoService;

@ManagedBean
@SessionScoped
public class DocumentoBean {

	@ManagedProperty("#{documentoService}")
	private DocumentoService documentoService;
	private Documento documento = new Documento();
	private UploadedFile uploadedFile;

	public UploadedFile getUploadFile() {
		return uploadedFile;
	}

	public void setUploadFile(UploadedFile file) {
		this.uploadedFile = file;
	}

	public DocumentoService getDocumentoService() {
		return documentoService;
	}

	public void setDocumentoService(DocumentoService documentoService) {
		this.documentoService = documentoService;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public String salvaDocumentoEntrada() {
		
		try {
			uploadedFile.getInputstream();
			
			URL url = new URL("http://localhost:8080/barramento/documento/" + documento.getNome());
			HttpURLConnection conn;
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept", "application/json");
			
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("O documento foi inserido"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	public void uploadEntrada() {

		try {
			InputStream inputstream = uploadedFile.getInputstream();

		    BufferedReader in = new BufferedReader(new InputStreamReader(uploadedFile.getInputstream()));
		    String line;

		    List<String> responseData = new ArrayList<String>();
		    while ((line = in.readLine()) != null) {
		        System.out.println(line);
		    }
		    //JAXB XML TO OBJECT
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", e.getMessage()));
		}
	}
}
