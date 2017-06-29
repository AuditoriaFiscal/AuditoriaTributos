package br.com.costa.fiscalcred.model.nfe;

public class NFe {
	private InfNFe infNFe;

	private String xmlns;

	private Signature Signature;

	public InfNFe getInfNFe() {
		return infNFe;
	}

	public void setInfNFe(InfNFe infNFe) {
		this.infNFe = infNFe;
	}

	public String getXmlns() {
		return xmlns;
	}

	public void setXmlns(String xmlns) {
		this.xmlns = xmlns;
	}

	public Signature getSignature() {
		return Signature;
	}

	public void setSignature(Signature Signature) {
		this.Signature = Signature;
	}

	@Override
	public String toString() {
		return "ClassPojo [infNFe = " + infNFe + ", xmlns = " + xmlns + ", Signature = " + Signature + "]";
	}
}
