package br.com.costa.fiscalcred.model.nfe;

public class Cobr {
	private Dup[] dup;

	public Dup[] getDup() {
		return dup;
	}

	public void setDup(Dup[] dup) {
		this.dup = dup;
	}

	@Override
	public String toString() {
		return "ClassPojo [dup = " + dup + "]";
	}
}
