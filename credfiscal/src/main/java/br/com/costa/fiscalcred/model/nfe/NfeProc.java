package br.com.costa.fiscalcred.model.nfe;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="nfeProc") 
public class NfeProc {
	
	private String protNFe;

	private String xmlns;

	private String versao;

	private NFe NFe;

	public String getProtNFe() {
		return protNFe;
	}

	public void setProtNFe(String protNFe) {
		this.protNFe = protNFe;
	}

	public String getXmlns() {
		return xmlns;
	}

	public void setXmlns(String xmlns) {
		this.xmlns = xmlns;
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public NFe getNFe() {
		return NFe;
	}

	public void setNFe(NFe NFe) {
		this.NFe = NFe;
	}

	@Override
	public String toString() {
		return "ClassPojo [protNFe = " + protNFe + ", xmlns = " + xmlns + ", versao = " + versao + ", NFe = " + NFe
				+ "]";
	}
}
