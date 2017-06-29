package br.com.costa.fiscalcred.model.nfe;

public class IPI {
	private String cEnq;

	private IPINT IPINT;

	public String getCEnq() {
		return cEnq;
	}

	public void setCEnq(String cEnq) {
		this.cEnq = cEnq;
	}

	public IPINT getIPINT() {
		return IPINT;
	}

	public void setIPINT(IPINT IPINT) {
		this.IPINT = IPINT;
	}

	@Override
    public String toString()
    {
        return "ClassPojo [cEnq = "+cEnq+", IPINT = "+IPINT+"]";
    }
}
