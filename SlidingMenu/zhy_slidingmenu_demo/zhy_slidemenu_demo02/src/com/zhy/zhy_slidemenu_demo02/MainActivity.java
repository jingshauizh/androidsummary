package com.zhy.zhy_slidemenu_demo02;

import android.os.Bundle;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;

public class MainActivity extends SlidingActivity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setBehindContentView(R.layout.leftmenu);
		// configure the SlidingMenu
		SlidingMenu menu = getSlidingMenu();
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
		// menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		// menu.setMenu(R.layout.leftmenu);

	}

}
