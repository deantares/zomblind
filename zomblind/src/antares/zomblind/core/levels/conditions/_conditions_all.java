package antares.zomblind.core.levels.conditions;

import android.content.Context;
import antares.zomblind.ZomblindActivity;
import antares.zomblind.core.Nucleo.data_nucleo;
import antares.zomblind.core.items.Armas.tipo_arma;

public class _conditions_all implements _conditions{
	ZomblindActivity _z;

	public _conditions_all(Context ctx) {
		_z = (ZomblindActivity) ctx;
	}
	
	public boolean test(){
		boolean res = false;

		if (_z._acelerometro.golpe_izquierda()) {
			data_nucleo._jugador._armas.getArma(tipo_arma.CUERPO).usar();
			data_nucleo._npcs.atacar(0, data_nucleo._jugador._armas.getArma(tipo_arma.CUERPO));
			res = true;
		}

		if (_z._acelerometro.golpe_frente()) {
			data_nucleo._jugador._armas.getArma(tipo_arma.CUERPO).usar();
			data_nucleo._npcs.atacar(1, data_nucleo._jugador._armas.getArma(tipo_arma.CUERPO));
			res = true;
		}

		if (_z._acelerometro.golpe_derecha()) {
			data_nucleo._jugador._armas.getArma(tipo_arma.CUERPO).usar();
			data_nucleo._npcs.atacar(2, data_nucleo._jugador._armas.getArma(tipo_arma.CUERPO));
			res = true;
		}

		if (_z._acelerometro.recargar()) {
			data_nucleo._jugador._armas.getArma(tipo_arma.DISTANCIA).recargar();
			res = true;
		}

		int disparo = _z._pantalla.isDisparando();

		if (disparo >= 0) {
			if (data_nucleo._jugador._armas.getArma(tipo_arma.DISTANCIA).usar()) {
				data_nucleo._npcs.atacar(disparo,data_nucleo._jugador._armas.getArma(tipo_arma.DISTANCIA));
				res = true;
			}

		}
		return res;
	}

}
