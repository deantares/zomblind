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

public class Orientacion {

	public Float azimut = 0f;
	public Float original_azimut =0f;
	Boolean calibrado = false;
	
	//Zona 0 - izquierda ,1 - centro ,2 - derecha
	
	int _zona_mirando=-1;
	
	ZomblindActivity _z;
	
	public Orientacion(Context ctx){
		_z = (ZomblindActivity) ctx;
	}
	
	public Orientacion(){
		
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
			
		// orientation contains: azimut, pitch and roll}
	}
	
	public void update(SensorEvent event){
		synchronized (this) {
			azimut = event.values[0];
		}
		
		float aux = (azimut - original_azimut + 180) % 360;
		
		 
		 int distancia_umbral = 30;
		 
		 if(aux < 180-distancia_umbral){
			_zona_mirando =0;
		 }else if (aux < 180+distancia_umbral ){
			 _zona_mirando = 1;
		 }else{
			 _zona_mirando = 2;
		 }
		
	}
	
	public String toString(){
		return azimut.toString() + " Original(" + original_azimut.toString() + ")";
		
	}
	
	public String mirando(){	 
		//return Float.toString((aux < 0 ? aux + 360 : aux) % 360);
	 float aux = (azimut - original_azimut + 180) % 360;
	 //return Float.toString(aux);
	 
	 int distancia_umbral = 30;
	 
	 if(aux < 180-distancia_umbral){
		 return "izquierda";
	 }else if (aux < 180+distancia_umbral ){
		 return "centro";
	 }else{
		 return "derecha";
	 }
	 
//		float aux_r = aux < 0 ? aux + 360 : aux;
//		if ((aux_r) < 180 - 30) {
//			return "izquierda";
//		} else if ((aux_r) > 180 + 30) {
//			return "derecha";
//		} else {
//			return "centro";
//		}
	}
	
}
