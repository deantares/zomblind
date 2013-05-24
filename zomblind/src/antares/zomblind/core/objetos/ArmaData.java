package antares.zomblind.core.objetos;

import antares.zomblind.R;
import antares.zomblind.core.objetos.ArmaLista.tipo_arma;
import antares.zomblind.core.objetos.ArmaLista.tipo_arma_recargar;
import antares.zomblind.core.objetos.ArmaLista.tipo_disparo;

public class ArmaData {

	public String _name = "";
	public int _alcance;
	public int _dano;
	public tipo_arma _tipo;
	public tipo_arma_recargar _tipo_recargar;
	public tipo_disparo _tipo_disparo;
	public int _cansancio;

	// Munición
	public int _arma_municion;
	public int _arma_municion_maxima;
	public int _municion;
	public int _municion_maxima;

	// Descubierta
	public boolean _descubierta = false;

	// Estado del arma
	public double _estado;

	// Patron de vibración
	protected long[] _vibra_patron_usar = { 10, 200 };
	protected long[] _vibra_patron_recargar = { 10, 100, 30, 200, 40, 100 };

	// Sonidos
	public int _S_ataque;
	public int _S_recargar;
	public int _S_fallo;

	// Aún sin usar
	double _prob_critico;
	double _cantidad_critico;
	
	public ArmaData(String _name, int _alcance, int _dano,
			tipo_arma _tipo, tipo_arma_recargar _recargar, tipo_disparo _disparo, int _arma_municion,
			int _arma_max_municion, int _municion, int _municion_max,
			double _estado, int _peso, double _prob_critico,
			double _cantidad_critico, int s_uso, int s_recargar,
			long[] vibra_usar, long[] vibra_recargar) {

		this._name = _name;
		this._alcance = _alcance;
		this._dano = _dano;
		this._tipo = _tipo;
		this._tipo_recargar = _recargar;
		this._tipo_disparo = _disparo;
		
		this._arma_municion = _arma_municion;
		this._arma_municion_maxima = _arma_max_municion;

		this._municion = _municion;
		this._municion_maxima = _municion_max;
		this._estado = _estado;
		this._cansancio = _peso;
		this._prob_critico = _prob_critico;
		this._cantidad_critico = _cantidad_critico;

		this._S_ataque = s_uso;
		this._S_recargar = s_recargar;

		if (vibra_usar != null)
			this._vibra_patron_usar = vibra_usar;
		if (vibra_usar != null)
			this._vibra_patron_recargar = vibra_recargar;

	}
	
	public static final ArmaData GOLPE1 = new ArmaData("Puños",
			2, 10,
			tipo_arma.CUERPO,
			tipo_arma_recargar.NULO,
			tipo_disparo.NULO,
			0, 0, 0, 0,
			1, 5,
			0, 0,
			R.raw.arma_punos, -1,
			new long[]{10,200},
			null);
	
	public static final ArmaData KATANA = new ArmaData ("Katana",
			3, 100, //Alcance, Daño
			tipo_arma.CUERPO, //Tipo de arma
			tipo_arma_recargar.NULO, //Tipo de recargue
			tipo_disparo.NULO,
			0, 0, //_arma_municion, _arma_max_municion
			0, 0, //_municion,_municion_max,
			1, 2, //Estado , Cansancio
			0, 0,
			R.raw.arma_katana, -1,
			new long[]{10,100},
			null);
	
	public static final ArmaData BASTONAZO = new ArmaData ("Bastón de Ramón",
			2, 60, //Alcance, Daño
			tipo_arma.CUERPO, //Tipo de arma
			tipo_arma_recargar.NULO, //Tipo de recargue
			tipo_disparo.NULO,
			0, 0, //_arma_municion, _arma_max_municion
			0, 0, //_municion,_municion_max,
			1, 5, //Estado , Cansancio
			0, 0,
			R.raw.arma_punos, -1,
			new long[]{5,80,20,100},
			null);
	
	public static final ArmaData PISTOLA9MM = new ArmaData("Pistola 9mm",
			6, 25,
			tipo_arma.DISTANCIA, tipo_arma_recargar.AUTO, tipo_disparo.MANUAL,
			15, 15, 30, 90,
			1, 5,
			0, 0,
			R.raw.arma_pistola9mm_disparo_corto, R.raw.arma_pistola9mm_recargar,
			new long[]{20,200},
			new long[]{20,100,100,200});
	
	public static final ArmaData RIFLECAZA = new ArmaData("Rifle Caza",
			10, 100,
			tipo_arma.DISTANCIA, tipo_arma_recargar.MANUAl, tipo_disparo.MANUAL,
			1, 1, 30, 30,
			1, 5,
			0, 0,
			R.raw.arma_rfilecaza_disparo, R.raw.arma_rfilecaza_recargar,
			new long[]{20,200},
			new long[]{20,100,10,100,100,200});
	
	public static final ArmaData MOTOSIERRA = new ArmaData ("Motosierra",
			4, 200, //Alcance, Daño
			tipo_arma.DISTANCIA, //Tipo de arma
			tipo_arma_recargar.AUTO, //Tipo de recargue
			tipo_disparo.AUTO,
			200, 1000, //_arma_municion, _arma_max_municion
			200, 1000, //_municion,_municion_max,
			1, 2, //Estado , Cansancio
			0, 0,
			R.raw.arma_motosierra_disparo_corto, R.raw.arma_motosierra_recargar,
			new long[]{5,80,20,100,5,80,20,100},
			new long[]{60,200,100,200,100,200,100,200,100,200,200,100,200,100,200,100,100});
	
	
	
}
