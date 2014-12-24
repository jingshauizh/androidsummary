package com.jason.listviewoverview;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jason.listviewoverview.adapter.arrayadapter.ArrayListActivity;
import com.jason.listviewoverview.adapter.baseAdapter.BaseAdapterActivity;
import com.jason.listviewoverview.adapter.generic.GenericAdapter;
import com.jason.listviewoverview.adapter.readjson.JSONTextActivity;
import com.jason.listviewoverview.adapter.simpleAdapter.SimpleAdapterActivity;
import com.jason.listviewoverview.adapter.simpleAdapter.SimpleAdapterSpinner;
import com.jason.listviewoverview.adapter.simpleAdapter.SimpleImageActivity;
import com.jason.listviewoverview.pullload.XListViewActivity;
import com.jason.listviewoverview.util.AppJsonFileReader;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ListView listView;
	private List<Map<String, String>> data;
	private final static String fileName = "mainlistitems.json";
	private ProgressDialog pd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.json);
		init();
		pd.show();
		new DataThread().start();
		
	}

	/**
	 * 
	 */
	private void init() {
		listView = (ListView) findViewById(R.id.listView);
		data = new ArrayList<Map<String, String>>();
		pd = new ProgressDialog(this);
		pd.setMessage("Item Data loading");
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				String type = data.get(arg2).get("type");
				if (type.equals("generic")) {
					Intent openIntent = new Intent(arg1.getContext(),
							com.jason.listviewoverview.adapter.readjson.JSONTextActivity.class);
					startActivity(openIntent);
				}
				if (type.equals("array")) {
					Intent openIntent = new Intent(arg1.getContext(),
							ArrayListActivity.class);
					startActivity(openIntent);
				}
				
				if (type.equals("simple")) {
					Intent openIntent = new Intent(arg1.getContext(),
							SimpleAdapterActivity.class);
					startActivity(openIntent);
				}
				if (type.equals("simple_img")) {
					Intent openIntent = new Intent(arg1.getContext(),
							SimpleImageActivity.class);
					startActivity(openIntent);
				}
				//base_ad
				if (type.equals("base_ad")) {
					Intent openIntent = new Intent(arg1.getContext(),
							BaseAdapterActivity.class);
					startActivity(openIntent);
				}
				//base_ad
				if (type.equals("simple_spinner")) {
					Intent openIntent = new Intent(arg1.getContext(),
							SimpleAdapterSpinner.class);
					startActivity(openIntent);
				}
				//pull_load
				if (type.equals("pull_load")) {
					Intent openIntent = new Intent(arg1.getContext(),
							XListViewActivity.class);
					startActivity(openIntent);
				}

			}
		});

	}

	/**
	 * 
	 */
	class DataThread extends Thread {
		@Override
		public void run() {
			String jsonStr = AppJsonFileReader.getJson(getBaseContext(),
					fileName);
			data = AppJsonFileReader.setListData(jsonStr);
			dataHandler.sendMessage(dataHandler.obtainMessage());
		}
	}

	/**
	 * 
	 */
	Handler dataHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			GenericAdapter adapter = new GenericAdapter(getApplication(), data);
			listView.setAdapter(adapter);
			pd.hide();
			getApplication().setTheme(R.style.myAppTheme);
		}
	};
}
