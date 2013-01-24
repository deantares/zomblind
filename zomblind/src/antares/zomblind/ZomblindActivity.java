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
	public pantalla _pantalla;// = new pantalla(this);

	// Clases de control de salida OUT
	public debug _debug = new debug(this);
	public interfaz _interfaz;
	public habladora _habladora = new habladora(this);
	public TextToSpeech _talker;
	public vibrador _vibrador;

	// Clases manejadoras de eventos
	private static SensorManager _sensorServiceOrientacion,
			_sensorServiceAcelerometro;
	private Sensor _sensorOrientacion, _sensorAcelerometro;

	// Clase de entorno de juego
	public entorno _entorno = null;

	// Variables booleanas de control
	public Boolean salir = false;
	public Boolean empezar = false;
	public Boolean muerto = false;
	

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
		
		_pantalla = new pantalla(this);
		_vibrador = new vibrador(this);

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
		}else if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP)) {
			Log.i(this.getClass().getName(), "Volume Up button pressed");
			//_sr.startListening(RecognizerIntent.getVoiceDetailsIntent(getApplicationContext()));
			//_sr.setRecognitionListener(_listener);
			
			//_sr.stopListening();
			//_sr.cancel();
			return true;
			
			
		}else if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)) {
			Log.i(this.getClass().getName(), "Volume Down button pressed");
			//_sr.destroy();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	public boolean onKeyUp(int keyCode, KeyEvent event){
		if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP)) {
			Log.i(this.getClass().getName(), "Volume Up button release");
			//_sr.startListening(RecognizerIntent.getVoiceDetailsIntent(getApplicationContext()));
			//_sr.setRecognitionListener(_listener);
			
			//_sr.startListening(RecognizerIntent.getVoiceDetailsIntent(getApplicationContext()));
			//_sr.cancel();
			return true;
			
			
		}
		
		return super.onKeyUp(keyCode, event);
		
	}

}
