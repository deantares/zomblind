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
