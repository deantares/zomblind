package antares.zomblind.core.npcs;

import java.util.Random;
import java.util.Stack;

import antares.zomblind.core.objetos.Arma;

public class NpcLista {

	public Npc[] _npc = new Npc[3];

	Random pos = new Random();

	Stack<Npc> _npcs = new Stack<Npc>();
	private int _max_npcs = 10;

	double _p = 0.3;

	public NpcLista() {
		_npc[0] = null;
		_npc[1] = null;
		_npc[2] = null;
	}

	public void acercar() {
		if (_npc[0] != null)
			_npc[0].acercar();
		if (_npc[1] != null)
			_npc[1].acercar();
		if (_npc[2] != null)
			_npc[2].acercar();
	}

	public void play() {
		if (_npc[0] != null)
			_npc[0].play();
		if (_npc[1] != null)
			_npc[1].play();
		if (_npc[2] != null)
			_npc[2].play();
	}

	public void push(Npc n) {
		if (_npcs.size() < _max_npcs) {
			_npcs.push(n);
		}
	}

	public void ataque() {
		
		
		if (_npc[0] == null && _npc[1] == null && _npc[2] == null) {
			if (pos.nextDouble() < _p) {
				if (_npcs.size() > 0) {
					_npc[0] = _npcs.pop();
					_npc[0]._distancia = 10;
					_npc[0]._zona = 0;
				}
			}
			if (pos.nextDouble() < _p) {
				if (_npcs.size() > 0) {
					_npc[1] = _npcs.pop();
					_npc[1]._distancia = 10;
					_npc[1]._zona = 1;
				}
			}
			if (pos.nextDouble() < _p) {
				if (_npcs.size() > 0) {
					_npc[2] = _npcs.pop();
					_npc[2]._distancia = 10;
					_npc[2]._zona = 2;
				}
			}
		}else{
			if (_npc[0] != null) {_npc[0].ataque();}
			if (_npc[1] != null) {_npc[1].ataque();}
			if (_npc[2] != null) {_npc[2].ataque();}
			
		}
	}

	public void atacar(int zona, Arma arma) {
		// Existe npc en la zona
		if (_npc[zona] != null) {
			if (_npc[zona]._distancia <= arma._alcance) {
				if (_npc[zona].atacar(arma)) {
					// El npc ha muerto
					_npc[zona] = null;
				}
			}

		}

	}
	
	public boolean noZombieInGame(){
		return (_npc[0]==null)&&(_npc[1]==null)&&(_npc[2]==null);
	}

	@Override
	public String toString() {
		return (_npc[0] != null ? _npc[0].toString() : " - ") + "\n"
				+ (_npc[1] != null ? _npc[1].toString() : " - ") + "\n"
				+ (_npc[2] != null ? _npc[2].toString() : " - ");
	}

}
