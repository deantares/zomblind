package antares.zomblind.controles;

import android.content.Context;
import android.hardware.SensorEvent;

public class orientacion {

	Float azimut;
	Float original_azimut;
	Boolean calibrado = false;
	
	private Context _context;
	
	public orientacion(Context ctx){
		_context = ctx;
	}
	
	public orientacion(){
		
	}
	
	public Boolean isCalibrate(){
		return calibrado;
	}
	
	public void calibrate(SensorEvent event){
		synchronized (this) {
			original_azimut = event.values[0];
			calibrado = true;
		}
	}
		
	public void calibrate(){
			synchronized (this) {
				original_azimut = azimut;
				calibrado = true;
			}
			
			
		
		// orientation contains:
		// azimut, pitch and
		// roll}
	}
	
	public void update(SensorEvent event){
		synchronized (this) {
			azimut = event.values[0];
		}
		
	}
	
	public String toString(){
		return azimut.toString() + " Original(" + original_azimut.toString() + ")";
		
	}
	
	public String mirando(){
	 String aux2;
	 
	 float aux = (azimut - original_azimut) % 360;
		float aux_r = aux < 0 ? aux + 360 : aux;
		if ((aux_r) < 180 - 30) {
			return "izquierda";
		} else if ((aux_r) > 180 + 30) {
			return "derecha";
		} else {
			return "centro";
		}
	}
	
}
