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

package antares.zomblind.out;

import android.content.Context;
import android.os.Vibrator;
import antares.zomblind.ZomblindActivity;

public class Vibrador{

	private ZomblindActivity _z;
	private Vibrator _v;
	
	public Vibrador(Context ctx){
		_z = (ZomblindActivity) ctx;
		_v = (Vibrator)_z.getSystemService(Context.VIBRATOR_SERVICE);
	}
	
	public void vibrar(long dura){
		_v.vibrate(dura);
	}
	
	public void vibrar_golpe(){
		_v.vibrate(100);
	}
	
	public void vibrarpattern(long[] p){
		_v.vibrate(p,-1);
	}
	
}
