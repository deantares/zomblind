package antares.zomblind.core.npcs;

import android.content.Context;
import antares.zomblind.R.raw;
import antares.zomblind.ZomblindActivity;
import antares.zomblind.core.npcs.npc.tipo_npc;

public class DataNPCs {
	ZomblindActivity _z;
	npc _zombie_light_1;
	npc lord_ferguson; 
	
	DataNPCs(Context ctx){
		_z = (ZomblindActivity) ctx;
		
		_zombie_light_1 = new npc(_z, tipo_npc.HOSTIL,
				5, 10, // Puntos de ataque y Salud
				1, 1, // Rango de ataque y distancia
				0, 0, //Armadura cuerpo a cuerpo y armaduras a distancia
				raw.zombie_light_01_acercando,
				raw.zombie_light_01_ataque,
				raw.zombie_light_01_muerte,
				-1);
		
		lord_ferguson = new npc(_z, tipo_npc.HOSTIL,
				5, 10, // Puntos de ataque y Salud
				1, 1, // Rango de ataque y distancia
				0, 0, //Armadura cuerpo a cuerpo y armaduras a distancia
				raw.zombie_light_01_acercando,
				raw.zombie_light_01_ataque,
				raw.zombie_light_01_muerte,
				-1);

	}
	
	
	
	
	
}
