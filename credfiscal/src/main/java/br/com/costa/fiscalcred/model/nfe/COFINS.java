package br.com.costa.fiscalcred.model.nfe;

public class COFINS {
	private COFINSAliq COFINSAliq;

	public COFINSAliq getCOFINSAliq() {
		return COFINSAliq;
	}

	public void setCOFINSAliq(COFINSAliq COFINSAliq) {
		this.COFINSAliq = COFINSAliq;
	}

	@Override
	public String toString() {
		return "ClassPojo [COFINSAliq = " + COFINSAliq + "]";
	}
}
