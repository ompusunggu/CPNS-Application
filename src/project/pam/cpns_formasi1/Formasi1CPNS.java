package project.pam.cpns_formasi1;

import java.util.List;

import project.pam.cpns_2014.MainActivity;
import project.pam.cpns_2014.R;
import project.pam.cpns_formasi1.ControllerFormasi1CPNS;
import project.pam.cpns_formasi1.ModelFormasi1CPNS;
import project.pam.cpns_formasi2.SplashscreenFormasi2;
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

public class Formasi1CPNS extends Activity {
	ListView dataCPNS;
	
	private static final class Holder{
		public TextView txIdFORMASI;
		public TextView txLEMBAGA;
		public TextView txDETAIL;
	}
	
	public Formasi1CPNS(){}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formasi1_cpns);
		
		dataCPNS = (ListView) findViewById(R.id.dataCPNS);
		
		ControllerFormasi1CPNS myData = new ControllerFormasi1CPNS(this);
		myData.open();
		myData.getData();
		
		formasi1CPNSAdapter adapter = new formasi1CPNSAdapter(this, android.R.layout.simple_list_item_1, myData.getData());
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
			String tmp = ((TextView) menuInfo.targetView.findViewById(R.id.f1IdFormasi)).getText().toString();
			Intent sendIntent = new Intent(getApplicationContext(), SplashscreenFormasi2.class);
			sendIntent.putExtra("id", String.valueOf(tmp));
			startActivity(sendIntent);
		}
		return true;
	}
	
	private class formasi1CPNSAdapter extends ArrayAdapter<ModelFormasi1CPNS>{
		private LayoutInflater mInflater;
		public formasi1CPNSAdapter (Context context, int textViewResourceId, List<ModelFormasi1CPNS> objects){
			super (context, textViewResourceId, objects);
			mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
		
		@Override
		public View getView (int position, View convertView, ViewGroup parent){
			View view = convertView;
			Holder holder;
			
			if (view == null) {
				view = mInflater.inflate(R.layout.load_formasi1_cpns, parent, false);
				
				holder = new Holder();
				holder.txIdFORMASI = (TextView) view.findViewById(R.id.f1IdFormasi);
				holder.txLEMBAGA = (TextView) view.findViewById(R.id.f1Lembaga);
				holder.txDETAIL = (TextView) view.findViewById(R.id.f1Detail);
				
				view.setTag(holder);
				
			} else {
				holder = (Holder) view.getTag();
			}
			
			ModelFormasi1CPNS stream = getItem(position);
			
			holder.txIdFORMASI.setText(Integer.toString(stream.getIdFormasi()));
			holder.txLEMBAGA.setText(stream.getLembaga());
			holder.txDETAIL.setText(stream.getDetail());
			
			return view;
		}
	}
}
