package project.pam.cpns_formasi1;

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

public class SplashscreenFormasi1 extends Activity {
	Context myContext;
	ProgressDialog progress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splashscreen_formasi1);
		
		myContext = getApplicationContext();
		progress = ProgressDialog.show(SplashscreenFormasi1.this, "Inisialisasi data", "Sedang mengunduh data CPNS", true);
		RESTClient.get().getFormasi1CPNS(new Callback<ResponseFormasi1CPNS>() {
			@Override
			public void failure(RetrofitError error) {
				// TODO Auto-generated method stub
				progress.dismiss();
				Toast.makeText(getApplicationContext(),"AKSES KE SERVER GAGAL"+error.getMessage(), Toast.LENGTH_LONG).show();
				
			}
			
			@Override
			public void success(ResponseFormasi1CPNS formasi1, Response response) {
				// TODO Auto-generated method stub
				if (formasi1.getFormasi1().size() > 0) {
					ControllerFormasi1CPNS cF = new ControllerFormasi1CPNS(myContext);
					cF.open();
					cF.deleteData();
					
					for(int x = 0; x <formasi1.getFormasi1().size(); x++){
						ModelFormasi1CPNS tmpFormasi1 = formasi1.getFormasi1().get(x);
						
						cF.insertData(tmpFormasi1.getIdFormasiLembaga(), tmpFormasi1.getIdFormasi(), tmpFormasi1.getLembaga(), tmpFormasi1.getDetail());
					}
					
					cF.close();
					
					Intent sendIntent = new Intent(myContext, Formasi1CPNS.class);
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
		getMenuInflater().inflate(R.menu.splashscreen_formasi1, menu);
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
