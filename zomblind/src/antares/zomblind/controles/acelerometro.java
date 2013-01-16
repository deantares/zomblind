package antares.zomblind.controles;

import android.hardware.SensorEvent;

public class acelerometro {

	// Variables de sensores: Acelerómetro
	float last_update = 0, last_movement = 0;
	float prevX = 0, prevY = 0, prevZ = 0;
	float curX = 0, curY = 0, curZ = 0;
	String res_acelerometro = "";
	String res_acelerometroX = "";
	String res_acelerometroY = "";
	String res_acelerometroZ = "";
	String res_acelerometro_anterior = "";
	
	long current_time;
	public short X=0,Y=0,Z=0;
	boolean instanciado = false;

	public acelerometro(){
		
	}
	
	public acelerometro(float[] values, long current_time){

		curX = values[0];
		curY = values[1];
		curZ = values[2];
		
		this.current_time = current_time;

		if (prevX == 0 && prevY == 0 && prevZ == 0) {
			last_update = current_time;
			last_movement = current_time;
			prevX = curX;
			prevY = curY;
			prevZ = curZ;
		}
	}
	
	public void update(SensorEvent event){
		curX = event.values[0];
		curY = event.values[1];
		curZ = event.values[2];
		
		this.current_time=event.timestamp;
		
		if(!instanciado){
			last_update = current_time;
			last_movement = current_time;
			
			prevX = curX;
			prevY = curY;
			prevZ = curZ;
		}
		
		
	}
	
	
	
}
