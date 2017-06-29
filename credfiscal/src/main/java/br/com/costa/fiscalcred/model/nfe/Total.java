package br.com.costa.fiscalcred.model.nfe;

public class Total {
	private ICMSTot ICMSTot;

	public ICMSTot getICMSTot() {
		return ICMSTot;
	}

	public void setICMSTot(ICMSTot ICMSTot) {
		this.ICMSTot = ICMSTot;
	}

	@Override
	public String toString() {
		return "ClassPojo [ICMSTot = " + ICMSTot + "]";
	}
}
