package com.andbase.main;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import android.support.v4.app.Fragment;
import com.andbase.R;
import com.andbase.demo.image.search.SearchResultActivity;
import com.andbase.global.MyApplication;



import android.app.Activity;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class TakeImageFregment extends Fragment {

	private Button mButton;
	private Button mSButton;
	private MyApplication application;
	private Activity mActivity = null;

	private ImageView mImageView;

	private File mPhotoFile;
	private Bitmap selectBitmap;

	private String mPhotoPath;

	private Uri mPhotoOnSDCardUri;

	public final static int CAMERA_RESULT = 777;

	public final static int CAMERA_RESULT_CUT = 888;

	public final static int CAMERA_RESULT_CUT_OVER = 999;

	public final static String TAG = "xx";
	private final String returnJson="[" +
			"\"http://b.hiphotos.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=358dbbdbcebf6c81e33a24badd57da50/b21c8701a18b87d68e70f4ac050828381f30fd57.jpg\"," +
			"\"http://b.hiphotos.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=358dbbdbcebf6c81e33a24badd57da50/b21c8701a18b87d68e70f4ac050828381f30fd57.jpg\"," +
			"\"http://b.hiphotos.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=358dbbdbcebf6c81e33a24badd57da50/b21c8701a18b87d68e70f4ac050828381f30fd57.jpg\"," +
			"\"http://b.hiphotos.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=358dbbdbcebf6c81e33a24badd57da50/b21c8701a18b87d68e70f4ac050828381f30fd57.jpg\"," +
			"\"http://b.hiphotos.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=358dbbdbcebf6c81e33a24badd57da50/b21c8701a18b87d68e70f4ac050828381f30fd57.jpg\"," +
			"\"http://b.hiphotos.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=358dbbdbcebf6c81e33a24badd57da50/b21c8701a18b87d68e70f4ac050828381f30fd57.jpg\"," +
			"\"http://b.hiphotos.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=358dbbdbcebf6c81e33a24badd57da50/b21c8701a18b87d68e70f4ac050828381f30fd57.jpg\"," +
			"\"http://b.hiphotos.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=358dbbdbcebf6c81e33a24badd57da50/b21c8701a18b87d68e70f4ac050828381f30fd57.jpg\"," +
			"\"http://b.hiphotos.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=358dbbdbcebf6c81e33a24badd57da50/b21c8701a18b87d68e70f4ac050828381f30fd57.jpg\"" +
			"]";
			
	

	private final String url = "http://192.168.4.103:8080/portal-avalanche-iod-backend-war-15.3.9/iod/imagepost";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		//super.onCreate(savedInstanceState);
		mActivity = this.getActivity();
		application = (MyApplication) mActivity.getApplication();
		View view = inflater.inflate(R.layout.image_search_take_image, null);
		//setContentView(R.layout.image_search_take_image);

		mButton = (Button) view.findViewById(R.id.button);
		mSButton = (Button) view.findViewById(R.id.button2);
		// mButton.setOnClickListener(new ButtonOnClickListener());

		mButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("Test", "This is OnClick");
				try {

					Intent intent = new Intent(
							"android.media.action.IMAGE_CAPTURE");
					// mPhotoPath =
					// Environment.getExternalStoragePublicDirectory("")+"/DCIM/Camera/"
					// + getPhotoFileName();
					mPhotoPath = Environment
							.getExternalStoragePublicDirectory("")
							+ "/DCIM/"
							+ getPhotoFileName();
					Log.d("Test", "mPhotoPath=" + mPhotoPath);
					mPhotoFile = new File(mPhotoPath);
					if (!mPhotoFile.exists()) {
						mPhotoFile.createNewFile();
					}
					mPhotoOnSDCardUri = Uri.fromFile(mPhotoFile);
					intent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoOnSDCardUri);
					// 拍照后显示此图片
					// startActivityForResult(intent,CAMERA_RESULT);
					// 拍照后先修改再显示此图片
					startActivityForResult(intent, CAMERA_RESULT_CUT);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
		mSButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("Test", "This is mSButton OnClick");
				
				String param1 = "11111";
				String param2 = "22222";
				//SendHttpRequestTask t = new SendHttpRequestTask();
				//String[] params = new String[] { url, param1, param2 };
				//t.execute(params);
				
				
				
				/*
				SendHttpRequestInfoTask tinfo = new SendHttpRequestInfoTask();
				String[] params = new String[] { url };
				tinfo.execute(params);
				*/
				
				
				
				 Intent intent = new Intent(v.getContext(),
						 SearchResultActivity.class);
				 intent.putExtra("json", returnJson);
				 startActivity(intent);
				 
			}
		});

		mButton.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				Log.d("Test", "This is onTouch,return false");
				return false;
			}
		});
		mSButton.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				Log.d("Test", "This is onTouch,return false");
				return false;
			}
		});
		mImageView = (ImageView) view.findViewById(R.id.imageView);
		return view;
	}

	private File getAbosoluteFile(String relativePath, Context context) {
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			return new File(context.getExternalFilesDir(null), relativePath);
		} else {
			return new File(context.getFilesDir(), relativePath);
		}
	}

	private class ButtonOnClickListener implements View.OnClickListener {

		public void onClick(View v) {

			try {

				Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");

				//mPhotoPath = "mnt/sdcard/DCIM/Camera/" + getPhotoFileName();
				mPhotoPath= Environment
				.getExternalStoragePublicDirectory("")+"/DCIM/" + getPhotoFileName();

				mPhotoFile = new File(mPhotoPath);

				if (!mPhotoFile.exists()) {

					mPhotoFile.createNewFile();

				}

				mPhotoOnSDCardUri = Uri.fromFile(mPhotoFile);

				intent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoOnSDCardUri);

				// 拍照后显示此图片

				// startActivityForResult(intent,CAMERA_RESULT);

				// 拍照后先修改再显示此图片

				startActivityForResult(intent, CAMERA_RESULT_CUT);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);

		// 1 拍照后显示照片
		Log.d("TAG", "resultCode="+resultCode);

		if (requestCode == CAMERA_RESULT) {

			Bitmap bitmap = BitmapFactory.decodeFile(mPhotoPath, null);

			mImageView.setImageBitmap(bitmap);

		}

		// 2 拍照后剪切照片,然后显示

		// 2.1拍照且剪裁

		if (requestCode == CAMERA_RESULT_CUT) {

			Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
					mPhotoOnSDCardUri);

			this.getActivity().sendBroadcast(intent);

			try {

				Thread.sleep(2000);

			} catch (InterruptedException e) {

				e.printStackTrace();

			}

			Uri systemImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

			ContentResolver contentResolver = this.getActivity().getContentResolver();

			Cursor cursor = contentResolver.query(systemImageUri, null,

			MediaStore.Images.Media.DISPLAY_NAME + "='"

			+ mPhotoFile.getName() + "'", null, null);

			Uri photoUriInMedia = null;

			if (cursor != null && cursor.getCount() > 0) {

				cursor.moveToLast();

				long id = cursor.getLong(0);

				photoUriInMedia = ContentUris
						.withAppendedId(systemImageUri, id);

			}

			cursor.close();

			Intent in = new Intent("com.android.camera.action.CROP");

			// 需要裁减的图片格式

			in.setDataAndType(photoUriInMedia, "image/*");

			// 允许裁减

			in.putExtra("crop", "true");

			// 剪裁后ImageView显时图片的宽

			in.putExtra("outputX", 250);

			// 剪裁后ImageView显时图片的高

			in.putExtra("outputY", 250);

			// 设置剪裁框的宽高比例

			in.putExtra("aspectX", 1);

			in.putExtra("aspectY", 1);

			in.putExtra("return-data", true);

			startActivityForResult(in, CAMERA_RESULT_CUT_OVER);

		}

		// 2.2显示

		if (requestCode == CAMERA_RESULT_CUT_OVER) {

			// 剪切图片的时候,若"放弃"则回答的data为null

			if (data != null) {

				Bitmap bitmap = (Bitmap) data.getExtras().get("data");
				selectBitmap = bitmap;
				mImageView.setImageBitmap(bitmap);

			}

		}

	}

	private String getPhotoFileName() {

		Date date = new Date(System.currentTimeMillis());

		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"'IMG'_yyyyMMdd_HHmmss");

		return dateFormat.format(date) + ".jpg";

	}

	public boolean canBack() {
		// TODO Auto-generated method stub
		return false;
	}

	

}
