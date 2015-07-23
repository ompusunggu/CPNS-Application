package project.pam.cpns_jadwal;

import java.util.List;

import project.pam.cpns_2014.MainActivity;
import project.pam.cpns_2014.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class JadwalCPNS extends Activity {
	ListView dataCPNS;
	
	private static final class Holder {
		public TextView INSTANSI;
		public TextView STATUS;
		public TextView JADWAL_DAFTAR;
		public TextView TOTAL_PELAMAR;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jadwal_cpns);
		
		dataCPNS = (ListView) findViewById(R.id.dataCPNS);
		
		ControllerJadwalCPNS myData = new ControllerJadwalCPNS(this);
		myData.open();
		myData.getData();
		
		jadwalCPNSAdapter adapter = new jadwalCPNSAdapter(this, android.R.layout.simple_list_item_1, myData.getData());
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
	
	private class jadwalCPNSAdapter extends ArrayAdapter<ModelJadwalCPNS>{
		private LayoutInflater miInflater;
		public jadwalCPNSAdapter (Context context, int textViewResourceId, List<ModelJadwalCPNS> objects){
			super (context, textViewResourceId, objects);
			
			miInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent){
			View view = convertView;
			Holder holder;
			
			if(view == null){
				view = miInflater.inflate(R.layout.load_jadwal_cpns, parent, false);
				
				holder = new Holder();
				
				holder.INSTANSI = (TextView) view.findViewById(R.id.jInstansi);
				holder.STATUS = (TextView) view.findViewById(R.id.jStatus);
				holder.JADWAL_DAFTAR = (TextView) view.findViewById(R.id.jJadwalDaftar);
				holder.TOTAL_PELAMAR = (TextView) view.findViewById(R.id.jTotalDaftar);
				
				view.setTag(holder);
			} else {
				holder = (Holder) view.getTag();
			}
			
			ModelJadwalCPNS stream = getItem(position);
			
			holder.INSTANSI.setText(stream.getInstansi());
			holder.STATUS.setText(stream.getStatus());
			holder.JADWAL_DAFTAR.setText(stream.getJadwalDaftar());
			
			int tP = stream.getTotalPelamar();
			String TP = String.valueOf(tP);
			holder.TOTAL_PELAMAR.setText(TP);
			
			return view;
			
		}
	}
}
