package in.ac.srmuniv.services;


import in.ac.srmuniv.services.R;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class BoundedAudioPlayerService extends Service {

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
				Log.d(LOGCAT, "Problem in Playing Audio");
			}
			return START_STICKY;
		}

		public void onDestroy(){
			AudioPlayer.stop();
			AudioPlayer.release();
		}
		public class MyLocalBinder extends Binder {
	        BoundedAudioPlayerService getService() {
	            return BoundedAudioPlayerService.this;
	        }
	    }
		private final IBinder myBinder = new MyLocalBinder();
		
		@Override
		public IBinder onBind(Intent arg0) {
			// TODO Auto-generated method stub
			return myBinder;
		}
		public void plauseAudio() {
			if(AudioPlayer.isPlaying())
		          AudioPlayer.pause();	
			
		}
	}



