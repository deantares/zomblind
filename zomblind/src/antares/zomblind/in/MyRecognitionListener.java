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
package antares.zomblind.in;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.util.Log;
import antares.zomblind.ZomblindActivity;

public class MyRecognitionListener implements RecognitionListener{
	private ZomblindActivity _z;
	
	
	public MyRecognitionListener(Context ctx){
		_z = (ZomblindActivity) ctx;
	}

	public void onBeginningOfSpeech() {
		// TODO Auto-generated method stub
		  Log.d("Speech", "onBeginningOfSpeech");
	}

	public void onBufferReceived(byte[] buffer) {
		// TODO Auto-generated method stub
		  Log.d("Speech", "onBufferReceived");

	}

	public void onEndOfSpeech() {
		// TODO Auto-generated method stub
		  Log.d("Speech", "onEndOfSpeech");
		  
		
	}

	public void onError(int error) {
		// TODO Auto-generated method stub
		  Log.d("Speech", "onError");
		  

		
	}

	public void onEvent(int eventType, Bundle params) {
		// TODO Auto-generated method stub
		  Log.d("Speech", "onEvent");

		
	}

	public void onPartialResults(Bundle partialResults) {
		// TODO Auto-generated method stub
		  Log.d("Speech", "onPartialResults");

		
	}

	public void onReadyForSpeech(Bundle params) {
		// TODO Auto-generated method stub
		  Log.d("Speech", "onReadyForSpeech");

		
	}

	public void onResults(Bundle results) {
		// TODO Auto-generated method stub
		  Log.d("Speech", "onResults");
		  ArrayList strlist = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
		  for (int i = 0; i < strlist.size();i++ ) {
		   Log.d("Speech", "resultadomolon=" + strlist.get(i));
		  }
		  
		  _z.oido = (String) strlist.get(0);
		  //_z._sr.cancel();
	}

	public void onRmsChanged(float rmsdB) {
		// TODO Auto-generated method stub
		  Log.d("Speech", "onRmsChanged");

		
	}

}
