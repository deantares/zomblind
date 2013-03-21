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

public class Acelerometro {

	// Variables de sensores: Acelerómetro
	float last_update = 0, last_movement = 0;
	float prevX = 0, prevY = 0, prevZ = 0;
	float curX = 0, curY = 0, curZ = 0;
	
	long current_time;
	public short X = 0, Y = 0, Z = 0;
	boolean instanciado = false;
	
	//Degradación: Duración del movimiento en el tiempo. A más degradación menor duración. Mayor siempre de 0.
	//double degradacion = 1.05;
	double degradacion = 1.02;
	
	//Amplitud: Como de grande es el móvimiento.
	int amplitud_flojo = 100;
	int amplitud_fuerte = 200;
	
	//Umbrales
	int umbral_flojo = 50;
	int umbral_medio = 100;
	int umbral_fuerte = 150;

	// Sensibilidad del sensor de golpe fuerte. Cuanto "mayor" valor, mayor movimiento se necesitará para detectar un cambio
	float min_movement_fuerte =0.0000004f; // 2f ;//1E-6f;
	
	// Sensibilidad del sensor de golpe normal. Cuanto "mayor" valor, mayor movimiento se necesitará para detectar un cambio
	float min_movement_flojo = 0.0000003f; // 2f ;//1E-6f; //0.0000003f 

	//Contexto
	private ZomblindActivity _z;
	
	public Acelerometro(Context ctx){
		_z = (ZomblindActivity) ctx;
	}
	
	public Acelerometro() {

	}

	public Acelerometro(float[] values, long current_time) {

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
		
		synchronized (this) {
		
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

			if (movement_X > min_movement_fuerte) {
				X = (short) (direcion_X == true ? amplitud_fuerte : -amplitud_fuerte);
			}else if (movement_X > min_movement_flojo) {
				X = (short) (direcion_X == true ? amplitud_flojo : -amplitud_flojo);
			} else {
				X = (short) (X/degradacion);
			}

			float movement_Y = Math.abs(curY - prevY) / time_difference;
			boolean direcion_Y = curX > prevY ? true : false;

			if (movement_Y > min_movement_fuerte) {
				Y = (short) (direcion_Y == true ? amplitud_fuerte : -amplitud_fuerte);
			}else if (movement_Y > min_movement_flojo) {
				Y = (short) (direcion_Y == true ? amplitud_flojo : -amplitud_flojo);
			} else {
				Y = (short) (Y/degradacion);
			}
			float movement_Z = Math.abs(curZ - prevZ) / time_difference;
			boolean direcion_Z = curX > prevZ ? true : false;

			if (movement_Z > min_movement_fuerte) {
				Z = (short) (direcion_Z == true ? amplitud_fuerte : -amplitud_fuerte);
			}else if (movement_Z > min_movement_flojo) {
				Z = (short) (direcion_Z == true ? amplitud_flojo : -amplitud_flojo);
			} else {
				Z = (short) (Z/degradacion);
			}

			prevX = curX;
			prevY = curY;
			prevZ = curZ;
			last_update = current_time;
		}
		}

	}
	
	public String toString(){
		return X+":"+Y+":"+Z;
		
	}
	
	public boolean golpe_izquierda(){
		return X>umbral_flojo;
	}
	
	public boolean golpe_izquierda_fuerte(){
		return X>umbral_fuerte;
	}
	
	public boolean golpe_derecha(){
		return X<-umbral_flojo;
	}
	
	public boolean golpe_derecha_fuerte(){
		return X<-umbral_fuerte;
	}
	
	public boolean golpe_frente(){
		return Z>umbral_flojo;
	}
	
	public boolean golpe_frente_fuerte(){
		return Z>umbral_fuerte;
	}
	
	public boolean recargar(){
		return Y>umbral_flojo;
	}
	
	public boolean lanzar(){
		return Y<-umbral_medio;
	}
	
	public boolean esquivar(){
		return Z<-umbral_medio;
	}
	
	public boolean isgolpeando(){
		return (X>umbral_flojo||X<-umbral_flojo||Z<-umbral_flojo);
	}

}
