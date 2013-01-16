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

public class ZomblindActivity extends Activity {

	// Variables booleanas de control
	Boolean debug = true;
	Boolean calibrado = false;
	Boolean salir = false;

	// Variables de sensores: Orientaci�n
	Float azimut; // Orientaci�n
	Float original_azimut; // Orientaci�n de calibrado
	float position_touch_x = 50;
	float position_touch_y = 50;

	// Variables de sensores: Aceler�metro
	float last_update = 0, last_movement = 0;
	float prevX = 0, prevY = 0, prevZ = 0;
	float curX = 0, curY = 0, curZ = 0;
	String res_acelerometro = "";
	String res_acelerometroX = "";
	String res_acelerometroY = "";
	String res_acelerometroZ = "";
	String res_acelerometro_anterior = "";

	// Cadenas auxiliares
	String action = "";
	entorno _entorno = null;

	// Objectos activadores de servicios
	public TextToSpeech _talker;

	// Posici�n Zombie
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
				canvas.drawText("Posici�n toque pantalla", 10, delay_text,
						paint);
				canvas.drawText("x= " + position_touch_x, 10, delay_text * 2,
						paint);
				canvas.drawText("y= " + position_touch_y, 10, delay_text * 3,
						paint);
				paint.setTextSize(size_text);
				canvas.drawText("�ltima acci�n pantalla", 10, delay_text * 4,
						paint);
				canvas.drawText(action, 10, delay_text * 5, paint);
				canvas.drawText("Posici�n original de mira", 10,
						delay_text * 6, paint);
				canvas.drawText(original_azimut.toString(), 10, delay_text * 7,
						paint);
				canvas.drawText("Posici�n de mira", 10, delay_text * 8, paint);
				canvas.drawText(azimut.toString(), 10, delay_text * 9, paint);

				canvas.drawText("Posici�n de mira (relativa)", 10,
						delay_text * 10, paint);
				float aux = (azimut - original_azimut) % 360;
				float aux_r = aux < 0 ? aux + 360 : aux;
				canvas.drawText(Float.toString(aux_r), 10, delay_text * 11,
						paint);

				canvas.drawText("Posici�n de mira (entera)", 10,
						delay_text * 12, paint);
				String aux2 = "";
				if ((aux_r) < 180 - 30) {
					aux2 = "izquierda";
				} else if ((aux_r) > 180 + 30) {
					aux2 = "derecha";
				} else {
					aux2 = "centro";
				}
				canvas.drawText(aux2, 10, delay_text * 13, paint);

				canvas.drawText("Posici�n de Zombie", 10, delay_text * 14,
						paint);
				canvas.drawText(zombie, 10, delay_text * 15, paint);

				canvas.drawText("Aceler�metro: X - Y - Z", 10, delay_text * 16,
						paint);
				canvas.drawText(curX + " - " + curY + " - " + curZ, 10,
						delay_text * 17, paint);
				canvas.drawText(res_acelerometro + "( " + res_acelerometro_anterior + " )", 10, delay_text * 18, paint);
				canvas.drawText(res_acelerometroX, 10, delay_text * 19, paint);
				canvas.drawText(res_acelerometroY, 10, delay_text * 20, paint);
				canvas.drawText(res_acelerometroZ, 10, delay_text * 21, paint);
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

	private static SensorManager sensorServiceOrientacion,
			sensorServiceAcelerometro;
	private Sensor sensorOrientacion, sensorAcelerometro;
	public mInInitListener _speaker = new mInInitListener();

	protected void onCreate(Bundle savedInstanceState) {

		// Creamos la actividad
		super.onCreate(savedInstanceState);

		// Definimos su vista
		mCustomDrawableView = new CustomDrawableView(this);
		setContentView(mCustomDrawableView);

		// Bloqueamos la orientaci�n
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		// Inicializamos el sensor de orientaci�n
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

		// Inicializamos el sensor de aceleraci�n
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

	private class mInInitListener implements OnInitListener {

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
			synchronized (this) {
				if (original_azimut == null) {
					original_azimut = event.values[0]; // orientation contains:
														// azimut, pitch and
														// roll}
				} else {
					azimut = event.values[0];
				}
				mCustomDrawableView.invalidate();
			}
		}

	};

	private SensorEventListener mySensorEventListenerAceleracion = new SensorEventListener() {

		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}

		public void onSensorChanged(SensorEvent event) {

			synchronized (this) {

				long current_time = event.timestamp;

				curX = event.values[0];
				curY = event.values[1];
				curZ = event.values[2];

				if (prevX == 0 && prevY == 0 && prevZ == 0) {
					last_update = current_time;
					last_movement = current_time;
					prevX = curX;
					prevY = curY;
					prevZ = curZ;
				}

				long time_difference = (long) (current_time - last_update);
				if (time_difference > 0) {
					// float movement_abs = Math.abs((curX + curY + curZ) -
					// (prevX - prevY - prevZ)) / time_difference;
					float movement_X = Math.abs(curX - prevX) / time_difference;
					boolean direcion_X = curX > prevX ? true : false;
					float movement_Y = Math.abs(curY - prevY) / time_difference;
					boolean direcion_Y = curX > prevY ? true : false;
					float movement_Z = Math.abs(curZ - prevZ) / time_difference;
					boolean direcion_Z = curX > prevZ ? true : false;

					int limit = 1500;

					// Sensibilidad del sensor. Cuanto "mayor" valor, mayor movimiento se necesitar� para detectar un cambio
					float min_movement = 0.00000015f; // 2f ;//1E-6f;

					// if (movement_abs > min_movement) {
					// if (current_time - last_movement >= limit) {
					// //Toast.makeText(getApplicationContext(),
					// "Hay movimiento de " + movement,
					// Toast.LENGTH_SHORT).show();
					// res_acelerometro = "Movimiento de " + movement_abs;
					// res_acelerometroX = "X : " + movement_X + " " +
					// direcion_X;
					// res_acelerometroY = "Y : " + movement_Y + " " +
					// direcion_Y;
					// res_acelerometroZ = "Z : " + movement_Z + " " +
					// direcion_Z;
					//
					// }
					// last_movement = current_time;
					// }

					// Nos tenemos que quedar con el "movimiento mayor"

					if (movement_X > movement_Y) {
						if (movement_X > movement_Z) {
							// Movement_X el mayor
							if (movement_X > min_movement) {
								res_acelerometro = "Movimiento de " + (direcion_X == true ? "+" : "-") + "X";
							}else{
								res_acelerometro += "�" ;
							}

						} else {
							// Movement_Z el mayor
							if (movement_Z > min_movement) {
								res_acelerometro = "Movimiento de " + (direcion_Z == true ? "+" : "-") + "Z";
							}else{
								res_acelerometro += "�" ;
							}
						}
					} else {
						if (movement_Y > movement_Z) {
							// Movement_Y el mayor
							if (movement_Y > min_movement) {
								res_acelerometro = "Movimiento de " + (direcion_Y == true ? "+" : "-") + "Y";
							}else{
								res_acelerometro += "�" ;
							}
						} else {
							// Movement_Z el mayor
							if (movement_Z > min_movement) {
								res_acelerometro = "Movimiento de " + (direcion_Z == true ? "+" : "-") + "Z";
							}else{
								res_acelerometro += "�" ;
							}
						}

					}

//					if (movement_X > min_movement) {
//						if (current_time - last_movement >= limit) {
//							// Toast.makeText(getApplicationContext(),
//							// "Hay movimiento de " + movement,
//							// Toast.LENGTH_SHORT).show();
//							res_acelerometro = "Movimiento de "
//									+ (direcion_X == true ? "+" : "-") + "X";
//
//						}
//						last_movement = current_time;
//					} else if (movement_Y > min_movement) {
//						if (current_time - last_movement >= limit) {
//							// Toast.makeText(getApplicationContext(),
//							// "Hay movimiento de " + movement,
//							// Toast.LENGTH_SHORT).show();
//							res_acelerometro = "Movimiento de "
//									+ (direcion_Y == true ? "+" : "-") + "Y";
//
//						}
//						last_movement = current_time;
//					} else if (movement_Z > min_movement) {
//						if (current_time - last_movement >= limit) {
//							// Toast.makeText(getApplicationContext(),
//							// "Hay movimiento de " + movement,
//							// Toast.LENGTH_SHORT).show();
//							res_acelerometro = "Movimiento de "
//									+ (direcion_Z == true ? "+" : "-") + "Z";
//
//						}
//						last_movement = current_time;
//					} else {
//						if (current_time - last_movement >= limit) {
//							// Toast.makeText(getApplicationContext(),
//							// "Hay movimiento de " + movement,
//							// Toast.LENGTH_SHORT).show();
//							// res_acelerometro = "Sin movimiento";
//
//						}
//					}

					prevX = curX;
					prevY = curY;
					prevZ = curZ;
					last_update = current_time;
				}

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

	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	public void onSensorChanged(SensorEvent arg0) {
		// TODO Auto-generated method stub

	}

}