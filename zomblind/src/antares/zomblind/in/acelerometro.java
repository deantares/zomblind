/*******************************************************************************
 * Copyright 2013 Antonio Fernández Ares (antares.es@gmail.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package antares.zomblind.in;

import android.content.Context;
import android.hardware.SensorEvent;
import antares.zomblind.ZomblindActivity;

public class acelerometro {

	// Variables de sensores: Acelerómetro
	float last_update = 0, last_movement = 0;
	float prevX = 0, prevY = 0, prevZ = 0;
	float curX = 0, curY = 0, curZ = 0;
	
	long current_time;
	public short X = 0, Y = 0, Z = 0;
	boolean instanciado = false;
	
	//Degradación: Duración del movimiento en el tiempo. A más degradación menor duración. Mayor siempre de 0.
	double degradacion = 1.05;
	
	//Amplitud: Como de grande es el móvimiento.
	int amplitud = 100;

	// Sensibilidad del sensor. Cuanto "mayor" valor, mayor movimiento se necesitará para detectar un cambio
	float min_movement = 0.0000003f; // 2f ;//1E-6f;

	//Contexto
	private ZomblindActivity _z;
	
	public acelerometro(Context ctx){
		_z = (ZomblindActivity) ctx;
	}
	
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
	
	public boolean golpe_izquierda(){
		return X>50;
	}
	
	public boolean golpe_derecha(){
		return X<-50;
	}
	
	public boolean golpe_frente(){
		return Z<-50;
	}
	
	public boolean isgolpeando(){
		return (X>50||X<-50||Z<-50);
	}

}
