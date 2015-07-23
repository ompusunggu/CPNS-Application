package project.pam.cpns_formasi3;

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


public class Formasi3CPNS extends Activity {
	ListView dataCPNS;
	
	private static final class Holder{
		public TextView txIdFORMASI3;
		public TextView txLEMBAGA3;
		public TextView txDETAIL3;
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formasi3_cpns);
		
		dataCPNS = (ListView) findViewById(R.id.dataCPNS);
		
		ControllerFormasi3CPNS myData = new ControllerFormasi3CPNS(this);
		myData.open();
		myData.getData();
		
		formasi3CPNSAdapter adapter = new formasi3CPNSAdapter(this, android.R.layout.simple_list_item_1, myData.getData());
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

	private class formasi3CPNSAdapter extends ArrayAdapter<ModelFormasi3CPNS>{
		private LayoutInflater mInflater;
		public formasi3CPNSAdapter (Context context, int textViewResourceId, List<ModelFormasi3CPNS> objects){
			super (context, textViewResourceId, objects);
			mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
		
		@Override
		public View getView (int position, View convertView, ViewGroup parent){
			View view = convertView;
			Holder holder;
			
			if (view == null) {
				view = mInflater.inflate(R.layout.load_formasi3_cpns, parent, false);
				
				holder = new Holder();
				holder.txIdFORMASI3 = (TextView) view.findViewById(R.id.f1IdFormasi3);
				holder.txLEMBAGA3 = (TextView) view.findViewById(R.id.f1Lembaga3);
				holder.txDETAIL3 = (TextView) view.findViewById(R.id.f1Detail3);
				
				view.setTag(holder);
				
			} else {
				holder = (Holder) view.getTag();
			}
			
			ModelFormasi3CPNS stream = getItem(position);
			
			int idFL3 = stream.getIdFormasiLembagaMoreDet();
			String IDFL3 = String.valueOf(idFL3);
			
			holder.txIdFORMASI3.setText(IDFL3);
			holder.txLEMBAGA3.setText(stream.getLembaga());
			holder.txDETAIL3.setText(stream.getDetail());
			
			return view;
		}
	}
}
