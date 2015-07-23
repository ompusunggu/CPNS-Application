package project.pam.cpns_formasi1;

import java.util.List;

public class ResponseFormasi1CPNS {
	private String error;
	private List<ModelFormasi1CPNS> formasi1;
	
	public String getError() {
		return this.error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public List<ModelFormasi1CPNS> getFormasi1() {
		return this.formasi1;
	}
	public void setFormasi1(List<ModelFormasi1CPNS> formasi1) {
		this.formasi1 = formasi1;
	}
	
}
