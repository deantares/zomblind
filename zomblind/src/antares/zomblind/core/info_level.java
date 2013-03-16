package antares.zomblind.core;

import java.util.ArrayList;

public class info_level {
	public class atomic_level{
		String _m;
		String _c;
		String _g;
		
		public atomic_level(String _m, String _c, String _g) {
			this._m = _m;
			this._c = _c;
			this._g = _g;
		}

	}
	
	public ArrayList<atomic_level> _l = new ArrayList<info_level.atomic_level>();
	int _c = 0;

	public info_level() {
		super();
	}

	public void push(String m, String c, String g) {
		_l.add(new atomic_level(m, c, g));
		
	}
	

}
