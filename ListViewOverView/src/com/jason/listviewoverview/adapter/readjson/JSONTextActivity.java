package com.jason.listviewoverview.adapter.readjson;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;


import com.jason.listviewoverview.R;
import com.jason.listviewoverview.util.AppJsonFileReader;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;

import android.widget.ListView;
import android.widget.SimpleAdapter;

public class JSONTextActivity extends Activity {

	private ListView listView;
	private List<Map<String, String>> data;
	private final static String fileName = "listmenu.json";
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
	 * 初始化
	 */
	private void init() {
		listView = (ListView) findViewById(R.id.listView);
		data = new ArrayList<Map<String, String>>();
		pd = new ProgressDialog(this);
		pd.setMessage("数据加载中……");

	}

	/**
	 * 加载数据线程
	 */
	class DataThread extends Thread {

		@Override
		public void run() {
			String jsonStr = AppJsonFileReader.getJson(getBaseContext(),fileName);
			data = AppJsonFileReader.setData(jsonStr);
			dataHandler.sendMessage(dataHandler.obtainMessage());
		}

	}

	/**
	 * 加载数据线程完成处理Handler
	 */
	Handler dataHandler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			if (pd != null) {
				pd.dismiss();
			}
			SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(),
					data, R.layout.json_item, new String[] { "operator",
							"loginDate", "logoutDate" }, new int[] {
							R.id.operator_tv, R.id.loginDate_tv,
							R.id.logoutDate_tv });
			listView.setAdapter(adapter);
			getApplication().setTheme(R.style.myAppTheme);
		}
	};
}