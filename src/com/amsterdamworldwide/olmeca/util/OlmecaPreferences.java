package com.amsterdamworldwide.olmeca.util;



import android.content.Context;
import android.content.SharedPreferences;

public class OlmecaPreferences {
	
	private SharedPreferences pref = null;
	private final String FACEBOOK_TOKEN = "fab_token";
	// default value
	
	public static final String CURRENT_SESSION_ID = "session_id";
	
	
	public OlmecaPreferences(Context context) {
		pref = context.getSharedPreferences("OlmecaPrefs", Context.MODE_PRIVATE);
	}

	
	public String getFacebookToken(){
		return pref.getString(FACEBOOK_TOKEN, null);
	}
	
	public void setFacebookToken(String token){
		pref.edit().putString(FACEBOOK_TOKEN, token).commit();
	}
	
	
	// for FACEBOOK for now unknown where used
	public String getCurrentSessionToken(){
		return pref.getString(CURRENT_SESSION_ID, null);
	}
	
	
	
	
}
