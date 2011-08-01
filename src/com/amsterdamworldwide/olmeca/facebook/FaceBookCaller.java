/*
 * @ authour AMANDEEP   This class make call to sever and get the 
 * list of friends their names and pictures ...
 * also the profile image of the user
 * 
 */

package com.amsterdamworldwide.olmeca.facebook;


import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.amsterdamworldwide.olmeca.http.HttpCallListener;
import com.amsterdamworldwide.olmeca.http.HttpContentTask;
import com.amsterdamworldwide.olmeca.http.HttpMethodType;
import com.amsterdamworldwide.olmeca.model.IEHttpResponse;

public class FaceBookCaller implements OnClickListener,HttpCallListener{
	
	private String httpUrlFriendList="https://graph.facebook.com/me/friends?access_token=";
	private String httpUrlprofileImage="http://graph.facebook.com";
	private HttpCallListener listener;
	String accessToken;
	String urlFriendList;
	String urlProfileImage;
	SharedPreferences pref;
	private String Constant = "accesstoken",authentication;
	private final int FACEBOOK_PROFILE_IMAGE = 1;
	private final int FACEBOOK_FRIENDLIST = 2;
	private StringBuilder sb; 
	private String httpResponse;
	private JSONObject jObject;
	private String jFriendList = null;
	private int jsonLenght;
	private String tag = "FaceBookCaller";
	
	public void getAccessToken(){
		
		//pref.getString(Constant,authentication);
		
	}
	
	public void getUrlFriendList(){
		
		sb = new StringBuilder(httpUrlFriendList);
		sb.append(authentication);
		urlFriendList = sb.toString();
	}
	
	public void getUrlProfileImage(){
		
	
	//http://graph.facebook.com/600074425/picture
	
		sb = new StringBuilder(httpUrlprofileImage);
		sb.append(authentication);
		sb.append("/picture");
		urlProfileImage = sb.toString();
	}
	
	public void profileImage(){
		
		
		HttpContentTask httpContentTask= new HttpContentTask(urlProfileImage,listener,FACEBOOK_PROFILE_IMAGE);	
		httpContentTask.setHttpMethodType(HttpMethodType.HTTP_GET);
		httpContentTask.setHttpEntity(null);
       // progbarreset .setVisibility(View.VISIBLE);
        httpContentTask.execute();
		
		
		
	}
	public void friendList(){
		
		
	
		Log.i("called friend list", "FRIEND LIST CALLED");
		HttpContentTask httpContentTask= new HttpContentTask("https://graph.facebook.com/me/friends?access_token=600074425",listener,FACEBOOK_FRIENDLIST);
		
		// calling the get method 
		 httpContentTask.setHttpMethodType(HttpMethodType.HTTP_GET);
		 httpContentTask.setHttpEntity(null);
         //progbarreset .setVisibility(View.VISIBLE);
         httpContentTask.execute();
	}


	

	
	@Override
	public void onResponse(IEHttpResponse response, int pageID) {
		// TODO Auto-generate	d method stub
		httpResponse = response.getResponse();	
		Log.i("RESPONSE", httpResponse);
		
		
		if(pageID == FACEBOOK_FRIENDLIST){
			try {
				jObject = new JSONObject(httpResponse);
				
				ArrayList<ID> id=new ArrayList<ID>();
				
				
				JSONArray jArray = jObject.getJSONArray("data");
				
				
				for(int i = 0; i <=jArray.length();i++){
						jObject = jArray.getJSONObject(i);
						jObject.getString("name");
						ID objID = new ID();
						objID.setId(jObject.getString("name"));
						Log.i(tag," NAME  "+jObject.getString("name"));
						jObject.getString("id");
						objID.setId(jObject.getString("id"));
						Log.i(tag,"ID "+jObject.getString("id"));
						id.add(objID);
					
					
					}
		
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
			
			
			
			
		}else if(pageID ==FACEBOOK_PROFILE_IMAGE){
			
			
		
			
			
			
			
			
			
		}
	}

	@Override
	public void onError(String error, int pageId) {
		// TODO Auto-generated method stub
		
		
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}





