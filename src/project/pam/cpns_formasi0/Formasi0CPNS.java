package project.pam.cpns_formasi0;

import java.util.List;

import project.pam.cpns_2014.MainActivity;
import project.pam.cpns_2014.R;
import project.pam.cpns_formasi1.Formasi1CPNS;
import project.pam.cpns_formasi1.SplashscreenFormasi1;
import project.pam.cpns_jadwal.SplashscreenJadwal;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.internal.widget.AdapterViewCompat.AdapterContextMenuInfo;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class Formasi0CPNS extends Activity {
	ListView dataCPNS;
	
	private static final class Holder{
		public TextView ID_FORMASI;
		public TextView FORMASI;
		public TextView DETAIL;
	}
	
	public Formasi0CPNS(){
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formasi0_cpns);
		
		dataCPNS = (ListView) findViewById(R.id.dataCPNS);
		
		ControllerFormasi0CPNS myData = new ControllerFormasi0CPNS(this);
		myData.open();
		myData.getData();
		
		formasi0CPNSAdapter adapter = new formasi0CPNSAdapter(this, android.R.layout.simple_list_item_1, myData.getData());
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
			String tmp = ((TextView) menuInfo.targetView.findViewById(R.id.idFormasi)).getText().toString();
			Intent sendIntent = new Intent(getApplicationContext(), SplashscreenFormasi1.class);
			sendIntent.putExtra("id", String.valueOf(tmp));
			startActivity(sendIntent);
		}
		return true;
	}
	
	
	public class formasi0CPNSAdapter extends ArrayAdapter<ModelFormasi0CPNS>{
		private LayoutInflater mInflater;
		public formasi0CPNSAdapter (Context context, int textViewResourceId, List<ModelFormasi0CPNS> objects){
			super (context, textViewResourceId, objects);
			mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
		
		@Override
		public View getView (int position, View convertView, ViewGroup parent){
			View view = convertView;
			Holder holder;
			
			if (view == null) {
				view = mInflater.inflate(R.layout.load_formasi0_cpns, parent, false);
				
				holder = new Holder();
				
				holder.ID_FORMASI = (TextView) view.findViewById(R.id.idFormasi);
				holder.FORMASI = (TextView) view.findViewById(R.id.fFormasi);
				holder.DETAIL = (TextView) view.findViewById(R.id.fDetail);
				
				view.setTag(holder);
				
			} else {
				holder = (Holder) view.getTag();
			}
			
			ModelFormasi0CPNS stream = getItem(position);
			
			holder.ID_FORMASI.setText(Integer.toString(stream.getIdFormasi()));
			holder.FORMASI.setText(stream.getFormasi());
			holder.DETAIL.setText(stream.getDetail());
			
			return view;
		}
	}
	
	
}
