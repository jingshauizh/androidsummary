package com.andbase.demo.image.search;

import com.andbase.R;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.Volley;
import com.ericsson.iodsearch.HttpConnection;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.LruCache;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailedInfoActivity extends Activity {

	private TextView textName;
	private TextView textCategory1;
	private TextView textAttribute1;
	private TextView textViewDesText1;
	private ImageView faceImg1;

	private static final int MSG_SUCCESS = 0;// 获取图片成功的标识
	private static final int MSG_FAILURE = 1;// 获取图片失败的标识

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MSG_SUCCESS:
				faceImg1.setImageBitmap((Bitmap) msg.obj);
				break;
			}

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detailed_info_view);
		// 显示GridView的界面
		textName = (TextView) findViewById(R.id.textName);
		textCategory1 = (TextView) findViewById(R.id.textCategory);
		textAttribute1 = (TextView) findViewById(R.id.textAttribute);
		textViewDesText1 = (TextView) findViewById(R.id.textViewDesText);
		faceImg1 = (ImageView) findViewById(R.id.faceImg);
		Bundle bundle = this.getIntent().getExtras();
		String sname = bundle.getString("name");
		String sCategory = bundle.getString("category");
		String sAttribute = bundle.getString("attribute");
		String sViewDesText = bundle.getString("description");
		String imagePath = bundle.getString("imageUrlFull");

		Log.i("DetailedInfoActivity", "sname=" + sname);
		Log.i("DetailedInfoActivity", "sCategory=" + sCategory);
		Log.i("DetailedInfoActivity", "sAttribute=" + sAttribute);
		Log.i("DetailedInfoActivity", "sViewDesText=" + sViewDesText);
		Log.i("DetailedInfoActivity", "imagePath=" + imagePath);

		textName.setText(sname);
		if (textCategory1 != null) {
			textCategory1.setText(String.valueOf(sCategory));
		}
		if (textAttribute1 != null) {
			textAttribute1.setText(String.valueOf(sAttribute));
		}
		if (textViewDesText1 != null) {
			textViewDesText1.setText(String.valueOf(sViewDesText));
		}
		

		loadImageByVolley(imagePath,faceImg1);	
		/*
		if (faceImg1 != null) {
			SendHttpRequestImageTask tinfo = new SendHttpRequestImageTask();
			String[] params = new String[] { imagePath };
			tinfo.execute(params);
		}
		*/
	}

	private void loadImageByVolley(String imageUrl, ImageView imageView) {
		RequestQueue requestQueue = Volley.newRequestQueue(this);
		final LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>(
				20);
		ImageCache imageCache = new ImageCache() {
			@Override
			public void putBitmap(String key, Bitmap value) {
				lruCache.put(key, value);
			}

			@Override
			public Bitmap getBitmap(String key) {
				return lruCache.get(key);
			}
		};
		ImageLoader imageLoader = new ImageLoader(requestQueue, imageCache);
		ImageListener listener = ImageLoader.getImageListener(imageView,
				R.drawable.ic_launcher, R.drawable.ic_launcher);
		imageLoader.get(imageUrl, listener);
	}

	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {

			super.handleMessage(msg);
		}
	};

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

	private class SendHttpRequestImageTask extends
			AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... params) {
			String url = params[0];
			Bitmap bitmap = HttpConnection.getHttpBitmap(url);
			mHandler.obtainMessage(MSG_SUCCESS, bitmap).sendToTarget();
			return null;
		}

		@Override
		protected void onPostExecute(String data) {
			// do nothing

		}
	}
}
