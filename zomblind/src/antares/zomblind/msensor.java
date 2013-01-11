package antares.zomblind;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class msensor extends Activity implements SensorEventListener {
	
	private SensorManager sensorManager;

	float xCoor; // declare X axis object
	float yCoor; // declare Y axis object
	float zCoor; // declare Z axis object
	
	private Context _context;
	
	public msensor( Context contexto){
		_context = contexto;
	}

	@Override
	public void onCreate(Bundle savedInstanceState){

		super.onCreate(savedInstanceState);

		sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
		// add listener. The listener will be HelloAndroid (this) class
		sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_GAME);

		/*	More sensor speeds (taken from api docs)
		    SENSOR_DELAY_FASTEST get sensor data as fast as possible
		    SENSOR_DELAY_GAME	rate suitable for games
		 	SENSOR_DELAY_NORMAL	rate (default) suitable for screen orientation changes
		*/
	}

	public void onAccuracyChanged(Sensor sensor,int accuracy){

	}

	public void onSensorChanged(SensorEvent event){

		// check sensor type
	//	if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){

			// assign directions
			xCoor=event.values[0];
			yCoor=event.values[1];
			zCoor=event.values[2];
	//	}
	}
	
	public String toString(){
		return xCoor + "-" + yCoor + "-" + zCoor;
		
	}
}
