/*******************************************************************************
 * Copyright 2013 Antonio Fern�ndez Ares (antares.es@gmail.com)
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

import antares.zomblind.R;
import antares.zomblind.ZomblindActivity;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.media.MediaPlayer;

public class entorno {

	// Variables del jugador;
	public Jugador _jugador;

	public Timer _eventos;
	private long TASK_DELAY = 1000;
	private long TASK_PERIOD = 2000;

	private ZomblindActivity _z;

	public npcLista _npcs = new npcLista();
	
	MediaPlayer zombie1;
	MediaPlayer zombie2;
	MediaPlayer zombie3;
	
	//Variables de dificultad
	double _atenuancion_distancia = 0.55;
	double _max_volumen = 2;

	Random ale = new Random();

	private class tarea_generacion extends TimerTask {

		public tarea_generacion(Context contexto) {
			// TODO Auto-generated constructor stub
			_z = (ZomblindActivity) contexto;

		}

		@Override
		public void run() {
			if (_z.empezar == true) {
				if (_z._orientacion.isCalibrate() == true) {
					if (!_z._talker.isSpeaking()) {
						int a = ale.nextInt(10);

//						
//						if (a == 0) {
//							zombie1.start();
//							_z.zombie = "derecha";
//						}
//						if (a == 1) {
//							zombie2.start();
//							_z.zombie = "izquierda";
//						}
//						if (a == 2) {
//							zombie3.start();
//							_z.zombie = "centro";
//						}
						// if(a == 4){_z._habladora.say("Hola Rafa");
						// _z.zombie="amigo";}
						// if(a == 5){_z._habladora.say("Hola Flan");
						// _z.zombie="amigo";}
						// if(a == 6){_z._habladora.say("Hola Ana");
						// _z.zombie="amigo";}
					}
				}
			}
		}

	}

	private class tarea_comprobacion extends TimerTask {

		public tarea_comprobacion(Context contexto) {
			// TODO Auto-generated constructor stub
			_z = (ZomblindActivity) contexto;

		}

		@Override
		public void run() {

			if (_z.empezar == true) {			

				if (_z.zombie == "izquierda"
						&& _z._acelerometro.golpe_izquierda()) {
					_z.zombie = "Muerto";
					_z._habladora.say("�Si!");
				} else if (_z.zombie == "centro"
						&& _z._acelerometro.golpe_frente()) {
					_z.zombie = "Muerto";
					_z._habladora.say("�Si!");
				} else if (_z.zombie == "derecha"
						&& _z._acelerometro.golpe_derecha()) {
					_z.zombie = "Muerto";
					_z._habladora.say("�Si!");
				} else if (_z.zombie == "amigo"
						&& _z._acelerometro.isgolpeando()) {
					_z.zombie = "Nooooooooooooooooooooooooo";
					_z._habladora.say("Fallo");
				}

			}
		}

	}

	public entorno(Context contexto) {
		_eventos = new Timer();
		_eventos.scheduleAtFixedRate(new tarea_generacion(contexto),
				TASK_DELAY, TASK_PERIOD);
		_eventos.scheduleAtFixedRate(new tarea_comprobacion(contexto),
				TASK_DELAY, TASK_PERIOD / 10);

		zombie1 = MediaPlayer.create(_z, R.raw.zombie01);
		zombie2 = MediaPlayer.create(_z, R.raw.zombie01);
		zombie3 = MediaPlayer.create(_z, R.raw.zombie01);

		zombie1.setLooping(false);
		zombie2.setLooping(false);
		zombie3.setLooping(false);

		zombie1.setVolume(0, 1);
		zombie2.setVolume(1, 0);
		zombie3.setVolume(1, 1);

		_jugador = new Jugador(_z);

	}

}
