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
package antares.zomblind.core.levels;

import antares.zomblind.ZomblindActivity;
import antares.zomblind.core.*;
import antares.zomblind.core.items.Armas;
import antares.zomblind.core.items.Armas.tipo_arma;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.widget.Toast;

public class L000_menu implements Nucleo{

	// Variables del jugador;
	public Jugador _jugador;
	public Timer _eventos;
	
	private long TASK_DELAY = 2000;
	private long TASK_PERIOD = 5000;

	private ZomblindActivity _z;

	public npcLista _npcs = new npcLista();

	// Variables de dificultad
	//public double _atenuancion_distancia_base = 5;
	public double _atenuancion_distancia_base = 5;
	public double _atenuancion_distancia_expo = -0.8;

	// Variable control volumen
	public float _max_volume = (float) 2;
	public float _max_volume_inc = (float) 0.2;

	Random ale = new Random();
	
	//Variable que controla la informaci�n del juego
	
//	public info_level _l = new info_level(new ArrayList<info_level.atomic_level>(){{
//		add(new atomic_level("Bienvenido a zomblind", new _conditions_all(_z) , new _generate_random(_z)));
//		add(new atomic_level("Bienvenido a zomblind", new _conditions_all(_z) , new _generate_random(_z)));
//	}});
	
	public info_level _l = new info_level();
	
		//_l._l.push(new atomic_level("Bienvenido a zomblind", new _conditions_all(_z) , new _generate_random(_z)));
		//atomic_level b = new atomic_level();

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

						// Los enemigos atacan
						_npcs.ataque();
						_jugador._resistencia.regenerar();

						// Generamos enemigos
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
			// Miramos que eventos de control se han activado;
			if (_z.empezar == true) {

				if (_z._acelerometro.golpe_izquierda()) {
					_jugador._armas.getArma(tipo_arma.CUERPO)
							.usar();
					_npcs.atacar(0, _jugador._armas
							.getArma(tipo_arma.CUERPO));
				}

				if (_z._acelerometro.golpe_frente()) {
					_jugador._armas.getArma(tipo_arma.CUERPO).usar();
					_npcs.atacar(1, _jugador._armas.getArma(tipo_arma.CUERPO));
				}

				if (_z._acelerometro.golpe_derecha()) {
					_jugador._armas.getArma(tipo_arma.CUERPO)
							.usar();
					_npcs.atacar(2, _jugador._armas
							.getArma(tipo_arma.CUERPO));
				}

				if (_z._acelerometro.recargar()) {
					_jugador._armas.getArma(tipo_arma.DISTANCIA)
							.recargar();
				}

				int zona_disparo = _z._pantalla.isDisparando();

				if (zona_disparo >= 0) {
					if (_jugador._armas
							.getArma(tipo_arma.DISTANCIA).usar()) {

						_npcs.atacar(zona_disparo,
								_jugador._armas
										.getArma(tipo_arma.DISTANCIA));
					}

				}
			}
		}

	}

	public L000_menu(Context contexto) {
		_z = (ZomblindActivity) contexto;
		data_nucleo._jugador = new Jugador(_z);
		
		_z._habladora.say("Cargando");
		_l.push("Bienvenido a zomblind", "_c_all"  , "_g_random" );
		
		_eventos = new Timer();
		_eventos.scheduleAtFixedRate(new tarea_generacion(contexto),
				TASK_DELAY, TASK_PERIOD);
		_eventos.scheduleAtFixedRate(new tarea_comprobacion(contexto),
				TASK_DELAY, TASK_PERIOD / 20);

		

	}

}