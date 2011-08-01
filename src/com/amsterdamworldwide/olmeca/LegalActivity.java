package com.amsterdamworldwide.olmeca;



import com.amsterdamworldwide.olmeca.profilesetup.ProfileSetupActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;




//**
public class LegalActivity extends Activity implements OnClickListener{

	
	private Button btnEnter;
	private Spinner spnLanguage;
	private Spinner spnCountry;
	private EditText day,month,year;
	private Intent intent;
	
	
	public void onCreate(Bundle savedInstanceState) {

	setContentView(R.layout.legal);
	
	btnEnter = (Button)findViewById(R.id.btn_enter);
	spnCountry = (Spinner)findViewById(R.id.spn_country);
	spnLanguage = (Spinner)findViewById(R.id.spn_language);
	day = (EditText)findViewById(R.id.edit_day);
	month = (EditText)findViewById(R.id.edit_month);
	year = (EditText)findViewById(R.id.edit_year);
	
	
	
	
	
	btnEnter.setOnClickListener(this);
		
		
	   }



	public void onClick(View v) {
	if(v.getId()==R.id.btn_enter){
		// TODO functionality of ENTER BUTTON
		intent = new Intent(this,ProfileSetupActivity.class);
		startActivity(intent);
		
	}	
		
		
		
	}
}
