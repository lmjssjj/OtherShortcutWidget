package com.lmjssjj.othershortcutwidget;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

public class WidgetAction extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main2);

	}
	
	public void create(View view){
		createShortCut();
		finish();
	}

	public void createShortCut() {
		Intent shortcutintent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
		shortcutintent.setFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
		// 需要现实的名称
		shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_NAME, getString(R.string.app_name));
		// 是否允许重复创建
		shortcutintent.putExtra("duplicate", true);
		// 快捷图片
		Parcelable icon = Intent.ShortcutIconResource.fromContext(getApplicationContext(), R.drawable.ic_launcher);
		shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
		// 点击快捷图片，运行的程序主入口
		// shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new
		// Intent(context.getApplicationContext(), context.getClass()));

		// 第一种方法创建快捷方式要打开的目标intent
		Intent targetIntent = new Intent();
		// 设置应用程序卸载时同时也删除桌面快捷方式
		targetIntent.setAction(Intent.ACTION_MAIN);
		targetIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		ComponentName componentName = new ComponentName(getPackageName(),
				"com.lmjssjj.othershortcutwidget.MainActivity");
		targetIntent.setComponent(componentName);
		// 第二种方法创建快捷方式要打开的目标intent
		shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, targetIntent);
		setResult(RESULT_OK, shortcutintent);
	}
}
