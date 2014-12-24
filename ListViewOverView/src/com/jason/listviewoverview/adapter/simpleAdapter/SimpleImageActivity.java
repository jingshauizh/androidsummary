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

public class SimpleImageActivity extends Activity {
	
	private ListView listView;
	private List<Map<String,Object>> dataList;
	private SimpleAdapter simpleAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		listView = new ListView(this);
		//simpleAdapter = new SimpleAdapter(this,getData(),R.layout.simple_adapter ,new String[]{"title","img"},new int[]{R.id.title,R.id.img });
		simpleAdapter = new SimpleAdapter(this,getData(),R.layout.simple_vlist,
                  new String[]{"title","info","img"},
                  new int[]{R.id.title,R.id.info,R.id.img});
		listView.setAdapter(simpleAdapter);
		this.setContentView(listView);
	}
	
	private List<Map<String,Object>> getData(){
		dataList = new ArrayList<Map<String, Object>>();
		     
          Map<String, Object> map = new HashMap<String, Object>();
          map.put("title", "G1");
          map.put("info", "google 1");
          map.put("img", R.drawable.ic_launcher);
          dataList.add(map);
   
          map = new HashMap<String, Object>();
          map.put("title", "G2");
          map.put("info", "google 2");
          map.put("img", R.drawable.ic_launcher);
          dataList.add(map);
   
          map = new HashMap<String, Object>();
          map.put("title", "G3");
          map.put("info", "google 3");
          map.put("img", R.drawable.ic_launcher);
          dataList.add(map);           
          return dataList;
	}
}
