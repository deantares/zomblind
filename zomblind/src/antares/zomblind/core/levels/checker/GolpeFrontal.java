package antares.zomblind.core.levels.checker;

import android.content.Context;
import antares.zomblind.ZomblindActivity;
import antares.zomblind.core.objetos.ArmaLista.tipo_arma;

public class GolpeFrontal implements _Chequeador{
	ZomblindActivity _z;

	public GolpeFrontal(Context ctx) {
		_z = (ZomblindActivity) ctx;
	}
	
	public boolean test(){
		boolean res = false;

		if (_z._acelerometro.golpe_frente()) {
			_z._entorno._jugador._armas.getArma(tipo_arma.CUERPO).usar();
			_z._entorno._npcs.atacar(1, _z._entorno._jugador._armas.getArma(tipo_arma.CUERPO));
			res = true;
		}
		
		return res;
	}

}
