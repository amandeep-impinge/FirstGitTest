package com.amsterdamworldwide.olmeca.facebook;

import com.amsterdamworldwide.olmeca.R;
import com.amsterdamworldwide.olmeca.facebook.FaceBookCaller;
import android.app.Activity;
import android.os.Bundle;

public class FaceBookActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fb);
		FaceBookCaller fbCaller= new FaceBookCaller();
		//fbCaller.getAccessToken();
		//fbCaller.getUrlFriendList();
		fbCaller.friendList();
		
		
		
		
	}
	

}
