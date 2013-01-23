package antares.zomblind.core;

import java.util.ArrayList;
import java.util.List;

public class npcLista {
	
	List<npc> _npcs;
	
	public npcLista(){
		_npcs = new ArrayList<npc>();
	}
	
	public void acercar(){
		for(npc t : _npcs){
			t.acercar();
		}
	}

}
