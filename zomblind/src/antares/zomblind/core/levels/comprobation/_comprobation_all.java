package antares.zomblind.core.levels.comprobation;

import android.content.Context;
import antares.zomblind.ZomblindActivity;
import antares.zomblind.core.items.Armas.tipo_arma;

public class _comprobation_all implements _comprobation{
	ZomblindActivity _z;

	public _comprobation_all(Context ctx) {
		_z = (ZomblindActivity) ctx;
	}
	
	public boolean test(){
		boolean res = false;

		if (_z._acelerometro.golpe_izquierda()) {
			_z._entorno._jugador._armas.getArma(tipo_arma.CUERPO).usar();
			_z._entorno._npcs.atacar(0, _z._entorno._jugador._armas.getArma(tipo_arma.CUERPO));
			res = true;
		}

		if (_z._acelerometro.golpe_frente()) {
			_z._entorno._jugador._armas.getArma(tipo_arma.CUERPO).usar();
			_z._entorno._npcs.atacar(1, _z._entorno._jugador._armas.getArma(tipo_arma.CUERPO));
			res = true;
		}

		if (_z._acelerometro.golpe_derecha()) {
			_z._entorno._jugador._armas.getArma(tipo_arma.CUERPO).usar();
			_z._entorno._npcs.atacar(2, _z._entorno._jugador._armas.getArma(tipo_arma.CUERPO));
			res = true;
		}

		if (_z._acelerometro.recargar()) {
			_z._entorno._jugador._armas.getArma(tipo_arma.DISTANCIA).recargar();
			res = true;
		}

		int disparo = _z._pantalla.isDisparando();

		if (disparo >= 0) {
			if (_z._entorno._jugador._armas.getArma(tipo_arma.DISTANCIA).usar()) {
				_z._entorno._npcs.atacar(disparo,_z._entorno._jugador._armas.getArma(tipo_arma.DISTANCIA));
				res = true;
			}

		}
		return res;
	}

}
