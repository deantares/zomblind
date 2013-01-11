package antares.zomblind;


import antares.zomblind.zombie;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.media.MediaPlayer;

public class entorno {
	Timer _eventos;
	private long TASK_DELAY = 1000;
	private long TASK_PERIOD = 2000;
	
	private Context _context;
	
	private class tarea extends TimerTask{

		public tarea(Context contexto) {
			// TODO Auto-generated constructor stub
			_context = contexto;

		}

		@Override
		public void run() {
		
		/*	while(!((ZomblindActivity)_context).salir){
				
			} */


			if(!((ZomblindActivity)_context).calibrado){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				
				MediaPlayer zombie1 = MediaPlayer.create(_context, R.raw.zombie01);
				MediaPlayer zombie2 = MediaPlayer.create(_context, R.raw.zombie01);
				MediaPlayer zombie3 = MediaPlayer.create(_context, R.raw.zombie01);
				
				
				zombie1.setLooping(false);
				zombie2.setLooping(false);
				zombie3.setLooping(false);
				
				zombie1.setVolume(0, 1);
				zombie2.setVolume(1, 0);
				zombie3.setVolume(1, 1);
				
				
				Random ale = new Random();
				
				
					if(!((ZomblindActivity)_context)._talker.isSpeaking()){
						int a = ale.nextInt(20);
						if(a == 0){zombie1.start(); ((ZomblindActivity) _context).zombie = "derecha"; }
						if(a == 1){zombie2.start(); ((ZomblindActivity) _context).zombie = "izquierda"; }
						if(a == 2){zombie3.start(); ((ZomblindActivity) _context).zombie = "centro"; }
					}
				
				
				
				
//	        MediaPlayer mediaPlayer = MediaPlayer.create(_context,R.raw.mus_levelup_01);
//	        mediaPlayer.setLooping(false);
//	        
//	        MediaPlayer m2 = MediaPlayer.create(_context, R.raw.mus_levelup_02); 
//	        
//	        mediaPlayer.start();
//	        //m2.start();
//	        float t=0;
//	        boolean lado = true;
//	        while(mediaPlayer.isPlaying()){
//	        	mediaPlayer.setVolume(0+t,1-t);
//	            try {
//					Thread.sleep(100);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//	            if(lado){t = t + 0.01f;}else{t = t - 0.01f;}
//	            if(t>0.9){lado=false;}
//	            if(t<0.1){lado=false;}
//	        }
//			
		}}
		
	}
	
	
	entorno(Context contexto){
		_eventos = new Timer();
		_eventos.scheduleAtFixedRate(new tarea(contexto),TASK_DELAY,TASK_PERIOD);
	}
	
	
}
