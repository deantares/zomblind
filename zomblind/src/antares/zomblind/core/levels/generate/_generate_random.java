package antares.zomblind.core.levels.generate;

import java.util.Random;

import android.content.Context;
import android.util.Log;
import antares.zomblind.ZomblindActivity;
import antares.zomblind.core.npcs.DataNPCs;
import antares.zomblind.core.npcs.npc;

public class _generate_random implements _generate{
	
	ZomblindActivity _z;
	
	public Random ale = new Random();
//	public _generate_random(Context ctx) {
//		_z = (ZomblindActivity) ctx;
//	}
	
	public _generate_random(Context ctx) {
		// TODO Auto-generated constructor stub
		_z = (ZomblindActivity) ctx;
	}
	
	public void run(){

		//int a = ale.nextInt(10);
		Log.i("Generate_random", "Principio ejecución ");
		
		int a = 3;
		//_z._entorno._npcs.push(new npc(_z,DataNPCs._alemana));
		
		// Generamos enemigos
		if (a < 5) {
			int b = ale.nextInt(20);
			if(b==1){_z._entorno._npcs.push(new npc(_z,DataNPCs._abogado));}
			else if(b==2){_z._entorno._npcs.push(new npc(_z,DataNPCs._alemana));}
			else if(b==3){_z._entorno._npcs.push(new npc(_z,DataNPCs._arrastrao));}
			else if(b==4){_z._entorno._npcs.push(new npc(_z,DataNPCs._carnavalera));}
			else if(b==5){_z._entorno._npcs.push(new npc(_z,DataNPCs._cobarde));}
			else if(b==6){_z._entorno._npcs.push(new npc(_z,DataNPCs._duquesa));}
			else if(b==7){_z._entorno._npcs.push(new npc(_z,DataNPCs._fotografo));}
			else if(b==8){_z._entorno._npcs.push(new npc(_z,DataNPCs._hipster));}
			else if(b==9){_z._entorno._npcs.push(new npc(_z,DataNPCs._lazaro));}
			else if(b==10){_z._entorno._npcs.push(new npc(_z,DataNPCs._lord_ferguson));}
			else if(b==11){_z._entorno._npcs.push(new npc(_z,DataNPCs._mariquieta));}
			else if(b==12){_z._entorno._npcs.push(new npc(_z,DataNPCs._novia));}
			else if(b==13){_z._entorno._npcs.push(new npc(_z,DataNPCs._olakase));}
			else if(b==14){_z._entorno._npcs.push(new npc(_z,DataNPCs._oveja));}
			else if(b==15){_z._entorno._npcs.push(new npc(_z,DataNPCs._pecosete));}
			else if(b==16){_z._entorno._npcs.push(new npc(_z,DataNPCs._periodista));}
			else if(b==17){_z._entorno._npcs.push(new npc(_z,DataNPCs._perrete));}
			else {_z._entorno._npcs.push(new npc(_z,DataNPCs._light));}
		}
		Log.i("Generate_random", "Fin ejecución "+ a );
		}

	}


