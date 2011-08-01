package com.amsterdamworldwide.olmeca.util;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.widget.Toast;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;

public class FacebookAuthentication implements DialogListener{

	private Context context = null;
	private Facebook facebook = null;
	private SharedPreferences preferences;
	private String Constant = "accesstoken";
	
	
	public FacebookAuthentication(Facebook facebook,Context context) {
		this.context = context;
		this.facebook = facebook;
	}
	
	@Override
	public void onCancel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onComplete(Bundle values) {
		// TODO Put  code for post functionality
		if (values.isEmpty()) {
			// "skip" clicked ?
			return;
		}
		
		// if facebookClient.authorize(...) was successful, this runs
		// this also runs after successful post
		// after posting, "post_id" is added to the values bundle
		// I use that to differentiate between a call from
		// faceBook.authorize(...) and a call from a successful post
		// is there a better way of doing this?
		
		if(!values.containsKey("post_id")){
			try{
			//Bundle parameters = new Bundle();
			//parameters.putString("message"," "  );
			
				String me = facebook.request("me");
				
				
				//for converting name String into json object
				JSONObject nameObj = new JSONObject(me);   
				String	usernameFacebook = nameObj.getString("name");
				
				String accessToken = facebook.getAccessToken();
				
				
				//TODO adding token to preferences
				
				
				preferences = context.getSharedPreferences(
                        Constant, Context.MODE_PRIVATE);
				preferences.edit().putString(Constant,accessToken).commit();
				
				
				
			   new OlmecaPreferences(context).setFacebookToken(accessToken);
			
			
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void onError(DialogError e) {
		// TODO Auto-generated method stub
		Toast.makeText(context, "Error!\n"+e.getMessage(), Toast.LENGTH_LONG).show();
	}

	@Override
	public void onFacebookError(FacebookError e) {
		Toast.makeText(context, "Error!\n"+e.getMessage(), Toast.LENGTH_LONG).show();
		
	}
	

}
