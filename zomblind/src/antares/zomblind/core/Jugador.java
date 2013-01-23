/*******************************************************************************
 * Copyright 2013 antares
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
package antares.zomblind.core;

import android.content.Context;
import antares.zomblind.ZomblindActivity;

public class Jugador {
	
	public int _vida = 5;
	public int _stamina = 10;
	public int balas = 6;
	
	private ZomblindActivity _z;
	
	public Jugador (Context ctx){
		_z = (ZomblindActivity) ctx;
	}
	
	public String toString(){
		return "VIDA: " + _vida + "  ESTAMINA: " + _stamina + "   BALAS: " + balas;
	}
	

}
