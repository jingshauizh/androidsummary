package com.zhy.zhy_slidemenu_demo;

import android.app.Activity;
import android.os.Bundle;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// configure the SlidingMenu
		SlidingMenu menu = new SlidingMenu(this);
		menu.setMode(SlidingMenu.LEFT);
		// 设置触摸屏幕的模式
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);

		// 设置滑动菜单视图的宽度
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		// 设置渐入渐出效果的值
		menu.setFadeDegree(0.35f);
		/**
		 * SLIDING_WINDOW will include the Title/ActionBar in the content
		 * section of the SlidingMenu, while SLIDING_CONTENT does not.
		 */
		//把滑动菜单添加进所有的Activity中，可选值SLIDING_CONTENT ， SLIDING_WINDOW
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		//为侧滑菜单设置布局
		menu.setMenu(R.layout.leftmenu);

	}

}
