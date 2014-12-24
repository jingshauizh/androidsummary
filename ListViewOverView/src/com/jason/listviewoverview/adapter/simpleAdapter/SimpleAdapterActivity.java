package com.jason.listviewoverview.adapter.simpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.jason.listviewoverview.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SimpleAdapterActivity extends Activity {
	
	private ListView listView;
	private List<Map<String,Object>> dataList;
	private SimpleAdapter simpleAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		listView = new ListView(this);
		simpleAdapter = new SimpleAdapter(this,getData(),R.layout.simple_adapter ,new String[]{"title","img"},new int[]{R.id.title,R.id.img });
		listView.setAdapter(simpleAdapter);
		this.setContentView(listView);
	}
	
	private List<Map<String,Object>> getData(){
		dataList = new ArrayList<Map<String,Object>>();
		 Map<String, Object> map = new HashMap<String, Object>();
         map.put("title", "苹果");
         map.put("img", R.drawable.ic_launcher);
         dataList.add(map);
         
         map = new HashMap<String, Object>();
         map.put("title", "诺基亚");
         map.put("img", R.drawable.ic_launcher);
         dataList.add(map);
         
         map = new HashMap<String, Object>();
         map.put("title", "三星");
         map.put("img", R.drawable.ic_launcher);
         dataList.add(map);
         return dataList;
	}
}
