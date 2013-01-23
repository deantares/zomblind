/*******************************************************************************
 * Copyright 2013 antares
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
package antares.zomblind.core;

import android.content.Context;
import android.media.MediaPlayer;
import antares.zomblind.R;

public class zombie extends MediaPlayer {
	private Context _ctx;
	
	public zombie(Context ctx , int pos){
		_ctx = ctx;
		MediaPlayer.create(_ctx, R.raw.zombie01);
		this.setLooping(false);
		
		if(pos == 0) this.setVolume(1, 1);
		if(pos == -1) this.setVolume(2, 0);
		if(pos == 1) this.setVolume(0, 2);		
		
	}
	
}
