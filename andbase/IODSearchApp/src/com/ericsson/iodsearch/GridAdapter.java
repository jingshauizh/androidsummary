package com.ericsson.iodsearch;

import java.util.ArrayList;

import com.andbase.R;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.LruCache;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {
	private ArrayList<String> mNameList = new ArrayList<String>();
	private ArrayList<String> mDrawableList = new ArrayList<String>();
	private LayoutInflater mInflater;
	private Context mContext;
	LinearLayout.LayoutParams params;

	public GridAdapter(Context context, ArrayList<String> nameList,
			ArrayList<String> drawableList) {
		mNameList = nameList;
		mDrawableList = drawableList;
		mContext = context;
		mInflater = LayoutInflater.from(context);

		params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		params.gravity = Gravity.CENTER;
	}

	public int getCount() {
		return mNameList.size();
	}

	public Object getItem(int position) {
		return mNameList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ItemViewTag viewTag;

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.gridview_item, null);

			// construct an item tag
			viewTag = new ItemViewTag(
					(ImageView) convertView.findViewById(R.id.grid_icon));
			convertView.setTag(viewTag);
		} else {
			viewTag = (ItemViewTag) convertView.getTag();
		}

		
		// set icon
		//viewTag.mIcon.setBackgroundDrawable(mDrawableList.get(position));
		loadImageByVolley(mDrawableList.get(position),viewTag.mIcon);
		viewTag.mIcon.setLayoutParams(params);
		return convertView;
	}
	
	private void loadImageByVolley(String imageUrl, ImageView imageView) {
		RequestQueue requestQueue = Volley.newRequestQueue(imageView.getContext());
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


	class ItemViewTag {
		protected ImageView mIcon;
	

		/**
		 * The constructor to construct a navigation view tag
		 * 
		 * @param name
		 *            the name view of the item
		 * @param size
		 *            the size view of the item
		 * @param icon
		 *            the icon view of the item
		 */
		public ItemViewTag(ImageView icon) {			
			this.mIcon = icon;
		}
	}

}