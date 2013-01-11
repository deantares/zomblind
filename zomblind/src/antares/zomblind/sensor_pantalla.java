package antares.zomblind;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class sensor_pantalla extends View{
	
	float sx,sy,sz;
	
	String action = "";

	//Salidas por pantalla:
	float position_touch_x = 50;
	float position_touch_y = 50;
	
	
	public sensor_pantalla(Context context) {
		super(context);
	}
	
	protected void onDraw(Canvas canvas){
		canvas.drawColor(Color.LTGRAY);
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.GREEN);
		canvas.drawCircle(position_touch_x, position_touch_y, 20, paint);
		paint.setColor(Color.BLACK);
		paint.setTextSize(35);
		canvas.drawText("x= " +position_touch_x,100, 50, paint);
		canvas.drawText("y= " +position_touch_y,100, 90, paint);
		paint.setTextSize(10);
		canvas.drawText(action,10, 130, paint);
		//canvas.drawText(_sensor.toString(),10,150, paint);
		
		
	}
	
	public boolean onTouchEvent(MotionEvent evento){
		action=String.valueOf(evento.getAction());
		if(evento.getAction()==MotionEvent.ACTION_DOWN){
			position_touch_x=evento.getX();
			position_touch_y=evento.getY();
			Log.v("Entorno", position_touch_x+","+position_touch_y);
			invalidate();
		}else if(evento.getAction()==MotionEvent.EDGE_BOTTOM){
			position_touch_x=evento.getX();
			position_touch_y=evento.getY();
			Log.v("Entorno", position_touch_x+","+position_touch_y);
			invalidate();
		}
			invalidate();
		
		return true;
		
	}
	
}
