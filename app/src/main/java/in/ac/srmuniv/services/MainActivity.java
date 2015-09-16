package in.ac.srmuniv.services;
import in.ac.srmuniv.services.BoundedAudioPlayerService.MyLocalBinder;
import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;

public class MainActivity extends Activity {
    BoundedAudioPlayerService myService;
    private boolean isBound;
    ImageButton btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btn=(ImageButton)findViewById(R.id.button1);
        isBound=false;
       
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void playAudio(View view) {
        Intent objIntent = new Intent(this,  BoundedAudioPlayerService.class);
        if(!isBound)
        {
        bindService(objIntent, myConnection, Context.BIND_AUTO_CREATE);
        isBound=true;
        btn.setBackgroundResource(R.drawable.pause);//setText("Pause");
        startService(objIntent);
        }
        else
        {
            myService.plauseAudio();
            btn.setBackgroundResource(R.drawable.play);
            isBound=false;
            unbindService(myConnection);
        }
               
    }

    public void stopAudio(View view) {
        Intent objIntent = new Intent(this, BoundedAudioPlayerService.class);
        if(isBound)
        {
            isBound=false;
        unbindService(myConnection);   
        stopService(objIntent);
       
        }
        else
            stopService(objIntent);
        btn.setBackgroundResource(R.drawable.play);
    }
    private ServiceConnection myConnection = new ServiceConnection() {

       

        public void onServiceConnected(ComponentName className,
                IBinder service) {
            myService = ((BoundedAudioPlayerService.MyLocalBinder) service).getService();
            isBound = true;
        }
       
        public void onServiceDisconnected(ComponentName arg0) {
            isBound = false;
        }
       
       };
      
        @Override
        protected void onDestroy() {
            super.onDestroy();
            if (isBound) {
                // Disconnect from an application service. You will no longer
                // receive calls as the service is restarted, and the service is
                // now allowed to stop at any time.
                unbindService(myConnection);
                isBound = false;
            }
        }

}