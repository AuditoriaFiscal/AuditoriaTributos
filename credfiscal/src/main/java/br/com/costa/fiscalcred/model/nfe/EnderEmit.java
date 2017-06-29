package br.com.costa.fiscalcred.model.nfe;

public class EnderEmit {
	private String xLgr;

	private String cPais;

	private String nro;

	private String CEP;

	private String xBairro;

	private String xMun;

	private String UF;

	private String xPais;

	private String cMun;

	public String getXLgr() {
		return xLgr;
	}

	public void setXLgr(String xLgr) {
		this.xLgr = xLgr;
	}

	public String getCPais() {
		return cPais;
	}

	public void setCPais(String cPais) {
		this.cPais = cPais;
	}

	public String getNro() {
		return nro;
	}

	public void setNro(String nro) {
		this.nro = nro;
	}

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String CEP) {
		this.CEP = CEP;
	}

	public String getXBairro() {
		return xBairro;
	}

	public void setXBairro(String xBairro) {
		this.xBairro = xBairro;
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

	public String getXPais() {
		return xPais;
	}

	public void setXPais(String xPais) {
		this.xPais = xPais;
	}

	public String getCMun() {
		return cMun;
	}

	public void setCMun(String cMun) {
		this.cMun = cMun;
	}

	@Override
	public String toString() {
		return "ClassPojo [xLgr = " + xLgr + ", cPais = " + cPais + ", nro = " + nro + ", CEP = " + CEP + ", xBairro = "
				+ xBairro + ", xMun = " + xMun + ", UF = " + UF + ", xPais = " + xPais + ", cMun = " + cMun + "]";
	}
}
