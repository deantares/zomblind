package antares.zomblind.core.levels.condiciones;

import android.content.Context;
import antares.zomblind.ZomblindActivity;
import antares.zomblind.core.objetos.ArmaLista.tipo_arma;

public class SinZombies implements _Condicion{
	ZomblindActivity _z;

	public SinZombies(Context ctx) {
		_z = (ZomblindActivity) ctx;
	}
	
	public boolean test(){
		boolean res = false;

		return _z._entorno._npcs.noZombieInGame();

	}

}
