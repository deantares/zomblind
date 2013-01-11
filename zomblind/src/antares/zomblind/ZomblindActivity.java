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
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;



/*public class ZomblindActivity extends Activity {
	
	//private msensor _sensor;
	//private entorno _musica;
	//private sensor_pantalla _sensor_pantalla;

	
	
    *//** Called when the activity is first created. *//*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _sensor_pantalla = new sensor_pantalla(this);
        setContentView(_sensor_pantalla);
        _musica = new entorno(ZomblindActivity.this);
        _sensor = new msensor(ZomblindActivity.this);
    }
    
	
	

}*/

 
public class ZomblindActivity extends Activity /*implements SensorEventListener*/ {
  
  Boolean debug = true;
  Boolean calibrado = false;
  Float azimut;  // Orientación
  Float original_azimut; //Orientación de calibrado
  float position_touch_x = 50;
  float position_touch_y = 50;
  
  String action = "";
  
  entorno _entorno = null;
 
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
    	
    	if(debug){
    		int size_text = 15;
    		int delay_text = 18;
    	
		canvas.drawColor(Color.LTGRAY);
		paint.setColor(Color.GREEN);
		canvas.drawCircle(position_touch_x, position_touch_y, 20, paint);
		paint.setColor(Color.BLACK);
		paint.setTextSize(size_text);
		canvas.drawText("Posición toque pantalla",10, delay_text, paint);
		canvas.drawText("x= " +position_touch_x,10, delay_text*2, paint);
		canvas.drawText("y= " +position_touch_y,10, delay_text*3, paint);
		paint.setTextSize(size_text);
		canvas.drawText("Última acción pantalla",10, delay_text*4, paint);
		canvas.drawText(action,10, delay_text*5, paint);
		canvas.drawText("Posición original de mira",10, delay_text*6, paint);
		canvas.drawText(original_azimut.toString(), 10, delay_text*7, paint);
		canvas.drawText("Posición de mira",10, delay_text*8, paint);
		canvas.drawText(azimut.toString(), 10, delay_text*9, paint);
		
		canvas.drawText("Posición de mira (relativa)",10, delay_text*10, paint);
		canvas.drawText(Float.toString(azimut - original_azimut), 10, delay_text*11, paint);
		
		canvas.drawText("Posición de mira (entera)",10, delay_text*12, paint);
		String aux = "";
		if((azimut - original_azimut)<0.5){
			aux = "izquierda";
		}else if((azimut - original_azimut)>0.5){
			
		}
		canvas.drawText(aux, 10, delay_text*13, paint);
    	}
      
    }
    
	public boolean onTouchEvent(MotionEvent evento){
		action=String.valueOf(evento.getAction());
		if(evento.getAction()==MotionEvent.ACTION_DOWN){
			position_touch_x=evento.getX();
			position_touch_y=evento.getY();
			Log.v("Entorno", position_touch_x+","+position_touch_y);
			if(calibrado==false){calibrado=true; original_azimut=azimut;}
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
 
  CustomDrawableView mCustomDrawableView;
//  private SensorManager mSensorManager;
 // Sensor accelerometer;
//  Sensor magnetometer;
 
  private static SensorManager sensorService;
  private Sensor sensor;
  
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    _entorno = new entorno(ZomblindActivity.this);
    mCustomDrawableView = new CustomDrawableView(this);
    setContentView(mCustomDrawableView);    // Register the sensor listeners
//    mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
//    accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//    magnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
      sensorService = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
      sensor = sensorService.getDefaultSensor(Sensor.TYPE_ORIENTATION);
    if (sensor != null) {
      sensorService.registerListener(mySensorEventListener, sensor,
          SensorManager.SENSOR_DELAY_NORMAL);
      Log.i("Compass MainActivity", "Registerered for ORIENTATION Sensor");

    } else {
      Log.e("Compass MainActivity", "Registerered for ORIENTATION Sensor");
      //Toast.makeText(this, "ORIENTATION Sensor not found",
      //    Toast.LENGTH_LONG).show();
      finish();
    }
    
  }
  
  private SensorEventListener mySensorEventListener = new SensorEventListener() {

	    public void onAccuracyChanged(Sensor sensor, int accuracy) {
	    }

	    public void onSensorChanged(SensorEvent event) {
	      // angle between the magnetic north directio
	      // 0=North, 90=East, 180=South, 270=West
	      // azimut = event.values[0];
	      //compassView.updateData(azimuth);
	      
	      if(original_azimut==null){ original_azimut = event.values[0]; // orientation contains: azimut, pitch and roll}
        }else {azimut = event.values[0];}
	      mCustomDrawableView.invalidate();
	    }
	    
	    
	  };
  
 
//  protected void onResume() {
//    super.onResume();
//    mSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
//    mSensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_GAME);
//  }
// 
//  protected void onPause() {
//    super.onPause();
//    mSensorManager.unregisterListener(this);
//  }
// 
//  public void onAccuracyChanged(Sensor sensor, int accuracy) {  }
// 
//  float[] mGravity;
//  float[] mGeomagnetic;
//  public void onSensorChanged(SensorEvent event) {
//	  synchronized (this) {
//    if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
//      mGravity = event.values;
//    if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
//      mGeomagnetic = event.values;
//    if (mGravity != null && mGeomagnetic != null) {
//      float R[] = new float[9];
//      float I[] = new float[9];
//      boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic);
//      if (success) {
//        float orientation[] = new float[3];
//        SensorManager.getOrientation(R, orientation);
//        if(original_azimut==null){ original_azimut = orientation[0]; // orientation contains: azimut, pitch and roll}
//        }else {azimut = orientation[0];}
//        
//      }
//    }
//    mCustomDrawableView.invalidate();
//	  }
//  }
}