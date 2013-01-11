package antares.zomblind;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.media.MediaPlayer;

public class entorno {
	Timer _eventos;
	private long TASK_DELAY = 1000;
	private long TASK_PERIOD = 1000;
	
	private Context _context;
	
	private class tarea extends TimerTask{

		public tarea(Context contexto) {
			// TODO Auto-generated constructor stub
			_context = contexto;

		}

		@Override
		public void run() {

	        MediaPlayer mediaPlayer = MediaPlayer.create(_context,R.raw.mus_levelup_01);
	        mediaPlayer.setLooping(false);
	        
	        MediaPlayer m2 = MediaPlayer.create(_context, R.raw.mus_levelup_02); 
	        
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
	
	
	entorno(Context contexto){
		_eventos = new Timer();
		_eventos.scheduleAtFixedRate(new tarea(contexto),TASK_DELAY,TASK_PERIOD);
	}
	
	
}
