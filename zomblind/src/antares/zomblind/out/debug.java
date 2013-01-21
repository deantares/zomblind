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
		canvas.drawText("x= " + _z._pantalla.x, 10, delay_text * 2, paint);
		canvas.drawText("y= " + _z._pantalla.y, 10, delay_text * 3, paint);
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
		}
	}
}
