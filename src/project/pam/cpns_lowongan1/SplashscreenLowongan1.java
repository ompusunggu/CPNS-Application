package project.pam.cpns_lowongan1;

import project.pam.cpns_2014.R;
import project.pam.cpns_2014.RESTClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class SplashscreenLowongan1 extends ActionBarActivity {
	Context myContext;
	ProgressDialog progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splashscreen_lowongan1);
		
		myContext = getApplicationContext();
		progress = ProgressDialog.show(SplashscreenLowongan1.this, "Inisialisasi data", "Sedang mengunduh data CPNS", true);
		RESTClient.get().getLowongan1CPNS(new Callback<ResponseLowongan1CPNS>() {
			@Override
			public void failure(RetrofitError error) {
				// TODO Auto-generated method stub
				progress.dismiss();
				Toast.makeText(getApplicationContext(),"AKSES KE SERVER GAGAL"+error.getMessage(), Toast.LENGTH_LONG).show();
				
			}
			
			@Override
			public void success(ResponseLowongan1CPNS lowongan1, Response response) {
				// TODO Auto-generated method stub
				if (lowongan1.getLowongan1().size() > 0) {
					ControllerLowongan1CPNS cJP = new ControllerLowongan1CPNS(myContext);
					cJP.open();
					cJP.deleteData();
					
					for(int x = 0; x <lowongan1.getLowongan1().size(); x++){
						ModelLowongan1CPNS tmpLow = lowongan1.getLowongan1().get(x);
						
						cJP.inserData(tmpLow.getIdFormasiLowonganMoreDet(), tmpLow.getIdFormasiLembagaMoreDet(), tmpLow.getTahun(), tmpLow.getLembaga(), tmpLow.getJabatan(), 
								tmpLow.getKualifikasi(), tmpLow.getGolRuang(), tmpLow.getAlokasi(), tmpLow.getPenempatan());
					}
					
					cJP.close();
					
					Intent sendIntent = new Intent(myContext, Lowongan1.class);
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
		getMenuInflater().inflate(R.menu.splashscreen_lowongan1, menu);
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
