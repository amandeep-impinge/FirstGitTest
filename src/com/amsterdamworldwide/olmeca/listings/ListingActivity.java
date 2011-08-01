package com.amsterdamworldwide.olmeca.listings;

import com.amsterdamworldwide.olmeca.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ListingActivity extends Activity implements OnClickListener{

	Button btnMood,btnGenre,btnExpension,btnRefresh,btnList,btnMap;
	public void onCreate(Bundle savedInstanceState) {
		
	setContentView(R.layout.listing_one);
	btnMood = (Button)findViewById(R.id.btn_mood);
	btnMap = (Button)findViewById(R.id.btn_map);
	btnExpension = (Button)findViewById(R.id.btn_expension);
	btnRefresh = (Button)findViewById(R.id.btn_refresh);
	btnList = (Button)findViewById(R.id.btn_list);
	btnGenre = (Button)findViewById(R.id.btn_genre);
	
	
	}
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.btn_mood){
			
		}
		else if(v.getId()==R.id.btn_map){
			
			
			
		}else if(v.getId()==R.id.btn_expension){
			
			
			
		}else if(v.getId()==R.id.btn_list){
			
			
			
		}else if(v.getId()==R.id.btn_genre){
			
			
			
		}else if(v.getId()==R.id.btn_refresh){
			
			
			
		}
	}
}
