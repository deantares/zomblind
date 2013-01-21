package antares.zomblind.in;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import antares.zomblind.ZomblindActivity;

public class pantalla {

	public float x = 50;
	public float y = 50;
	
	public String action ="";
	
	private ZomblindActivity _z;
	
	public pantalla(Context ctx){
		_z = (ZomblindActivity) ctx;
	}
	
	public void update(MotionEvent evento){
		
		synchronized (this) {
		
		action = String.valueOf(evento.getAction());
		
		if (evento.getAction() == MotionEvent.ACTION_DOWN) {
			x = evento.getX();
			y = evento.getY();
			Log.v("Entorno", x + "," + y);

			if (_z._orientacion.isCalibrate() == false) {
				_z._habladora.say("Dispositivo calibrado");
				_z._orientacion.calibrate();
			}
		} else if (evento.getAction() == MotionEvent.EDGE_BOTTOM) {
			x = evento.getX();
			y = evento.getY();
			Log.v("Entorno", x + "," + y);
		}
		}
	}
	
	
}
