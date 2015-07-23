package project.pam.cpns_jadwal;

//import javax.security.auth.callback.Callback;
import project.pam.cpns_2014.R;
import project.pam.cpns_2014.RESTClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class SplashscreenJadwal extends Activity {
	Context myContext;
	ProgressDialog progress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splashscreen_jadwal);
		
		myContext = getApplicationContext();
		progress = ProgressDialog.show(SplashscreenJadwal.this, "Inisialisasi data", "Sedang mengunduh data CPNS", true);
		RESTClient.get().getJadwalCPNS(new Callback<ResponseJadwalCPNS_2014>() {
			@Override
			public void failure(RetrofitError error) {
				// TODO Auto-generated method stub
				progress.dismiss();
				Toast.makeText(getApplicationContext(),"AKSES KE SERVER GAGAL"+error.getMessage(), Toast.LENGTH_LONG).show();
				
			}
			
			@Override
			public void success(ResponseJadwalCPNS_2014 jadwal, Response response) {
				// TODO Auto-generated method stub
				if (jadwal.getJadwalCPNS().size() > 0) {
					ControllerJadwalCPNS cJP = new ControllerJadwalCPNS(myContext);
					cJP.open();
					cJP.deleteData();
					
					for(int x = 0; x <jadwal.getJadwalCPNS().size(); x++){
						ModelJadwalCPNS tmpJadwal = jadwal.getJadwalCPNS().get(x);
						
						cJP.inserData(tmpJadwal.getIdInstansi(), tmpJadwal.getInstansi(), tmpJadwal.getStatus(), tmpJadwal.getJadwalDaftar(), tmpJadwal.getTotalPelamar());
					}
					
					cJP.close();
					
					Intent sendIntent = new Intent(myContext, JadwalCPNS.class);
					startActivity(sendIntent);
					finish();
					
				} else {
					Toast.makeText(getApplicationContext(),"DATA SEDANG TIDAK TERSEDIA", Toast.LENGTH_LONG).show();	
				}
				
				progress.dismiss();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splashscreen_jadwal, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
