/*******************************************************************************
 * Copyright 2013 Antonio Fernández Ares (antares.es@gmail.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package antares.zomblind.core.levels.generadores;

import java.util.Random;

import android.content.Context;
import android.util.Log;
import antares.zomblind.ZomblindActivity;
import antares.zomblind.core.npcs.NpcData;
import antares.zomblind.core.npcs.Npc;

public class AleatorioTodos implements _Generador {

	ZomblindActivity _z;

	public Random ale = new Random();

	public AleatorioTodos(Context ctx) {
		// TODO Auto-generated constructor stub
		_z = (ZomblindActivity) ctx;
	}

	public void run(Npc n) {
		_z._entorno._npcs.push(n);
	}

	public void run() {

		// int a = ale.nextInt(10);
		Log.i("Generate_random", "Principio ejecución ");

		int a = 3;
		// _z._entorno._npcs.push(new npc(_z,DataNPCs._alemana));

		// Generamos enemigos
		if (a < 5) {
			int b = ale.nextInt(20);
			if (b == 1) {
				_z._entorno._npcs.push(new Npc(_z, NpcData._abogado));
			} else if (b == 2) {
				_z._entorno._npcs.push(new Npc(_z, NpcData._alemana));
			} else if (b == 3) {
				_z._entorno._npcs.push(new Npc(_z, NpcData._arrastrao));
			} else if (b == 4) {
				_z._entorno._npcs.push(new Npc(_z, NpcData._carnavalera));
			} else if (b == 5) {
				_z._entorno._npcs.push(new Npc(_z, NpcData._cobarde));
			} else if (b == 6) {
				_z._entorno._npcs.push(new Npc(_z, NpcData._duquesa));
			} else if (b == 7) {
				_z._entorno._npcs.push(new Npc(_z, NpcData._fotografo));
			} else if (b == 8) {
				_z._entorno._npcs.push(new Npc(_z, NpcData._hipster));
			} else if (b == 9) {
				_z._entorno._npcs.push(new Npc(_z, NpcData._lazaro));
			} else if (b == 10) {
				_z._entorno._npcs.push(new Npc(_z, NpcData._lord_ferguson));
			} else if (b == 11) {
				_z._entorno._npcs.push(new Npc(_z, NpcData._mariquieta));
			} else if (b == 12) {
				_z._entorno._npcs.push(new Npc(_z, NpcData._novia));
			} else if (b == 13) {
				_z._entorno._npcs.push(new Npc(_z, NpcData._olakase));
			} else if (b == 14) {
				_z._entorno._npcs.push(new Npc(_z, NpcData._oveja));
			} else if (b == 15) {
				_z._entorno._npcs.push(new Npc(_z, NpcData._pecosete));
			} else if (b == 16) {
				_z._entorno._npcs.push(new Npc(_z, NpcData._periodista));
			} else if (b == 17) {
				_z._entorno._npcs.push(new Npc(_z, NpcData._perrete));
			} else {
				_z._entorno._npcs.push(new Npc(_z, NpcData._light));
			}
		}
		Log.i("Generate_random", "Fin ejecución " + a);
	}

}
