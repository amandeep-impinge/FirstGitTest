package com.amsterdamworldwide.olmeca.listings;

import com.amsterdamworldwide.olmeca.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailListActivity extends Activity implements OnClickListener{
	
	
	private Button btnAll,btnPrevious,btnNext,btnShareThis;
	private TextView txtTags,txtTime,txtTitle,txtLocation,txtDiscription;
	private ImageView imgDetailList;
	public void onCreate(Bundle savedInstanceState) {
		
		setContentView(R.layout.list_details);
		
		
		btnAll = (Button)findViewById(R.id.btn_all);
		btnPrevious = (Button)findViewById(R.id.btn_previous);
		btnNext = (Button)findViewById(R.id.btn_next);
		btnShareThis = (Button)findViewById(R.id.btn_share_this);
		
		
		txtTags = (TextView)findViewById(R.id.text_tags);
		txtTime = (TextView)findViewById(R.id.text_time_details);
		txtTitle = (TextView)findViewById(R.id.text_title);
		txtLocation = (TextView)findViewById(R.id.text_location);
		txtDiscription = (TextView)findViewById(R.id.text_description);
		
		imgDetailList = (ImageView)findViewById(R.id.img_list_detail);
		
		}
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.btn_all){
			
		} else if(v.getId()==R.id.btn_previous){
			
		}else if(v.getId()==R.id.btn_next){
			
		}else if(v.getId()==R.id.btn_share_this){
			
		}
		
	}

}
