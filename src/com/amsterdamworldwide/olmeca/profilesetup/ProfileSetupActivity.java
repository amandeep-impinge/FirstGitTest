package com.amsterdamworldwide.olmeca.profilesetup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amsterdamworldwide.olmeca.R;
import com.amsterdamworldwide.olmeca.util.FacebookAuthentication;
import com.amsterdamworldwide.olmeca.util.Utils;
import com.facebook.android.Facebook;

public class ProfileSetupActivity extends Activity implements OnClickListener {

	private Button btnSignIn, btnSkipSignIn;
	private boolean addWord = true;
	private Intent intent;
	private Facebook facebookClient;
	private ImageView imgWelcome;
	private SharedPreferences prefs;
	private boolean atSearchListDetail = false;
	public static String MY_PREFERENCE = "my_facebook_prefs";

	private TextView txtWeclomeHeading, txtWelcomeSubHeading, txtProfileSetUp;

	public void onCreate(Bundle savedInstanceState) {

		setContentView(R.layout.profilesetup);
		btnSignIn = (Button) findViewById(R.id.btn_sign_facebook);
		btnSkipSignIn = (Button) findViewById(R.id.btn_skip_sign_facebook);
		facebookClient = new Facebook();

		txtWeclomeHeading = (TextView) findViewById(R.id.text_welcome_heading);
		txtWelcomeSubHeading = (TextView) findViewById(R.id.text_welcome_Sub_heading);
		txtProfileSetUp = (TextView) findViewById(R.id.text_profilesetup);

		imgWelcome = (ImageView) findViewById(R.id.img_welcome_image);

		btnSignIn.setOnClickListener(this);
		btnSkipSignIn.setOnClickListener(this);
	}

	public void onClick(View v) {

		if (v.getId() == R.id.btn_sign_facebook) {
			// TODO Functionality of face book sign in button will
			// open login page of face book
			facebookClient.authorize(this, Utils.FACEBOOK_APP_ID,
					Utils.FACEBOOK_RIGHTS, new FacebookAuthentication(
							facebookClient, this));
			
			

		}

		else if (v.getId() == R.id.btn_skip_sign_facebook) {
			// TODO Functionality of skip face book sign in button
			// will open of face book

			intent = new Intent(this, FacebookSkippedActivity.class);
			startActivity(intent);

		}

	}

	protected Boolean isNetWorking() {
		try {

			ConnectivityManager ConMgr = (ConnectivityManager) this
					.getSystemService(CONNECTIVITY_SERVICE);
			if (ConMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED
					|| ConMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
							.getState() == NetworkInfo.State.CONNECTED)
				return true;
			else
				return false;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}