package antares.zomblind.core.levels.conditions;

import android.content.Context;
import antares.zomblind.ZomblindActivity;

public class SinZombies implements _Condicion{
	ZomblindActivity _z;

	public SinZombies(Context ctx) {
		_z = (ZomblindActivity) ctx;
	}
	
	public boolean test(){
		return _z._entorno._npcs.noZombieInGame();
	}

}
