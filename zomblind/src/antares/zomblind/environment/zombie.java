package antares.zomblind.environment;

import android.content.Context;
import android.media.MediaPlayer;
import antares.zomblind.R;
import antares.zomblind.R.raw;

public class zombie extends MediaPlayer {
	private Context _ctx;
	
	public zombie(Context ctx , int pos){
		_ctx = ctx;
		this.create(_ctx, R.raw.zombie01);
		this.setLooping(false);
		
		if(pos == 0) this.setVolume(1, 1);
		if(pos == -1) this.setVolume(2, 0);
		if(pos == 1) this.setVolume(0, 2);		
		
	}
	
}
