package antares.zomblind.core.levels;

import java.util.List;


//Una instancia define un "momento" en el capítulo
public class instancia {
	public class transicion{
		int audio_main = -1;
		int audio_aux = -1;
		long[] vibra = {};
	}
	
	List<transicion> _t = null;
	String _condition;
	String _check;
	String _generation;
	
}
