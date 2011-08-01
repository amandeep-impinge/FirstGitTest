package com.amsterdamworldwide.olmeca.livetag;

import com.amsterdamworldwide.olmeca.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FreindsActivity extends Activity implements OnClickListener{
@Override
protected void onCreate(Bundle savedInstanceState) {

 
	ImageView ImgUser;
	TextView txtFriendsHeading;
	Button btnGo;
	GridView gridFriends;
	super.onCreate(savedInstanceState);
	setContentView(R.layout.fb_connected_friends);
	ImgUser = (ImageView)findViewById(R.id.image_user);
	txtFriendsHeading = (TextView)findViewById(R.id.text_friendheading);
	btnGo = (Button)findViewById(R.id.btn_go);
	gridFriends = (GridView)findViewById(R.id.grid_frnds);
	
	
	
	btnGo.setOnClickListener(this);
	}

@Override
public void onClick(View v) {
	if(v.getId()==R.id.btn_go){
		//TODO Functionality of GO button
		
		
		
	}
	
}


}
