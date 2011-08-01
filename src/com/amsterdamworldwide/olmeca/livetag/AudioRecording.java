package com.amsterdamworldwide.olmeca.livetag;

import java.io.File;
import java.io.IOException;
import com.amsterdamworldwide.olmeca.util.Utils;
import android.media.MediaRecorder;


/**
 * @author shiwindersingh
 */
public class AudioRecording {

  final MediaRecorder recorder = new MediaRecorder();
  final File path= Utils.getAudioFileName();
  
  /**
   * Starts a new recording.
   */
  public void start() throws IOException {
    String state = android.os.Environment.getExternalStorageState();
    if(!state.equals(android.os.Environment.MEDIA_MOUNTED))  {
        throw new IOException("SD Card is not mounted.  It is " + state + ".");
    }

    // make sure the directory we plan to store the recording in exists
    
    
    File directory = path.getParentFile();
    if (!directory.exists() && !directory.mkdirs()) {
      throw new IOException("Path to file could not be created.");
    }

    recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
    recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
    recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
    recorder.setOutputFile(path.getAbsolutePath());
    recorder.prepare();
    recorder.start();
  }

  /**
   * Stops a recording that has been previously started.
   */
  public void stop() throws IOException {
    recorder.stop();
    recorder.release();
  }

}

