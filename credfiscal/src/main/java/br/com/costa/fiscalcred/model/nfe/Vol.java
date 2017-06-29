package br.com.costa.fiscalcred.model.nfe;

public class Vol {
	private Lacres lacres;

	private String pesoB;

	private String qVol;

	private String pesoL;

	public Lacres getLacres() {
		return lacres;
	}

	public void setLacres(Lacres lacres) {
		this.lacres = lacres;
	}

	public String getPesoB() {
		return pesoB;
	}

	public void setPesoB(String pesoB) {
		this.pesoB = pesoB;
	}

	public String getQVol() {
		return qVol;
	}

	public void setQVol(String qVol) {
		this.qVol = qVol;
	}

	public String getPesoL() {
		return pesoL;
	}

	public void setPesoL(String pesoL) {
		this.pesoL = pesoL;
	}

	@Override
	public String toString() {
		return "ClassPojo [lacres = " + lacres + ", pesoB = " + pesoB + ", qVol = " + qVol + ", pesoL = " + pesoL + "]";
	}
}
