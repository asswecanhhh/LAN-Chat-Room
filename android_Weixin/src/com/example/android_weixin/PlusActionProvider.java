package com.example.android_weixin;

import android.content.Context;
import android.util.Log;
import android.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import android.widget.Toast;

public class PlusActionProvider extends ActionProvider {
	 private Context context;  
	  
	    public PlusActionProvider(Context context) {  
	        super(context);  
	        this.context = context;  
	    }  
	
	@Override
	public View onCreateActionView() {
		// TODO Auto-generated method stub
		return null;
		
	}
	
	
    public void onPrepareSubMenu(SubMenu subMenu) {  
    	super.onPrepareSubMenu(subMenu);
        subMenu.clear(); 
        try {
        	subMenu.add(context.getString(R.string.stringfqql))  
            .setIcon(R.drawable.icon_fqql2)  
            .setOnMenuItemClickListener(new OnMenuItemClickListener() {  
                public boolean onMenuItemClick(MenuItem item) {  
                    return true;  
                }  
            });  
		} catch (Exception e) {
			// TODO: handle exception
			Log.i("err",e.toString());
		}
        
        
        subMenu.add(context.getString(R.string.stringtjhy))  
                .setIcon(R.drawable.icon_tjhy2)  
                .setOnMenuItemClickListener(new OnMenuItemClickListener() {  
                      
                    public boolean onMenuItemClick(MenuItem item) {  
                        return false;  
                    }  
                });  
        subMenu.add(context.getString(R.string.stringsys))  
                .setIcon(R.drawable.icon_sys2)  
                .setOnMenuItemClickListener(new OnMenuItemClickListener() {  
                    
                    public boolean onMenuItemClick(MenuItem item) {  
                        return false;  
                    }  
                });  
        subMenu.add(context.getString(R.string.stringsz))  
                .setIcon(R.drawable.icon_sz2)  
                .setOnMenuItemClickListener(new OnMenuItemClickListener() {  
                    public boolean onMenuItemClick(MenuItem item) {  
                        return false;  
                    }  
                });  
    }  
  
    @Override  
    public boolean hasSubMenu() {  
        return true;  
    }  
  

}
