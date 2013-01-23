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
package antares.zomblind.out;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import antares.zomblind.ZomblindActivity;

public class debug {
	private ZomblindActivity _z;
	
	Boolean debugactivo = true;
	int size_text = 15;
	int delay_text = 18;
	int conta=0;
	
	public debug(Context ctx){
		_z = (ZomblindActivity) ctx;
	}
	
	public void change(){
		debugactivo = !debugactivo;
	}
	
	public void update(Canvas canvas, Paint paint){
		
		if(debugactivo){
		
		canvas.drawColor(Color.LTGRAY);
		paint.setColor(Color.GREEN);
		canvas.drawCircle(_z._pantalla.x, _z._pantalla.y, 20, paint);
		paint.setColor(Color.BLACK);
		paint.setTextSize(size_text);
		canvas.drawText("Posición toque pantalla", 10, delay_text,
				paint);
		canvas.drawText("x= " + _z._pantalla.x + "   [0," + _z._pantalla.width + "]", 10, delay_text * 2, paint);
		canvas.drawText("y= " + _z._pantalla.y + "   [0," + _z._pantalla.height + "]", 10, delay_text * 3, paint);
		
		
		paint.setTextSize(size_text);
		canvas.drawText("Última acción pantalla", 10, delay_text * 4,
				paint);
		canvas.drawText(_z._pantalla.action, 10, delay_text * 5, paint);
		canvas.drawText("Posición de mira", 10, delay_text * 6, paint);
		canvas.drawText(_z._orientacion.toString(), 10, delay_text * 7,
				paint);
		canvas.drawText("Posición de mira (mirando)", 10,
				delay_text * 8, paint);

		canvas.drawText(_z._orientacion.mirando(), 10, delay_text * 9,
				paint);

		canvas.drawText("Posición de Zombie", 10, delay_text * 10,
				paint);
		canvas.drawText(_z.zombie, 10, delay_text * 11, paint);

		canvas.drawText("Acelerómetro: X - Y - Z", 10, delay_text * 12,
				paint);
		canvas.drawText(_z._acelerometro.toString(), 10, delay_text * 13,
				paint);
		
		canvas.drawText("Palabra oida", 10, delay_text * 14,
				paint);
		canvas.drawText(_z.oido, 10, delay_text * 15,
				paint);
		
		//Pintamos una cruceta
		
		canvas.drawText(_z._entorno._jugador.toString() , size_text, _z._pantalla.height - size_text * 6, paint);
		
		}
	}
}
