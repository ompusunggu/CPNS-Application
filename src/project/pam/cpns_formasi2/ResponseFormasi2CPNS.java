package project.pam.cpns_formasi2;

import java.util.List;

public class ResponseFormasi2CPNS {
	private String error;
	private List<ModelFormasi2CPNS> formasi2;
	
	public String getError() {
		return this.error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public List<ModelFormasi2CPNS> getFormasi2() {
		return this.formasi2;
	}
	public void setFormasi1(List<ModelFormasi2CPNS> formasi2) {
		this.formasi2 = formasi2;
	}
	
}
