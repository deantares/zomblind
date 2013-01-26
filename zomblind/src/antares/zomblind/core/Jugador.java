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

import android.content.Context;
import antares.zomblind.ZomblindActivity;


/**
 * @author antares
 * 
 */
public class Jugador {
	
	public class infeccion{
		public int _max = 100;
		public int _actual = 0;
		
		@Override
		public String toString() {
			return "infeccion [_max=" + _max + ", _actual=" + _actual + "]";
		}
		
		public void add(int a){
			_actual = _actual+a>=_max ? _max : _actual+a;
		}
		
		public void sub(int a){
			_actual = _actual-a<=0 ? 0 : _actual-a;
		}
		
	}
	
	public class resistencia{
		public int _max = 100;
		public int _actual = _max;
		@Override
		public String toString() {
			return "resistencia [_max=" + _max + ", _actual=" + _actual + "]";
		}	
		
		public void add(int a){
			_actual = _actual+a>=_max ? _max : _actual+a;
		}
		
		public void sub(int a){
			_actual = _actual-a<=0 ? 0 : _actual-a;
		}
	}
	
	public infeccion _infeccion = new infeccion();
	public resistencia _resistencia = new resistencia();
	
	private ZomblindActivity _z;
	
	
	public Jugador (Context ctx){
		_z = (ZomblindActivity) ctx;
	}
	
	public String toString(){
		return "VIDA: " + _infeccion.toString() + "  ESTAMINA: " + _resistencia.toString();
	}
	
	public void atacado(int a){
		_infeccion.add(a);
	}
//	
//	public void disparado(){
//		_vida = _vida==0 ? 0 : _vida--;
//	}
//	
	public boolean esVivo(){
		return _infeccion._actual>0;
	}
//	
//	public void curar(){
//		_vida = _vida == 5? 5 : _vida+1;
//		
//	}
//	
	public void info(){
		if (_infeccion._actual==_infeccion._max){
			_z._habladora.say("Infectado");
		}
		_z._habladora.say(_infeccion._actual + " % de infección" );
	}
	

}
