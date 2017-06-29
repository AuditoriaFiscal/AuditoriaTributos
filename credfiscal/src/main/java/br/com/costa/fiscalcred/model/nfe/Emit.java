package br.com.costa.fiscalcred.model.nfe;

public class Emit {
	private String CRT;

	private String IM;

	private String xNome;

	private EnderEmit enderEmit;

	private String CNPJ;

	private String xFant;

	private String IE;

	public String getCRT() {
		return CRT;
	}

	public void setCRT(String CRT) {
		this.CRT = CRT;
	}

	public String getIM() {
		return IM;
	}

	public void setIM(String IM) {
		this.IM = IM;
	}

	public String getXNome() {
		return xNome;
	}

	public void setXNome(String xNome) {
		this.xNome = xNome;
	}

	public EnderEmit getEnderEmit() {
		return enderEmit;
	}

	public void setEnderEmit(EnderEmit enderEmit) {
		this.enderEmit = enderEmit;
	}

	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String CNPJ) {
		this.CNPJ = CNPJ;
	}

	public String getXFant() {
		return xFant;
	}

	public void setXFant(String xFant) {
		this.xFant = xFant;
	}

	public String getIE() {
		return IE;
	}

	public void setIE(String IE) {
		this.IE = IE;
	}

	@Override
	public String toString() {
		return "ClassPojo [CRT = " + CRT + ", IM = " + IM + ", xNome = " + xNome + ", enderEmit = " + enderEmit
				+ ", CNPJ = " + CNPJ + ", xFant = " + xFant + ", IE = " + IE + "]";
	}
}
