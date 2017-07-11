package br.com.costa.fiscalcred.bean;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.Query;
import javax.xml.bind.JAXBException;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

	private List<UploadedFile> arquivosLote;
	private List<Documento> documentosLote;
	private List<Map<String, String>> xmlUploads;

	private UploadedFile arquivoEntrada;
	private UploadedFile arquivoSaida;
	private NfeProc nfeEntrada;
	private NfeProc nfeSaida;
	private String nomeArquivoEntrada;
	private String nomeArquivoSaida;
	private Documento documentoEntrada;
	private Documento documentoSaida;

	private boolean comparacaoEfetuda = false;
	private boolean uploadEntrada = false;
	private boolean uploadSaida = false;

	public boolean isUploadEntrada() {
		return uploadEntrada;
	}

	public void setUploadEntrada(boolean uploadEntrada) {
		this.uploadEntrada = uploadEntrada;
	}

	public boolean isUploadSaida() {
		return uploadSaida;
	}

	public void setUploadSaida(boolean uploadSaida) {
		this.uploadSaida = uploadSaida;
	}

	public boolean isComparacaoEfetuda() {
		return comparacaoEfetuda;
	}

	public void setComparacaoEfetuda(boolean comparacaoEfetuda) {
		this.comparacaoEfetuda = comparacaoEfetuda;
	}

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

	public List<Map<String, String>> getXmlUploads() {
		return xmlUploads;
	}

	public void setXmlUploads(List<Map<String, String>> xmlUploads) {
		this.xmlUploads = xmlUploads;
	}

	@PostConstruct
	private void doInit() {
		arquivosLote = new ArrayList<UploadedFile>();
		documentosLote = new ArrayList<Documento>();
		xmlUploads = new ArrayList<Map<String, String>>();
		arquivoEntrada = null;
		arquivoSaida = null;
		documentoEntrada = new Documento();
		documentoSaida = new Documento();
		comparacaoEfetuda = false;
		uploadEntrada = false;
		uploadSaida = false;
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
			BufferedReader in = new BufferedReader(new InputStreamReader(arquivoEntrada.getInputstream()));
			String line;
			StringBuffer xmlUpload = new StringBuffer();
			while ((line = in.readLine()) != null) {
				xmlUpload.append(line);
			}

			NotaUtil util = new NotaUtil();
			util.replacesNfe(xmlUpload.toString());
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
			uploadEntrada = true;
		} catch (JAXBException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", e.getMessage()));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, MensagensConstants.ERRO_INESPERADO, e.getMessage()));
		}

	}

	@SuppressWarnings("static-access")
	public void limparComparacaoNfes(ActionEvent ae) {
		doInit();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, MensagensConstants.ARQUIVOS_LIMPOS, null));
	}

	@SuppressWarnings("static-access")
	public void uploadSaida() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(arquivoSaida.getInputstream()));
			String line;
			StringBuffer xmlUpload = new StringBuffer();
			while ((line = in.readLine()) != null) {
				xmlUpload.append(line);
			}

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
			uploadSaida = true;
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

				NCM ncm = lista.get(0);
				itensNota[i].getProd().setComparadorNCM(NFCompareUtils.compareNFDescription(ncm, itensNota[i]));
				if (!itensNota[i].getProd().isComparadorNCM()) {
					itensResult.add(gravaDadosDocumentoResult(Long.parseLong(itensNota[i].getProd().getcProd()),
							item.getId(), ncm.getDescricao(), itensNota[i].getProd().getxProd()));
				}
			} else {
				itensResult
						.add(gravaDadosDocumentoResultNaoEncontrado(Long.parseLong(itensNota[i].getProd().getcProd()),
								Long.parseLong(itensNota[i].getProd().getnItemPed()), itensNota[i].getProd().getNCM()));
			}

			item.setItensResult(itensResult);
			itens.add(item);
		}

		return itens;
	}

	@SuppressWarnings("static-access")
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
			comparacaoEfetuda = true;
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informe uma nota de entrada e uma de saida.", null));
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

	public void upload(FileUploadEvent event) {
		try {

			UploadedFile arquivo = event.getFile();
			BufferedReader in = new BufferedReader(new InputStreamReader(arquivo.getInputstream()));

			String line;
			StringBuffer xmlUpload = new StringBuffer();
			while ((line = in.readLine()) != null) {
				xmlUpload.append(line);
			}

			Map<String, String> notaMap = new HashMap<String, String>();
			notaMap.put(arquivo.getFileName(), xmlUpload.toString());

			this.xmlUploads.add(notaMap);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Upload completo", "O arquivo " + event.getFile().getFileName() + " foi salvo!"));

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", e.getMessage()));
		}
	}

	@SuppressWarnings({ "resource", "deprecation", "static-access" })
	public void exportExcel() {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("ComparacaoNCM_" + new Date().getTime());
		String excelFilePath = "D:\\Profissional\\Excel\\ComparacaoNCM_" + new Date().getTime() + ".xlsx";

		int rowNum = 0;

		XSSFFont headerFont = workbook.createFont();
		CellStyle headerStyle = workbook.createCellStyle();

		headerFont.setBoldweight(headerFont.BOLDWEIGHT_BOLD);
		headerFont.setFontHeightInPoints((short) 12);
		headerStyle.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
		headerStyle.setAlignment(headerStyle.ALIGN_CENTER);
		headerStyle.setFont(headerFont);
		headerStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);

		for (Documento documento : this.documentosLote) {

			Row rowHeader = sheet.createRow(rowNum++);
			Cell cellDocHeader = rowHeader.createCell(1);
			cellDocHeader.setCellValue("Documento");
			cellDocHeader.setCellStyle(headerStyle);

			Row row = sheet.createRow(rowNum++);
			Cell cellDoc = row.createCell(1);
			cellDoc.setCellValue(documento.getNomeNota());

			Row rowItemHeader = sheet.createRow(rowNum++);
			Cell cellItemHeader = rowItemHeader.createCell(1);
			cellItemHeader.setCellValue("NCM");
			cellItemHeader.setCellStyle(headerStyle);

			cellItemHeader = rowItemHeader.createCell(2);
			cellItemHeader.setCellValue("Descrição Encontrada");
			cellItemHeader.setCellStyle(headerStyle);

			cellItemHeader = rowItemHeader.createCell(3);
			cellItemHeader.setCellValue("Descrição Esperada");
			cellItemHeader.setCellStyle(headerStyle);

			for (DocumentoItem item : documento.getItens()) {
				Row rowItem = sheet.createRow(rowNum++);

				Cell cellItem = rowItem.createCell(1);
				cellItem.setCellValue(item.getIdNcm());

				for (DocumentoItemResult result : item.getItensResult()) {
					cellItem = rowItem.createCell(2);
					if (result.isFlDescricaoNaoEncontrada()) {
						cellItem.setCellValue(result.getDescricaoNaoEncontrada());
					} else {
						cellItem.setCellValue(result.getDescricaoEncontrada());

						cellItem = rowItem.createCell(3);
						cellItem.setCellValue(result.getDescricaoEsperada());
					}
				}
			}
		}

		documentosLote = new ArrayList<Documento>();

		try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
			workbook.write(outputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "static-access", "deprecation", "resource" })
	public void excelNovo() {
		try {

			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("ComparacaoNCM_" + new Date().getTime());
			String excelFilePath = "D:\\Profissional\\Excel\\ComparacaoNCM_" + new Date().getTime() + ".xlsx";

			int rowNum = 0;

			XSSFFont headerFont = workbook.createFont();
			CellStyle headerStyle = workbook.createCellStyle();

			headerFont.setBoldweight(headerFont.BOLDWEIGHT_BOLD);
			headerFont.setFontHeightInPoints((short) 12);
			headerStyle.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
			headerStyle.setAlignment(headerStyle.ALIGN_CENTER);
			headerStyle.setFont(headerFont);
			headerStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);

			for (Map<String, String> mapXml : this.xmlUploads) {

				String xmlUpload = mapXml.values().toString();
				setNfeEntrada(convertStringToObject(xmlUpload));
				Documento documento = crateDocumento(xmlUpload,
						new Long(getNfeEntrada().getNFe().getInfNFe().getIde().getcNF()),
						new Long(getNfeEntrada().getNFe().getInfNFe().getEmit().getCNPJ()), mapXml.keySet().toString());
				documento.setItens(compararNotas(documento.getId()));

				Row rowHeader = sheet.createRow(rowNum++);
				Cell cellDocHeader = rowHeader.createCell(1);
				cellDocHeader.setCellValue("Documento");
				cellDocHeader.setCellStyle(headerStyle);

				Row row = sheet.createRow(rowNum++);
				Cell cellDoc = row.createCell(1);
				cellDoc.setCellValue(documento.getNomeNota());

				Row rowItemHeader = sheet.createRow(rowNum++);
				Cell cellItemHeader = rowItemHeader.createCell(1);
				cellItemHeader.setCellValue("NCM");
				cellItemHeader.setCellStyle(headerStyle);

				cellItemHeader = rowItemHeader.createCell(2);
				cellItemHeader.setCellValue("Descrição Encontrada");
				cellItemHeader.setCellStyle(headerStyle);

				cellItemHeader = rowItemHeader.createCell(3);
				cellItemHeader.setCellValue("Descrição Esperada");
				cellItemHeader.setCellStyle(headerStyle);

				for (DocumentoItem item : documento.getItens()) {
					Row rowItem = sheet.createRow(rowNum++);

					Cell cellItem = rowItem.createCell(1);
					cellItem.setCellValue(item.getIdNcm());

					for (DocumentoItemResult result : item.getItensResult()) {
						cellItem = rowItem.createCell(2);
						if (result.isFlDescricaoNaoEncontrada()) {
							cellItem.setCellValue(result.getDescricaoNaoEncontrada());
						} else {
							cellItem.setCellValue(result.getDescricaoEncontrada());

							cellItem = rowItem.createCell(3);
							cellItem.setCellValue(result.getDescricaoEsperada());
						}
					}
				}
			}

			this.xmlUploads = new ArrayList<Map<String, String>>();

			try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
				workbook.write(outputStream);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", e.getMessage()));
		}
	}
}