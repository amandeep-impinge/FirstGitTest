package com.amsterdamworldwide.olmeca.profilesetup;

import java.util.prefs.Preferences;

import com.amsterdamworldwide.olmeca.R;
import com.amsterdamworldwide.olmeca.util.FacebookAuthentication;
import com.amsterdamworldwide.olmeca.util.Utils;
import com.facebook.android.Facebook;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;


public class FacebookSkippedActivity extends Activity implements OnClickListener{

	
	private Button btnSignIn,btnNotification;
	private TextView txtWelcomeHeading,txtWelcomeSubHeading,txtAnonymous;
	private GridView gridLotsOgPpl;
	private SharedPreferences preferences;
	private String Constant = "accesstoken",authentication;
	private Facebook facebookClient;
	
	
	
	public void onCreate(Bundle savedInstanceState) {
		
		setContentView(R.layout.profilesetup_skipped);
		btnSignIn = (Button)findViewById(R.id.btn_facebooksignin);
		btnNotification = (Button)findViewById(R.id.btn_notificatin);
		
		txtAnonymous = (TextView)findViewById(R.id.text_anonymous);
		txtWelcomeHeading = (TextView)findViewById(R.id.text_welcome_heading);
		txtWelcomeSubHeading = (TextView)findViewById(R.id.text_welcome_Sub_heading);
		
		gridLotsOgPpl = (GridView)findViewById(R.id.gridview_lots_of_ppl);
		
		btnSignIn.setOnClickListener(this);
		btnNotification.setOnClickListener(this);
	}

	public void onClick(View v) {
		if(v.getId()==R.id.btn_facebooksignin){
			// TODO Functionality of sign in button under skipped mode
			
			// TODO Functionality of share with friends
			preferences.getString(Constant,authentication);
			if(authentication == null){
				
//		 		TODO Functionality of face book sign in button will 
			    //open login page of face book because user is not 
//				/online on face book	
			    facebookClient.authorize(this, Utils.FACEBOOK_APP_ID, Utils.FACEBOOK_RIGHTS,
				new FacebookAuthentication(facebookClient, this)
			);	
				
			
			
			
		}else if(v.getId()==R.id.btn_notificatin){
			// TODO Functionality of Notification round button
			
			
			
		}		
	}

	
	
	}
}
