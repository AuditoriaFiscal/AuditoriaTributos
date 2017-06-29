package br.com.costa.fiscalcred.model.nfe;

public class Imposto {
	private II II;

	private PIS PIS;

	private String vTotTrib;

	private IPI IPI;

	private COFINS COFINS;

	private ICMS ICMS;

	public II getII() {
		return II;
	}

	public void setII(II II) {
		this.II = II;
	}

	public PIS getPIS() {
		return PIS;
	}

	public void setPIS(PIS PIS) {
		this.PIS = PIS;
	}

	public String getVTotTrib() {
		return vTotTrib;
	}

	public void setVTotTrib(String vTotTrib) {
		this.vTotTrib = vTotTrib;
	}

	public IPI getIPI() {
		return IPI;
	}

	public void setIPI(IPI IPI) {
		this.IPI = IPI;
	}

	public COFINS getCOFINS() {
		return COFINS;
	}

	public void setCOFINS(COFINS COFINS) {
		this.COFINS = COFINS;
	}

	public ICMS getICMS() {
		return ICMS;
	}

	public void setICMS(ICMS ICMS) {
		this.ICMS = ICMS;
	}

	@Override
	public String toString() {
		return "ClassPojo [II = " + II + ", PIS = " + PIS + ", vTotTrib = " + vTotTrib + ", IPI = " + IPI
				+ ", COFINS = " + COFINS + ", ICMS = " + ICMS + "]";
	}
}
