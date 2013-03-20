package antares.zomblind.core;

import java.util.ArrayList;

import android.content.Context;
import antares.zomblind.ZomblindActivity;
import antares.zomblind.core.levels.generate.*;
import antares.zomblind.core.levels.conditions.*;
import java.lang.reflect.*;


public class info_level {
	ZomblindActivity _z;
	
	public class atomic_level{
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
	
	public ArrayList<atomic_level> _l = new ArrayList<info_level.atomic_level>();
	int _c = 0;

	public info_level(Context ctx) {
		_z = (ZomblindActivity) ctx;
	}

	public void push(String m, String c, String g) {
		_l.add(new atomic_level(m, c, g));
		
	}

	public void run_comprobation() throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException{
		Class c = Class.forName("antares.zomblind.core.levels.comprobation."+_l.get(_c)._comprobation);
		Constructor constructor = c.getConstructor(new Class[]{Context.class});
		_generate _d = (_generate) constructor.newInstance(_z);
		//_generate _d = (_generate) c.newInstance();
		_d.run();
	}
	
	public void run_generate() throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException{
		Class c = Class.forName("antares.zomblind.core.levels.generate."+_l.get(_c)._generation);
		Constructor constructor = c.getConstructor(new Class[]{Context.class});
		_generate _d = (_generate) constructor.newInstance(_z);
		//_generate _d = (_generate) c.newInstance();
		_d.run();
	}
	
	public boolean run_condition() throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException{
		Class c = Class.forName("antares.zomblind.core.levels.conditions."+_l.get(_c)._condition);
		Constructor constructor = c.getConstructor(new Class[]{Context.class});
		_conditions _d = (_conditions) constructor.newInstance(_z);
		return _d.test();
	}
	
	public String get_mensaje(){
		return _l.get(_c)._mensagge;
	}
	
	public void next(){
		if (_c<_l.size()-1){_c++;};
	}

}
