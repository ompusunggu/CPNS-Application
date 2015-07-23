package project.pam.cpns_formasi3;

import java.util.List;

public class ResponseFormasi3CPNS {
	private String error;
	private List<ModelFormasi3CPNS> formasi3;
	
	public String getError() {
		return this.error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public List<ModelFormasi3CPNS> getFormasi3() {
		return this.formasi3;
	}
	public void setFormasi3(List<ModelFormasi3CPNS> formasi3) {
		this.formasi3 = formasi3;
	}
	
}
