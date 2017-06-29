package br.com.costa.fiscalcred.model.nfe;

public class X509Data {
	private String X509Certificate;

	public String getX509Certificate() {
		return X509Certificate;
	}

	public void setX509Certificate(String X509Certificate) {
		this.X509Certificate = X509Certificate;
	}

	@Override
	public String toString() {
		return "ClassPojo [X509Certificate = " + X509Certificate + "]";
	}
}
