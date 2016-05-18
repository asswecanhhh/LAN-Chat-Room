package com.example.android_weixin;
import android.app.Activity;
import android.graphics.Color;
import android.inputmethodservice.Keyboard;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView.FixedViewInfo;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


public class Message extends Activity
{
	EditText input;
	LinearLayout show;
	ImageView send;
	Handler handler;
	ScrollView sv;
	Button bn;

	ClientThread clientThread;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chat);
		input = (EditText) findViewById(R.id.et_content);
		send = (ImageView) findViewById(R.id.ibn_send);
		show =  (LinearLayout) findViewById(R.id.MainView);
		sv = (ScrollView) findViewById(R.id.chat_sv);
		bn = (Button) findViewById(R.id.bn_talk);
		bn.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction()==MotionEvent.ACTION_DOWN) {
					bn.setBackground(getResources().getDrawable(R.drawable.shape_touch));
				} else if(event.getAction()==MotionEvent.ACTION_UP){
					bn.setBackground(getResources().getDrawable(R.drawable.shape));
				}
				return false;
			}
		});

		handler = new Handler() 
		{
			@Override
			public void handleMessage(android.os.Message msg)
			{
				if (msg.what == 0x123)
				{
					LinearLayout layout_talk = new LinearLayout(Message.this);
					TextView tv_speak = new TextView(Message.this);
					TextView name = new TextView(Message.this);
					LinearLayout layout_user = new LinearLayout(Message.this);
					ImageView iv_user = new ImageView(Message.this);

					//test
					String cmd = msg.obj.toString();
					if(cmd.startsWith(LoginActivity.uname)) {
						String[] str = cmd.split(",");
						String content = str[1];
						tv_speak.append(content);
						tv_speak.setBackgroundColor(Color.alpha(255));
						tv_speak.setMaxWidth(190);
						tv_speak.setTextSize(18);

						LayoutParams par_iv = new LayoutParams(50,50);
						iv_user.setLayoutParams(par_iv);
						iv_user.setBackground(getResources().getDrawable(R.drawable.icon_user));
						iv_user.setScaleType(ScaleType.FIT_XY);



						LayoutParams par_user = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
						layout_user.setLayoutParams(par_user);
						layout_user.setOrientation(LinearLayout.VERTICAL);
						layout_user.setGravity(Gravity.CENTER_HORIZONTAL);

						LinearLayout.LayoutParams par_talk = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
						par_talk.gravity = Gravity.END;

						layout_talk.setLayoutParams(par_talk);
						layout_talk.setOrientation(LinearLayout.HORIZONTAL);

						tv_speak.setBackground(getResources().getDrawable(R.drawable.talk_me));
						tv_speak.setPadding(10, 10, 20, 10);
						tv_speak.setGravity(Gravity.CENTER);


						name.setText(str[0]);
						name.setTextSize(14);
						name.setMaxWidth(5);
						name.setGravity(Gravity.CENTER_HORIZONTAL);

						layout_talk.addView(tv_speak);
						layout_talk.addView(layout_user);
						layout_user.addView(iv_user);
						layout_user.addView(name);
						show.addView(layout_talk);

					}
					else {
						String[] str = cmd.split(",");
						String content = str[1];
						tv_speak.append(content);
						tv_speak.setBackgroundColor(Color.alpha(255));
						tv_speak.setMaxWidth(190);
						tv_speak.setTextSize(18);

						LayoutParams par_iv = new LayoutParams(50,50);
						iv_user.setLayoutParams(par_iv);
						iv_user.setBackground(getResources().getDrawable(R.drawable.icon_user));
						iv_user.setScaleType(ScaleType.FIT_XY);



						LayoutParams par_user = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
						layout_user.setLayoutParams(par_user);
						layout_user.setOrientation(LinearLayout.VERTICAL);
						layout_user.setGravity(Gravity.CENTER_HORIZONTAL);

						LinearLayout.LayoutParams par_talk = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
						par_talk.gravity = Gravity.START;

						layout_talk.setLayoutParams(par_talk);
						layout_talk.setOrientation(LinearLayout.HORIZONTAL);

						tv_speak.setBackground(getResources().getDrawable(R.drawable.talk_other));
						tv_speak.setPadding(20, 10, 10, 10);
						tv_speak.setGravity(Gravity.CENTER);


						name.setText(str[0]);
						name.setTextSize(14);
						name.setMaxWidth(5);
						name.setGravity(Gravity.CENTER_HORIZONTAL);


						layout_talk.addView(layout_user);
						layout_talk.addView(tv_speak);
						layout_user.addView(iv_user);
						layout_user.addView(name);
						show.addView(layout_talk);


					}
					sv.post(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							sv.fullScroll(ScrollView.FOCUS_DOWN);
						}
					});
				}
			}
		};
		clientThread = new ClientThread(handler);
		new Thread(clientThread).start(); 
		send.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				try
				{
					String content = null;
					android.os.Message msg = new android.os.Message();
					msg.what = 0x345;
					msg.obj = LoginActivity.uname+","+input.getText().toString();
					content = input.getText().toString();
					if(content.isEmpty()) {
						Toast.makeText(getApplication(), "不能发送空文本", 1000).show();
						return;
					}
					clientThread.revHandler.sendMessage(msg);
					input.setText("");
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	public void changetotalk(View v) {
		LinearLayout talk = new LinearLayout(this);
		LinearLayout keyboard = new LinearLayout(this);
		keyboard = (LinearLayout) findViewById(R.id.layout_keyboard);
		talk = (LinearLayout) findViewById(R.id.layout_talk);
		talk.setVisibility(View.VISIBLE);
		keyboard.setVisibility(View.GONE);
	}
	public void changetokeyboard(View v) {
		LinearLayout talk = new LinearLayout(this);
		LinearLayout keyboard = new LinearLayout(this);
		keyboard = (LinearLayout) findViewById(R.id.layout_keyboard);
		talk = (LinearLayout) findViewById(R.id.layout_talk);
		talk.setVisibility(View.GONE);
		keyboard.setVisibility(View.VISIBLE);
	}
}


