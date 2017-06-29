package br.com.costa.fiscalcred.model.nfe;

public class Transporta {
	private String xEnder;

	private String xNome;

	private String xMun;

	private String UF;

	private String CNPJ;

	private String IE;

	public String getXEnder() {
		return xEnder;
	}

	public void setXEnder(String xEnder) {
		this.xEnder = xEnder;
	}

	public String getXNome() {
		return xNome;
	}

	public void setXNome(String xNome) {
		this.xNome = xNome;
	}

	public String getXMun() {
		return xMun;
	}

	public void setXMun(String xMun) {
		this.xMun = xMun;
	}

	public String getUF() {
		return UF;
	}

	public void setUF(String UF) {
		this.UF = UF;
	}

	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String CNPJ) {
		this.CNPJ = CNPJ;
	}

	public String getIE() {
		return IE;
	}

	public void setIE(String IE) {
		this.IE = IE;
	}

	@Override
	public String toString() {
		return "ClassPojo [xEnder = " + xEnder + ", xNome = " + xNome + ", xMun = " + xMun + ", UF = " + UF
				+ ", CNPJ = " + CNPJ + ", IE = " + IE + "]";
	}
}
