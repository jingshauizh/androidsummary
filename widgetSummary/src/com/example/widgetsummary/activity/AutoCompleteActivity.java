package com.example.widgetsummary.activity;



import com.example.widgetsummary.R;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class AutoCompleteActivity extends ActionBarActivity {
	private static final String[] data = new String[] { "aa", "aaa", "aaaaaaa",
		"aaaaa", "aaaaaa", "bb" };
	private AutoCompleteTextView searchtext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_auto_complete);	
		searchtext = (AutoCompleteTextView) findViewById(R.id.autoText);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				AutoCompleteActivity.this, android.R.layout.simple_dropdown_item_1line,
				data);
		searchtext.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.auto_complete, menu);
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
}
