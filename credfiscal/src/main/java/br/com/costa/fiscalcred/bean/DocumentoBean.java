package br.com.costa.fiscalcred.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Query;
import javax.xml.bind.JAXBException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.annotations.Type;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.costa.credfiscal.util.DocumentoConstants;
import br.com.costa.credfiscal.util.MensagensConstants;
import br.com.costa.credfiscal.util.NFCompareUtils;
import br.com.costa.credfiscal.util.NotaUtil;
import br.com.costa.fiscalcred.model.Documento;
import br.com.costa.fiscalcred.model.DocumentoItem;
import br.com.costa.fiscalcred.model.DocumentoItemResult;
import br.com.costa.fiscalcred.model.NCM;
import br.com.costa.fiscalcred.model.nfe.Det;
import br.com.costa.fiscalcred.model.nfe.NfeProc;
import br.com.costa.fiscalcred.service.DocumentoService;

@ManagedBean
@SessionScoped
public class DocumentoBean {

	@ManagedProperty("#{documentoService}")
	private DocumentoService documentoService;

	List<UploadedFile> arquivosLote;
	List<Documento> documentosLote;

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

	public List<UploadedFile> getArquivosLote() {
		return arquivosLote;
	}

	public void setArquivosLote(List<UploadedFile> arquivosLote) {
		this.arquivosLote = arquivosLote;
	}

	public List<Documento> getDocumentosLote() {
		return documentosLote;
	}

	public void setDocumentosLote(List<Documento> documentosLote) {
		this.documentosLote = documentosLote;
	}

	@PostConstruct
	private void doInit() {
		arquivosLote = new ArrayList<UploadedFile>();
		documentosLote = new ArrayList<Documento>();
		arquivoEntrada = null;
		arquivoSaida = null;
	}

	@SuppressWarnings("static-access")
	private static NfeProc convertStringToObject(String xmlStr) {
		NotaUtil util = new NotaUtil();
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
			StringBuffer xmlUpload = new StringBuffer();

			NotaUtil util = new NotaUtil();
			util.xmlToObject(xmlUpload.toString(), NfeProc.class);
			setNfeEntrada(convertStringToObject(xmlUpload.toString()));
			Documento documento = crateDocumento(xmlUpload.toString(),
					new Long(getNfeEntrada().getNFe().getInfNFe().getIde().getcNF()),
					new Long(getNfeEntrada().getNFe().getInfNFe().getEmit().getCNPJ()), arquivoEntrada.getFileName());
			documento.setItens(compararNotas(documento.getId()));

			documentoEntrada = documento;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(MensagensConstants.MSG_UPLOAD_COMPLETO, MensagensConstants.INICIO_MSG_ARQUIVO_SALVO
							+ arquivoEntrada.getFileName() + MensagensConstants.FIM_MSG_ARQUIVO_SALVO));
		} catch (JAXBException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", e.getMessage()));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, MensagensConstants.ERRO_INESPERADO, e.getMessage()));
		}

	}

	@SuppressWarnings("static-access")
	public void uploadSaida() {
		try {
			StringBuffer xmlUpload = new StringBuffer();

			NotaUtil util = new NotaUtil();
			util.xmlToObject(xmlUpload.toString(), NfeProc.class);
			setNfeSaida(convertStringToObject(xmlUpload.toString()));

			Documento documento = crateDocumento(xmlUpload.toString(),
					new Long(getNfeSaida().getNFe().getInfNFe().getIde().getcNF()),
					new Long(getNfeSaida().getNFe().getInfNFe().getEmit().getCNPJ()), arquivoSaida.getFileName());
			documento.setItens(compararNotas(documento.getId()));

			documentoSaida = documento;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(MensagensConstants.MSG_UPLOAD_COMPLETO, MensagensConstants.INICIO_MSG_ARQUIVO_SALVO
							+ arquivoSaida.getFileName() + MensagensConstants.FIM_MSG_ARQUIVO_SALVO));
		} catch (JAXBException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", e.getMessage()));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, MensagensConstants.ERRO_INESPERADO, e.getMessage()));
		}
	}

	@SuppressWarnings("unchecked")
	public List<DocumentoItem> compararNotas(Long idDocumento) {

		List<DocumentoItem> itens = new ArrayList<DocumentoItem>();

		Det[] itensNota = getNfeEntrada().getNFe().getInfNFe().getDet();

		for (int i = 0; itensNota.length > i; i++) {
			List<DocumentoItemResult> itensResult = new ArrayList<DocumentoItemResult>();

			DocumentoItem item = gravaDadosDocumento(itensNota[i].getProd().getxProd(), idDocumento,
					new Long(itensNota[i].getProd().getNCM()));

			Query q = documentoService.getEm().createNativeQuery(DocumentoConstants.SELECT_NCM, NCM.class)
					.setParameter(1, itensNota[i].getProd().getNCM());

			List<NCM> lista = q.getResultList();

			if (lista.size() > 0) {

				DocumentoItemResult itemResult = new DocumentoItemResult();
				NCM ncm = lista.get(0);
				itensNota[i].getProd().setComparadorNCM(NFCompareUtils.compareNFDescription(ncm, itensNota[i]));
				if (!itensNota[i].getProd().isComparadorNCM()) {
					itensResult.add(gravaDadosDocumentoResult(Long.parseLong(itensNota[i].getProd().getCProd()),
							Long.parseLong(itensNota[i].getProd().getNItemPed()), itensNota[i].getProd().getNCM(),
							ncm.getDescricao()));
				}
			} else {
				itensResult
						.add(gravaDadosDocumentoResultNaoEncontrado(Long.parseLong(itensNota[i].getProd().getCProd()),
								Long.parseLong(itensNota[i].getProd().getNItemPed()), itensNota[i].getProd().getNCM()));
			}

			item.setItensResult(itensResult);
			itens.add(item);
		}

		return itens;
	}

	public void compararNotasEntradaSaida() {
		if (null != documentoEntrada && null != documentoSaida) {
			for (DocumentoItem itemEntrada : documentoEntrada.getItens()) {
				itemEntrada.setItensResultNotasCompare(new ArrayList<>());
				boolean find = false;
				for (DocumentoItem itemSaida : documentoSaida.getItens()) {
					if (itemEntrada.getIdNcm().equals(itemSaida.getIdNcm())) {
						if (!itemEntrada.getDescricao().equals(itemSaida.getDescricao())) {
							itemEntrada.getItensResultNotasCompare()
									.add(createDocumentoResultInconsistente(itemEntrada.getIdDocumento(),
											itemEntrada.getIdNcm(), itemEntrada.getDescricao(),
											itemEntrada.getDescricao()));
							find = true;
						} else {
							find = true;
						}
					}
				}
				if (!find) {
					itemEntrada.getItensResultNotasCompare().add(createDocumentoResultNaoEncontrado(
							itemEntrada.getIdDocumento(), itemEntrada.getIdNcm(), itemEntrada.getDescricao()));
				}
			}
		}
	}

	public DocumentoItemResult createDocumentoResultInconsistente(long idDocumento, long idNcm,
			String descricaoEsperada, String descricaoEncontrada) {

		DocumentoItemResult result = new DocumentoItemResult();

		result.setIdDocumentoItem(idDocumento);
		result.setIdNcm(idNcm);
		result.setDescricaoEsperada(descricaoEsperada);
		result.setDescricaoEncontrada(descricaoEncontrada);

		result.setFlDescricaoNaoEncontrada(false);

		return result;
	}

	public DocumentoItemResult createDocumentoResultNaoEncontrado(long idDocumento, long idNcm,
			String descricaoEsperada) {

		DocumentoItemResult result = new DocumentoItemResult();

		result.setIdDocumentoItem(idDocumento);
		result.setIdNcm(idNcm);
		result.setDescricaoNaoEncontrada(descricaoEsperada);

		result.setFlDescricaoNaoEncontrada(true);

		return result;
	}

	public Documento crateDocumento(String xml, Long numeroNF, Long cnpj, String nomeArquivo) {

		Documento documento = new Documento();

		documento.setNota(xml);
		documento.setCnpj(cnpj);
		documento.setNumeroNF(numeroNF);
		documento.setNomeNota(nomeArquivo);

		return documentoService.register(documento);
	}

	public DocumentoItem gravaDadosDocumento(String descricao, Long idDocumento, Long idNcm) {
		DocumentoItem item = new DocumentoItem();

		item.setDescricao(descricao);
		item.setIdDocumento(idDocumento);
		item.setIdNcm(idNcm);

		return documentoService.registerItem(item);
	}

	public DocumentoItemResult gravaDadosDocumentoResult(long idNcm, long idDocumentoItem, String descricaoEsperada,
			String descricaoEncontrada) {
		DocumentoItemResult itemResult = new DocumentoItemResult();

		itemResult.setIdNcm(idNcm);
		itemResult.setIdDocumentoItem(idDocumentoItem);
		itemResult.setDescricaoEsperada(descricaoEsperada);
		itemResult.setDescricaoEncontrada(descricaoEncontrada);
		itemResult.setFlDescricaoNaoEncontrada(false);

		return documentoService.registerItemResult(itemResult);
	}

	public DocumentoItemResult gravaDadosDocumentoResultNaoEncontrado(long idNcm, long idDocumentoItem,
			String descricaoEsperada) {
		DocumentoItemResult itemResult = new DocumentoItemResult();

		itemResult.setIdNcm(idNcm);
		itemResult.setIdDocumentoItem(idDocumentoItem);
		itemResult.setDescricaoNaoEncontrada(descricaoEsperada);
		itemResult.setFlDescricaoNaoEncontrada(true);

		return documentoService.registerItemResult(itemResult);
	}

	@SuppressWarnings("static-access")
	public void upload(FileUploadEvent event) {
		try {
			UploadedFile arquivo = event.getFile();

			StringBuffer xmlUpload = new StringBuffer();

			NotaUtil util = new NotaUtil();
			util.xmlToObject(xmlUpload.toString(), NfeProc.class);
			setNfeEntrada(convertStringToObject(xmlUpload.toString()));
			Documento documento = crateDocumento(xmlUpload.toString(),
					new Long(getNfeEntrada().getNFe().getInfNFe().getIde().getcNF()),
					new Long(getNfeEntrada().getNFe().getInfNFe().getEmit().getCNPJ()), event.getFile().getFileName());
			documento.setItens(compararNotas(documento.getId()));

			this.arquivosLote.add(arquivo);
			this.documentosLote.add(documento);

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Upload completo", "O arquivo " + event.getFile().getFileName() + " foi salvo!"));
		} catch (JAXBException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", e.getMessage()));
		}
	}

	@SuppressWarnings("resource")
	public void exportExcel() {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("ComparacaoNCM_" + new Date().getTime());

		int rowNum = 0;
		System.out.println("Creating excel");

		for (Documento documento : this.documentosLote) {
			Row row = sheet.createRow(rowNum++);
			writeBook(documento, row);
		}
	}

	private void writeBook(Documento documento, Row row) {
		Cell cell = row.createCell(1);
		cell.setCellValue(documento.getNomeNota());

		for (DocumentoItem item : documento.getItens()) {
			cell = row.createCell(2);
			cell.setCellValue(item.getIdNcm());
		}
	}
}