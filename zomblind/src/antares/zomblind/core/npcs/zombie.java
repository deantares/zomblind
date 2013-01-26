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
package antares.zomblind.core.npcs;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

public class zombie extends npc {
	
	public zombie(Context ctx){
		super (ctx);
		this._tipo=tipo_npc.HOSTIL;
		this._pi_ataque = 5;
		this._salud = 10;
		this._rango_ataque = 1;
		
		//_s0 = MediaPlayer.create(_z, R.raw.zombie_light_01);
		
		//_s0 = MediaPlayer.create(_z, Uri.parse("android.resource://antares.zomblind/raw/"+"zombie_light_01"));
	}	
}
