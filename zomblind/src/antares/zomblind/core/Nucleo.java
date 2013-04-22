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
import antares.zomblind.core.levels.NivelInfo;
import antares.zomblind.core.npcs.NpcLista;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

public class Nucleo {

	// Variables del jugador;
	public Jugador _jugador = null;
	public Timer _eventos = null;
	public NpcLista _npcs = null;

	// Object _jugador = null;

	long TASK_DELAY = 2000;
	long TASK_PERIOD = 5000;

	public ZomblindActivity _z = null;

	// Variables de dificultad
	// public double _atenuancion_distancia_base = 5;
	public double _atenuancion_distancia_base = 5;
	public double _atenuancion_distancia_expo = -0.8;

	// Variable control volumen
	public float _max_volume = (float) 2;
	public float _max_volume_inc = (float) 0.2;

	//Información del nivel
	public NivelInfo _l;
	
	//Sonidos auxiliares
	protected MediaPlayer _S_jugador = null;
	protected MediaPlayer _S_entorno = null;
	
	protected MediaPlayer _S_main = null;
	protected MediaPlayer _S_aux = null;

	class tarea_generacion extends TimerTask {
		ZomblindActivity _z;

		public tarea_generacion(Context contexto) {
			_z = (ZomblindActivity) contexto;
		}

		@Override
		public void run() {
			if (_z.empezar == true) {
				if (_z._orientacion.isCalibrate() == true) {
					if (!_z._talker.isSpeaking()) {

						_z._habladora.decir(_l.get_mensaje());
						Log.i("Generador", "Generando");
						try {
							_l.run_generate();
							
						} catch (Exception e) {
							Log.e("Excepción generate", e.toString());
						}
						
						// Los enemigos atacan
						_npcs.ataque();
						_jugador._resistencia.regenerar();

						// Los acercamos
						_npcs.acercar();

						// Reproducimos el audio
						_npcs.play();
						
						if(_S_aux!=null){_S_aux.start();}

					}
				}
			}

		}
	}
		class tarea_comprobacion extends TimerTask {

			ZomblindActivity _z;

			public tarea_comprobacion(Context contexto) {
				_z = (ZomblindActivity) contexto;

			}

			@Override
			public void run() {
				// Miramos que eventos de control se han activado;
				if (_z.empezar == true) {
					boolean t=false;
					Log.i("Generador", "Generando");
					try {
						_l.run_check();
						t = _l.run_condition();
						
						
					} catch (Exception e) {
						Log.e("Excepción generate", e.toString());
					}
					
					//if(t){_l.next();}

				}
			}

		}

	public Nucleo(Context contexto) {
		_z = (ZomblindActivity) contexto;
		_jugador = new Jugador(_z);
		_npcs = new NpcLista();
		_eventos = new Timer("Nivel 000");
		
		//_l = new NivelInfo(_z);
		_l = new NivelInfo(_z,"level00.xml");
				
		_z._habladora.decir("Cargando");
		_l.push("", "SinZombies"  , "AleatorioFlojos", "Todos" );
		_l.push("Bien hecho", "_conditions_all"  , "" , "");
		_l.push("Final del juego", ""  , "", "" );
		
		_eventos.scheduleAtFixedRate(new tarea_generacion(contexto),
				TASK_DELAY, TASK_PERIOD);
		_eventos.scheduleAtFixedRate(new tarea_comprobacion(contexto),
				TASK_DELAY, TASK_PERIOD / 20);

		

	}
	
}
