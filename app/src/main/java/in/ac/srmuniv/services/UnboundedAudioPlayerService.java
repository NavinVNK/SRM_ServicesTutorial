package in.ac.srmuniv.services;

import in.ac.srmuniv.services.R;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class UnboundedAudioPlayerService extends Service{
	private static final String LOGCAT = null;
	MediaPlayer AudioPlayer;

	public void onCreate(){
		super.onCreate();
		Log.d(LOGCAT, "Service Started!");
		AudioPlayer = MediaPlayer.create(this,R.raw.krtheme);
	}

	public int onStartCommand(Intent intent, int flags, int startId){
		AudioPlayer.start();
		Log.d(LOGCAT, "Media Player started!");
		if(AudioPlayer.isLooping() != true){
			Log.d(LOGCAT, "Error in Playing Audio");
		}
		return START_STICKY;
	}
	public void onDestroy(){
		AudioPlayer.stop();
		AudioPlayer.release();
	}
	@Override
	public IBinder onBind(Intent Audioindent) {
		return null;
	}
}


