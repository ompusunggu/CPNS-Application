package project.pam.cpns_formasi2;

import java.util.List;

import project.pam.cpns_2014.MainActivity;
import project.pam.cpns_2014.R;
import project.pam.cpns_formasi1.SplashscreenFormasi1;
import project.pam.cpns_formasi3.SplashscreenFormasi3;
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

public class Formasi2CPNS extends Activity {
	ListView dataCPNS;
	
	private static final class Holder{
		public TextView txIdFORMASI2;
		public TextView txLEMBAGA2;
		public TextView txDETAIL2;
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formasi2_cpns);
		
		dataCPNS = (ListView) findViewById(R.id.dataCPNS);
		
		ControllerFormasi2CPNS myData = new ControllerFormasi2CPNS(this);
		myData.open();
		myData.getData();
		
		formasi2CPNSAdapter adapter = new formasi2CPNSAdapter(this, android.R.layout.simple_list_item_1, myData.getData());
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
			String tmp = ((TextView) menuInfo.targetView.findViewById(R.id.f1IdFormasi2)).getText().toString();
			Intent sendIntent = new Intent(getApplicationContext(), SplashscreenFormasi3.class);
			sendIntent.putExtra("id", String.valueOf(tmp));
			startActivity(sendIntent);
		}
		return true;
	}
	
	private class formasi2CPNSAdapter extends ArrayAdapter<ModelFormasi2CPNS>{
		private LayoutInflater mInflater;
		public formasi2CPNSAdapter (Context context, int textViewResourceId, List<ModelFormasi2CPNS> objects){
			super (context, textViewResourceId, objects);
			mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
		
		@Override
		public View getView (int position, View convertView, ViewGroup parent){
			View view = convertView;
			Holder holder;
			
			if (view == null) {
				view = mInflater.inflate(R.layout.load_formasi2_cpns, parent, false);
				
				holder = new Holder();
				holder.txIdFORMASI2 = (TextView) view.findViewById(R.id.f1IdFormasi2);
				holder.txLEMBAGA2 = (TextView) view.findViewById(R.id.f1Lembaga2);
				holder.txDETAIL2 = (TextView) view.findViewById(R.id.f1Detail2);
				
				view.setTag(holder);
			} else {
				holder = (Holder) view.getTag();
			}
			
			ModelFormasi2CPNS stream = getItem(position);
			
			int idFL = stream.getIdFormasiLembaga();
			String IDFL = String.valueOf(idFL);
			
			holder.txIdFORMASI2.setText(IDFL);
			holder.txLEMBAGA2.setText(stream.getLembaga());
			holder.txDETAIL2.setText(stream.getDetail());
			
			return view;
		}
	}
	
}
