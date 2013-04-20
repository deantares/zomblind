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

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.Log;
import antares.zomblind.R;
import antares.zomblind.ZomblindActivity;
import antares.zomblind.core.objetos.ArmaLista.tipo_arma;

public class Debug {
	private ZomblindActivity _z;

	Boolean debugactivo = true;
	int size_text = 11;
	int delay_text = 15;
	
	int conta = 0;
	int conta_delay = 0;
	int conta_max_delay = 5;

	int VELOCIDAD_ANIMACION = 15;
	
	ArrayList<Bitmap> _fondos;

	public Debug(Context ctx) {
		_z = (ZomblindActivity) ctx;
		_fondos = new ArrayList<Bitmap>();

		_fondos.add(BitmapFactory.decodeResource(_z.getResources(),
				R.raw.fondo_01));
		_fondos.add(BitmapFactory.decodeResource(_z.getResources(),
				R.raw.fondo_02));
		_fondos.add(BitmapFactory.decodeResource(_z.getResources(),
				R.raw.fondo_03));
		_fondos.add(BitmapFactory.decodeResource(_z.getResources(),
				R.raw.fondo_04));
		_fondos.add(BitmapFactory.decodeResource(_z.getResources(),
				R.raw.fondo_05));
		_fondos.add(BitmapFactory.decodeResource(_z.getResources(),
				R.raw.fondo_06));
		_fondos.add(BitmapFactory.decodeResource(_z.getResources(),
				R.raw.fondo_07));
		_fondos.add(BitmapFactory.decodeResource(_z.getResources(),
				R.raw.fondo_08));
		_fondos.add(BitmapFactory.decodeResource(_z.getResources(),
				R.raw.fondo_09));
		_fondos.add(BitmapFactory.decodeResource(_z.getResources(),
				R.raw.fondo_10));
		_fondos.add(BitmapFactory.decodeResource(_z.getResources(),
				R.raw.fondo_11));
		_fondos.add(BitmapFactory.decodeResource(_z.getResources(),
				R.raw.fondo_12));
		_fondos.add(BitmapFactory.decodeResource(_z.getResources(),
				R.raw.fondo_13));
		_fondos.add(BitmapFactory.decodeResource(_z.getResources(),
				R.raw.fondo_14));
		_fondos.add(BitmapFactory.decodeResource(_z.getResources(),
				R.raw.fondo_15));
		_fondos.add(BitmapFactory.decodeResource(_z.getResources(),
				R.raw.fondo_16));

	}

	public void change() {
		debugactivo = !debugactivo;
	}

	public boolean isActivo() {
		return debugactivo;
	}

	public void update(Canvas canvas, Paint paint) {
		canvas.drawBitmap(_fondos.get(conta), null, new Rect(0, 0,
				_z._pantalla.width, _z._pantalla.height), paint);
		if (conta_delay == conta_max_delay) {
			conta = conta == VELOCIDAD_ANIMACION ? 0 : conta + 1;
			conta_delay = 0;
		} else {
			conta_delay++;
		}

		if (debugactivo && _z._entorno._jugador != null) {
			// Pintamos "la posición";
			paint.setColor(Color.GRAY);
			canvas.drawCircle(_z._pantalla.x, _z._pantalla.y, 20, paint);

			// Rayas de zonas
			paint.setColor(Color.RED);
			paint.setStrokeWidth(1);
			canvas.drawRect((float) 0, (float) (_z._pantalla.height * 0.25),
					(float) _z._pantalla.width,
					(float) (_z._pantalla.height * 0.25), paint);
			canvas.drawRect((float) 0, (float) (_z._pantalla.height * 0.75),
					(float) _z._pantalla.width,
					(float) (_z._pantalla.height * 0.75), paint);

			canvas.drawRect((float) (_z._pantalla.width * 0.25),
					(float) (_z._pantalla.height * 0.75),
					(float) _z._pantalla.width,
					(float) (_z._pantalla.height * 0.25), paint);
			canvas.drawRect((float) (_z._pantalla.width * 0.75),
					(float) (_z._pantalla.height * 0.75),
					(float) _z._pantalla.width,
					(float) (_z._pantalla.height * 0.25), paint);

			// Configuración básica del texto
			paint.setColor(Color.GRAY);
			paint.setTextSize(size_text);

			// Pintamos la barra de Infección
			paint.setColor(Color.GRAY);
			paint.setStrokeWidth(1);
			canvas.drawText("Infección", 5, 15, paint);
			canvas.drawRect(0, 20, _z._pantalla.width, 35, paint);

			if (_z._entorno._jugador._infeccion._actual > 0) {
				paint.setStrokeWidth(10);
				canvas.drawRect(0, 25, ((_z._pantalla.width)
						* _z._entorno._jugador._infeccion._actual / 100), 30,
						paint);
				paint.setStrokeWidth(1);
				paint.setColor(Color.BLACK);
				canvas.drawText(
						Integer.toString(_z._entorno._jugador._infeccion._actual),
						((_z._pantalla.width)
								* _z._entorno._jugador._infeccion._actual / 100) - 14,
						32, paint);
			}

			// Pintamos la barra de Stamina
			paint.setColor(Color.GRAY);
			paint.setStrokeWidth(1);
			canvas.drawText("Stamina", 5, 45, paint);
			canvas.drawRect(0, 50, _z._pantalla.width, 65, paint);

			if (_z._entorno._jugador._resistencia._actual > 0) {
				paint.setStrokeWidth(10);
				canvas.drawRect(0, 55, ((_z._pantalla.width)
						* _z._entorno._jugador._resistencia._actual / 100), 60,
						paint);
				paint.setStrokeWidth(1);
				paint.setColor(Color.BLACK);
				canvas.drawText(Integer
						.toString(_z._entorno._jugador._resistencia._actual),
						5, 62, paint);
			}

			paint.setStyle(Style.STROKE);

			paint.setColor(Color.GRAY);
			canvas.drawText(_z._entorno._jugador.toString(), 5, delay_text * 5,
					paint);

			canvas.drawText(
					_z._entorno._jugador._armas.getArma(tipo_arma.CUERPO)
							.toString(), 5, delay_text * 6, paint);
			canvas.drawText(
					_z._entorno._jugador._armas.getArma(tipo_arma.DISTANCIA)
							.toString(), 5, delay_text * 7, paint);
			canvas.drawText(
					_z._entorno._jugador._armas.getArma(tipo_arma.ARROJADIZA)
							.toString(), 5, delay_text * 8, paint);
			canvas.drawText(
					_z._entorno._jugador._armas.getArma(tipo_arma.ESPECIAL)
							.toString(), 5, delay_text * 9, paint);

			canvas.drawText("X - Y - Z: " + _z._acelerometro.toString(), 5,
					delay_text * 10, paint);

			canvas.drawText(
					_z._entorno._npcs._npc[0] != null ? _z._entorno._npcs._npc[0]
							.toString() : "--null--", 5, delay_text * 11, paint);
			canvas.drawText(
					_z._entorno._npcs._npc[1] != null ? _z._entorno._npcs._npc[1]
							.toString() : "--null--", 5, delay_text * 12, paint);
			canvas.drawText(
					_z._entorno._npcs._npc[2] != null ? _z._entorno._npcs._npc[2]
							.toString() : "--null--", 5, delay_text * 13, paint);

			canvas.drawText(
					"Posición de mira (mirando): " + _z._orientacion.mirando(),
					10, delay_text * 15, paint);
			canvas.drawText("Posición de mira (original_azimut): "
					+ _z._orientacion.original_azimut, 10, delay_text * 16,
					paint);
			canvas.drawText("Posición de mira (azimut): "
					+ _z._orientacion.azimut, 10, delay_text * 17, paint);

			//Salida por fichero de log:
			Log.i("Zomblind INFO Jugador", _z._entorno._jugador.toString());
			Log.i("Zomblind INFO Arma cuerpo", _z._entorno._jugador._armas.getArma(tipo_arma.CUERPO).toString());
			Log.i("Zomblind INFO Arma distancia", _z._entorno._jugador._armas.getArma(tipo_arma.DISTANCIA).toString());
			//Log.i("Zomblind INFO Zombie 0", _z._entorno._npcs._npc[0].toString());
			//Log.i("Zomblind INFO Zombie 1", _z._entorno._npcs._npc[1].toString());
			//Log.i("Zomblind INFO Zombie 2", _z._entorno._npcs._npc[2].toString());
			
		}
	}
}
