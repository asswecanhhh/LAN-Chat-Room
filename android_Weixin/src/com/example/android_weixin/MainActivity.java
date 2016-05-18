package com.example.android_weixin;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.*;
public class MainActivity extends Activity  implements android.view.View.OnClickListener {
	private ViewPager mViewPager;
	private PagerAdapter mPagerAdapter;
	private List<View> mViews = new ArrayList<View>();
	private LinearLayout mTabWeiXin;
	private LinearLayout mTabAddress;
	private LinearLayout mTabFrd;
	private LinearLayout mTabSetting;
	private ImageButton mWeiXinImg;
	private ImageButton mAddressImg;
	private ImageButton mFrdImg;
	private ImageButton mSettingImg; 
	
	private Button addbn;
	private ListView lv;
	private ImageButton mfqlt;
	private ImageButton mttms;
	private ImageButton mdlwy;
	private ImageButton msys;
	
	private TextView fwx;
	private TextView ftxl;
	private TextView ffx;
	private TextView fw;
	
	//tab4
	private TextView t;
	
	@Override
	
	protected void onCreate(Bundle savedInstanceState){ 
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		setOverflowShowingAlways(); 
		initView();
		initViewPage();
		initEvent();
		 
	}
	
	 public boolean onCreateOptionsMenu(Menu menu) {  
	        getMenuInflater().inflate(R.menu.main, menu);  
	        return true;  
	    }  
	
	 @Override  
	    public boolean onMenuOpened(int featureId, Menu menu) {  
	        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {  
	            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {  
	                try {  
	                    Method m = menu.getClass().getDeclaredMethod(  
	                            "setOptionalIconsVisible", Boolean.TYPE);  
	                    m.setAccessible(true);  
	                    m.invoke(menu, true);  
	                } catch (Exception e) {  
	                }  
	            }  
	        }  
	        return super.onMenuOpened(featureId, menu);  
	    }  
	  
	    private void setOverflowShowingAlways() {  
	        try {  
	            ViewConfiguration config = ViewConfiguration.get(this);  
	            Field menuKeyField = ViewConfiguration.class  
	                    .getDeclaredField("sHasPermanentMenuKey");  
	            menuKeyField.setAccessible(true);  
	            menuKeyField.setBoolean(config, false);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    }  
	  
	
	 
	private void initEvent() { 
		mTabWeiXin.setOnClickListener(this);
		mTabAddress.setOnClickListener(this);
		mTabFrd.setOnClickListener(this);
		mTabSetting.setOnClickListener(this);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				
				// TODO Auto-generated method stub
				int currentItem = mViewPager.getCurrentItem();
				switch (currentItem) {
				case 0:
					resetImg();
					mWeiXinImg.setImageResource(R.drawable.icon_wx_xz);
					fwx.setTextColor(Color.rgb(66, 195, 24));
					break;
				case 1:
					resetImg();
					mAddressImg.setImageResource(R.drawable.icon_txl_xz);
					ftxl.setTextColor(Color.rgb(66, 195, 24));
					break;
				case 2:
					resetImg();
					mFrdImg.setImageResource(R.drawable.icon_fx_xz);
					ffx.setTextColor(Color.rgb(66, 195, 24));
					break; 
				case 3:
					resetImg();
					mSettingImg.setImageResource(R.drawable.icon_w_xz);
					fw.setTextColor(Color.rgb(66, 195, 24));
					break;
				default:
					break;
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	//tab4
	
	
	
	/** 
	 * 初始化设置 
	 */ 
	private void initView() { 
		mViewPager = (ViewPager) findViewById(R.id.id_viewpage); 
		// 初始化四个LinearLayout  
		mTabWeiXin = (LinearLayout) findViewById(R.id.id_tab_weixin);
		mTabAddress = (LinearLayout) findViewById(R.id.id_tab_address);
		mTabFrd = (LinearLayout) findViewById(R.id.id_tab_frd);
		mTabSetting = (LinearLayout) findViewById(R.id.id_tab_settings);
		// 初始化四个按钮
		mWeiXinImg = (ImageButton) findViewById(R.id.id_tab_weixin_img);
		mAddressImg = (ImageButton) findViewById(R.id.id_tab_address_img);
		mFrdImg = (ImageButton) findViewById(R.id.id_tab_frd_img);
		mSettingImg = (ImageButton) findViewById(R.id.id_tab_settings_img);
		
		fwx = (TextView) findViewById(R.id.font_wx);
		ftxl = (TextView) findViewById(R.id.font_txl);
		ffx = (TextView) findViewById(R.id.font_fx);
		fw = (TextView) findViewById(R.id.font_w);
	} 

	/**
	 * 初始化ViewPage
	 */
	private void initViewPage() { 

		// 初始化四个布局  
		LayoutInflater mLayoutInflater = LayoutInflater.from(this);
		View tab01 = mLayoutInflater.inflate(R.layout.tab01, null);
		View tab02 = mLayoutInflater.inflate(R.layout.tab02, null);
		View tab03 = mLayoutInflater.inflate(R.layout.tab03, null);
		View tab04 = mLayoutInflater.inflate(R.layout.tab04, null);
		mViews.add(tab01);
		mViews.add(tab02);
		mViews.add(tab03);
		mViews.add(tab04);
		
		t = (TextView) tab04.findViewById(R.id.tv_username);
		t.setText("微信号:"+LoginActivity.uname);
		
		// 适配器初始化并设置
		mPagerAdapter = new PagerAdapter() {

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				container.removeView(mViews.get(position));
			}
			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				View view = mViews.get(position);
				container.addView(view);
				return view;
			}
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}
			@Override
			public int getCount() {
				return mViews.size();
			}
		};
		mViewPager.setAdapter(mPagerAdapter);
	}
	
	
	/**
	 * 判断哪个要显示，及设置按钮图片
 */
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.id_tab_weixin:
			mViewPager.setCurrentItem(0);
			resetImg();
			mWeiXinImg.setImageResource(R.drawable.icon_wx_xz);
			fwx.setTextColor(Color.rgb(66, 195, 24));
			break;
		case R.id.id_tab_address:
			mViewPager.setCurrentItem(1);
			resetImg();
			mAddressImg.setImageResource(R.drawable.icon_txl_xz);
			ftxl.setTextColor(Color.rgb(66, 195, 24));
			break;
		case R.id.id_tab_frd:
			mViewPager.setCurrentItem(2);
			resetImg();
			mFrdImg.setImageResource(R.drawable.icon_fx_xz);
			ffx.setTextColor(Color.rgb(66, 195, 24));
			break;
		case R.id.id_tab_settings:
			mViewPager.setCurrentItem(3);
			resetImg();
			mSettingImg.setImageResource(R.drawable.icon_w_xz);
			fw.setTextColor(Color.rgb(66, 195, 24));
			break;
		default:
			break;
		}
	}
	
	/** 
	 * 把所有图片变暗
	 */
	private void resetImg() {
		mWeiXinImg.setImageResource(R.drawable.icon_wx);
		mAddressImg.setImageResource(R.drawable.icon_txl);
		mFrdImg.setImageResource(R.drawable.icon_fx);
		mSettingImg.setImageResource(R.drawable.icon_w);
		fwx.setTextColor(Color.BLACK);
		ftxl.setTextColor(Color.BLACK);
		ffx.setTextColor(Color.BLACK);
		fw.setTextColor(Color.BLACK);
	}
	
   public void action_talk(View v) {
	   switch(v.getId()) {
	   case R.id.l:
		   //Toast.makeText(getApplication(), "打开对话框", 1000).show();
		   Intent intent = new Intent();
		   intent.setClass(MainActivity.this, Message.class);
		   startActivity(intent);
		  
		   break;
	   }
   }
   
   public void action_xx(View v) {
	   switch(v.getId()) {
	   default:
		   Toast.makeText(getApplication(), "敬请期待!!ヽ(´･ω･`)､", 1000).show();
		   break;
	   }
   }
}

    