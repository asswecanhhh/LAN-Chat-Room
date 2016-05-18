package com.example.android_weixin;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramSocket;
import java.net.Socket;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.*;

public class LoginActivity extends Activity {
	private Button bn_login;
	private Button bn_exit;
	private EditText et_user;
	private EditText et_password;
	private String Admin_user = "admin";
	private String Admin_password = "666";
	static String uname;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);	
		initView();
	}
	
	private void initView() {
		bn_login = (Button)findViewById(R.id.bn_login);
		et_user = (EditText)findViewById(R.id.et_user);
		et_password = (EditText)findViewById(R.id.et_password);
		bn_login.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction()==MotionEvent.ACTION_DOWN){
					bn_login.setBackground(getResources().getDrawable(R.drawable.shape_login_button_touch));
				} else if(event.getAction()==MotionEvent.ACTION_UP){
					bn_login.setBackground(getResources().getDrawable(R.drawable.shape_login_button));
					String s_user;
					String s_password;
					
					s_user = et_user.getText().toString();
					s_password = et_password.getText().toString();
					uname = s_user;
					//s_user.equals(Admin_user)
					if(true) {
						if(s_user.length()>5){
							Toast.makeText(getApplicationContext(), "用户名不能大于5个字母", 1000).show();
							et_user.setText("");
							return false;
						}
						if(s_password.equals(Admin_password)) {
							try {
								Intent intent = new Intent();
								intent.setClass(LoginActivity.this, MainActivity.class);
								startActivity(intent);	
								finish();
							} catch (Exception e) {
								// TODO: handle exception
								Log.i("err", e.toString());
							}
							
						} else {
							Toast.makeText(getApplication(), "密码错误", 1000).show();
						}
					} 
					
				}
				return false;
			}
		});

	}
}
