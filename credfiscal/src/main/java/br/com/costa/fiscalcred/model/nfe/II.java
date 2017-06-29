package br.com.costa.fiscalcred.model.nfe;

public class II {
	private String vII;

	private String vDespAdu;

	private String vIOF;

	private String vBC;

	public String getVII() {
		return vII;
	}

	public void setVII(String vII) {
		this.vII = vII;
	}

	public String getVDespAdu() {
		return vDespAdu;
	}

	public void setVDespAdu(String vDespAdu) {
		this.vDespAdu = vDespAdu;
	}

	public String getVIOF() {
		return vIOF;
	}

	public void setVIOF(String vIOF) {
		this.vIOF = vIOF;
	}

	public String getVBC() {
		return vBC;
	}

	public void setVBC(String vBC) {
		this.vBC = vBC;
	}

	@Override
	public String toString() {
		return "ClassPojo [vII = " + vII + ", vDespAdu = " + vDespAdu + ", vIOF = " + vIOF + ", vBC = " + vBC + "]";
	}
}
