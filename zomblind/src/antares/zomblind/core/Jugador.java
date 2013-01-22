package antares.zomblind.core;

import android.content.Context;
import antares.zomblind.ZomblindActivity;

public class Jugador {
	
	public int _vida = 5;
	public int _stamina = 10;
	public int balas = 6;
	
	private ZomblindActivity _z;
	
	public Jugador (Context ctx){
		_z = (ZomblindActivity) ctx;
	}
	
	public String toString(){
		return "VIDA: " + _vida + "  ESTAMINA: " + _stamina + "   BALAS: " + balas;
	}
	

}
