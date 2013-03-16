package antares.zomblind.core.npcs;

import android.content.Context;
import antares.zomblind.R;
import antares.zomblind.R.raw;
import antares.zomblind.ZomblindActivity;
import antares.zomblind.core.npcs.npc.tipo_npc;

public class DataNPCs {
	public String _name;
	public tipo_npc _tipo;
	public int _pi_ataque;
	public int _salud;
	public int _rango_ataque;
	public int _velocidad;
	public int _armadura_cuerpo = 0;
	public int _armadura_distancia = 0;

	public int _S_movimiento;
	public int _S_ataque;
	public int _S_muerte;
	public int _S_especial;

	public DataNPCs(String _name, tipo_npc _tipo, int _pi_ataque, int _salud,
			int _rango_ataque, int _velocidad, int _armadura_cuerpo,
			int _armadura_distancia, int _S_movimiento, int _S_ataque,
			int _S_muerte, int _S_especial) {
		super();
		this._name = _name;
		this._tipo = _tipo;
		this._pi_ataque = _pi_ataque;
		this._salud = _salud;
		this._rango_ataque = _rango_ataque;
		this._velocidad = _velocidad;
		this._armadura_cuerpo = _armadura_cuerpo;
		this._armadura_distancia = _armadura_distancia;

		this._S_movimiento = _S_movimiento;
		this._S_ataque = _S_ataque;
		this._S_muerte = _S_muerte;
		this._S_especial = _S_especial;
	}

			// --Stars--	1	2	3	4	5	E
			// pi_ataque	5	10	15	20	30	50
			// Salud		10	15	25	30	50	100
			// rango		1	2	3	4	5	10
			// velocidad	1	2	3	4	5	10
			// a_cuerpo		5	10	20	50
			// a_distancia	5	10	20	50

	public static final DataNPCs _light =
			new DataNPCs("light", tipo_npc.HOSTIL,
				5, 5, 1, 1, 0, 0,
				R.raw.zombie_light_01_acercando,
				R.raw.zombie_light_01_ataque,
				R.raw.zombie_light_01_muerte, -1);
	public static final DataNPCs _lord_ferguson =
			new DataNPCs("lord_ferguson", tipo_npc.HOSTIL,
				5, 5, 1, 1, 0, 0,
				R.raw.zombie_lord_ferguson_movimiento,
				R.raw.zombie_lord_ferguson_ataque,
				R.raw.zombie_lord_ferguson_muerte,
				R.raw.zombie_lord_ferguson_especial);
	public static final DataNPCs _carnavalera =
			new DataNPCs("carnavalera", tipo_npc.HOSTIL,
				5, 50, 1, 5, 0, 0,
				R.raw.zombie_carnavalera_movimiento,
				R.raw.zombie_carnavalera_ataque,
				R.raw.zombie_carnavalera_muerte,
				R.raw.zombie_carnavalera_especial);
	public static final DataNPCs _duquesa =
			new DataNPCs("duquesa", tipo_npc.HOSTIL,
				5, 10, 1, 1, 50, 50,
				R.raw.zombie_duquesa_movimiento,
				R.raw.zombie_duquesa_ataque,
				R.raw.zombie_duquesa_muerte,
				R.raw.zombie_duquesa_especial);
	public static final DataNPCs _pecosete =
			new DataNPCs("duquesa", tipo_npc.HOSTIL,
				5, 100, 1, 1, 0, 0,
				R.raw.zombie_pecosete_movimiento,
				R.raw.zombie_pecosete_ataque,
				R.raw.zombie_pecosete_muerte,
				R.raw.zombie_pecosete_especial);
	public static final DataNPCs _oveja =
			new DataNPCs("oveja", tipo_npc.HOSTIL,
				30, 50, 1, 1, 0, 0,
				R.raw.zombie_oveja_movimiento,
				R.raw.zombie_oveja_ataque,
				R.raw.zombie_oveja_muerte,
				R.raw.zombie_oveja_especial);
	public static final DataNPCs _olakase =
			new DataNPCs("olakase", tipo_npc.HOSTIL,
				0, 25, 3, 3, 5, 5,
				R.raw.zombie_olakease_movimiento,
				R.raw.zombie_olakease_ataque,
				R.raw.zombie_olakease_muerte,
				-1);
	public static final DataNPCs _hipster =
			new DataNPCs("hipster", tipo_npc.HOSTIL,
				15, 15, 2, 2, 5, 5,
				R.raw.zombie_hipster_movimiento,
				R.raw.zombie_hipster_ataque,
				R.raw.zombie_hipster_muerte,
				R.raw.zombie_hipster_especial);
	public static final DataNPCs _arrastrao =
			new DataNPCs("arrastrao", tipo_npc.HOSTIL,
				30, 50, 1, 1, 0, 0,
				R.raw.zombie_arrastrao_movimiento,
				R.raw.zombie_arrastrao_ataque,
				R.raw.zombie_arrastrao_muerte,
				R.raw.zombie_arrastrao_especial);

	public static final DataNPCs _alemana =
			new DataNPCs("alemana", tipo_npc.HOSTIL,
				10, 25, 2, 2, 5, 10,
				R.raw.zombie_alemana_movimiento,
				R.raw.zombie_alemana_ataque,
				R.raw.zombie_alemana_muerte,
				R.raw.zombie_alemana_especial);
	public static final DataNPCs _novia =
			new DataNPCs("novia", tipo_npc.HOSTIL,
				5, 50, 1, 1, 10, 10,
				R.raw.zombie_novia_acercando,
				R.raw.zombie_novia_ataque,
				R.raw.zombie_novia_muerte,
				R.raw.zombie_novia_especial);
	public static final DataNPCs _periodista =
			new DataNPCs("periodista", tipo_npc.HOSTIL,
				15, 15, 5, 1, 5, 0,
				R.raw.zombie_periodista_acercando,
				R.raw.zombie_periodista_ataque,
				R.raw.zombie_periodista_muerte,
				R.raw.zombie_periodista_especial);
	public static final DataNPCs _lazaro =
			new DataNPCs("lazaro", tipo_npc.HOSTIL,
				15, 15, 2, 4, 0, 0,
				R.raw.zombie_lazaro_acercando,
				R.raw.zombie_lazaro_ataque,
				R.raw.zombie_lazaro_muerte,
				R.raw.zombie_lazaro_especial);
//	public static final DataNPCs _predicador =
//			new DataNPCs("predicador", tipo_npc.HOSTIL,
//				5, 15, 1, 1, 20, 20,
//				R.raw.zombie_predicador_movimiento,
//				R.raw.zombie_predicador_ataque,
//				R.raw.zombie_predicador_muerte,
//				R.raw.zombie_predicador_especial);
	public static final DataNPCs _mariquieta =
			new DataNPCs("mariquieta", tipo_npc.HOSTIL,
				20, 25, 2, 3, 0, 0,
				R.raw.zombie_mariquieta_movimiento,
				R.raw.zombie_mariquieta_ataque,
				R.raw.zombie_mariquieta_muerte,
				R.raw.zombie_mariquieta_especial);
	public static final DataNPCs _cobarde =
			new DataNPCs("cobarde", tipo_npc.HOSTIL,
				15, 25, 5, 1, 0, 0,
				R.raw.zombie_cobarde_movimiento,
				R.raw.zombie_cobarde_ataque,
				R.raw.zombie_cobarde_muerte,
				R.raw.zombie_cobarde_especial);
	public static final DataNPCs _fotografo =
			new DataNPCs("fotografo", tipo_npc.HOSTIL,
				5, 5, 10, 1, 0, 0,
				R.raw.zombie_fotografo_movimiento,
				R.raw.zombie_fotografo_ataque,
				R.raw.zombie_fotografo_muerte,
				R.raw.zombie_fotografo_especial);
	public static final DataNPCs _abogado =
			new DataNPCs("abogado", tipo_npc.HOSTIL,
				15, 30, 2, 2, 5, 0,
				R.raw.zombie_abogado_movimiento,
				R.raw.zombie_abogado_ataque,
				R.raw.zombie_abogado_muerte,
				R.raw.zombie_abogado_abogado);
	public static final DataNPCs _perrete =
			new DataNPCs("perrete", tipo_npc.HOSTIL,
				15, 15, 1, 1, 10, 10,
				R.raw.zombie_perrete_movimiento,
				R.raw.zombie_perrete_ataque,
				R.raw.zombie_perrete_muerte,
				R.raw.zombie_perrete_especial);
	
}
