/**
 * 
 */
package com.amsterdamworldwide.olmeca.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;

import java.util.Calendar;


import com.amsterdamworldwide.olmeca.R;

import android.app.AlertDialog;
import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;



/**
 * @author amandeep
 * 
 */
public class Utils {
	
	private String constant = "/mnt/sdcard/";
	public static String FACEBOOK_APP_ID = "145592028834279";
    public static String[] FACEBOOK_RIGHTS = new String[] {
                    "publish_stream", "read_stream", "offline_access" };

	public static boolean isLocationManagerEnable(Context context) {

		LocationManager manager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);

		if (manager != null) {
			return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		} else {
			return false;
		}

	}
	
	
	
	public static boolean writeImageFile(File file,byte[] data){
		boolean isWritten = false;
		OutputStream out = null;
		try{
			
		     Log.e("", "Image File Name " + file.getName());
		     out = new FileOutputStream(file);
		    
		     if(data != null){
		    	 
		    		BitmapFactory.Options opts = new BitmapFactory.Options();
		    		opts.inSampleSize = 3;
					Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0,data.length, opts);
					bitmap.compress(Bitmap.CompressFormat.PNG, 99, out);
					
					out.write(data);
		     }else{
		    	 Log.e("", "Image Data is Null");	 
		     }
		     out.close();
		     isWritten = true;
		     Log.e("", "Image Data Written");	 
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		return isWritten;
		
	}

	public static boolean writeAudioFile(File file,byte[] data){
		boolean isWritten = false;
		OutputStream out = null;
		try{
			
		     Log.e("", "Audio File Name " + file.getName());
		     out = new FileOutputStream(file);
		    
		     if(data != null){
		    	 
		    	 
		    	 //TODO saving byte [] data in file
//		    		BitmapFactory.Options opts = new BitmapFactory.Options();
//		    		opts.inSampleSize = 3;
//					Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0,data.length, opts);
//					bitmap.compress(Bitmap.CompressFormat.PNG, 99, out);
		    	 
		    	 
		    	 out = new BufferedOutputStream(new FileOutputStream(file));
					out.write(data);
		     }else{
		    	 Log.e("", "Audio Data is Null");	 
		     }
		     out.close();
		     isWritten = true;
		     Log.e("", "Audio Data Written");	 
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		return isWritten;
		
	}
	
	//TODO when Location required
	public static Location getCurrenLocation(Context context) {

		LocationManager manager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);

		if (manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
			LocationProvider provider = manager
					.getProvider(LocationManager.NETWORK_PROVIDER);
			Log.e("", "Location Provicer" + provider.getName());

			if (provider != null) {

				return manager
						.getLastKnownLocation(LocationManager.GPS_PROVIDER);

			} else {
				return null;
			}
		}

		return null;
	}

	public static File getImageFileName() {

		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhmmss");
		StringBuilder sb = new StringBuilder();
		sb.append(dateFormat.format(Calendar.getInstance().getTime()));
		sb.append(".png");
		

		File file = new File(createStorageExternaly(), sb.toString());
		Log.i("", "File Name :" + file.getAbsolutePath() + ": "
				+ file.getPath());
		return file;

	}


	
	public static File getAudioFileName() {

		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhmmss");
		StringBuilder sb = new StringBuilder();
		sb.append(dateFormat.format(Calendar.getInstance().getTime()));
		sb.append(".wav");
		File file = new File(createStorageExternaly(), sb.toString());
		Log.i("", "File Name :" + file.getAbsolutePath() + ": "
				+ file.getPath());
		return file;

	}

	
	
	
	public static File createStorageDirectory() {

		File file = new File(Constants.OLMECA_MEDIA_STORE);

		if (file.exists()) {
			return file;
		} else {

			boolean created = file.mkdir();
			Log.i("", "DirectoryCreated");
			return file;
		}

	}


	
	public static File createStorageExternaly() {

		// /mnt/sdcard/
		String path = Environment.getExternalStorageDirectory().toString();

		File file = new File(path+"/"+Constants.OLMECA_MEDIA_STORE);

		if (file.exists()) {
			return file;
		} else {

			boolean created = file.mkdir();
			Log.i("", "DirectoryCreated" + created);
			return file;
		}
	}


	
	
	//TODO fo rmap location will need in coming time
//	public static String getGoogleSerachString(Location location) {
//
//		// http://maps.google.com/maps/geo?q=%f,%f&output=json&oe=utf8&sensor=true_or_false&key=ABQIAAAAO_WEDXjKUGDDHu8iB2FQ-xTFGipc9T1i1tjtezgvz0dWKwv3jxTjn5y1VrjIixtSrXmeMwiaCfxS8g
//
//		StringBuilder sb = new StringBuilder();
//		sb.append("http://maps.google.com/maps/geo?q=");
//		sb.append(location.getLatitude());
//		sb.append(",");
//		sb.append(location.getLongitude());
//		//sb.append("51.0450000,-114.0572222");
//		sb
//				.append("&output=json&oe=utf8&sensor=true_or_false&key=ABQIAAAAO_WEDXjKUGDDHu8iB2FQ-xTFGipc9T1i1tjtezgvz0dWKwv3jxTjn5y1VrjIixtSrXmeMwiaCfxS8g");
//
//		return sb.toString();
//	}
//
//	

	public static AlertDialog getAlertDialog(Context context, String msg,
			Handler handler) {

		AlertDialog alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.setTitle(R.string.app_name);
//		alertDialog.setIcon(R.drawable.logo);
		alertDialog.setMessage(msg);
		alertDialog.setButton("Ok", Message.obtain(handler, 10000));

		return alertDialog;
	}
	
	
	
	

	
	public static String getFileName(String filePath){
		
		int index = filePath.lastIndexOf("/");
		if(index > -1){
			
			return filePath = filePath.substring(index+1);
		}
		return filePath;
	}
	
	
	
	
	
	
	private  static final String DATE_TIME_FORMAT = "dd MMM yyyy";
	
	public static String getCurrentDate(){
		
		return new SimpleDateFormat(DATE_TIME_FORMAT).format(Calendar.getInstance().getTime());
		
	}
	
	
	
	
	
	
}

