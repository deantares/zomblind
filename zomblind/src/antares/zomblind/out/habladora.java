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
package antares.zomblind.out;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import antares.zomblind.ZomblindActivity;

public class habladora implements OnInitListener {
	
	ZomblindActivity _z;
	
	public habladora (Context ctx){
		_z = (ZomblindActivity) ctx;
	}

	public void onInit(int arg0) {
		
		
	}

	public void say(String text2say) {
		_z._talker.speak(text2say, TextToSpeech.QUEUE_FLUSH, null);
	}
};
