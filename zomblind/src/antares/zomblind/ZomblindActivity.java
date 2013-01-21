package antares.zomblind;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
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

import antares.zomblind.controles.*;

public class ZomblindActivity extends Activity {

	// Clases de control
	public acelerometro _acelerometro = new acelerometro();
	public orientacion _orientacion = new orientacion();
	public pantalla _pantalla = new pantalla(this);
	
	//Clases manejadoras de eventos
	private static SensorManager sensorServiceOrientacion, sensorServiceAcelerometro;
	private Sensor sensorOrientacion, sensorAcelerometro;
	public mInInitListener _speaker = new mInInitListener();

	//Clase de entorno de juego
	entorno _entorno = null;
	
	// Variables booleanas de control
	Boolean debug = true;
	Boolean salir = false;

	// Cadenas auxiliares


	// Objectos activadores de servicios
	public TextToSpeech _talker;

	// Posición Zombie
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
				canvas.drawCircle(_pantalla.x, _pantalla.y, 20, paint);
				paint.setColor(Color.BLACK);
				paint.setTextSize(size_text);
				canvas.drawText("Posición toque pantalla", 10, delay_text,
						paint);
				canvas.drawText("x= " + _pantalla.x, 10, delay_text * 2, paint);
				canvas.drawText("y= " + _pantalla.y, 10, delay_text * 3, paint);
				paint.setTextSize(size_text);
				canvas.drawText("Última acción pantalla", 10, delay_text * 4,
						paint);
				canvas.drawText(_pantalla.action, 10, delay_text * 5, paint);
				canvas.drawText("Posición de mira", 10, delay_text * 6, paint);
				canvas.drawText(_orientacion.toString(), 10, delay_text * 7,
						paint);
				canvas.drawText("Posición de mira (relativa)", 10,
						delay_text * 10, paint);

				canvas.drawText(_orientacion.mirando(), 10, delay_text * 13,
						paint);

				canvas.drawText("Posición de Zombie", 10, delay_text * 14,
						paint);
				canvas.drawText(zombie, 10, delay_text * 15, paint);

				canvas.drawText("Acelerómetro: X - Y - Z", 10, delay_text * 16,
						paint);
				canvas.drawText(_acelerometro.toString(), 10, delay_text * 17,
						paint);
			}

		}

		public boolean onTouchEvent(MotionEvent evento) {
			_pantalla.update(evento);
			invalidate();
			return true;
		}

	}

	CustomDrawableView mCustomDrawableView;



	protected void onCreate(Bundle savedInstanceState) {

		// Creamos la actividad
		super.onCreate(savedInstanceState);

		// Definimos su vista
		mCustomDrawableView = new CustomDrawableView(this);
		setContentView(mCustomDrawableView);

		// Bloqueamos la orientación
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		// Inicializamos el sensor de orientación
		sensorServiceOrientacion = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		sensorOrientacion = sensorServiceOrientacion
				.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		if (sensorOrientacion != null) {
			sensorServiceOrientacion.registerListener(
					mySensorEventListenerOrientacion, sensorOrientacion,
					SensorManager.SENSOR_DELAY_GAME);
			Log.i("Compass MainActivity", "Registerered for ORIENTATION Sensor");

		} else {
			Log.e("Compass MainActivity", "Registerered for ORIENTATION Sensor");
			Toast.makeText(this, "ORIENTATION Sensor not found",
					Toast.LENGTH_LONG).show();
			finish();
		}

		// Inicializamos el sensor de aceleración
		sensorServiceAcelerometro = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		sensorAcelerometro = sensorServiceAcelerometro
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		if (sensorAcelerometro != null) {
			sensorServiceAcelerometro.registerListener(
					mySensorEventListenerAceleracion, sensorAcelerometro,
					SensorManager.SENSOR_DELAY_GAME);
			Log.i("Compass MainActivity",
					"Registerered for ACCELEROMETERSensor");

		} else {
			Log.e("Compass MainActivity",
					"Registerered for ACCELEROMETER Sensor");
			Toast.makeText(this, "ACCELEROMETER Sensor not found",
					Toast.LENGTH_LONG).show();
			finish();
		}

		_entorno = new entorno(ZomblindActivity.this);

		_talker = new TextToSpeech(this, _speaker);
		_speaker.say("Toca la pantalla para calibrar");

	}

	public class mInInitListener implements OnInitListener {

		public void onInit(int arg0) {
			// say("Hola mundo");

		}

		public void say(String text2say) {
			_talker.speak(text2say, TextToSpeech.QUEUE_FLUSH, null);
		}
	};

	private SensorEventListener mySensorEventListenerOrientacion = new SensorEventListener() {

		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}

		public void onSensorChanged(SensorEvent event) {
			if (!_orientacion.isCalibrate()) {
				_orientacion.calibrate(event);
			} else {
				_orientacion.update(event);
				mCustomDrawableView.invalidate();
			}
		}

	};

	private SensorEventListener mySensorEventListenerAceleracion = new SensorEventListener() {

		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}

		public void onSensorChanged(SensorEvent event) {

			synchronized (this) {_acelerometro.update(event);}
			mCustomDrawableView.invalidate();
		}

	};

	// Capturamos las pulsaciones de teclas
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {

			Log.d(this.getClass().getName(), "back button pressed");
			if (salir == true) {
				_entorno._eventos.cancel();
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