package br.com.costa.fiscalcred.model.nfe;

public class ICMS {
	private ICMS00 ICMS00;

	public ICMS00 getICMS00() {
		return ICMS00;
	}

	public void setICMS00(ICMS00 ICMS00) {
		this.ICMS00 = ICMS00;
	}

	@Override
	public String toString() {
		return "ClassPojo [ICMS00 = " + ICMS00 + "]";
	}
}
