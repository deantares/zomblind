package antares.zomblind.core.levels.comprobation;

import android.content.Context;
import antares.zomblind.ZomblindActivity;
import antares.zomblind.core.items.Armas.tipo_arma;

public class _comprobation_hit_front implements _comprobation{
	ZomblindActivity _z;

	public _comprobation_hit_front(Context ctx) {
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
