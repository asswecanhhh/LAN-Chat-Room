package com.example.android_weixin;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

import org.apache.http.params.CoreConnectionPNames;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.support.annotation.DrawableRes;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class Message extends Activity {
	//初始化
	private EditText et_content;
	private ImageButton ibn_send;
	private LinearLayout layout_main;
	private String content;
	private ScrollView sv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chat);
		initView();
	}
	
	//初始化界面
	 
	
	private void initView() {
		et_content = (EditText)findViewById(R.id.et_content);
		ibn_send = (ImageButton)findViewById(R.id.ibn_send);
		layout_main = (LinearLayout)findViewById(R.id.MainView);
		sv = (ScrollView)findViewById(R.id.chat_sv);
	}
	
	public void action_send(View v) {
		//sendserver();
		sendadd();
	}
	
//	public void sendserver() {
//		if(event.getAction()== MotionEvent.ACTION_DOWN) {
//			try {
//				filename = new Date().getTime()+".arm";
//				recorder = new MediaRecorder();
//				recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//				recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//				recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//				
//				recorder.setOutputFile("/sdcard/"+filename);
//				try {
//						recorder.prepare();
//						
//				} catch (Exception e) {
//						// TODO Auto-generated catch block
//					Toast.makeText(MessageActivity.this, "try again", 1000).show();
//						e.printStackTrace();
//				}
//				recorder.start();
//				
//			} catch (Exception e) {
//				// TODO: handle exception
//				Log.i("exp",e.toString());
//			}
//			
//			
//			
//		} else if(event.getAction()== MotionEvent.ACTION_UP) {
//			try {
//				recorder.stop();
//				recorder.reset();
//				recorder.release();
//				
//			} catch (Exception e) {
//				// TODO: handle exception
//				Log.i("exp",e.toString());
//			}
//			
//			
//			LinearLayout client = new LinearLayout(MessageActivity.this);
//			TextView t = new TextView(MessageActivity.this);
//			t.setText(MainActivity.uname+ "talk:");
//			Button button = new Button(MessageActivity.this);
//			button.setText("play");
//			final String filepath = filename;
//			button.setOnClickListener(new OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					// TODO Auto-generated method stub
//					MediaPlayer mediaPlayer = new MediaPlayer();
//					try {
//						if(mediaPlayer.isPlaying()) {
//							mediaPlayer.reset();
//							mediaPlayer.release();
//						}
//					} catch (Exception e) {
//						// TODO: handle exception
//						Log.i("exp",e.toString());
//					}
//					
//					try {
//						mediaPlayer.setDataSource("/sdcard/"+filepath);
//						
//						mediaPlayer.prepare();
//						mediaPlayer.start();
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						Log.i("exp",e.toString());
//						e.printStackTrace();
//					}
//					
//				}
//			});
//			
//				client.setOrientation(LinearLayout.HORIZONTAL);
//				client.addView(t);
//				client.addView(button);
//				
//				line.addView(client);
//			
//			
//			
//			
//			try {
//				Socket socket = new Socket("172.18.8.101",8081);
//				InputStream in = socket.getInputStream();
//				OutputStream out = socket.getOutputStream();
//				out.write(("upload,"+new File("/sdcard/" + filename).length()+","+MainActivity.uname).getBytes());
//				out.flush();
//				
//				byte[] b = new byte[10];
//				in.read(b);
//				FileInputStream fin = new FileInputStream("/sdcard/"+filename);
//				int len = 0;
//				byte[] b1 = new byte[1024];
//				while( (len = fin.read(b1)) != -1) {
//					out.write(b1,0,len);
//					out.flush();
//				}
//				fin.close();
//				socket.close();
//			
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//		}
//		return true;
//	}
//	}
	
	public void sendadd() {
		content = et_content.getText().toString();
		if (content.equals("")) {
			Toast.makeText(getApplication(), "不能发送空文本", 1000).show();
			return;
		}
		LinearLayout layout_talk = new LinearLayout(Message.this);
		LinearLayout Second_layout  = new LinearLayout(Message.this);
		TextView speak = new TextView(Message.this);
		TextView me = new TextView(Message.this);


		speak.setText(content);
		speak.setBackgroundColor(Color.alpha(255));
		speak.setMaxWidth(210);
		speak.setTextSize(18);


		LinearLayout.LayoutParams talk = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		talk.setMargins(5, 5, 5, 5);
		talk.gravity  = Gravity.END;
		layout_talk.setLayoutParams(talk);
		layout_talk.setGravity(Gravity.END);
		layout_talk.setBackground(getResources().getDrawable(R.drawable.talk_bg));
		layout_talk.setPadding(10, 10, 10, 10);


		layout_talk.addView(speak);
		layout_main.addView(layout_talk);

		et_content.setText("");

		sv.fullScroll(ScrollView.FOCUS_DOWN);
	}
}
