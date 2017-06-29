package br.com.costa.fiscalcred.model.nfe;

public class ICMS00 {
	private String CST;

	private String pICMS;

	private String vICMS;

	private String orig;

	private String modBC;

	private String vBC;

	public String getCST() {
		return CST;
	}

	public void setCST(String CST) {
		this.CST = CST;
	}

	public String getPICMS() {
		return pICMS;
	}

	public void setPICMS(String pICMS) {
		this.pICMS = pICMS;
	}

	public String getVICMS() {
		return vICMS;
	}

	public void setVICMS(String vICMS) {
		this.vICMS = vICMS;
	}

	public String getOrig() {
		return orig;
	}

	public void setOrig(String orig) {
		this.orig = orig;
	}

	public String getModBC() {
		return modBC;
	}

	public void setModBC(String modBC) {
		this.modBC = modBC;
	}

	public String getVBC() {
		return vBC;
	}

	public void setVBC(String vBC) {
		this.vBC = vBC;
	}

	@Override
	public String toString() {
		return "ClassPojo [CST = " + CST + ", pICMS = " + pICMS + ", vICMS = " + vICMS + ", orig = " + orig
				+ ", modBC = " + modBC + ", vBC = " + vBC + "]";
	}
}
