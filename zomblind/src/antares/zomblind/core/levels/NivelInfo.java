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
import antares.zomblind.ZomblindActivity;
import antares.zomblind.core.levels.generadores.*;
import antares.zomblind.core.levels.chequeadores.*;
import antares.zomblind.core.levels.condiciones.*;

import java.lang.reflect.*;

public class NivelInfo {
	ZomblindActivity _z;
	public ArrayList<atomic_level> _l = new ArrayList<NivelInfo.atomic_level>();
	int _c = 0;

	public class atomic_level {
		String _mensagge;
		String _condition;
		String _comprobation;
		String _generation;

		public atomic_level(String _m, String _c, String _g) {
			this._mensagge = _m;
			this._condition = _c;
			this._generation = _g;
		}

	}

	public NivelInfo(Context ctx) {
		_z = (ZomblindActivity) ctx;
	}

	public void push(String m, String c, String g) {
		_l.add(new atomic_level(m, c, g));

	}

	public void run_comprobation() throws ClassNotFoundException,
			SecurityException, NoSuchMethodException, IllegalArgumentException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException {
		Class c = Class.forName("antares.zomblind.core.levels.comprobation."
				+ _l.get(_c)._comprobation);
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
