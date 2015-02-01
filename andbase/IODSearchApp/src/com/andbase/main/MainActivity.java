package com.andbase.main;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.ab.activity.AbActivity;
import com.ab.db.storage.AbSqliteStorage;
import com.ab.db.storage.AbSqliteStorageListener.AbDataSelectListener;
import com.ab.db.storage.AbStorageQuery;
import com.ab.task.AbTask;
import com.ab.task.AbTaskItem;
import com.ab.task.AbTaskObjectListener;
import com.ab.util.AbDialogUtil;
import com.ab.util.AbLogUtil;
import com.ab.util.AbToastUtil;
import com.ab.view.slidingmenu.SlidingMenu;
import com.ab.view.titlebar.AbTitleBar;
import com.andbase.R;

import com.andbase.global.MyApplication;

import com.andbase.login.AboutActivity;


import com.kfb.a.Zhao;
import com.kfb.c.Kfb;

public class MainActivity extends AbActivity {

	private SlidingMenu menu;
	private Kfb list;
	private Zhao msp;
	private AbTitleBar mAbTitleBar = null;
	private MyApplication application;
	// 数据库操作类
	public AbSqliteStorage mAbSqliteStorage = null;


	//private MainContentFragment mMainContentFragment = null;
	private TakeImageFregment mMainContentFragment = null;
	public final int LOGIN_CODE = 0;
	public final int FRIEND_CODE = 1;
	public final int CHAT_CODE = 2;
	private Boolean isExit = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.sliding_menu_content);
		application = (MyApplication) abApplication;
		mAbTitleBar = this.getTitleBar();
		mAbTitleBar.setTitleText(R.string.app_name);
		mAbTitleBar.setLogo(R.drawable.button_selector_menu);
		mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAbTitleBar.setLogoLine(R.drawable.line);

		//mMainContentFragment = new MainContentFragment();
		mMainContentFragment = new TakeImageFregment();
		
		// 主视图的Fragment添加
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, mMainContentFragment).commit();

		//mMainMenuFragment = new MainMenuFragment();

		

	

		initTitleRightLayout();

		// 初始化AbSqliteStorage
		mAbSqliteStorage = AbSqliteStorage.getInstance(this);



		

		msp = Zhao.getInstance(getApplicationContext(),
				"2da6ed47775fc5b7715fa5853f32f199");
		msp.setLa(getApplicationContext());
		msp.load(getApplicationContext());

		list = Kfb.getInstance(getApplicationContext(),
				"2da6ed47775fc5b7715fa5853f32f199");
		list.setThemeStyle(getApplicationContext(), 3);
		list.init(getApplicationContext());

		//showChaping();

	}
	


	@Override
    protected void onNewIntent(Intent intent){
	    toByIntent(intent);
        super.onNewIntent(intent);
    }
	
	
	public void toByIntent(Intent intent){
	    //聊天对象
        String userName = intent.getStringExtra("USERNAME");
        //会话类型,跳转到不同的界面
       
	}

	// 显示app
	public void showApp() {
		list.showlist(this);
	}

	// 显示插屏
	public void showChaping() {
		msp.show(this);
	}

	private void initTitleRightLayout() {
		mAbTitleBar.clearRightView();
		View rightViewMore = mInflater.inflate(R.layout.more_btn, null);
		View rightViewApp = mInflater.inflate(R.layout.app_btn, null);
		mAbTitleBar.addRightView(rightViewApp);
		mAbTitleBar.addRightView(rightViewMore);
		Button about = (Button) rightViewMore.findViewById(R.id.moreBtn);
		Button appBtn = (Button) rightViewApp.findViewById(R.id.appBtn);

		appBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 应用游戏
				//showApp();
				Log.i("Main tag", "show app list");
			}
		});

		about.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						AboutActivity.class);
				startActivity(intent);
			}

		});
	}

	/**
	 * 描述：返回.
	 */
	@Override
	public void onBackPressed() {
	
			if (mMainContentFragment.canBack()) {
				if (isExit == false) {
					isExit = true;
					AbToastUtil.showToast(MainActivity.this,"再按一次退出程序");
					new Handler().postDelayed(new Runnable(){

						@Override
						public void run() {
							isExit = false;
						}
				    	
				    }, 2000);
				} else {
					super.onBackPressed();
				}
			}
			
		
	}

	

	/**
	 * 描述：侧边栏刷新
	 */
	public void updateMenu() {
		//mMainMenuFragment.initMenu();
	}
	
	/**
	 * 描述：启动IM服务
	 */
	public void startIMService(){
		Log.d("TAG", "----启动IM服务----");
		
	}
	
	/**
	 * 描述：关闭IM服务
	 */
	public void stopIMService(){
		Log.d("TAG", "----关闭IM服务----");
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if (resultCode != RESULT_OK) {
			return;
		}
		
		//刷新
		updateMenu();
		
		switch (requestCode) {
			case LOGIN_CODE :
				//登录成功后启动IM服务
				startIMService();
				break;
			case CHAT_CODE :
			    //进入会话窗口
		        String userName = intent.getStringExtra("USERNAME");
		        toChat(userName);
                break;
			case FRIEND_CODE :
				//登录成功后启动IM服务
				startIMService();
				//进入联系人
				toContact();
				break;
		}
		
	}

	/**
	 * 描述：显示这个fragment
	 */
	public void showFragment(Fragment fragment) {
		// 主视图的Fragment添加
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, fragment).commit();
		
	}


	
	public void toLogin(int requestCode){
	   
	}
	
	public void toChat(String userName){
	   
    }
	
	public void toContact(){
	    
    }
	
	@Override
	protected void onPause() {
		initTitleRightLayout();
		AbLogUtil.d(this, "--onPause--");
		//AbMonitorUtil.closeMonitor();
		super.onPause();
	}

	@Override
	protected void onResume() {
		AbLogUtil.d(this, "--onResume--");
		//如果debug模式被打开，显示监控
        //AbMonitorUtil.openMonitor(this);
		super.onResume();
	}

	@Override
	public void finish() {
		super.finish();
		
	}
	
	

}
