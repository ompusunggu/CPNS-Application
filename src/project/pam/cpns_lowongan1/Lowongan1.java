package project.pam.cpns_lowongan1;

import java.util.List;

import project.pam.cpns_2014.MainActivity;
import project.pam.cpns_2014.R;
import project.pam.cpns_lowongan0.ControllerLowongan0CPNS;
import project.pam.cpns_lowongan0.ModelLowongan0CPNS;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Lowongan1 extends Activity {
ListView dataCPNS;
	
	private static final class Holder {
		public TextView ID;
		public TextView TAHUN;
		public TextView LEMBAGA;
		public TextView JABATAN;
		public TextView KUALIFIKASI;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lowongan1);
		
		dataCPNS = (ListView) findViewById(R.id.dataCPNS);
		
		ControllerLowongan1CPNS myData = new ControllerLowongan1CPNS(this);
		myData.open();
		myData.getData();
		
		lowongan1CPNSAdapter adapter = new lowongan1CPNSAdapter(this, android.R.layout.simple_list_item_1, myData.getData());
		dataCPNS.setAdapter(adapter);
		myData.close();
		
		Button buttonHome = (Button) findViewById(R.id.buttonHome);
		buttonHome.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v) {
				Intent sendIntent = new Intent(getBaseContext(), MainActivity.class);
				startActivity(sendIntent);
				finish();
			}
		});
	}

	private class lowongan1CPNSAdapter extends ArrayAdapter<ModelLowongan1CPNS>{
		private LayoutInflater miInflater;
		public lowongan1CPNSAdapter (Context context, int textViewResourceId, List<ModelLowongan1CPNS> objects){
			super (context, textViewResourceId, objects);
			
			miInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent){
			View view = convertView;
			Holder holder;
			
			if(view == null){
				view = miInflater.inflate(R.layout.load_lowongan1_cpns, parent, false);
				
				holder = new Holder();
				
				holder.ID = (TextView) view.findViewById(R.id.low1Id);
				holder.TAHUN = (TextView) view.findViewById(R.id.low1Tahun);
				holder.LEMBAGA = (TextView) view.findViewById(R.id.low1Lembaga);
				holder.JABATAN = (TextView) view.findViewById(R.id.low1Jabatan);
				holder.KUALIFIKASI = (TextView) view.findViewById(R.id.low1Kualifikasi);
				
				view.setTag(holder);
				
			} else {
				holder = (Holder) view.getTag();
			}
			
			ModelLowongan1CPNS stream = getItem(position);
	
			holder.ID.setText(Integer.toString(stream.getIdFormasiLowonganMoreDet()));
			holder.TAHUN.setText(stream.getTahun());
			holder.LEMBAGA.setText(stream.getLembaga());
			holder.JABATAN.setText(stream.getJabatan());
			holder.KUALIFIKASI.setText(stream.getKualifikasi());
			
			return view;
			
		}
	}
}
