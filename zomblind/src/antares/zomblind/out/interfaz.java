package antares.zomblind.out;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.MotionEvent;
import android.view.View;
import antares.zomblind.ZomblindActivity;

public class interfaz extends View {
	private ZomblindActivity _z;
	Paint paint = new Paint();
	
	public interfaz(Context context) {
		super(context);
		_z = (ZomblindActivity) context;
		paint.setColor(0xff00ff00);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(2);
		paint.setAntiAlias(true);
	};

	protected void onDraw(Canvas canvas) {
		_z._debug.update(canvas, paint);
	}

	public boolean onTouchEvent(MotionEvent evento) {
		_z._pantalla.update(evento);
		invalidate();
		return true;
	}

}