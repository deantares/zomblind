package antares.zomblind.out;

import android.content.Context;
import android.os.Vibrator;
import antares.zomblind.ZomblindActivity;

public class vibrador{

	private ZomblindActivity _z;
	private Vibrator _v;
	
	public vibrador(Context ctx){
		_z = (ZomblindActivity) ctx;
		_v = (Vibrator)_z.getSystemService(Context.VIBRATOR_SERVICE);
	}
	
	public void vibrar(long dura){
		_v.vibrate(dura);
	}
	
	public void vibrar_golpe(){
		_v.vibrate(100);
	}
	
	//Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

}
