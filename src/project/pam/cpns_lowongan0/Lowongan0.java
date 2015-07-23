package project.pam.cpns_lowongan0;

import java.util.List;

import project.pam.cpns_2014.MainActivity;
import project.pam.cpns_2014.R;
import project.pam.cpns_formasi1.SplashscreenFormasi1;
import project.pam.cpns_lowongan1.Lowongan1;
import project.pam.cpns_lowongan1.SplashscreenLowongan1;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class Lowongan0 extends Activity {
	ListView dataCPNS;
	
	private static final class Holder {
		public TextView ID;
		public TextView TAHUN;
		public TextView LEMBAGA;
		public TextView JABATAN;
		public TextView KUALIFIKASI;
		public TextView ALOKASI;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lowongan0);
		
		dataCPNS = (ListView) findViewById(R.id.dataCPNS);
		
		ControllerLowongan0CPNS myData = new ControllerLowongan0CPNS(this);
		myData.open();
		myData.getData();
		
		lowongan0CPNSAdapter adapter = new lowongan0CPNSAdapter(this, android.R.layout.simple_list_item_1, myData.getData());
		dataCPNS.setAdapter(adapter);
		
		registerForContextMenu(dataCPNS);
		
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
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo){
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("Pilih Aksi");
		menu.add(0, v.getId(), 0, "Lihat Detail");
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo)item.getMenuInfo();
		
		if (item.getTitle() == "Lihat Detail") {
			String tmp = ((TextView) menuInfo.targetView.findViewById(R.id.lowId)).getText().toString();
			Intent sendIntent = new Intent(getApplicationContext(), SplashscreenLowongan1.class);
			sendIntent.putExtra("id", String.valueOf(tmp));
			startActivity(sendIntent);
		}
		return true;
	}
	
	private class lowongan0CPNSAdapter extends ArrayAdapter<ModelLowongan0CPNS>{
		private LayoutInflater miInflater;
		public lowongan0CPNSAdapter (Context context, int textViewResourceId, List<ModelLowongan0CPNS> objects){
			super (context, textViewResourceId, objects);
			
			miInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent){
			View view = convertView;
			Holder holder;
			
			if(view == null){
				view = miInflater.inflate(R.layout.load_lowongan0_cpns, parent, false);
				
				holder = new Holder();
				
				holder.ID = (TextView) view.findViewById(R.id.lowId);
				holder.TAHUN = (TextView) view.findViewById(R.id.lowTahun);
				holder.LEMBAGA = (TextView) view.findViewById(R.id.lowLembaga);
				holder.JABATAN = (TextView) view.findViewById(R.id.lowJabatan);
				holder.KUALIFIKASI = (TextView) view.findViewById(R.id.lowKualifikasi);
				holder.ALOKASI = (TextView) view.findViewById(R.id.lowAlokasi);
				
				view.setTag(holder);
				
			} else {
				holder = (Holder) view.getTag();
			}
			
			ModelLowongan0CPNS stream = getItem(position);
			
			holder.ID.setText(Integer.toString(stream.getIdFormasiLowonganDet()));
			holder.TAHUN.setText(stream.getTahun());
			holder.LEMBAGA.setText(stream.getLembaga());
			holder.JABATAN.setText(stream.getJabatan());
			holder.KUALIFIKASI.setText(stream.getKualifikasi());
			holder.ALOKASI.setText(Integer.toString(stream.getAlokasi()));
			
			return view;
			
		}
	}
}
