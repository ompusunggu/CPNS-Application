package project.pam.cpns_2014;

import project.pam.cpns_formasi0.ResponseFormasi0CPNS;
import project.pam.cpns_formasi1.ResponseFormasi1CPNS;
import project.pam.cpns_formasi2.ResponseFormasi2CPNS;
import project.pam.cpns_formasi3.ResponseFormasi3CPNS;
import project.pam.cpns_jadwal.ResponseJadwalCPNS_2014;
import project.pam.cpns_lowongan0.ResponseLowongan0CPNS;
import project.pam.cpns_lowongan1.ResponseLowongan1CPNS;
import retrofit.Callback;
import retrofit.http.POST;

public interface API_CPNS {
	/* URL web service */
	@POST
	("/jadwal")
	void getJadwalCPNS(Callback<ResponseJadwalCPNS_2014> callback);
	
	@POST
	("/formasi0")
	void getFormasi0CPNS(Callback<ResponseFormasi0CPNS> callback);
	
	@POST
	("/formasi1")
	void getFormasi1CPNS(Callback<ResponseFormasi1CPNS> callback);
	
	
	@POST
	("/formasi2")
	void getFormasi2CPNS(Callback<ResponseFormasi2CPNS> callback);
	
	@POST
	("/formasi3")
	void getFormasi3CPNS(Callback<ResponseFormasi3CPNS> callback);
	
	@POST
	("/lowongan0")
	void getLowongan0CPNS(Callback<ResponseLowongan0CPNS> callback);
	
	@POST
	("/lowongan1")
	void getLowongan1CPNS(Callback<ResponseLowongan1CPNS> callback);
	
}
