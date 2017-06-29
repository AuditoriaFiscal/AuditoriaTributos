package br.com.costa.fiscalcred.model.nfe;

public class CanonicalizationMethod {
	private String Algorithm;

	public String getAlgorithm() {
		return Algorithm;
	}

	public void setAlgorithm(String Algorithm) {
		this.Algorithm = Algorithm;
	}

	@Override
	public String toString() {
		return "ClassPojo [Algorithm = " + Algorithm + "]";
	}
}
