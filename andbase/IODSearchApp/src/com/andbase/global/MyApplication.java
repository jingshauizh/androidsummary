package com.andbase.global;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.ab.global.AbAppConfig;


public class MyApplication extends Application {

	

	public String cityid = Constant.DEFAULTCITYID;
	public String cityName = Constant.DEFAULTCITYNAME;
	public boolean userPasswordRemember = false;
	public boolean ad = false;
	public boolean isFirstStart = true;
	public SharedPreferences mSharedPreferences = null;

	@Override
	public void onCreate() {
		super.onCreate();
		mSharedPreferences = getSharedPreferences(AbAppConfig.SHARED_PATH,
				Context.MODE_PRIVATE);
		initLoginParams();
		initIMConfig();
	}

	/**
	 * 上次登录参数
	 */
	private void initLoginParams() {
		SharedPreferences preferences = getSharedPreferences(
				AbAppConfig.SHARED_PATH, Context.MODE_PRIVATE);
		String userName = preferences.getString(Constant.USERNAMECOOKIE, null);
		String userPwd = preferences.getString(Constant.USERPASSWORDCOOKIE,
				null);
		Boolean userPwdRemember = preferences.getBoolean(
				Constant.USERPASSWORDREMEMBERCOOKIE, false);
		
	}

	

	/**
	 * 清空上次登录参数
	 */
	public void clearLoginParams() {
		Editor editor = mSharedPreferences.edit();
		editor.clear();
		editor.commit();
		
	}

	/**
	 * IM配置
	 */
	public void initIMConfig() {
	   
		
	}
	
	@Override
	public void onTerminate() {
		super.onTerminate();
	}

}
