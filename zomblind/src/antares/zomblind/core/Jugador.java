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

package antares.zomblind.core;

import android.R;
import android.content.Context;
import antares.zomblind.ZomblindActivity;


/**
 * @author antares
 * 
 */
public class Jugador {
	public int _maxVida = 5;
	public int _vida = 5;
	public int _resistencia = 10;
	
	private ZomblindActivity _z;
	
	
	public Jugador (Context ctx){
		_z = (ZomblindActivity) ctx;
	}
	
	public String toString(){
		return "VIDA: " + _vida + "  ESTAMINA: " + _resistencia;
	}
	
	public void atacado(){
		_vida = _vida==0 ? 0 : _vida--;
	}
	
	public void disparado(){
		_vida = _vida==0 ? 0 : _vida--;
	}
	
	public boolean esVivo(){
		return _vida==0;
	}
	
	public void curar(){
		_vida = _vida == 5? 5 : _vida+1;
		
	}
	
	public void info(){
		if (_vida==0){
			_z._habladora.say("Muerto");
		}
		_z._habladora.say(_vida + (_vida==1?" vida":" vidas" ) );
	}
	

}
