package com.cindy.crn.delicacy_designer;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class helloActivity extends Activity{
	private static final long SPLASH_DISPLAY_LENGHT = 3000;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.hello);
		
		new Handler().postDelayed(new Runnable() {

			public void run() {
		    Intent intent = new Intent(helloActivity.this,MainActivity.class);
		    startActivity(intent);
		    System.exit(0);
		    }
			},SPLASH_DISPLAY_LENGHT);
	}
}
