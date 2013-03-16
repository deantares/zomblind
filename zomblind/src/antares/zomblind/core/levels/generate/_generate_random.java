package antares.zomblind.core.levels.generate;

import java.util.Random;

import android.content.Context;
import antares.zomblind.core.Nucleo.data_nucleo;
import antares.zomblind.core.npcs.DataNPCs;
import antares.zomblind.core.npcs.npc;

public class _generate_random extends _generate{
	public Random ale = new Random();
	public _generate_random(Context ctx) {
		super(ctx);
	}
	
	void run(){
		
		
		
		int a = ale.nextInt(10);
		
		// Generamos enemigos
		if (a < 5) {
			int b = ale.nextInt(20);
			if(b==1){data_nucleo._npcs.push(new npc(_z,DataNPCs._abogado));}
			else if(b==2){data_nucleo._npcs.push(new npc(_z,DataNPCs._alemana));}
			else if(b==3){data_nucleo._npcs.push(new npc(_z,DataNPCs._arrastrao));}
			else if(b==4){data_nucleo._npcs.push(new npc(_z,DataNPCs._carnavalera));}
			else if(b==5){data_nucleo._npcs.push(new npc(_z,DataNPCs._cobarde));}
			else if(b==6){data_nucleo._npcs.push(new npc(_z,DataNPCs._duquesa));}
			else if(b==7){data_nucleo._npcs.push(new npc(_z,DataNPCs._fotografo));}
			else if(b==8){data_nucleo._npcs.push(new npc(_z,DataNPCs._hipster));}
			else if(b==9){data_nucleo._npcs.push(new npc(_z,DataNPCs._lazaro));}
			else if(b==10){data_nucleo._npcs.push(new npc(_z,DataNPCs._lord_ferguson));}
			else if(b==11){data_nucleo._npcs.push(new npc(_z,DataNPCs._mariquieta));}
			else if(b==12){data_nucleo._npcs.push(new npc(_z,DataNPCs._novia));}
			else if(b==13){data_nucleo._npcs.push(new npc(_z,DataNPCs._olakase));}
			else if(b==14){data_nucleo._npcs.push(new npc(_z,DataNPCs._oveja));}
			else if(b==15){data_nucleo._npcs.push(new npc(_z,DataNPCs._pecosete));}
			else if(b==16){data_nucleo._npcs.push(new npc(_z,DataNPCs._periodista));}
			else if(b==17){data_nucleo._npcs.push(new npc(_z,DataNPCs._perrete));}}
			else {data_nucleo._npcs.push(new npc(_z,DataNPCs._light));}
		}
	}


