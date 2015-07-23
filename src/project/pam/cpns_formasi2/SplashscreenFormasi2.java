package project.pam.cpns_formasi2;

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

public class SplashscreenFormasi2 extends Activity {
	Context myContext;
	ProgressDialog progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splashscreen_formasi2);
		
		myContext = getApplicationContext();
		progress = ProgressDialog.show(SplashscreenFormasi2.this, "Inisialisasi data", "Sedang mengunduh data CPNS", true);
		RESTClient.get().getFormasi2CPNS(new Callback<ResponseFormasi2CPNS>() {
			@Override
			public void failure(RetrofitError error) {
				// TODO Auto-generated method stub
				progress.dismiss();
				Toast.makeText(getApplicationContext(),"AKSES KE SERVER GAGAL"+error.getMessage(), Toast.LENGTH_LONG).show();
				
			}
			
			@Override
			public void success(ResponseFormasi2CPNS formasi2, Response response) {
				// TODO Auto-generated method stub
				if (formasi2.getFormasi2().size() > 0) {
					ControllerFormasi2CPNS cF = new ControllerFormasi2CPNS(myContext);
					cF.open();
					cF.deleteData();
					
					for(int x = 0; x <formasi2.getFormasi2().size(); x++){
						ModelFormasi2CPNS tmpFormasi1 = formasi2.getFormasi2().get(x);
						
						cF.insertData(tmpFormasi1.getIdFormasiLembagaDet(), tmpFormasi1.getIdFormasiLembaga(), tmpFormasi1.getLembaga(), tmpFormasi1.getDetail());
					}
					
					cF.close();
					
					Intent sendIntent = new Intent(myContext, Formasi2CPNS.class);
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
		getMenuInflater().inflate(R.menu.splashscreen_formasi2, menu);
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
