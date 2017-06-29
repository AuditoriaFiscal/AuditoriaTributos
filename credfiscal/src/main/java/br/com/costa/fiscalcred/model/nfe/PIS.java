package br.com.costa.fiscalcred.model.nfe;

public class PIS {
	private PISAliq PISAliq;

	public PISAliq getPISAliq() {
		return PISAliq;
	}

	public void setPISAliq(PISAliq PISAliq) {
		this.PISAliq = PISAliq;
	}

	@Override
	public String toString() {
		return "ClassPojo [PISAliq = " + PISAliq + "]";
	}
}
