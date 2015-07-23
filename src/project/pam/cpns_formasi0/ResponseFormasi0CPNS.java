package project.pam.cpns_formasi0;

import java.util.List;

public class ResponseFormasi0CPNS {
	private String error;
	
	private List<ModelFormasi0CPNS> formasi0;
	
	public String getError() {
		return this.error;
	}

	public void setError(String error) {
		this.error = error;
	} 
	
	public List <ModelFormasi0CPNS> getFormasi0() {
		return this.formasi0;
	}

	public void setFormasi0(List <ModelFormasi0CPNS> formasi0) {
		this.formasi0 = formasi0;
	}
}
