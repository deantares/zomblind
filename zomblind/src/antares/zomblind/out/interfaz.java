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
		this.setKeepScreenOn(true);
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
