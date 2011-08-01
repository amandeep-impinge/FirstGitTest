package com.amsterdamworldwide.olmeca;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {
    /** Called when the activity is first created. */
	
	Handler handler;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
       
        handler= new Handler();

        setContentView(R.layout.splash_activity);

		handler.postDelayed(runnable, 1000);
        
		 super.onCreate(savedInstanceState);
        
    }
    
    @Override
    public void finish() {

    	handler.removeCallbacks(runnable);
    	super.finish();
    }
    
    
    
    private Runnable runnable=new Runnable() {  
		
    	@Override
		public void run() {
			Intent intent=new Intent(getApplicationContext(),LegalActivity.class);
		startActivity(intent);
			finish();
			
		}
	};
    
}