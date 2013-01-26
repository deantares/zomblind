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
import antares.zomblind.R.raw;
import antares.zomblind.ZomblindActivity;
import antares.zomblind.core.Armas.tipo_arma;
import antares.zomblind.core.npcs.npc;
import antares.zomblind.core.npcs.zombie;
import antares.zomblind.core.npcs.npc.tipo_npc;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.media.MediaPlayer;
import android.provider.ContactsContract.RawContacts;

public class Nucleo {

	// Variables del jugador;
	public Jugador _jugador;

	public Timer _eventos;
	private long TASK_DELAY = 1000;
	private long TASK_PERIOD = 2000;

	private ZomblindActivity _z;

	public npcLista _npcs = new npcLista();

	// Variables de dificultad
	// double _atenuancion_distancia_base = 2.5;
	// double _atenuancion_distancia_expo = -0.89;

	public double _atenuancion_distancia_base = 5;
	public double _atenuancion_distancia_expo = -0.8;

	// Variable control volumen
	public float _max_volume = (float) 0.5;
	public float _max_volume_inc = (float) 0.2;

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

						// Los enemigos atacan
						_npcs.ataque();
						_jugador._resistencia.regenerar();

						// Generamos enemigos
						if (a < 1) {
							// Generar zombie

							// public npc(Context ctx, tipo_npc _tipo,
							// int _pi_ataque, int _salud, int _rango_ataque,
							// int _armadura_cuerpo, int _armadura_distancia,
							// int _S_movimiento, int _S_ataque,
							// int _S_muerte, int _S_espcial, long[]
							// _vibra_patron) {

							_npcs.push(new npc(_z, tipo_npc.HOSTIL, 5, 10, 1,
									0, 0, raw.zombie_light_01_acercando,
									raw.zombie_light_01_ataque,
									raw.zombie_light_01_muerte, -1));

							// _npcs.push(new zombie(_z ));
						}

						// Los acercamos
						_npcs.acercar();

						// Reproducimos el audio
						_npcs.play();

					}
				}
			}
		}

	}

	private class tarea_comprobacion extends TimerTask {
		int a = 0;

		public tarea_comprobacion(Context contexto) {
			// TODO Auto-generated constructor stub
			_z = (ZomblindActivity) contexto;

		}

		@Override
		public void run() {

			// Miramos que eventos de control se han activado;

			if (_z.empezar == true) {

				if (_z._acelerometro.golpe_izquierda()) {
					_z._entorno._jugador._armas.getArma(tipo_arma.CUERPO)
							.usar();
					_z._entorno._npcs.atacar(0, _z._entorno._jugador._armas
							.getArma(tipo_arma.CUERPO));
				}

				if (_z._acelerometro.golpe_frente()) {
					_z._entorno._jugador._armas.getArma(tipo_arma.CUERPO)
							.usar();
					_z._entorno._npcs.atacar(1, _z._entorno._jugador._armas
							.getArma(tipo_arma.CUERPO));
				}

				if (_z._acelerometro.golpe_derecha()) {
					_z._entorno._jugador._armas.getArma(tipo_arma.CUERPO)
							.usar();
					_z._entorno._npcs.atacar(2, _z._entorno._jugador._armas
							.getArma(tipo_arma.CUERPO));
				}

				if (_z._acelerometro.recargar()) {
					_z._entorno._jugador._armas.getArma(tipo_arma.DISTANCIA)
							.recargar();
				}

				int zona_disparo = _z._pantalla.isDisparando();

				if (zona_disparo >= 0) {
					if (_z._entorno._jugador._armas
							.getArma(tipo_arma.DISTANCIA).usar()) {

						_z._entorno._npcs.atacar(zona_disparo,
								_z._entorno._jugador._armas
										.getArma(tipo_arma.DISTANCIA));
					}

				}
			}
		}

	}

	public Nucleo(Context contexto) {
		_eventos = new Timer();
		_eventos.scheduleAtFixedRate(new tarea_generacion(contexto),
				TASK_DELAY, TASK_PERIOD);
		_eventos.scheduleAtFixedRate(new tarea_comprobacion(contexto),
				TASK_DELAY, TASK_PERIOD / 4);

		// zombie1 = MediaPlayer.create(_z, R.raw.zombie01);
		// zombie2 = MediaPlayer.create(_z, R.raw.zombie01);
		// zombie3 = MediaPlayer.create(_z, R.raw.zombie01);
		//
		// zombie1.setLooping(false);
		// zombie2.setLooping(false);
		// zombie3.setLooping(false);
		//
		// zombie1.setVolume(0, 1);
		// zombie2.setVolume(1, 0);
		// zombie3.setVolume(1, 1);

		_jugador = new Jugador(_z);

	}

}
