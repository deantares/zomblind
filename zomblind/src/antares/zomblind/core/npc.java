package antares.zomblind.core;

import android.R.bool;
import android.content.Context;
import android.media.MediaPlayer;

public abstract class npc extends MediaPlayer {

	private Context _ctx;

	public int _distancia;
	public int _zona;

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

}
