package project.pam.cpns_jadwal;

public class ModelJadwalCPNS {
	private int idInstansi;
	private String instansi;
	private String status;
	private String jadwalDaftar;
	private int totalPelamar;
	
	public int getIdInstansi() {
		return this.idInstansi;
	}
	public void setIdInstansi(int idInstansi) {
		this.idInstansi = idInstansi;
	}
	public String getInstansi() {
		return this.instansi;
	}
	public void setInstansi(String instansi) {
		this.instansi = instansi;
	}
	public String getStatus() {
		return this.status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getJadwalDaftar() {
		return this.jadwalDaftar;
	}
	public void setJadwalDaftar(String jadwalDaftar) {
		this.jadwalDaftar = jadwalDaftar;
	}
	public int getTotalPelamar() {
		return this.totalPelamar;
	}
	public void setTotalPelamar(int totalPelamar) {
		this.totalPelamar = totalPelamar;
	}
}
