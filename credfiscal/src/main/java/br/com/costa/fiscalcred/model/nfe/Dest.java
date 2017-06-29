package br.com.costa.fiscalcred.model.nfe;

public class Dest {
	private EnderDest enderDest;

	private String indIEDest;

	private String xNome;

	private String email;

	private String CNPJ;

	private String IE;

	public EnderDest getEnderDest() {
		return enderDest;
	}

	public void setEnderDest(EnderDest enderDest) {
		this.enderDest = enderDest;
	}

	public String getIndIEDest() {
		return indIEDest;
	}

	public void setIndIEDest(String indIEDest) {
		this.indIEDest = indIEDest;
	}

	public String getXNome() {
		return xNome;
	}

	public void setXNome(String xNome) {
		this.xNome = xNome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		return "ClassPojo [enderDest = " + enderDest + ", indIEDest = " + indIEDest + ", xNome = " + xNome
				+ ", email = " + email + ", CNPJ = " + CNPJ + ", IE = " + IE + "]";
	}
}
