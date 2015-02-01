package com.andbase.demo.image.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;

import com.ab.activity.AbActivity;
import com.ab.view.titlebar.AbTitleBar;
import com.andbase.R;
import com.ericsson.iodsearch.GridAdapter;
import com.ericsson.iodsearch.HttpConnection;

import com.ericsson.iodsearch.util.AppJsonFileReader;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class SearchResultActivity extends AbActivity {
	private ArrayList<String> mNameList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.activity_search_result);
		AbTitleBar mAbTitleBar = this.getTitleBar();
		mAbTitleBar.setTitleText(R.string.db_one2one_name);
		mAbTitleBar.setLogo(R.drawable.button_selector_back);
		mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAbTitleBar.setLogoLine(R.drawable.line);
		// 显示GridView的界面
		GridView gridView = (GridView) findViewById(R.id.gridview);
		ArrayList<HashMap<String, Object>> imagelist = new ArrayList<HashMap<String, Object>>();

		Bundle bundle = this.getIntent().getExtras();
		String sname = bundle.getString("json");
		mNameList = new ArrayList<String>();
		ArrayList<String> mDrawableList = new ArrayList<String>();
		JSONArray array = null;
		;
		try {
			array = new JSONArray(sname);

			int len = array.length();

			for (int i = 0; i < len; i++) {
				String object = array.getString(i);

				Log.i("TAG", "object=" + object);
				mDrawableList.add(object);
				String fileName = getFileName(object);
				Log.i("TAG", "fileName=" + fileName);
				mNameList.add(fileName);
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		gridView.setAdapter(new GridAdapter(this, mNameList, mDrawableList));
		gridView.setOnItemClickListener(new GridView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String name = mNameList.get(position);
				/*
				SendHttpRequestInfoTask tinfo = new SendHttpRequestInfoTask();
				String[] params = new String[] { name };
				tinfo.execute(params);
				*/
				Intent intent = new Intent(view.getContext(),DetailedInfoActivity.class); 
				intent.putExtra("name", "Sintel Sintel");
				intent.putExtra("id", "sintelSintel");
				intent.putExtra("category", "VOD Content");
				intent.putExtra("attribute", "Main actor");
				intent.putExtra("description", "Sintel is main Actor");
				intent.putExtra("imageUrlFull","http://b.hiphotos.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=358dbbdbcebf6c81e33a24badd57da50/b21c8701a18b87d68e70f4ac050828381f30fd57.jpg");
				startActivity(intent);				 
			}

		});
	}

	private String getFileName(String url) {

		String absolute = url;
		String fileName = null;

		String[] split = url.split("/");
		if (split.length > 0) {
			fileName = split[split.length - 1];
		}
		if (fileName != null) {
			String[] split2 = fileName.split("\\.");
			return split2[0];
		}
		return null;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_result, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private class SendHttpRequestInfoTask extends
			AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {

			String returnData = null;
			String url = "http://192.168.4.103:8080/portal-avalanche-iod-backend-war-15.3.9/iod/infoimage/";
			String filename = params[0];
			url = url + filename;
			try {
				Map<String, String> paraMap = new HashMap<String, String>();
				returnData = HttpConnection.sendGETJsonRequest(url, paraMap,
						"UTF-8");
				Log.i("MainActivity", "returnData=" + returnData);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Map<String, String> infoObjectMap = AppJsonFileReader
					.setInfoMapData(returnData);
			Intent intent = new Intent(
					getApplication().getApplicationContext(),
					DetailedInfoActivity.class);
			if (infoObjectMap != null) {
				intent.putExtra("name", infoObjectMap.get("name"));
				intent.putExtra("id", infoObjectMap.get("id"));
				intent.putExtra("category", infoObjectMap.get("category"));
				intent.putExtra("attribute", infoObjectMap.get("attribute"));
				intent.putExtra("description", infoObjectMap.get("description"));
				intent.putExtra("imageUrlFull",
						infoObjectMap.get("imageUrlFull"));
			}
			startActivity(intent);
			return null;
		}

		@Override
		protected void onPostExecute(String data) {
			// do nothing

		}

	}
}
