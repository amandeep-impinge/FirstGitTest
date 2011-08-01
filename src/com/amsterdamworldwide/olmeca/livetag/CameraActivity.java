package com.amsterdamworldwide.olmeca.livetag;

/*
 *@ author Shwinder Singh
 *This class supports camera activity .. including shooting and saving image
 * the uses function start and stop from AudioRecording class to start and stop audio recording 
 * Saving image and recording audio is done in doInBackground function
 */
import java.io.File;
import java.io.IOException;

import com.amsterdamworldwide.olmeca.R;
import com.amsterdamworldwide.olmeca.util.Constants;
import com.amsterdamworldwide.olmeca.util.Utils;




import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;






public class CameraActivity extends Activity implements OnClickListener,
SurfaceHolder.Callback , Camera.PictureCallback{
	private static final int ACTION_SAVE_DATA = 30;
	private static final int ACTION_DISCARD_DATA = 31;
	private Camera camera;
	private Button btnShoot,btnCancel;
	private final String tag="cameraActivity";
	private byte[] imageData;
	private File currentFile = null;  		// for image file
	private File currentAudioFile = null;	// for Audio file
	private SurfaceView cameraView;
	private SurfaceHolder sHolder;
//	private AudioRecording audioRecording;
	private byte[] audioData;
	AudioRecording audioRecording;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

	 
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fb_connected_shoot);
		btnShoot=(Button)findViewById(R.id.btn_shoot);
		btnCancel = (Button)findViewById(R.id.btn_cancel);
		cameraView = (SurfaceView)findViewById(R.id.camera_surface);
		
		
		
		// setting listeners 
		sHolder = cameraView.getHolder();
		btnShoot.setOnClickListener(this);
		btnCancel.setOnClickListener(this);
		sHolder.addCallback(this);
		sHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		
		
		
		
		}


	@Override
	public void onPictureTaken(byte[] data, Camera camera) {
		
		
		
		Log.i(tag, "Picture CallBack Jpeg");
		try {
			
			// setting image data in byte array
			setImageData(data);
			
			
			camera.lock();
			
			// showing Dialog for confirm or discard image
			showSaveDialog();
			
		} catch (Exception e) {
			
			
		
		}catch (OutOfMemoryError e) {
			
			
			
		}
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {
		
		Log.i(tag,	"surfaceChanged(SurfaceHolder holder, int format, int width,int height) ");
		CamcorderProfile cProfile = CamcorderProfile.get(1);
		Camera.Parameters parameters = camera.getParameters();
		parameters.setPreviewSize(cProfile.videoFrameWidth,
				cProfile.videoFrameHeight);
	    parameters.setPreviewFrameRate(cProfile.videoFrameRate);
	    
	    parameters.setPictureFormat(PixelFormat.JPEG);
	    
	    camera.setDisplayOrientation(90);
		camera.setParameters(parameters);
		camera.startPreview();
		
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		

		Log.i(tag, "surfaceCreated(SurfaceHolder holder) ");
		 try
         {       //Open the Camera in preview mode
			 		camera = Camera.open();
			 		camera.setPreviewDisplay(holder);
         } catch(IOException ioe)
         {
                 ioe.printStackTrace(System.out);
         }


	
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		releaseCamera();
		
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.btn_shoot){
			
				
				try{
					// reconnecting camera
					camera.reconnect();
					// taking picture
					camera.takePicture(null, null, this);
					
					
						
				}catch (Exception e) {
					e.printStackTrace();
					
				}
				
			

			
			
			
		}else if(v.getId()==R.id.btn_cancel){
			// finishing the activity
			finish();
			
			
		}
	}
	

	private void performeDiscard(){
		
		// setting image data as null
		setImageData(null);
		setAudioData(null);
		camera.startPreview();
	}
	

	private void performContinue() throws IOException {

		// setting path to save file for Audio and Image
		setCurrentFile(Utils.getImageFileName());
		
		setCurrentAudioFile(Utils.getAudioFileName());
		Log.e("FilePath_#_IMAGE", getCurrentFile().getPath());
		Log.e("FilePath_#_AUDIO", getCurrentAudioFile().getPath());
		
	
		// both image and audio is written here
		new WriteImageTask().execute();
		
		
		

				
	}
	
	

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case ACTION_SAVE_DATA:
				
				
				try {
					performContinue();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
			
				break;
			case ACTION_DISCARD_DATA:
				performeDiscard();
				
				break;

			default:
				break;
			}
		}
	};

	private void showSaveDialog(){
		
		
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle(R.string.app_name);
	
	
		alertDialog.setTitle("Do you want to continue with Picture?");	
		alertDialog.setButton("Continue", Message.obtain(handler, ACTION_SAVE_DATA));
		alertDialog.setButton2("Discard", Message.obtain(handler, ACTION_DISCARD_DATA));
		alertDialog.show();
		
	}

	

public void setImageData(byte[] imageData) {
	this.imageData = imageData;
}

public void setAudioData(byte[] AudioData) {
	this.audioData = audioData;
}


@Override
public void finish() {

	//releaseCamera();

	super.finish();
}

public void setCurrentFile(File currentFile) {
	this.currentFile = currentFile;
}

public void setCurrentAudioFile(File currentAudioFile) {
	this.currentAudioFile = currentAudioFile;
}




/**
 * @return the currentFile
 */
public File getCurrentFile() {
	return currentFile;
}



/**
 * @return the currentFileAudio
 */
public File getCurrentAudioFile() {
	return currentAudioFile;
}



/**
 * @return the imageData
 */
public byte[] getImageData() {
	return imageData;
}

/**
 * @return the audioData
 */
public byte[] getAudioData() {
	return imageData;
}



// releasing camera
private void releaseCamera(){
	if(camera != null){
		try{
			camera.stopPreview();
	        camera.release();
	        camera = null;
	        }catch (Exception e) {
				e.printStackTrace();
	   }
	}
}


class WriteImageTask extends AsyncTask<Void, Void, Void>{

	ProgressDialog dialog = null;
	
	
	
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPreExecute()
	 */
	@Override
	protected void onPreExecute() {
	
		
		super.onPreExecute();
		dialog = ProgressDialog.show(CameraActivity.this, "Olmeca" , "Saving..");
		
	}
	
	
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected Void doInBackground(Void... params) {


		
		
		/*
		 *  Recording Audio starts
		 * 
		 */
			audioRecording = new AudioRecording();
			
			try {
				audioRecording.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			// Image saving
			Utils.writeImageFile(getCurrentFile(), getImageData());
			
			
			
			/*
			 *  Recording Audio stop
			 * 
			 */

			
			try {
				audioRecording.stop();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setImageData(null);
			//TODO Utils.writeAudioFile(file, data);
			
		//	Utils.writeAudioFile(getCurrentAudioFile(), getAudioData());
			//setAudioData(null);
			
			
			
			
		return null;
	}
	
	@Override
	protected void onPostExecute(Void result) {

		super.onPostExecute(result);
		if(dialog != null){
			dialog.dismiss();
		}
	}
	
}





}	



	

