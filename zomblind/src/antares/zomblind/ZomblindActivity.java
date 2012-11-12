package antares.zomblind;

import android.R.bool;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;



public class ZomblindActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      
        setContentView(R.layout.main);
        
        
        MediaPlayer mediaPlayer = MediaPlayer.create(ZomblindActivity.this,R.raw.mus_levelup_01);
        mediaPlayer.setLooping(false);
        
        MediaPlayer m2 = MediaPlayer.create(ZomblindActivity.this, R.raw.mus_levelup_02); 
        
        mediaPlayer.start();
        //m2.start();
        float t=0;
        boolean lado = true;
        while(mediaPlayer.isPlaying()){
        	mediaPlayer.setVolume(0+t,1-t);
            try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            if(lado){t = t + 0.01f;}else{t = t - 0.01f;}
            if(t>0.9){lado=false;}
            if(t<0.1){lado=false;}
        }
        
    }
}