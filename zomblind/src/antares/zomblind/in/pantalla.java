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
package antares.zomblind.in;

import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import antares.zomblind.ZomblindActivity;

public class pantalla {

	public float x = 50;
	public float y = 50;
	
	public int width;
	public int height;
	
	public String action ="";
	
	private ZomblindActivity _z;
	
	public pantalla(Context ctx){
		_z = (ZomblindActivity) ctx;
		Display display = _z.getWindowManager().getDefaultDisplay();
		width = display.getWidth();
		height = display.getHeight();
		
		
	}
	
	public void update(MotionEvent evento){
		
		synchronized (this) {
		
		action = String.valueOf(evento.getAction());
		if (evento.getAction() == MotionEvent.ACTION_DOWN) {
			x = evento.getX();
			y = evento.getY();
			Log.v("Entorno", x + "," + y);

			if (_z._orientacion.isCalibrate() == false) {
				_z._habladora.say("Dispositivo calibrado");
				_z._orientacion.calibrate();
			}
		} else if (evento.getAction() == MotionEvent.EDGE_BOTTOM) {
			x = evento.getX();
			y = evento.getY();
			Log.v("Entorno", x + "," + y);
		}
		}
	}
	
	
}
