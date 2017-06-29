package br.com.costa.fiscalcred.model.nfe;

public class Transp {
	private Vol vol;

	private Transporta transporta;

	private String modFrete;

	public Vol getVol() {
		return vol;
	}

	public void setVol(Vol vol) {
		this.vol = vol;
	}

	public Transporta getTransporta() {
		return transporta;
	}

	public void setTransporta(Transporta transporta) {
		this.transporta = transporta;
	}

	public String getModFrete() {
		return modFrete;
	}

	public void setModFrete(String modFrete) {
		this.modFrete = modFrete;
	}

	@Override
	public String toString() {
		return "ClassPojo [vol = " + vol + ", transporta = " + transporta + ", modFrete = " + modFrete + "]";
	}
}
