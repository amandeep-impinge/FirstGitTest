package com.amsterdamworldwide.olmeca.streaming;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.amsterdamworldwide.olmeca.R;

public class StreainingAcivity extends Activity implements OnClickListener{
	
	ImageView imgBig,imgSmall;
	TextView channelName, trackDetail;
	Button previousChannel,playChannel,nextChannel,shareThis;
	ListView channelList;
	
	
	public void onCreate(Bundle savedInstanceState) {

		setContentView(R.layout.streaming);
		imgBig = (ImageView)findViewById(R.id.img_big_channel);
		imgSmall = (ImageView)findViewById(R.id.img_small_channel);
		channelName = (TextView)findViewById(R.id.text_channel_name);
		trackDetail = (TextView)findViewById(R.id.text_channel_detail);
		
		channelList = (ListView)findViewById(R.id.list_channel);
		
		previousChannel = (Button)findViewById(R.id.btn_previous);
		playChannel = (Button)findViewById(R.id.btn_play);
		nextChannel = (Button)findViewById(R.id.btn_next);
		
			
			
		   }


	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.btn_previous){
			//TODU Functionality for previous button
		}
		else if(v.getId()==R.id.btn_play){
			//TODU Functionality for play button
		}
		else if(v.getId()==R.id.btn_next){
			// TODU Functionality for next button
		}
		
	}

}
