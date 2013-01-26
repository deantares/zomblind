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

import antares.zomblind.R;
import antares.zomblind.ZomblindActivity;
import antares.zomblind.core.npcs.zombie;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.media.MediaPlayer;

public class Nucleo {

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
//	double _atenuancion_distancia_base = 2.5;
//	double _atenuancion_distancia_expo = -0.89;
	
	public double _atenuancion_distancia_base = 5;
	public double _atenuancion_distancia_expo = -0.8;
	

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
						
						//Los enemigos atacan
						_npcs.atacar();
						
						//Generamos enemigos
						if(a<5){
							//Generar zombie
							_npcs.push(new zombie(_z));
						}
						
						//Los acercamos
						_npcs.acercar();
						
						//Reproducimos el audio
						_npcs.play();
						
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
					_z._habladora.say("¡Si!");
					_z._vibrador.vibrar_golpe();
				} else if (_z.zombie == "centro"
						&& _z._acelerometro.golpe_frente()) {
					_z.zombie = "Muerto";
					_z._habladora.say("¡Si!");
					_z._vibrador.vibrar_golpe();
				} else if (_z.zombie == "derecha"
						&& _z._acelerometro.golpe_derecha()) {
					_z.zombie = "Muerto";
					_z._habladora.say("¡Si!");
					_z._vibrador.vibrar_golpe();
				} else if (_z.zombie == "amigo"
						&& _z._acelerometro.isgolpeando()) {
					_z.zombie = "Nooooooooooooooooooooooooo";
					_z._habladora.say("Fallo");
					_z._vibrador.vibrar(5000);
				}

			}
		}

	}

	public Nucleo(Context contexto) {
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
