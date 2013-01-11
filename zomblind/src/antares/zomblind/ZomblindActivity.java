package antares.zomblind;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class ZomblindActivity extends Activity {

	
	//Variables booleanas de control
	Boolean debug = true;
	Boolean calibrado = false;
	Boolean salir = false;

	//Variables de sensores
	Float azimut; // Orientación
	Float original_azimut; // Orientación de calibrado
	float position_touch_x = 50;
	float position_touch_y = 50;

	//Cadenas auxiliares
	String action = "";
	entorno _entorno = null;
	
	//Objectos activadores de servicios
	public TextToSpeech _talker;
	
	//Posición Zombie
	String zombie = "";

	public class CustomDrawableView extends View {
		Paint paint = new Paint();

		public CustomDrawableView(Context context) {
			super(context);
			paint.setColor(0xff00ff00);
			paint.setStyle(Style.STROKE);
			paint.setStrokeWidth(2);
			paint.setAntiAlias(true);
		};

		protected void onDraw(Canvas canvas) {

			if (debug) {
				int size_text = 15;
				int delay_text = 18;

				canvas.drawColor(Color.LTGRAY);
				paint.setColor(Color.GREEN);
				canvas.drawCircle(position_touch_x, position_touch_y, 20, paint);
				paint.setColor(Color.BLACK);
				paint.setTextSize(size_text);
				canvas.drawText("Posición toque pantalla", 10, delay_text,
						paint);
				canvas.drawText("x= " + position_touch_x, 10, delay_text * 2,
						paint);
				canvas.drawText("y= " + position_touch_y, 10, delay_text * 3,
						paint);
				paint.setTextSize(size_text);
				canvas.drawText("Última acción pantalla", 10, delay_text * 4,
						paint);
				canvas.drawText(action, 10, delay_text * 5, paint);
				canvas.drawText("Posición original de mira", 10,
						delay_text * 6, paint);
				canvas.drawText(original_azimut.toString(), 10, delay_text * 7,
						paint);
				canvas.drawText("Posición de mira", 10, delay_text * 8, paint);
				canvas.drawText(azimut.toString(), 10, delay_text * 9, paint);

				canvas.drawText("Posición de mira (relativa)", 10,
						delay_text * 10, paint);
				canvas.drawText(Float.toString(azimut - original_azimut), 10,
						delay_text * 11, paint);

				canvas.drawText("Posición de mira (entera)", 10,
						delay_text * 12, paint);
				String aux = "";
				if ((azimut - original_azimut) < 0.5) {
					aux = "izquierda";
				} else if ((azimut - original_azimut) > 0.5) {

				}
				canvas.drawText(aux, 10, delay_text * 13, paint);
				
				canvas.drawText("Posición de Zombie", 10,
						delay_text * 14, paint);
				canvas.drawText(zombie, 10,
						delay_text * 15, paint);
			}

		}

		public boolean onTouchEvent(MotionEvent evento) {
			action = String.valueOf(evento.getAction());
			if (evento.getAction() == MotionEvent.ACTION_DOWN) {
				position_touch_x = evento.getX();
				position_touch_y = evento.getY();
				Log.v("Entorno", position_touch_x + "," + position_touch_y);
				if (calibrado == false) {
					_speaker.say("Dispositivo calibrado");
					calibrado = true;
					original_azimut = azimut;
				}
				invalidate();
			} else if (evento.getAction() == MotionEvent.EDGE_BOTTOM) {
				position_touch_x = evento.getX();
				position_touch_y = evento.getY();
				Log.v("Entorno", position_touch_x + "," + position_touch_y);
				invalidate();
			}
			invalidate();

			calibrado = true;
			return true;

		}

	}

	CustomDrawableView mCustomDrawableView;

	private static SensorManager sensorService;
	private Sensor sensor;
	public mInInitListener _speaker = new mInInitListener();

	protected void onCreate(Bundle savedInstanceState) {
		
		//Creamos la actividad
		super.onCreate(savedInstanceState);
		
		//Definimos su vista
		mCustomDrawableView = new CustomDrawableView(this);
		setContentView(mCustomDrawableView);
		
		//Inicializamos el sensor
		sensorService = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		sensor = sensorService.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		if (sensor != null) {
			sensorService.registerListener(mySensorEventListener, sensor,
					SensorManager.SENSOR_DELAY_NORMAL);
			Log.i("Compass MainActivity", "Registerered for ORIENTATION Sensor");

		} else {
			Log.e("Compass MainActivity", "Registerered for ORIENTATION Sensor");
			Toast.makeText(this, "ORIENTATION Sensor not found",
					Toast.LENGTH_LONG).show();
			finish();
		}


		
		_entorno = new entorno(ZomblindActivity.this);
		
		_talker = new TextToSpeech(this, _speaker);
		_speaker.say("Toca la pantalla para calibrar");

	}

	private class mInInitListener implements OnInitListener {

		public void onInit(int arg0) {
			// say("Hola mundo");

		}

		public void say(String text2say) {
			_talker.speak(text2say, TextToSpeech.QUEUE_FLUSH, null);
		}
	};

	private SensorEventListener mySensorEventListener = new SensorEventListener() {

		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}

		public void onSensorChanged(SensorEvent event) {
			if (original_azimut == null) {
				original_azimut = event.values[0]; // orientation contains:
													// azimut, pitch and roll}
			} else {
				azimut = event.values[0];
			}
			mCustomDrawableView.invalidate();
		}

	};

	// Capturamos las pulsaciones de teclas
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {

			Log.d(this.getClass().getName(), "back button pressed");
			if (salir == true) {
				finish();
			} else {
				_speaker.say(getString(R.string.speaker_button_back_exit));

				salir = true;
				return true;
			}
		} else if ((keyCode == KeyEvent.KEYCODE_MENU)) {

			Log.d(this.getClass().getName(), "menu button pressed");
			debug = !debug;
		}
		return super.onKeyDown(keyCode, event);
	}

}