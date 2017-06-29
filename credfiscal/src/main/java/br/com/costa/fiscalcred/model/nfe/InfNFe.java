package br.com.costa.fiscalcred.model.nfe;

public class InfNFe {
	private Total total;

	private Emit emit;

	private Dest dest;

	private Det[] det;

	private Ide ide;

	private InfAdic infAdic;

	private String Id;

	private String versao;

	private Transp transp;

	private Cobr cobr;

	public Total getTotal() {
		return total;
	}

	public void setTotal(Total total) {
		this.total = total;
	}

	public Emit getEmit() {
		return emit;
	}

	public void setEmit(Emit emit) {
		this.emit = emit;
	}

	public Dest getDest() {
		return dest;
	}

	public void setDest(Dest dest) {
		this.dest = dest;
	}

	public Det[] getDet() {
		return det;
	}

	public void setDet(Det[] det) {
		this.det = det;
	}

	public Ide getIde() {
		return ide;
	}

	public void setIde(Ide ide) {
		this.ide = ide;
	}

	public InfAdic getInfAdic() {
		return infAdic;
	}

	public void setInfAdic(InfAdic infAdic) {
		this.infAdic = infAdic;
	}

	public String getId() {
		return Id;
	}

	public void setId(String Id) {
		this.Id = Id;
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public Transp getTransp() {
		return transp;
	}

	public void setTransp(Transp transp) {
		this.transp = transp;
	}

	public Cobr getCobr() {
		return cobr;
	}

	public void setCobr(Cobr cobr) {
		this.cobr = cobr;
	}

	@Override
	public String toString() {
		return "ClassPojo [total = " + total + ", emit = " + emit + ", dest = " + dest + ", det = " + det + ", ide = "
				+ ide + ", infAdic = " + infAdic + ", Id = " + Id + ", versao = " + versao + ", transp = " + transp
				+ ", cobr = " + cobr + "]";
	}
}
