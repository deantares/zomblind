package antares.zomblind;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import antares.zomblind.core.*;
import antares.zomblind.in.*;
import antares.zomblind.out.*;

public class ZomblindActivity extends Activity {

	// Clases de control de entrada IN
	public acelerometro _acelerometro = new acelerometro(this);
	public orientacion _orientacion = new orientacion(this);
	public pantalla _pantalla = new pantalla(this);

	// Clases de control de salida OUT
	public debug _debug = new debug(this);
	public interfaz _interfaz;
	public habladora _habladora = new habladora(this);
	public TextToSpeech _talker;

	// Clases manejadoras de eventos
	private static SensorManager _sensorServiceOrientacion,
			_sensorServiceAcelerometro;
	private Sensor _sensorOrientacion, _sensorAcelerometro;

	// Clase de entorno de juego
	entorno _entorno = null;

	// Variables booleanas de control
	Boolean salir = false;

	// Posición Zombie
	public String zombie = "";

	protected void onCreate(Bundle savedInstanceState) {
		// Creamos la actividad
		super.onCreate(savedInstanceState);

		_interfaz = new interfaz(this);
		// Definimos su vista
		setContentView(_interfaz);

		// Bloqueamos la orientación
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		// Inicializamos el sensor de orientación
		_sensorServiceOrientacion = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		_sensorOrientacion = _sensorServiceOrientacion
				.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		if (_sensorOrientacion != null) {
			_sensorServiceOrientacion.registerListener(
					mySensorEventListenerOrientacion, _sensorOrientacion,
					SensorManager.SENSOR_DELAY_GAME);
			Log.i("Compass MainActivity", "Registerered for ORIENTATION Sensor");

		} else {
			Log.e("Compass MainActivity", "Registerered for ORIENTATION Sensor");
			Toast.makeText(this, "ORIENTATION Sensor not found",
					Toast.LENGTH_LONG).show();
			finish();
		}

		// Inicializamos el sensor de aceleración
		_sensorServiceAcelerometro = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		_sensorAcelerometro = _sensorServiceAcelerometro
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		if (_sensorAcelerometro != null) {
			_sensorServiceAcelerometro.registerListener(
					mySensorEventListenerAceleracion, _sensorAcelerometro,
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
		_talker = new TextToSpeech(this, _habladora);

	}

	private SensorEventListener mySensorEventListenerOrientacion = new SensorEventListener() {

		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}

		public void onSensorChanged(SensorEvent event) {
			if (!_orientacion.isCalibrate()) {
				_orientacion.calibrate(event);
			} else {
				_orientacion.update(event);
				_interfaz.invalidate();
			}
		}

	};

	private SensorEventListener mySensorEventListenerAceleracion = new SensorEventListener() {

		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}

		public void onSensorChanged(SensorEvent event) {

			synchronized (this) {
				_acelerometro.update(event);
			}
			_interfaz.invalidate();
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
				_habladora.say(getString(R.string.speaker_button_back_exit));
				salir = true;
				return true;
			}
		} else if ((keyCode == KeyEvent.KEYCODE_MENU)) {

			Log.d(this.getClass().getName(), "menu button pressed");
			_debug.change();
		}
		return super.onKeyDown(keyCode, event);
	}

}