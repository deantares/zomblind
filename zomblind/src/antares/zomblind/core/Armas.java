package antares.zomblind.core;

import java.util.ArrayList;

public class Armas {

	public enum tipo {
		NULO, CUERPO, DISTANCIA, ARROJADIZA, ESPECIAL
	}

	public class Arma {
		String _name = "";
		int _alcance;
		int _dano;
		tipo _tipo;
		int _cantidad;
		double _desgaste;
		
		public Arma(String name){
			this._name = name;
			this._alcance = 0;
			this._dano = 0;
			this._tipo = tipo.NULO;
			this._cantidad = 1;
			this._desgaste = 0;
		}

		public Arma(String _name, int _alcance, int _dano, tipo _tipo) {
			this._name = _name;
			this._alcance = _alcance;
			this._dano = _dano;
			this._tipo = _tipo;
			this._cantidad = 1;
			this._desgaste = 0;
		}

		public Arma(String _name, int _alcance, int _dano, tipo _tipo,
				int _cantidad, double _desgaste) {
			this._name = _name;
			this._alcance = _alcance;
			this._dano = _dano;
			this._tipo = _tipo;
			this._cantidad = _cantidad;
			this._desgaste = _desgaste;
		}

	}

	ArrayList<Arma> _lista;
	int _current_cuerpo;
	int _current_distancia;
	int _current_arrojadiza;
	int _current_especial;
		
	public Armas(){
		this._lista = new ArrayList<Armas.Arma>();
		this._lista.add(0, new Arma("Ninguna"));
		this._lista.add(1, new Arma("Puños",1,5,tipo.CUERPO));
		
		this._current_cuerpo = 1;
		this._current_distancia = 0;
		this._current_arrojadiza = 0;
		this._current_especial = 0;
		
	}
	
	public Arma getArma(Armas.tipo t){
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

	public void next(Armas.tipo t){
		int i;
		boolean encontrado = false;
		switch (t) {
		case CUERPO:
			i = this._current_cuerpo;
			do{
				i++;
				if(i==_lista.size()) i = 0;
				if(_lista.get(i)._tipo == Armas.tipo.CUERPO ){
					encontrado = true;
				}
			}while(i!=this._current_cuerpo && !encontrado);
			this._current_cuerpo = i;
		case DISTANCIA:
			i = this._current_distancia;
			do{
				i++;
				if(i==_lista.size()) i = 0;
				if(_lista.get(i)._tipo == Armas.tipo.DISTANCIA ){
					encontrado = true;
				}
			}while(i!=this._current_distancia && !encontrado);
			this._current_distancia = i;
		case ARROJADIZA:
			i = this._current_arrojadiza;
			do{
				i++;
				if(i==_lista.size()) i = 0;
				if(_lista.get(i)._tipo == Armas.tipo.ARROJADIZA ){
					encontrado = true;
				}
			}while(i!=this._current_arrojadiza && !encontrado);
			this._current_arrojadiza = i;
		case ESPECIAL:
			i = this._current_especial;
			do{
				i++;
				if(i==_lista.size()) i = 0;
				if(_lista.get(i)._tipo == Armas.tipo.ESPECIAL ){
					encontrado = true;
				}
			}while(i!=this._current_especial && !encontrado);
			this._current_especial = i;
		}
		
	}

	

}