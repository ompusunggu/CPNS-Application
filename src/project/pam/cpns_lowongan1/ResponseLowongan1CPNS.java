package project.pam.cpns_lowongan1;

import java.util.List;

public class ResponseLowongan1CPNS {
	private String error;
	private List<ModelLowongan1CPNS> lowongan1;
	
	public String getError() {
		return this.error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public List<ModelLowongan1CPNS> getLowongan1() {
		return this.lowongan1;
	}
	public void setLowongan0(List<ModelLowongan1CPNS> lowongan1) {
		this.lowongan1 = lowongan1;
	}
	
}	
