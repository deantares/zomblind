package antares.zomblind.core.objetos;

import java.util.ArrayList;

import android.content.Context;
import antares.zomblind.ZomblindActivity;

public class ArmaLista {

	public enum tipo_arma {
		NULO, CUERPO, DISTANCIA, ARROJADIZA, ESPECIAL
	}

	public enum tipo_arma_recargar {
		NULO, AUTO, MANUAl
	}

	public enum tipo_disparo {
		NULO, AUTO, MANUAL, RAFAGA_2, RAFAGA_5
	}

	private ZomblindActivity _z;

	ArrayList<Arma> _lista;
	int _current_cuerpo;
	int _current_distancia;
	int _current_arrojadiza;
	int _current_especial;

	public ArmaLista(Context ctx) {
		
		this._z = (ZomblindActivity) ctx;
		this._lista = new ArrayList<Arma>();
		this._lista.add(0, new Arma(_z, "Ninguna"));
		
		this._lista.add(1, new Arma(_z, ArmaData.GOLPE1));
		
		this._lista.add(new Arma(_z, ArmaData.KATANA));
		
		this._lista.add(new Arma(_z, ArmaData.PISTOLA9MM));
		
		this._lista.add(new Arma(_z, ArmaData.RIFLECAZA));
		
		this._lista.add(new Arma(_z, ArmaData.MOTOSIERRA));

		for(Arma a:this._lista){a._descubierta = true;};
		
		this._current_cuerpo = 1;
		this._current_distancia = 3;
		this._current_arrojadiza = 0;
		this._current_especial = 0;

	}

	public Arma getArma(ArmaLista.tipo_arma t) {
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

	public void next(ArmaLista.tipo_arma t) {
		int i;
		boolean encontrado = false;
		if(t==tipo_arma.CUERPO){
			i = this._current_cuerpo;
			do {
				i++;
				if (i == _lista.size())
					i = 0;
				if (_lista.get(i)._tipo == ArmaLista.tipo_arma.CUERPO && _lista.get(i)._descubierta) {
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
				if (_lista.get(i)._tipo == ArmaLista.tipo_arma.DISTANCIA && _lista.get(i)._descubierta) {
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
				if (_lista.get(i)._tipo == ArmaLista.tipo_arma.ARROJADIZA && _lista.get(i)._descubierta) {
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
				if (_lista.get(i)._tipo == ArmaLista.tipo_arma.ESPECIAL && _lista.get(i)._descubierta) {
					encontrado = true;
				}
			} while (i != this._current_especial && !encontrado);
			this._current_especial = i;
		}
	}


}