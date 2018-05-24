package othello.util;

import java.util.Iterator;
import java.util.List;

import othello.util.StrategyTree.Node;

public class h_noeud implements Iterable<h_noeud> {
	
	Boolean explored;
	Node noeud;
	Status status;

	
	private List<h_noeud> children;
	private List<h_noeud> bros;
	
	//CONSTRUCTEUR
	h_noeud (Node init_noeud, Status init_status){
		noeud = init_noeud;
		explored = false;
		status = init_status;
	}
	
	public Iterator<h_noeud> iterator() {
		return children.iterator();
	}
	
	public Iterator<h_noeud> bros_iterator() {
		return bros.iterator();
	}
}