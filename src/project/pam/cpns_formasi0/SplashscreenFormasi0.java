package project.pam.cpns_formasi0;

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

public class SplashscreenFormasi0 extends Activity {
	Context myContext;
	ProgressDialog progress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splashscreen_formasi0);
		
		myContext = getApplicationContext();
		progress = ProgressDialog.show(SplashscreenFormasi0.this, "Inisialisasi data", "Sedang mengunduh data CPNS", true);
		RESTClient.get().getFormasi0CPNS(new Callback<ResponseFormasi0CPNS>() {
			
			@Override
			public void failure(RetrofitError error) {
				// TODO Auto-generated method stub
				progress.dismiss();
				Toast.makeText(getApplicationContext(),"AKSES KE SERVER GAGAL"+error.getMessage(), Toast.LENGTH_LONG).show();
				
			}
			
			@Override
			public void success(ResponseFormasi0CPNS formasi0, Response response) {
				// TODO Auto-generated method stub
				if (formasi0.getFormasi0().size() > 0) {
					ControllerFormasi0CPNS cF = new ControllerFormasi0CPNS(myContext);
					cF.open();
					cF.deleteData();
					
					for(int x = 0; x <formasi0.getFormasi0().size(); x++){
						ModelFormasi0CPNS tmpFormasi = formasi0.getFormasi0().get(x);
						
						cF.insertData(tmpFormasi.getIdFormasi(), tmpFormasi.getFormasi(), tmpFormasi.getDetail());
					}
					
					cF.close();
					
					Intent sendIntent = new Intent(myContext, Formasi0CPNS.class);
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
		getMenuInflater().inflate(R.menu.splashscreen_formasi0, menu);
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
	