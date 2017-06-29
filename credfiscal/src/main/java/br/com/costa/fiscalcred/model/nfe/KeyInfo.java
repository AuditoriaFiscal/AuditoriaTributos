package br.com.costa.fiscalcred.model.nfe;

public class KeyInfo {
	private X509Data X509Data;

	public X509Data getX509Data() {
		return X509Data;
	}

	public void setX509Data(X509Data X509Data) {
		this.X509Data = X509Data;
	}

	@Override
	public String toString() {
		return "ClassPojo [X509Data = " + X509Data + "]";
	}
}
