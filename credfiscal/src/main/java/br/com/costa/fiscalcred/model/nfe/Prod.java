package br.com.costa.fiscalcred.model.nfe;

public class Prod {
	private String uCom;

	private String vUnCom;

	private String NCM;

	private String xPed;

	private String xProd;

	private String cProd;

	private String vProd;

	private String nItemPed;

	private String uTrib;

	private String cEAN;

	private String qTrib;

	private String vUnTrib;

	private String CFOP;

	private String indTot;

	private String cEANTrib;

	private String qCom;
	
	private boolean comparadorNCM = false;

	public String getUCom() {
		return uCom;
	}

	public void setUCom(String uCom) {
		this.uCom = uCom;
	}

	public String getVUnCom() {
		return vUnCom;
	}

	public void setVUnCom(String vUnCom) {
		this.vUnCom = vUnCom;
	}

	public String getNCM() {
		return NCM;
	}

	public void setNCM(String NCM) {
		this.NCM = NCM;
	}

	public String getXPed() {
		return xPed;
	}

	public void setXPed(String xPed) {
		this.xPed = xPed;
	}

	public String getxProd() {
		return xProd;
	}

	public void setxProd(String xProd) {
		this.xProd = xProd;
	}

	public String getcProd() {
		return cProd;
	}

	public void setcProd(String cProd) {
		this.cProd = cProd;
	}

	public String getVProd() {
		return vProd;
	}

	public void setVProd(String vProd) {
		this.vProd = vProd;
	}

	public String getnItemPed() {
		return nItemPed;
	}

	public void setnItemPed(String nItemPed) {
		this.nItemPed = nItemPed;
	}

	public String getUTrib() {
		return uTrib;
	}

	public void setUTrib(String uTrib) {
		this.uTrib = uTrib;
	}

	public String getCEAN() {
		return cEAN;
	}

	public void setCEAN(String cEAN) {
		this.cEAN = cEAN;
	}

	public String getQTrib() {
		return qTrib;
	}

	public void setQTrib(String qTrib) {
		this.qTrib = qTrib;
	}

	public String getVUnTrib() {
		return vUnTrib;
	}

	public void setVUnTrib(String vUnTrib) {
		this.vUnTrib = vUnTrib;
	}

	public String getCFOP() {
		return CFOP;
	}

	public void setCFOP(String CFOP) {
		this.CFOP = CFOP;
	}

	public String getIndTot() {
		return indTot;
	}

	public void setIndTot(String indTot) {
		this.indTot = indTot;
	}

	public String getCEANTrib() {
		return cEANTrib;
	}

	public void setCEANTrib(String cEANTrib) {
		this.cEANTrib = cEANTrib;
	}

	public String getQCom() {
		return qCom;
	}

	public void setQCom(String qCom) {
		this.qCom = qCom;
	}

	
	public boolean isComparadorNCM() {
		return comparadorNCM;
	}

	public void setComparadorNCM(boolean comparadorNCM) {
		this.comparadorNCM = comparadorNCM;
	}

	@Override
	public String toString() {
		return "ClassPojo [uCom = " + uCom + ", vUnCom = " + vUnCom + ", NCM = " + NCM + ", xPed = " + xPed
				+ ", xProd = " + xProd + ", cProd = " + cProd + ", vProd = " + vProd + ", nItemPed = " + nItemPed
				+ ", uTrib = " + uTrib + ", cEAN = " + cEAN + ", qTrib = " + qTrib + ", vUnTrib = " + vUnTrib
				+ ", CFOP = " + CFOP + ", indTot = " + indTot + ", cEANTrib = " + cEANTrib + ", qCom = " + qCom + "]";
	}
}
