package antares.zomblind.core;

import android.content.Context;
import android.media.MediaPlayer;
import antares.zomblind.ZomblindActivity;

public abstract class npc extends MediaPlayer {

	protected ZomblindActivity _z;

	public int _distancia;
	public int _zona;
	public tipo _tipo;
	
	public npc(Context ctx , int distancia, int zona, tipo tipo){
		_z = (ZomblindActivity) ctx;
		this.setLooping(false);
	}
	
	public enum tipo {
		ALIADO, HOSTIL, PASIVO , ESPECIAL; 
	}

	public int get_distancia() {
		return _distancia;
	}

	public void set_distancia(int _distancia) {
		this._distancia = _distancia;
	}

	public int get_zona() {
		return _zona;
	}

	public void set_zona(int _zona) {
		this._zona = _zona;
	}

	public tipo get_tipo() {
		return _tipo;
	}

	public void set_tipo(tipo _tipo) {
		this._tipo = _tipo;
	}

	@Override
	public String toString() {
		return "npc [_distancia=" + _distancia + ", _zona=" + _zona + "]";
	}

	public void acercar() {
		_distancia = _distancia==0?0:_distancia-1;
	}
	
	public boolean acercar_isCerca() {
		_distancia = _distancia==0?0:_distancia-1;
		return _distancia==0;
	}
	
	public void play(){
		float generalVolume = (float) (_z._entorno._max_volumen /(1+  _z._entorno._atenuancion_distancia * this._distancia));
		float leftVolume =  this._zona>=0?generalVolume:0;
		float rightVolume = this._zona<=0?generalVolume:0;;
		this.setVolume(leftVolume, rightVolume);
		this.play();
	}
	
	

}
