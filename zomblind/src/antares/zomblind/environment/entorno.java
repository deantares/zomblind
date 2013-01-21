package antares.zomblind.environment;


import antares.zomblind.R;
import antares.zomblind.ZomblindActivity;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.media.MediaPlayer;

public class entorno {
	public Timer _eventos;
	private long TASK_DELAY = 1000;
	private long TASK_PERIOD = 2000;
	
	private Context _context;
	ZomblindActivity _z;
	
	private class tarea extends TimerTask{

		public tarea(Context contexto) {
			// TODO Auto-generated constructor stub
			_context = contexto;
			_z = (ZomblindActivity) contexto;

		}

		@Override
		public void run() {

			if(_z._orientacion.isCalibrate()==true){
				
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
						int a = ale.nextInt(10);
						if(a == 0){zombie1.start(); ((ZomblindActivity) _context).zombie = "derecha"; }
						if(a == 1){zombie2.start(); ((ZomblindActivity) _context).zombie = "izquierda"; }
						if(a == 2){zombie3.start(); ((ZomblindActivity) _context).zombie = "centro"; }
						if(a == 4){_z._habladora.say("Hola Rafa");}
						if(a == 5){_z._habladora.say("Hola Flan");}
						if(a == 6){_z._habladora.say("Hola Ana");}
					}		
		}}
		
	}
	
	
	public entorno(Context contexto){
		_eventos = new Timer();
		_eventos.scheduleAtFixedRate(new tarea(contexto),TASK_DELAY,TASK_PERIOD);
	}
	
	
}
