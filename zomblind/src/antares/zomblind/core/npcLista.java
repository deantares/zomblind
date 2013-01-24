package antares.zomblind.core;

import java.util.ArrayList;
import java.util.List;

public class npcLista {
	
	List<npc> _npcs_izq;
	List<npc> _npcs_centro;
	List<npc> _npcs_der;
	
	public npcLista(){
		_npcs_izq = new ArrayList<npc>();
		_npcs_centro = new ArrayList<npc>();
		_npcs_der = new ArrayList<npc>();
	}
	
	public void acercar(){
		for(npc t : _npcs_izq){
			t.acercar();
		}
		for(npc t : _npcs_centro){
			t.acercar();
		}
		for(npc t : _npcs_der){
			t.acercar();
		}
	}
	
	public void play(){
		for(npc t : _npcs_izq){
			t.play();
		}
		for(npc t : _npcs_izq){
			t.play();
		}
		for(npc t : _npcs_izq){
			t.play();
		}
	}
	
	

}
