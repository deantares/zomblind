package antares.zomblind.core.npcs;

import java.util.Arrays;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import antares.zomblind.ZomblindActivity;
import antares.zomblind.core.Armas.*;

public /*abstract*/ class npc {

	protected ZomblindActivity _z;

	// Zona 0 - izquierda , 1 - centro , 2 - derecha
	public int _zona;
	
	// Distancia en metros: 1,2,3,4,5,6,7,8,9,10
	public int _distancia;

	protected tipo_npc _tipo;
	protected int _pi_ataque;
	protected int _salud;
	protected int _rango_ataque;
	
	// La armadura "resta" daño que recibe el personaje
	protected int _armadura_cuerpo = 0;
	protected int _armadura_distancia = 0;

	//protected MediaPlayer _s0;

	protected MediaPlayer _S_movimiento;
	protected MediaPlayer _S_ataque;
	protected MediaPlayer _S_muerte;
	protected MediaPlayer _S_espcial;

	protected long[] _vibra_patron = { 10, 200, 30, 500 };
	
	

	public npc(Context ctx, tipo_npc _tipo,
			int _pi_ataque, int _salud, int _rango_ataque,
			int _armadura_cuerpo, int _armadura_distancia,
			int _S_movimiento, int _S_ataque,
			int _S_muerte, int _S_espcial) {
		this._z = (ZomblindActivity) ctx;
		this._tipo = _tipo;
		this._pi_ataque = _pi_ataque;
		this._salud = _salud;
		this._rango_ataque = _rango_ataque;
		this._armadura_cuerpo = _armadura_cuerpo;
		this._armadura_distancia = _armadura_distancia;
		
		this._S_movimiento = MediaPlayer.create(_z, _S_movimiento);
		this._S_ataque = MediaPlayer.create(_z, _S_ataque);
		this._S_muerte = MediaPlayer.create(_z, _S_muerte);
		//this._S_espcial = MediaPlayer.create(_z, _S_espcial);
	}

	public npc(Context ctx) {
		_z = (ZomblindActivity) ctx;
		//_s0 = new MediaPlayer();
		//_s0.setLooping(false);
	}

	public enum tipo_npc {
		ALIADO, HOSTIL, PASIVO, ESPECIAL;
	}

	@Override
	public String toString() {
		if (this != null) {
			return "npc[" + _zona + " " + _distancia + "m " + _tipo + ", PA="
					+ _pi_ataque + " PS=" + _salud + " RANGO=" + _rango_ataque
					+ " AC" + _armadura_cuerpo + " AD=" + _armadura_distancia
					+ "]";
		} else {
			return "npc[--null--]";
		}
	}

	public void acercar() {
		_distancia = _distancia == 1 ? 1 : _distancia - 1;
	}

	public boolean acercar_isCerca() {
		_distancia = _distancia == 1 ? 1 : _distancia - 1;
		return _distancia == 0;
	}

	public void play() {
		if(_distancia>_rango_ataque){
		this.setVolumen(_S_movimiento);
		_S_movimiento.start();
		}
	}

	public void setVolumen(MediaPlayer s0) {
//		float generalVolume = (float) (_z._entorno._atenuancion_distancia_base * Math
//				.exp(_z._entorno._atenuancion_distancia_expo * this._distancia));
//		Log.i("Npc.play", Float.toString(generalVolume));
//		float leftVolume = this._zona < 2 ? generalVolume : 0;
//		float rightVolume = this._zona > 1 ? generalVolume : 0;
//
//		Log.i("Npc.play",
//				"l:" + Float.toString(leftVolume) + "  r:"
//						+ Float.toString(rightVolume));
//
//		s0.setVolume(leftVolume, rightVolume);

		float generalVolume = (float) (_z._entorno._atenuancion_distancia_base * Math
				.exp(_z._entorno._atenuancion_distancia_expo * this._distancia));
		
		//Aplicamos el factor máximo: 2.2 es el máximo volumen que genera nuestra función
		generalVolume = (float) ((generalVolume * _z._entorno._max_volume)/2.2);
		
		float leftVolume = this._zona < 2 ? generalVolume : 0;
		float rightVolume = this._zona > 0 ? generalVolume : 0;

		s0.setVolume(leftVolume, rightVolume);

		
		
		
	}

	public void ataque() {
		if (this._distancia - _rango_ataque <= 0) {
			setVolumen(_S_ataque);
			_S_ataque.start();
			_z._entorno._jugador.atacado(_pi_ataque);
			_z._vibrador.vibrarpattern(_vibra_patron);
		}
	}

	// Devuelve si el NPC muere
	public boolean atacar(Arma arma) {
			if (arma._tipo == tipo_arma.CUERPO) {
				_salud = _salud - (arma._dano - _armadura_cuerpo);
			} else if (arma._tipo == tipo_arma.DISTANCIA) {
				_salud = _salud - (arma._dano - _armadura_distancia);
			}

			if(_salud<=0){
				setVolumen(_S_muerte);
				_S_muerte.start();
				return true;
			}
		return false;
	}

}

	