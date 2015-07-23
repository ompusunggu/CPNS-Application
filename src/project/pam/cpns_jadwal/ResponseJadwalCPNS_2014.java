package project.pam.cpns_jadwal;

import java.util.List;

public class ResponseJadwalCPNS_2014 {
	private String error;
	private List <ModelJadwalCPNS> jadwal;
	
	public String getError() {
	  return this.error;
	}
	public void setError(String error) {
	  this.error = error;
	}
	public List <ModelJadwalCPNS> getJadwalCPNS() {
	  return this.jadwal;
	}
	public void setJadwalCPNS(List <ModelJadwalCPNS> jadwal) {
	  this.jadwal = jadwal;
	}
}
