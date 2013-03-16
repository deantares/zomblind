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
import android.view.Display;
import android.view.MotionEvent;
import antares.zomblind.ZomblindActivity;
import antares.zomblind.core.Nucleo.data_nucleo;
import antares.zomblind.core.items.Armas.tipo_arma;

public class pantalla {

	public float x = 50;
	public float y = 50;

	public float x0;
	public float y0;

	public int width;
	public int height;

	public String action = "";

	private ZomblindActivity _z;

	public pantalla(Context ctx) {
		_z = (ZomblindActivity) ctx;
		Display display = _z.getWindowManager().getDefaultDisplay();
		width = display.getWidth();
		height = display.getHeight();

	}

	public int zona(float x, float y) {
		if (y < height * 0.25) {
			return 1;

		} else if (y < height * 0.75) {
			if (x < width * 0.25){
				return 3;
			}else if(x < width * 0.75){
				return 0;
			}else {
				return 4;
			}
			

		} else {
			return 2;

		}

	}
	
	public int isDisparando(){
		if(action == "disparo"){
			action = "";
			return _z._orientacion._zona_mirando;
		}else{
			return -1;
		}

	}

	public void update(MotionEvent evento) {

		if (_z.empezar == false) {
			_z.empezar = true;
		} else {

			if (evento.getAction() == MotionEvent.ACTION_DOWN) {
				x = evento.getX();
				y = evento.getY();

				x0 = x;
				y0 = y;
			} else if (evento.getAction() == MotionEvent.ACTION_UP) {
				x = evento.getX();
				y = evento.getY();

				if (zona(x,y)== 0 && zona(x0,y0)==0){
					action ="disparo";
					
					//Codigo del disparo
				}else if(zona(x,y)== 1 && zona(x0,y0)==0){
					action = "info_jugador";
					data_nucleo._jugador.info();
					//Codigo de la información del jugador
					
				}else if(zona(x,y)== 2 && zona(x0,y0)==0){
					action = "info_armas";
					//Información del arma
					
				}else if(zona(x,y)== 3 && zona(x0,y0)==0){
					action = "cambio_arma_cuerpo";
					
					//Codigo del cambio de arma cuerpo a cuerpo
					
				}else if(zona(x,y)== 4 && zona(x0,y0)==0){
					action = "cambio_arma_distancia";
					
					//Codigo del cambio de arma a distancia
					
				}else if(zona(x0,y0)== 4 && zona(x,y)==4){
					data_nucleo._jugador._armas.next(tipo_arma.DISTANCIA);
					_z._habladora.say(data_nucleo._jugador._armas.getArma(tipo_arma.DISTANCIA)._name);
				}else if(zona(x0,y0)== 3 && zona(x,y)==3){
					data_nucleo._jugador._armas.next(tipo_arma.CUERPO);
					_z._habladora.say(data_nucleo._jugador._armas.getArma(tipo_arma.CUERPO)._name);
				}
				
			}

//			synchronized (this) {

				// Detectamos eventos

//				action = String.valueOf(evento.getAction());
//				if (evento.getAction() == MotionEvent.ACTION_DOWN) {
//					x = evento.getX();
//					y = evento.getY();
//					Log.v("Entorno", x + "," + y);
//
//					if (_z._orientacion.isCalibrate() == false) {
//						_z._habladora.say("Dispositivo calibrado");
//						_z._orientacion.calibrate();
//					}
//				} else if (evento.getAction() == MotionEvent.EDGE_BOTTOM) {
//					x = evento.getX();
//					y = evento.getY();
//					Log.v("Entorno", x + "," + y);
//				}
//			}
		}
	}

}
