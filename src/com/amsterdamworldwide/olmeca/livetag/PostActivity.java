package com.amsterdamworldwide.olmeca.livetag;



/*
 * @ author amandeep 
 * This Activity class helps user to post the comment on facebook under live tag
 * 
 */
import com.amsterdamworldwide.olmeca.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class PostActivity extends Activity implements OnClickListener{

	
	
	private TextView txtPostHeading,txtArtist,txtSoundTitle;
	private TextView txtPositionTitle,txtCity,txtLocation;
	private Button btnRelisten,btnNotUsedSound,btnReposition,btnNotUsedPosition;
	private Button btnShareIt;
	private ImageView imgPostImage;
	private EditText editPost;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.fb_connected_post);
		
		imgPostImage = (ImageView)findViewById(R.id.img_post);
		
		txtPostHeading = (TextView)findViewById(R.id.text_post_heading);
		txtArtist = (TextView)findViewById(R.id.text_artist);
		txtCity = (TextView)findViewById(R.id.text_city);
		txtSoundTitle = (TextView)findViewById(R.id.text_title_sound);
		txtPositionTitle = (TextView)findViewById(R.id.text_position_title);
		txtLocation = (TextView)findViewById(R.id.text_location);
		
		btnShareIt = (Button)findViewById(R.id.btn_share_it_post);
		btnRelisten = (Button)findViewById(R.id.btn_relisten);
		btnReposition = (Button)findViewById(R.id.btn_reposition);
		
		editPost = (EditText)findViewById(R.id.edit_text_post);
		
		
		
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.btn_share_it_post){
			//TODO Functionality of sharing post on facebook
			
			
		}else if(v.getId()==R.id.btn_relisten){
			//TODO Functionality of sharing post on relisten
			
			
		}else if(v.getId()==R.id.btn_reposition){
			//TODO Functionality of sharing post on reposition
			
			
		}
		
	}
	
}
