package project.pam.cpns_2014;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import project.pam.cpns_2014.Analytics.TrackerName;
import project.pam.cpns_formasi0.SplashscreenFormasi0;
import project.pam.cpns_lowongan0.SplashscreenLowongan0;
import project.pam.cpns_jadwal.SplashscreenJadwal;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity{
	private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context c, Intent i) {
			int level = i.getIntExtra("level", 0);
			ProgressBar pb = (ProgressBar) findViewById(R.id.progressbar);
			pb.setProgress(level);
			TextView tv = (TextView) findViewById(R.id.textfield);
			tv.setText("Battery Level: " + Integer.toString(level) + "%");
		}

	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
		
		
		registerReceiver(mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
		
		try{
			Tracker t = ((Analytics) getApplication()).getTracker(TrackerName.APP_TRACKER);
			t.enableAdvertisingIdCollection(true);
			t.setScreenName("MyScreenName");
			
			t.send(new HitBuilders.AppViewBuilder().build());
		}
		catch (Exception e){
			Toast.makeText(getApplicationContext(), "Error" + e.getMessage(), 1).show();
		}
		
		Button button = (Button) findViewById(R.id.btnJadwal);
		button.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
				Intent sendIntent = new Intent(getBaseContext(),SplashscreenJadwal.class);
				startActivity(sendIntent);
				finish();
			}
		});
		
		Button button2 = (Button) findViewById(R.id.btnFormasi0);
		button2.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
				Intent sendIntent = new Intent(getBaseContext(),SplashscreenFormasi0.class);
				startActivity(sendIntent);
				finish();
			}
		});
		
		
		
		Button button6 = (Button) findViewById(R.id.btnLowongan0);
		button6.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
				Intent sendIntent = new Intent(getBaseContext(),SplashscreenLowongan0.class);
				startActivity(sendIntent);
				finish();
			}
		}); 
	}
	
	@Override
	protected void onStart(){
		super.onStart();
		GoogleAnalytics.getInstance(this).reportActivityStart(this);
	}
	
	@Override
	protected void onStop(){
		super.onStop();
		GoogleAnalytics.getInstance(this).reportActivityStop(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
