package antares.zomblind.core.levels.conditions;

import android.content.Context;
import antares.zomblind.ZomblindActivity;
import antares.zomblind.core.items.Armas.tipo_arma;

public class _conditions_no_zombies_in_game implements _conditions{
	ZomblindActivity _z;

	public _conditions_no_zombies_in_game(Context ctx) {
		_z = (ZomblindActivity) ctx;
	}
	
	public boolean test(){
		boolean res = false;

		return _z._entorno._npcs.noZombieInGame();

	}

}
