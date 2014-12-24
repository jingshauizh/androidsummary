package com.jason.listviewoverview.adapter.arrayadapter;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ArrayListActivity extends Activity {
	private ListView listview;
	private ArrayList<String> list = new ArrayList<String>();
	private ArrayAdapter<String> arrayAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		listview = new ListView(this);
		arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, getData());
		listview.setAdapter(arrayAdapter);
		this.setContentView(listview);
	}

	private List<String> getData() {
		list.add("test data 1");
		list.add("test data 2");
		list.add("test data 3");
		list.add("test data 4");
		return list;

	}

}
