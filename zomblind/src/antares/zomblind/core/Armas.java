package antares.zomblind.core;

import java.util.ArrayList;

import android.content.Context;
import android.media.MediaPlayer;
import antares.zomblind.R;
import antares.zomblind.ZomblindActivity;

public class Armas {

	public enum tipo_arma {
		NULO, CUERPO, DISTANCIA, ARROJADIZA, ESPECIAL
	}

	public enum tipo_arma_recargar {
		NULO, AUTO, MANUAl
	}

	public class Arma {

		private ZomblindActivity _z;

		public String _name = "";
		public int _alcance;
		public int _dano;
		public tipo_arma _tipo;
		public tipo_arma_recargar _tipo_recargar;
		public int _cansancio;

		// Munición
		public int _arma_municion;
		public int _arma_municion_maxima;
		public int _municion;
		public int _municion_maxima;

		// Estado del arma
		public double _estado;

		public MediaPlayer _S_ataque;
		public MediaPlayer _S_recargar;

		// Aún sin usar
		double _prob_critico;
		double _cantidad_critico;

		//Constructor para arma "vacia"
		public Arma(Context ctx, String name) {
			_z = (ZomblindActivity) ctx;
				//this(_z, "Puños", 2, 12, tipo_arma.CUERPO,tipo_arma_recargar.NULO, 0, 0, 0, 0, 1, 5, 0, 0,R.raw.arma_punos, -1);
			
			this._z = (ZomblindActivity) ctx;
			this._name = name;
			this._alcance = 0;
			this._dano = 0;
			this._tipo = tipo_arma.NULO;
			this._tipo_recargar = tipo_arma_recargar.NULO;
			this._arma_municion = 1;
			this._estado = 0;
			this._cansancio = 0;
			
			//this(ctx, "Ninguna");
			
//			this._lista.add(1, new Arma);
//			this._lista.add(new Arma(_z, "Pistola 9mm", 6, 12, tipo_arma.DISTANCIA,
//					tipo_arma_recargar.AUTO, 15, 15, 30, 90, 1, 5, 0, 0,
//					R.raw.arma_pistola9mm_disparo, R.raw.arma_pistola9mm_recargar));

			
		}

		public Arma(Context ctx, String _name, int _alcance, int _dano,
				tipo_arma _tipo, tipo_arma_recargar _recargar,
				int _arma_municion, int _arma_max_municion, int _municion,
				int _municion_max, double _estado, int _peso,
				double _prob_critico, double _cantidad_critico, int s_uso,
				int s_recargar) {

			this._z = (ZomblindActivity) ctx;
			this._name = _name;
			this._alcance = _alcance;
			this._dano = _dano;
			this._tipo = _tipo;
			this._tipo_recargar = _recargar;
			this._arma_municion = _arma_municion;
			this._arma_municion_maxima = _arma_max_municion;

			this._municion = _municion;
			this._municion_maxima = _municion_max;
			this._estado = _estado;
			this._cansancio = _peso;
			this._prob_critico = _prob_critico;
			this._cantidad_critico = _cantidad_critico;

			this._S_ataque = MediaPlayer.create(_z, s_uso);
			this._S_ataque.setLooping(false);
			if (s_recargar != -1){
				this._S_recargar = MediaPlayer.create(_z, s_recargar);
				this._S_recargar.setLooping(false);
			}

		}

		@Override
		public String toString() {
			return "[" + _name + " " + _alcance + "m " + _dano + "ps " + _tipo
					+ " " + _arma_municion + "/" + _arma_municion_maxima + " "
					+ _estado * 100 + "% " + _cansancio + "Kg " + _prob_critico
					+ "%C (+" + _cantidad_critico + ")]";
		}

		public boolean usar() {
			if (_tipo == tipo_arma.DISTANCIA) {
				if (_arma_municion > 0) {
					this._arma_municion--;
					_S_ataque.setVolume(_z._entorno._max_volume,_z._entorno._max_volume);
					_S_ataque.start();
					return true;
				} else {
					return false;
				}
			} else if (_tipo == tipo_arma.CUERPO) {
				_S_ataque.setVolume(_z._entorno._max_volume,_z._entorno._max_volume);
				_S_ataque.start();
				_z._entorno._jugador._resistencia.restar(this._cansancio);
			}
			return true;

		}

		public void recargar() {
			if (_tipo == tipo_arma.DISTANCIA) {
				if (_tipo_recargar == tipo_arma_recargar.AUTO) {
					if (_arma_municion < _arma_municion_maxima && _municion > 0) {
						_S_recargar.setVolume(_z._entorno._max_volume,_z._entorno._max_volume);
						_S_recargar.start();
						int a = _arma_municion_maxima - _arma_municion;
						if (a > _municion) {
							_arma_municion = _arma_municion + _municion;
							_municion = 0;
						} else {
							_municion = _municion - a;
							_arma_municion = _arma_municion + a;
						}
					}
				} else if (_tipo_recargar == tipo_arma_recargar.MANUAl) {
					if (_arma_municion < _arma_municion_maxima && _municion > 0) {
						_S_recargar.setVolume(_z._entorno._max_volume,_z._entorno._max_volume);
						_S_recargar.start();
						_municion--;
						_arma_municion++;
						this.recargar();
					}
				}

			}
		}
	}

	private ZomblindActivity _z;

	ArrayList<Arma> _lista;
	int _current_cuerpo;
	int _current_distancia;
	int _current_arrojadiza;
	int _current_especial;

	public Armas(Context ctx) {
		this._z = (ZomblindActivity) ctx;
		this._lista = new ArrayList<Armas.Arma>();
		this._lista.add(0, new Arma(_z, "Ninguna"));

		this._lista.add(1, new Arma(_z, "Puños", 2, 12, tipo_arma.CUERPO,
				tipo_arma_recargar.NULO, 0, 0, 0, 0, 1, 5, 0, 0,
				R.raw.arma_punos, -1));
		this._lista.add(new Arma(_z, "Pistola 9mm", 6, 12, tipo_arma.DISTANCIA,
				tipo_arma_recargar.AUTO, 15, 15, 30, 90, 1, 5, 0, 0,
				R.raw.arma_pistola9mm_disparo, R.raw.arma_pistola9mm_recargar));

		this._current_cuerpo = 1;
		this._current_distancia = 2;
		this._current_arrojadiza = 0;
		this._current_especial = 0;

	}

	public Arma getArma(Armas.tipo_arma t) {
		switch (t) {
		case CUERPO:
			return this._lista.get(this._current_cuerpo);
		case DISTANCIA:
			return this._lista.get(this._current_distancia);
		case ARROJADIZA:
			return this._lista.get(this._current_arrojadiza);
		case ESPECIAL:
			return this._lista.get(this._current_especial);
		default:
			return this._lista.get(0);
		}
	}

	public void next(Armas.tipo_arma t) {
		int i;
		boolean encontrado = false;
		switch (t) {
		case CUERPO:
			i = this._current_cuerpo;
			do {
				i++;
				if (i == _lista.size())
					i = 0;
				if (_lista.get(i)._tipo == Armas.tipo_arma.CUERPO) {
					encontrado = true;
				}
			} while (i != this._current_cuerpo && !encontrado);
			this._current_cuerpo = i;
		case DISTANCIA:
			i = this._current_distancia;
			do {
				i++;
				if (i == _lista.size())
					i = 0;
				if (_lista.get(i)._tipo == Armas.tipo_arma.DISTANCIA) {
					encontrado = true;
				}
			} while (i != this._current_distancia && !encontrado);
			this._current_distancia = i;
		case ARROJADIZA:
			i = this._current_arrojadiza;
			do {
				i++;
				if (i == _lista.size())
					i = 0;
				if (_lista.get(i)._tipo == Armas.tipo_arma.ARROJADIZA) {
					encontrado = true;
				}
			} while (i != this._current_arrojadiza && !encontrado);
			this._current_arrojadiza = i;
		case ESPECIAL:
			i = this._current_especial;
			do {
				i++;
				if (i == _lista.size())
					i = 0;
				if (_lista.get(i)._tipo == Armas.tipo_arma.ESPECIAL) {
					encontrado = true;
				}
			} while (i != this._current_especial && !encontrado);
			this._current_especial = i;
		}

	}

}