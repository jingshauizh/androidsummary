package com.jason.intentsummary.mainListView;

import java.util.ArrayList;
import java.util.HashMap;

import com.jason.intentsummary.MainIntentopenActivity;
import com.jason.intentsummary.R;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author Administrator
 * 
 * 
 *         (1）创建类，继承自BaseAdapter --->MyAdapter
 * 
 *         2）重写其中的四个方法
 * 
 *         ①int　getCount()：返回的是数据源对象的个数，即列表项数
 * 
 *         ②Object　getItem(int position)：返回指定位置position上的列表
 * 
 *         ③long　getItemId(int position)：返回指定位置处的行ＩＤ
 * 
 *         ④View getView()：返回列表项对应的视图，方法体中
 * 
 *         ◆实例化视图填充器 ----->holder = new ViewHolder();
 * 
 *         ◆用视图填充器，根据Xml文件，实例化视图 convertView =
 *         mInflater.inflate(R.layout.base_item, null); holder = new
 *         ViewHolder(); holder.title = (TextView) convertView
 *         .findViewById(R.id.ItemTitle); holder.text = (TextView) convertView
 *         .findViewById(R.id.ItemText); holder.bt = (Button)
 *         convertView.findViewById(R.id.ItemButton);
 *         convertView.setTag(holder);// 绑定ViewHolder对象
 * 
 *         ◆根据布局找到控件，并设置属性 holder.title = (TextView) convertView
 *         .findViewById(R.id.ItemTitle); holder.text = (TextView) convertView
 *         .findViewById(R.id.ItemText); holder.bt = (Button)
 *         convertView.findViewById(R.id.ItemButton);
 *         convertView.setTag(holder);// 绑定ViewHolder对象
 * 
 *         ◆返回View视图 ---->return convertView;
 * 
 */
public class BaseAdapterActivity extends Activity {

	private ListView lv;
	/* 定义一个动态数组 */
	ArrayList<HashMap<String, Object>> listItem;
	
	private final int REQUEST_CODE = 1;

	private final String LOF_FLAG = "BaseAdapterActivity";

	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.base_main);

		lv = (ListView) findViewById(R.id.listView);
		MyAdapter mAdapter = new MyAdapter(this);// 得到一个MyAdapter对象
		lv.setAdapter(mAdapter);// 为ListView绑定Adapter /*为ListView添加点击事件*/

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(arg1.getContext(),
						"MyListViewBase 你点击了ListView条目" + arg2, 1000).show();// 在LogCat中输出信息
			}
		});

	}/* 添加一个得到数据的方法，方便使用 */
	
	
	/**
	 * 使用setResult，针对
		startActivityForResult(it,REQUEST_CODE)启动的Activity）
	 */
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE){
            if(resultCode==RESULT_CANCELED)
                  setTitle("cancle");
            else if (resultCode==RESULT_OK) {
                 String temp=null;
                 Bundle bundle=data.getExtras();
                 if(bundle!=null)   temp=bundle.getString("name");
                 setTitle(temp);
            }
        }
    }

	private final String OPEN_ACTIVITY = "OPEN_ACTIVITY";
	private final String OPEN_OHONE_CALL = "OPEN_CALL";
	private final String OPEN_SEND_MASSAGE = "SEND_MESSAGE";
	private final String OPEN_SEND_MASSAGE_ATTACH = "SEND_MESSAGE_ATTACH";
	private final String OPEN_SEND_EMAIL = "OPEN_SEND_EMAIL";
	private final String OPEN_PLAY_MEDIA = "OPEN_PLAY_MEDIA";
	private final String OPEN_SEARCH = "OPEN_SEARCH";
	private final String OPEN_BROWSER = "OPEN_BROWSER";
	private final String OPEN_HIDE_INTENT = "OPEN_HIDE_INTENT";

	private ArrayList<HashMap<String, Object>> getDate() {

		ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
		/*
		 * 为动态数组添加数据
		 * 
		 * 1.在AndroidManifest.xml与layout等xml文件里:
		 * android:text="@string/resource_name" 2.在activity里：
		 * 方法一:this.getString(R.string.resource_name);
		 * 方法二:getResources().getString(R.string.resource_name);
		 * 3.在其他java文件（必须有Context或pplication） 方法一:
		 * context.getString(R.string.resource_name); 方法二:
		 * application.getString(R.string.resource_name);
		 */
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("ItemTitle", this.getString(R.string.open_activity));
		map.put("ItemText", "");
		map.put("type", OPEN_ACTIVITY);
		listItem.add(map);
		map = new HashMap<String, Object>();
		map.put("ItemTitle", this.getString(R.string.open_phone_call));
		map.put("ItemText", "");
		map.put("type", OPEN_OHONE_CALL);

		listItem.add(map);
		map = new HashMap<String, Object>();
		map.put("ItemTitle", this.getString(R.string.send_message));
		map.put("ItemText", "");
		map.put("type", OPEN_SEND_MASSAGE);

		listItem.add(map);
		map = new HashMap<String, Object>();
		map.put("ItemTitle", this.getString(R.string.send_message_attach));
		map.put("ItemText", "");
		map.put("type", OPEN_SEND_MASSAGE_ATTACH);

		listItem.add(map);
		map = new HashMap<String, Object>();
		map.put("ItemTitle", this.getString(R.string.send_email));
		map.put("ItemText", "");
		map.put("type", OPEN_SEND_EMAIL);

		listItem.add(map);
		map = new HashMap<String, Object>();
		map.put("ItemTitle", this.getString(R.string.send_play_mp3));
		map.put("ItemText", "");
		map.put("type", OPEN_PLAY_MEDIA);

		listItem.add(map);
		map = new HashMap<String, Object>();
		map.put("ItemTitle", this.getString(R.string.send_search));
		map.put("ItemText", "");
		map.put("type", OPEN_SEARCH);

		listItem.add(map);
		map = new HashMap<String, Object>();
		map.put("ItemTitle", this.getString(R.string.send_open_browser));
		map.put("ItemText", "");
		map.put("type", OPEN_BROWSER);
		
		listItem.add(map);
		map = new HashMap<String, Object>();
		map.put("ItemTitle", this.getString(R.string.send_hide_intent));
		map.put("ItemText", "");
		map.put("type", OPEN_HIDE_INTENT);
		
		

		listItem.add(map);

		return listItem;

	}/*
	 * * 新建一个类继承BaseAdapter，实现视图与数据的绑定
	 */

	private class MyAdapter extends BaseAdapter {
		private LayoutInflater mInflater;// 得到一个LayoutInfalter对象用来导入布局 /*构造函数*/

		public MyAdapter(Context context) {
			this.mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {

			return getDate().size();// 返回数组的长度
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		/* 书中详细解释该方法 */
		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			ViewHolder holder;
			// 观察convertView随ListView滚动情况

			Log.v(LOF_FLAG, "getView " + position + " " + convertView);
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.base_item, null);
				holder = new ViewHolder();
				/* 得到各个控件的对象 */

				holder.title = (TextView) convertView
						.findViewById(R.id.ItemTitle);
				holder.text = (TextView) convertView
						.findViewById(R.id.ItemText);
				holder.bt = (Button) convertView.findViewById(R.id.ItemButton);
				convertView.setTag(holder);// 绑定ViewHolder对象
			} else {
				holder = (ViewHolder) convertView.getTag();// 取出ViewHolder对象
			}
			/* 设置TextView显示的内容，即我们存放在动态数组中的数据 */

			holder.title.setText(getDate().get(position).get("ItemTitle")
					.toString());
			holder.text.setText(getDate().get(position).get("ItemText")
					.toString());
			holder.bt.setText("Open");
			/* 为Button添加点击事件 */
			holder.bt.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Object type = getDate().get(position).get("type");
					/**
					 * // put Bundle Data Intent intent = new Intent("xxxxx");
					 * Bundle bundle = new Bundle(); bundle.putInt("id", 0);
					 * bundle.putString("name", "scott");
					 * intent.putExtras(bundle); startActivity(intent);
					 * 
					 * // get Bundle Data Bundle bundle = intent.getExtras();
					 * int id = bundle.getInt("id"); String name =
					 * bundle.getString("name"); or int id =
					 * intent.getIntExtra(); String name =
					 * intent.getStringExtra();
					 */
					if (type == OPEN_ACTIVITY) {
						Intent intent = new Intent(getApplication()
								.getBaseContext(), MainIntentopenActivity.class);
						String text = "我是从BaseAdapterActivity来的Intent";
						intent.putExtra("text", text);
						//startActivity(intent);
						startActivityForResult(intent,REQUEST_CODE);
					}
					
					/**
					 * 隐式Intent 需要 Activity声明 Filter action
					 */
					if (type == OPEN_HIDE_INTENT) {
						Intent it = new Intent();
						it.setAction("com.google.test");
						String text = "我是隐式Intent";
						it.putExtra("text", text);
						startActivity(it);
					}

					/**
					 * Uri uri = Uri.parse("tel:10086"); Intent intent = new
					 * Intent(Intent.ACTION_DIAL, uri); startActivity(intent);
					 */
					if (type == OPEN_OHONE_CALL) {
						Uri uri = Uri.parse("tel:10086");
						Intent intent = new Intent(Intent.ACTION_DIAL, uri);
						startActivity(intent);
					}

					/***
					 * // 给10086发送内容为“Hello”的短信 Uri uri =
					 */
					if (type == OPEN_SEND_MASSAGE) {
						Uri uri = Uri.parse("smsto:10086");
						Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
						intent.putExtra("sms_body", "Hello");
						startActivity(intent);
					}

					/**
					 * // 发送彩信（相当于发送带附件的短信） 我这里怎么打开了 网易 邮箱大师 变成发邮件了
					 */
					if (type == OPEN_SEND_MASSAGE_ATTACH) {
						Intent intent = new Intent(Intent.ACTION_SENDTO);
						intent.putExtra("sms_body", "Hello");
						Uri uri = Uri
								.parse("content://media/external/images/media/23");
						intent.putExtra(Intent.EXTRA_STREAM, uri);
						intent.setType("image/png");
						startActivity(intent);
					}

					/**
					 * 
					 */
					if (type == OPEN_SEND_EMAIL) {
						// Uri uri = Uri.parse("mailto:wuxianglong098@163.com");
						// Intent intent = new Intent(Intent.ACTION_SENDTO,
						// uri);
						// startActivity(intent);
						Intent intent = new Intent(Intent.ACTION_SEND);
						String[] tos = { "me@abc.com" };
						String[] ccs = { "you@abc.com" };
						intent.putExtra(Intent.EXTRA_EMAIL, tos);
						intent.putExtra(Intent.EXTRA_CC, ccs);
						intent.putExtra(Intent.EXTRA_TEXT,
								"The email body text");
						intent.putExtra(Intent.EXTRA_SUBJECT,
								"The email subject text");
						intent.setType("message/rfc822");
						startActivity(Intent.createChooser(intent,
								"Choose Email Client"));
					}

					/**
					 * play mp3
					 */
					if (type == OPEN_PLAY_MEDIA) {
						Intent intent = new Intent(Intent.ACTION_VIEW);
						Uri uri = Uri.parse("file:///sdcard/cwj.mp3");
						intent.setDataAndType(uri, "audio/mp3");
						startActivity(intent);
					}

					/**
					 * 
					 */

					if (type == OPEN_SEARCH) {
						Intent intent = new Intent();
						intent.setAction(Intent.ACTION_WEB_SEARCH);
						intent.putExtra(SearchManager.QUERY, "android");
						startActivity(intent);
					}

					/***
					 * 
					 */
					if (type == OPEN_BROWSER) {
						Uri uri = Uri.parse("http://www.baidu.com");
						Intent intent = new Intent(Intent.ACTION_VIEW, uri);
						startActivity(intent);
					}
					
				

				}
			});

			return convertView;
		}

	}/* 存放控件 */

	public final class ViewHolder {
		public TextView title;
		public TextView text;
		public Button bt;
	}
}
