package br.com.costa.fiscalcred.model.nfe;

public class Det {
	private Imposto imposto;

	private String nItem;

	private Prod prod;

	public Imposto getImposto() {
		return imposto;
	}

	public void setImposto(Imposto imposto) {
		this.imposto = imposto;
	}

	public String getNItem() {
		return nItem;
	}

	public void setNItem(String nItem) {
		this.nItem = nItem;
	}

	public Prod getProd() {
		return prod;
	}

	public void setProd(Prod prod) {
		this.prod = prod;
	}

	@Override
	public String toString() {
		return "ClassPojo [imposto = " + imposto + ", nItem = " + nItem + ", prod = " + prod + "]";
	}
}
