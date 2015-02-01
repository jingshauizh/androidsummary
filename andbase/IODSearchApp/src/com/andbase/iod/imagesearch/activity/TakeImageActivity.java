package com.andbase.iod.imagesearch.activity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ab.activity.AbActivity;
import com.andbase.R;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class TakeImageActivity extends AbActivity {
	
	
	private Button mButton;

	private ImageView mImageView;

	private File mPhotoFile;

	private String mPhotoPath;

	private Uri mPhotoOnSDCardUri;

	public final static int CAMERA_RESULT = 777;

	public final static int CAMERA_RESULT_CUT = 888;

	public final static int CAMERA_RESULT_CUT_OVER = 999;

	public final static String TAG = "xx";
	

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_take_image);

		mButton = (Button) findViewById(R.id.button);

		mButton.setOnClickListener(new ButtonOnClickListener());

		mImageView = (ImageView) findViewById(R.id.imageView);

	}

	private class ButtonOnClickListener implements View.OnClickListener {

		public void onClick(View v) {

			try {

				Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");

				//mPhotoPath = "mnt/sdcard/DCIM/Camera/" + getPhotoFileName();
				mPhotoPath = Environment.getExternalStoragePublicDirectory("")+"/DCIM/Camera/" + getPhotoFileName();
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

			}

		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);

		// 1 拍照后显示照片

		if (requestCode == CAMERA_RESULT) {

			Bitmap bitmap = BitmapFactory.decodeFile(mPhotoPath, null);

			mImageView.setImageBitmap(bitmap);

		}

		// 2 拍照后剪切照片,然后显示

		// 2.1拍照且剪裁

		if (requestCode == CAMERA_RESULT_CUT) {

			Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
					mPhotoOnSDCardUri);

			sendBroadcast(intent);

			try {

				Thread.sleep(2000);

			} catch (InterruptedException e) {

				e.printStackTrace();

			}

			Uri systemImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

			ContentResolver contentResolver = getContentResolver();

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




	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.take_image, menu);
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
