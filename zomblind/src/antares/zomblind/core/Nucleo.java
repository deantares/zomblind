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
package antares.zomblind.core;


import antares.zomblind.ZomblindActivity;
import antares.zomblind.core.items.Armas.tipo_arma;
import antares.zomblind.core.npcs.DataNPCs;
import antares.zomblind.core.npcs.npc;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;

public interface Nucleo {

	// Variables del jugador;
	
	class data_nucleo{
	public static Jugador _jugador = null;
	public static Timer _eventos = null;
	public static npcLista _npcs = new npcLista();
	}
	
	data_nucleo _datos = new data_nucleo();
	
	//Object _jugador = null;
	
	long TASK_DELAY = 2000;
	long TASK_PERIOD = 5000;

	ZomblindActivity _z = null;

	

	// Variables de dificultad
	//public double _atenuancion_distancia_base = 5;
	public double _atenuancion_distancia_base = 5;
	public double _atenuancion_distancia_expo = -0.8;

	// Variable control volumen
	public float _max_volume = (float) 2;
	public float _max_volume_inc = (float) 0.2;

	

	class tarea_generacion extends TimerTask {
		ZomblindActivity _z;

		public tarea_generacion(Context contexto) {
			// TODO Auto-generated constructor stub
			_z = (ZomblindActivity) contexto;
		}

		@Override
		public void run() {
			if (_z.empezar == true) {
				if (_z._orientacion.isCalibrate() == true) {
					if (!_z._talker.isSpeaking()) {
						//int a = ale.nextInt(10);

						// Los enemigos atacan
						data_nucleo._npcs.ataque();
						data_nucleo._jugador._resistencia.regenerar();

//						// Generamos enemigos
//						if (a < 5) {
//							int b = ale.nextInt(20);
//							if(b==1){data_nucleo._npcs.push(new npc(_z,DataNPCs._abogado));}
//							else if(b==2){data_nucleo._npcs.push(new npc(_z,DataNPCs._alemana));}
//							else if(b==3){data_nucleo._npcs.push(new npc(_z,DataNPCs._arrastrao));}
//							else if(b==4){data_nucleo._npcs.push(new npc(_z,DataNPCs._carnavalera));}
//							else if(b==5){data_nucleo._npcs.push(new npc(_z,DataNPCs._cobarde));}
//							else if(b==6){data_nucleo._npcs.push(new npc(_z,DataNPCs._duquesa));}
//							else if(b==7){data_nucleo._npcs.push(new npc(_z,DataNPCs._fotografo));}
//							else if(b==8){data_nucleo._npcs.push(new npc(_z,DataNPCs._hipster));}
//							else if(b==9){data_nucleo._npcs.push(new npc(_z,DataNPCs._lazaro));}
//							else if(b==10){data_nucleo._npcs.push(new npc(_z,DataNPCs._lord_ferguson));}
//							else if(b==11){data_nucleo._npcs.push(new npc(_z,DataNPCs._mariquieta));}
//							else if(b==12){data_nucleo._npcs.push(new npc(_z,DataNPCs._novia));}
//							else if(b==13){data_nucleo._npcs.push(new npc(_z,DataNPCs._olakase));}
//							else if(b==14){data_nucleo._npcs.push(new npc(_z,DataNPCs._oveja));}
//							else if(b==15){data_nucleo._npcs.push(new npc(_z,DataNPCs._pecosete));}
//							else if(b==16){data_nucleo._npcs.push(new npc(_z,DataNPCs._periodista));}
//							else if(b==17){data_nucleo._npcs.push(new npc(_z,DataNPCs._perrete));}}
//							else {data_nucleo._npcs.push(new npc(_z,DataNPCs._light));}
//						}

						// Los acercamos
					data_nucleo._npcs.acercar();

						// Reproducimos el audio
					data_nucleo._npcs.play();

					}
				}
			}

	}

	class tarea_comprobacion extends TimerTask {
		
		ZomblindActivity _z;
		
		public tarea_comprobacion(Context contexto) {
			// TODO Auto-generated constructor stub
			_z = (ZomblindActivity) contexto;

		}

		@Override
		public void run() {
			// Miramos que eventos de control se han activado;
			if (_z.empezar == true) {

				if (_z._acelerometro.golpe_izquierda()) {
					data_nucleo._jugador._armas.getArma(tipo_arma.CUERPO).usar();
					data_nucleo._npcs.atacar(0, data_nucleo._jugador._armas.getArma(tipo_arma.CUERPO));
				}

				if (_z._acelerometro.golpe_frente()) {
					data_nucleo._jugador._armas.getArma(tipo_arma.CUERPO)
							.usar();
					data_nucleo._npcs.atacar(1, data_nucleo._jugador._armas
							.getArma(tipo_arma.CUERPO));
				}

				if (_z._acelerometro.golpe_derecha()) {
					data_nucleo._jugador._armas.getArma(tipo_arma.CUERPO)
							.usar();
					data_nucleo._npcs.atacar(2, data_nucleo._jugador._armas
							.getArma(tipo_arma.CUERPO));
				}

				if (_z._acelerometro.recargar()) {
					data_nucleo._jugador._armas.getArma(tipo_arma.DISTANCIA)
							.recargar();
				}

				int zona_disparo = _z._pantalla.isDisparando();

				if (zona_disparo >= 0) {
					if (data_nucleo._jugador._armas
							.getArma(tipo_arma.DISTANCIA).usar()) {

						data_nucleo._npcs.atacar(zona_disparo,
								data_nucleo._jugador._armas
										.getArma(tipo_arma.DISTANCIA));
					}

				}
			}
		}

	}

//	public Nucleo(Context contexto) {
//		//_eventos = new Timer();
//		//_eventos.scheduleAtFixedRate(new tarea_generacion(contexto),
//		//		TASK_DELAY, TASK_PERIOD);
//		//_eventos.scheduleAtFixedRate(new tarea_comprobacion(contexto),
//		//		TASK_DELAY, TASK_PERIOD / 20);
//
//		_jugador = new Jugador(_z);
//		_z = (ZomblindActivity) contexto;
//
//	}

	}
}
