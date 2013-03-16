package antares.zomblind.core.items;

import java.util.ArrayList;

import android.content.Context;
import antares.zomblind.R;
import antares.zomblind.ZomblindActivity;

public class Armas {

	public enum tipo_arma {
		NULO, CUERPO, DISTANCIA, ARROJADIZA, ESPECIAL
	}

	public enum tipo_arma_recargar {
		NULO, AUTO, MANUAl
	}

	public enum tipo_disparo {
		NULO, AUTO, MANUAL, RAFAGA
	}

	private ZomblindActivity _z;

	ArrayList<Arma> _lista;
	int _current_cuerpo;
	int _current_distancia;
	int _current_arrojadiza;
	int _current_especial;

	public Armas(Context ctx) {
		
		this._z = (ZomblindActivity) ctx;
		this._lista = new ArrayList<Arma>();
		this._lista.add(0, new Arma(_z, "Ninguna"));
		
		this._lista.add(1, new Arma(_z, "Puños",
				2, 12,
				tipo_arma.CUERPO,
				tipo_arma_recargar.NULO,
				0, 0, 0, 0,
				1, 5,
				0, 0,
				R.raw.arma_punos, -1,
				new long[]{10,200},
				null));
		
		this._lista.add(new Arma(_z, "Katana",
				3, 40, //Alcance, Daño
				tipo_arma.CUERPO, //Tipo de arma
				tipo_arma_recargar.NULO, //Tipo de recargue
				0, 0, //_arma_municion, _arma_max_municion
				0, 0, //_municion,_municion_max,
				1, 20, //Estado , Cansancio
				0, 0,
				R.raw.arma_punos, -1,
				new long[]{10,100},
				null));
		
		
		this._lista.add(new Arma(_z, "Pistola 9mm",
				6, 12,
				tipo_arma.DISTANCIA, tipo_arma_recargar.AUTO,
				15, 15, 30, 90,
				1, 5,
				0, 0,
				R.raw.arma_pistola9mm_disparo_corto, R.raw.arma_pistola9mm_recargar,
				new long[]{20,200},
				new long[]{20,100,100,200}));
		this._lista.add(new Arma(_z, "Rifle Caza",
				10, 25,
				tipo_arma.DISTANCIA, tipo_arma_recargar.MANUAl,
				1, 1, 30, 30,
				1, 5,
				0, 0,
				R.raw.arma_rfilecaza_disparo, R.raw.arma_rfilecaza_recargar,
				new long[]{20,200},
				new long[]{20,100,10,100,100,200}));

		for(Arma a:this._lista){a._descubierta = true;};
		
		this._current_cuerpo = 1;
		this._current_distancia = 3;
		this._current_arrojadiza = 0;
		this._current_especial = 0;

	}

	public Arma getArma(Armas.tipo_arma t) {
		switch (t) {
		case CUERPO:
			return this._lista.get(this._current_cuerpo);
		case DISTANCIA:
			return this._lista.get(this._current_distancia);
		case ARROJADIZA:
			return this._lista.get(this._current_arrojadiza);
		case ESPECIAL:
			return this._lista.get(this._current_especial);
		default:
			return this._lista.get(0);
		}
	}

	public void next(Armas.tipo_arma t) {
		int i;
		boolean encontrado = false;
		if(t==tipo_arma.CUERPO){
			i = this._current_cuerpo;
			do {
				i++;
				if (i == _lista.size())
					i = 0;
				if (_lista.get(i)._tipo == Armas.tipo_arma.CUERPO && _lista.get(i)._descubierta) {
					encontrado = true;
				}
			} while (i != this._current_cuerpo && !encontrado);
			this._current_cuerpo = i;
		}else if(t==tipo_arma.DISTANCIA){
			i = this._current_distancia;
			do {
				i++;
				if (i == _lista.size())
					i = 0;
				if (_lista.get(i)._tipo == Armas.tipo_arma.DISTANCIA && _lista.get(i)._descubierta) {
					encontrado = true;
				}
			} while (i != this._current_distancia && !encontrado);
			this._current_distancia = i;
		}else if(t==tipo_arma.ARROJADIZA){ 
			i = this._current_arrojadiza;
			do {
				i++;
				if (i == _lista.size())
					i = 0;
				if (_lista.get(i)._tipo == Armas.tipo_arma.ARROJADIZA && _lista.get(i)._descubierta) {
					encontrado = true;
				}
			} while (i != this._current_arrojadiza && !encontrado);
			this._current_arrojadiza = i;
		}else if(t==tipo_arma.CUERPO){
			i = this._current_especial;
			do {
				i++;
				if (i == _lista.size())
					i = 0;
				if (_lista.get(i)._tipo == Armas.tipo_arma.ESPECIAL && _lista.get(i)._descubierta) {
					encontrado = true;
				}
			} while (i != this._current_especial && !encontrado);
			this._current_especial = i;
		}
	}

	// Declaramos el listado de armas básicas como constantes

	// public Arma(Context ctx, String _name, int _alcance, int _dano,tipo_arma
	// _tipo, tipo_arma_recargar _recargar,int _arma_municion, int
	// _arma_max_municion, int _municion, int _municion_max, double _estado, int
	// _peso, double _prob_critico, double _cantidad_critico, int s_uso,int
	// s_recargar)

//	final Arma MANOS = new Arma(_z, "Puños", 2, 12, tipo_arma.CUERPO,
//		tipo_arma_recargar.NULO, 0, 0, 0, 0, 1, 5, 0, 0, R.raw.arma_punos,
//			-1);
//	final Arma PISTOLA9MM = new Arma(_z, "Pistola 9mm", 6, 12,
//			tipo_arma.DISTANCIA, tipo_arma_recargar.AUTO, 15, 15, 30, 90, 1, 5,
//			0, 0, R.raw.arma_pistola9mm_disparo, R.raw.arma_pistola9mm_recargar);
//	final Arma RIFLECAZA = new Arma(_z, "Rifle Caza", 10, 25,
//			tipo_arma.DISTANCIA, tipo_arma_recargar.MANUAl, 1, 1, 30, 30, 1, 5,
//			0, 0, R.raw.arma_rfilecaza_disparo, R.raw.arma_rfilecaza_recargar);

}