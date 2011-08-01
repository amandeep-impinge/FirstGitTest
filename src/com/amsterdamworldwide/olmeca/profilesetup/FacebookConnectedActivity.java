package com.amsterdamworldwide.olmeca.profilesetup;

import android.app.Activity; 
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import com.facebook.android.Facebook;

import com.amsterdamworldwide.olmeca.R;
import com.amsterdamworldwide.olmeca.util.FacebookAuthentication;
import com.amsterdamworldwide.olmeca.util.Utils;

public class FacebookConnectedActivity extends Activity implements OnClickListener {
	
	private Button btnShare,btnNotification;
	private TextView txtWelcomeHeading,txtWelcomeSubHeading,txtYour;
	private GridView gridFriendList;
	SharedPreferences preferences;
	private String authentication;
	private Facebook facebookClient;
	private String Constant = "accesstoken";
	public void onCreate(Bundle savedInstanceState) {
		
		setContentView(R.layout.profilesetup_skipped);
		btnShare = (Button)findViewById(R.id.btn_share_fb);
		btnNotification = (Button)findViewById(R.id.btn_notificatin);
		
		txtYour = (TextView)findViewById(R.id.text_your);
		txtWelcomeHeading = (TextView)findViewById(R.id.text_welcome_heading);
		txtWelcomeSubHeading = (TextView)findViewById(R.id.text_welcome_Sub_heading);
		
		gridFriendList = (GridView)findViewById(R.id.gridview_frnd_list);
		
		btnShare.setOnClickListener(this);
		btnNotification.setOnClickListener(this);
	}

	public void onClick(View v) {
		if(v.getId()==R.id.btn_share_fb){
			// TODO Functionality of share with friends
			preferences.getString(Constant,authentication);
			if(authentication == null){
				
//		 		TODO Functionality of face book sign in button will 
			    //open login page of face book because user is not 
//				/online on face book	
			    facebookClient.authorize(this, Utils.FACEBOOK_APP_ID, Utils.FACEBOOK_RIGHTS,
				new FacebookAuthentication(facebookClient, this)
			);	
				
				
			}else{
				// intent for not connected
				
			}
			
			
			
		}else if(v.getId()==R.id.btn_notificatin){
			// TODO Functionality of Notification round button
			
			
		}		
	}


}
