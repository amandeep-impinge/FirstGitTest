package com.amsterdamworldwide.olmeca.about;

import com.amsterdamworldwide.olmeca.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;




public class AboutActivity extends Activity{

	
	ImageView imgHeading,imgTitle1,imgTitle2;
	TextView txtHeading,txtSubHeading,txtTitle1,txtTitle2,txtLink1,txtLink2;
	
	public void onCreate(Bundle savedInstanceState) {

	setContentView(R.layout.about);
	imgHeading = (ImageView)findViewById(R.id.img_big);
	imgTitle1 = (ImageView)findViewById(R.id.img_subtitle1);
	imgTitle2 = (ImageView)findViewById(R.id.img_subtitle2);
	
	
	txtHeading = (TextView)findViewById(R.id.text_about_heading);
	txtSubHeading = (TextView)findViewById(R.id.text_about_subheading);
	txtTitle1 = (TextView)findViewById(R.id.text_sub_title1);
	txtTitle2 = (TextView)findViewById(R.id.text_sub_title2);
	
	
	txtLink1 = (TextView)findViewById(R.id.text_link1);
	txtLink2 = (TextView)findViewById(R.id.text_link2);
	
		
		
	   }
}
