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
	public short X = 0, Y = 0, Z = 0;
	boolean instanciado = false;
	
	//Degradación: Duración del movimiento en el tiempo. A más degradación menor duración. Mayor siempre de 0.
	double degradacion = 1.05;
	
	//Amplitud: Como de grande es el móvimiento.
	int amplitud = 100;

	// Sensibilidad del sensor. Cuanto "mayor" valor, mayor movimiento se necesitará para detectar un cambio
	float min_movement = 0.0000003f; // 2f ;//1E-6f;

	public acelerometro() {

	}

	public acelerometro(float[] values, long current_time) {

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

	public void update(SensorEvent event) {
		curX = event.values[0];
		curY = event.values[1];
		curZ = event.values[2];

		this.current_time = event.timestamp;

		if (instanciado==false) {
			last_update = current_time;
			last_movement = current_time;

			prevX = curX;
			prevY = curY;
			prevZ = curZ;
			
			instanciado=true;
		}

		long time_difference = (long) (current_time - last_update);
		
		if (time_difference > 0) {
			float movement_X = Math.abs(curX - prevX) / time_difference;
			boolean direcion_X = curX > prevX ? true : false;

			if (movement_X > min_movement) {
				X = (short) (direcion_X == true ? 100 : -100);
			} else {
				X = (short) (X/degradacion);
			}

			float movement_Y = Math.abs(curY - prevY) / time_difference;
			boolean direcion_Y = curX > prevY ? true : false;

			if (movement_Y > min_movement) {
				Y = (short) (direcion_Y == true ? 100 : -100);
			} else {
				Y = (short) (Y/degradacion);
			}
			float movement_Z = Math.abs(curZ - prevZ) / time_difference;
			boolean direcion_Z = curX > prevZ ? true : false;

			if (movement_Z > min_movement) {
				Z = (short) (direcion_Z == true ? 100 : -100);
			} else {
				Z = (short) (Z/degradacion);
			}

			prevX = curX;
			prevY = curY;
			prevZ = curZ;
			last_update = current_time;
		}

	}
	
	public String toString(){
		return X+":"+Y+":"+Z;
		
	}

}
