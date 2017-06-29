package br.com.costa.fiscalcred.model.nfe;

public class Dup {
	private String nDup;

	private String vDup;

	private String dVenc;

	public String getNDup() {
		return nDup;
	}

	public void setNDup(String nDup) {
		this.nDup = nDup;
	}

	public String getVDup() {
		return vDup;
	}

	public void setVDup(String vDup) {
		this.vDup = vDup;
	}

	public String getDVenc() {
		return dVenc;
	}

	public void setDVenc(String dVenc) {
		this.dVenc = dVenc;
	}

	@Override
	public String toString() {
		return "ClassPojo [nDup = " + nDup + ", vDup = " + vDup + ", dVenc = " + dVenc + "]";
	}
}
