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

package antares.zomblind.core.levels;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.Xml;
import android.widget.Toast;
import antares.zomblind.R;
import antares.zomblind.ZomblindActivity;
import antares.zomblind.core.levels.generate.*;
import antares.zomblind.core.levels.checker.*;
import antares.zomblind.core.levels.conditions.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.*;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class NivelInfo {
	ZomblindActivity _z;
	public ArrayList<atomic_level> _l = new ArrayList<NivelInfo.atomic_level>();
	int _c = 0;

	public class atomic_level {
		String _mensagge;
		String _condition;
		String _check;
		String _generation;
		public atomic_level(String _mensagge, String _condition, String _comprobation, String _generation) {
			this._mensagge = _mensagge;
			this._condition = _condition;
			this._check = _comprobation;
			this._generation = _generation;
		}
	}

	public NivelInfo(Context ctx) {
		_z = (ZomblindActivity) ctx;
	}
	
	public NivelInfo(Context ctx, String name){
		InputStream fin = null;
		_z = (ZomblindActivity) ctx;
		
		String TAG = "TratamientoXML";
		 StringBuffer sb = new StringBuffer();
		    XmlResourceParser xpp = _z.getResources().getXml(R.xml.level00);
		 
		    int eventType = -1;
		    try {
				xpp.next();
			    eventType = xpp.getEventType();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				Log.e(TAG,e1.getMessage());
			}

		     while (eventType != XmlPullParser.END_DOCUMENT) 
		     {
		         if(eventType == XmlPullParser.START_DOCUMENT) 
		         {
		        	//El comienzo del documento
		            //sb.append("******Start document");
		        	 
		        	 
		        	 
		         } 
		         else if(eventType == XmlPullParser.START_TAG) 
		         {
		            sb.append("nStart tag "+xpp.getName());
		            
		            for (int a=0; a< xpp.getAttributeCount();a++) {
						
						sb.append("=");
						sb.append(xpp.getAttributeIntValue(a,0));
						
						//if("zomblind".equals((xpp.getName())){ 										}
						
					}
		            
		         } 
		         else if(eventType == XmlPullParser.END_TAG) 
		         {
		            sb.append("nEnd tag "+xpp.getName());
		         } 
		         else if(eventType == XmlPullParser.TEXT) 
		         {
		            sb.append("nText "+xpp.getText());
		         }
		         try{
		         eventType = xpp.next();
		         }catch (Exception e) {
					Log.e(TAG,e.getMessage());
				}
		     }//eof-while
		     sb.append("n******End document");
		     Log.v(TAG,sb.toString());
	}

	public void push(String men, String cond, String gen, String check) {
		_l.add(new atomic_level(men, cond, check, gen));

	}

	public void run_check() throws ClassNotFoundException,
			SecurityException, NoSuchMethodException, IllegalArgumentException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException {
		Class c = Class.forName("antares.zomblind.core.levels.checker."
				+ _l.get(_c)._check);
		Constructor constructor = c
				.getConstructor(new Class[] { Context.class });
		_Chequeador _d = (_Chequeador) constructor.newInstance(_z);
		_d.test();
	}

	public void run_generate() throws ClassNotFoundException,
			SecurityException, NoSuchMethodException, IllegalArgumentException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException {
		Class c = Class.forName("antares.zomblind.core.levels.generate."
				+ _l.get(_c)._generation);
		Constructor constructor = c
				.getConstructor(new Class[] { Context.class });
		_Generador _d = (_Generador) constructor.newInstance(_z);
		_d.run();
	}

	public boolean run_condition() throws ClassNotFoundException,
			SecurityException, NoSuchMethodException, IllegalArgumentException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException {
		Class c = Class.forName("antares.zomblind.core.levels.conditions."
				+ _l.get(_c)._condition);
		Constructor constructor = c
				.getConstructor(new Class[] { Context.class });
		_Condicion _d = (_Condicion) constructor.newInstance(_z);
		return _d.test();
	}

	public String get_mensaje() {
		return _l.get(_c)._mensagge;
	}

	public void next() {
		if (_c < _l.size() - 1) {
			_c++;
		}
		;
	}

}
