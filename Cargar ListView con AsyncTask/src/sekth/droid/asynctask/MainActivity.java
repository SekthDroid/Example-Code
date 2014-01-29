package sekth.droid.asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private ListView list;
	private String[] sistemas = {"Ubuntu", "Android", "iOS", "Windows", "Mac OSX", 
								"Google Chrome OS", "Debian", "Mandriva", "Solaris", "Unix"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		list = (ListView)findViewById(R.id.listview);
		list.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int posicion,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Ha pulsado el elemento " + posicion, Toast.LENGTH_SHORT).show();
			}
			
		});
		
		CargarListView nuevaTarea = new CargarListView(this);
		nuevaTarea.execute();
		
	}

	private class CargarListView extends AsyncTask<Void, Void, ArrayAdapter<String>>{
		Context context;
		ProgressDialog pDialog;
		
		public CargarListView(Context context){
			this.context = context;
		}
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
			Log.i("AsyncTask PreExecute", "Entra en PreExecute");
			pDialog = new ProgressDialog(context);
			pDialog.setMessage("Cargando Lista");
			pDialog.setCancelable(true);
			pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pDialog.show();
		}
		
		@Override
		protected ArrayAdapter<String> doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			
			try{
				Thread.sleep(2000);
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
			Log.i("doInBackground", "Crea el Adaptador");
			ArrayAdapter<String> adaptador = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, sistemas);
			return adaptador;
		}

		@Override
		protected void onPostExecute(ArrayAdapter<String> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			list.setAdapter(result);
			pDialog.dismiss();
		}
		
	}

}
