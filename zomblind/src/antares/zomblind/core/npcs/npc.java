package antares.zomblind.core.npcs;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import antares.zomblind.ZomblindActivity;

public abstract class npc {

	protected ZomblindActivity _z;

	//Distancia en metros: 1,2,3,4,5,6,7,8,9,10
	public int _distancia;
	
	protected MediaPlayer _s0;
	
	//Zona
	//0 - izquierda
	//1 - centro
	//2 - derecha
	public int _zona;
	
	protected tipo _tipo;
	protected int _pi_ataque;
	protected int _salud;
	protected int _rango_ataque;
	protected long[] _vibra_patron = {10,200,30,500};
	
	public npc(Context ctx , int distancia, int zona, tipo tipo){
		_z = (ZomblindActivity) ctx;
		_s0 = new MediaPlayer();
		_s0.setLooping(false);
	}
	
	public npc(Context ctx){
		_z = (ZomblindActivity) ctx;
		_s0 = new MediaPlayer();
		_s0.setLooping(false);
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
		_distancia = _distancia==1?1:_distancia-1;
	}
	
	public boolean acercar_isCerca() {
		_distancia = _distancia==1?1:_distancia-1;
		return _distancia==0;
	}
	
	public void play() {
		this.setVolumen();
		_s0.start();
	}
	
	public void setVolumen(){
		float generalVolume = (float) (_z._entorno._atenuancion_distancia_base*Math.exp(_z._entorno._atenuancion_distancia_expo * this._distancia));
		Log.i("Npc.play", Float.toString(generalVolume));
		float leftVolume =  this._zona<2?generalVolume:0;
		float rightVolume = this._zona>1?generalVolume:0;
		
		Log.i("Npc.play", "l:" + Float.toString(leftVolume) + "  r:" + Float.toString(rightVolume));
		
		_s0.setVolume(leftVolume, rightVolume);
		
	}
	
	public void atacar(){
		if(this._distancia -_rango_ataque <= 0){
			_z._entorno._jugador.atacado(_pi_ataque);
			_z._vibrador.vibrarpattern(_vibra_patron);
		}
	}	
	

}
