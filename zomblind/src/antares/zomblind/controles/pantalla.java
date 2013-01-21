package antares.zomblind.controles;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import antares.zomblind.ZomblindActivity;

public class pantalla {

	public float x = 50;
	public float y = 50;
	
	public String action ="";
	
	private Context _context;
	
	public pantalla(Context ctx){
		_context = ctx;
	}
	
	public void update(MotionEvent evento){
		
		synchronized (this) {
		
		action = String.valueOf(evento.getAction());
		
		if (evento.getAction() == MotionEvent.ACTION_DOWN) {
			x = evento.getX();
			y = evento.getY();
			Log.v("Entorno", x + "," + y);

			if ((((ZomblindActivity) _context)._orientacion).isCalibrate() == false) {
				((ZomblindActivity) _context)._speaker.say("Dispositivo calibrado");
				((ZomblindActivity) _context)._orientacion.calibrate();
			}
		} else if (evento.getAction() == MotionEvent.EDGE_BOTTOM) {
			x = evento.getX();
			y = evento.getY();
			Log.v("Entorno", x + "," + y);
		}
		}
	}
	
	
}
