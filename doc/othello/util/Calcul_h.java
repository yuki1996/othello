package othello.util;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
//import java.util.Iterator;
//import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import othello.model.Board;
import othello.model.IBoard;
//import othello.util.MinSpaceStrategyTree.MSNode;
import othello.util.StrategyTree.Node;

/*
 * creer la liste de priorite avec tous ses fonctions //java.util.PriorityQueue
 * 
 * node (pour les noeud) : nom ou un pointeur ? (il serait mieux s'il soit un pointeur)
 *  
 * creer un methode que renvoie tous les cases par liste (pour faire des foreach)
 * 
 * adjouter un variable pour dire si le noued a ete explorer ou pas
 * 
 * à l'heure d'ajouter des noeuds, le programme les crée (la fonction sss* va dans mstree
 * pour apres recuperer pointeurs des noeuds)
 * 
 * INFO:
 * 
 * revoiyer une coordone
 * 
 */

public class Calcul_h{
	public static final String SSS_STAR = "sss";
	public static final String NEGA = "negalphabeta";
	
	private Node noeud_root;
	//int depth;
	int max_depth;
	
	public Calcul_h(Node init_root, int max_depth){
		this.noeud_root = init_root;
		this.max_depth = max_depth;
	}
	
	public Double getValue(String strategie) {
		
		//this.noeud_root.generateChildren();
		double h_value = Double.POSITIVE_INFINITY;
		
		if(this.max_depth == 1) {
			
//			for(Node n : this.noeud_root) {
//				System.out.println("Children de profondeur 1: "+n);
//				
//				h_value = Double.min(h_value, heuristique(n));
//			}
			
//			return Double.POSITIVE_INFINITY;
			return heuristique(noeud_root);
		}else {
			
			if(this.max_depth == 2) {
				
				for(Node n : this.noeud_root) {
					System.out.println("Children de profondeur 1: "+n);
					
					h_value = Double.min(h_value, heuristique(n));
				}
					return h_value;
				
			}else {
				
			
				for(Node n : this.noeud_root) {
					if(strategie == NEGA) {
						h_value = Double.min(h_value, Negalphabeta(n,Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY));
					}else if(strategie == SSS_STAR) {
						h_value = Double.min(h_value, sss_etoile(n));
					}
				}
				
				//double sss_etoile = sss_etoile();
				System.out.println("Value SSS* : "+h_value);
			
				return h_value;
			}
		}
	}
		
	public Double Negalphabeta(Node node,double alpha, double beta) {
		//node.generateChildren();
		
		if(node.getDepth() == max_depth) {
			if(node.getPlayerColor() == Color.BLACK)
				return heuristique(node);
			else
				return -heuristique(node);
		}else {
		
			double val = Double.NEGATIVE_INFINITY;
			
			Iterator<Node> childs = node.iterator();
			
			while( alpha < beta && childs.hasNext()){
				val = Double.max(val, -Negalphabeta(childs.next(),-beta,-alpha));
				alpha = Double.max(val, alpha);
			}
			
			return alpha;
		}
	}
	
	public Double sss_etoile(Node root) {
		
		Comparator<h_noeud> valuecomp = new ValComp();
		
		PriorityQueue<h_noeud> G = new PriorityQueue<h_noeud>(100, valuecomp);
		
		root.setEval(Double.POSITIVE_INFINITY);
		
		h_noeud h_root = new h_noeud(root,Status.alive);
		
		G.add(h_root);
		
		h_noeud node = null;
		
		double h;
		
		//System.out.println("Value dans sss pour "+root.hashCode()+": "+G.peek().noeud.getEval());
		
		while( true ) {
			node = G.poll();
			
			if(node.status == Status.resolved && node.noeud == root) {
				return node.noeud.getEval();
			}else {
				if(node.status == Status.alive) {
					if(node.noeud.getDepth() >= root.getDepth()) {
							h = heuristique(node.noeud);
							
							G.add(newNoeud(node.noeud,Status.resolved,Double.min(node.noeud.getEval(),h)));
						}else {
							if(node.noeud.getPlayerColor() == Color.BLACK) {
	//							foreach(node,child in tree where child.parent == node.name){
	//								insert(child.name,alive,child.value,G)
	//							}
								
								for (Node frere : node.noeud.getParent()) {
									if(frere != node.noeud) {
										G.add(newNoeud(frere,Status.alive,node.noeud.getEval()));
									}
								}
							}else {
//								bro = get_bro(node,tree)
//								insert(bro.name,alive,bro.value,G)
								
								
							}
						}
	//				}else {
	//					if(color(node,tree) == Blanc) {
	//						insert(node.parent.name,resolved,node.value,G)
	//						delete_childs(node.parent,G)
	//					}else {
	//						if(have_not_explored_bros(node,tree)) {
	//							bro = get_bro(node,tree)
	//							insert(bro.name,alive,node.value,G)
	//						}else {
	//							insert(node.parent.name,resolved,node.value,G)
	//						}
	//					}
					}
				}
				//return Double.NEGATIVE_INFINITY;
//			}
		
		
			return Double.NEGATIVE_INFINITY;
		}
	}
	
	h_noeud newNoeud(Node noeud,Status status, Double eval) {
		noeud.setEval(eval);
		return new h_noeud(noeud,status);
	}
	
	Double heuristique(Node noeud) {
		if(noeud.isFull()) {
			if(noeud.getDisksOfPlayer(Color.BLACK).size() > noeud.getDisksOfPlayer(Color.WHITE).size()) {
				return Double.POSITIVE_INFINITY;
			}else if(noeud.getDisksOfPlayer(Color.BLACK).size() < noeud.getDisksOfPlayer(Color.WHITE).size()) {
				return Double.NEGATIVE_INFINITY;
			}else {
				return (double) 0;
			}
		}else {
			double mob_stab = mobilite_stabilite(noeud);
			//double coin = coin(noeud);
			
			double coin = (double)0;
			
			return coin+mob_stab;
		}
	}
	
	Double mobilite_stabilite(Node noeud) {
		
		double poid_mobilite = (double) 10;
		
		double poid_stabilite = (double) 10;
	
		double mblt = 0;
		
		double stbl = 0;

	//	foreach(disc in noeud){
	//		case noeud.canmove(disc) && disc.color == Noir
	//				mblt += poid_mobilite;
	//		case noeud.canmove(disc) && disc.color == Blanc
	//				mblt -= poid_mobilite;
	//		case !noeud.canmove(disc) && disc.color == Noir
	//				mblt -= poid_mobilite;
	//		case !noeud.canmove(disc) && disc.color == Blanc
	//				mblt += poid_mobilite;
	//		
	//		
	//		case flank(disc) && disc.color == Noir
	//			stbl -= poid_stabilite;
	//		case flank(disc) && disc.color == Blanc
	//			stbl += poid_stabilite;
	//		case !flank(disc) && disc.color == Noir
	//			stbl += poid_stabilite;
	//		case !flank(disc) && disc.color == Blanc
	//			stbl -= poid_stabilite;
	//	
	//	}
	//	
	//	return stbl+mblt;
		
		Set<Coord> set_coord_moves_black = noeud.getValidMoves(Color.BLACK);
		
		Set<Coord> set_coord_moves_white = noeud.getValidMoves(Color.WHITE);
		
		Set<Coord> set_coord_black_flank = new HashSet<Coord>();
		
		Set<Coord> set_coord_white_flank = new HashSet<Coord>();
		
		Set<Coord> set_coord_black_stable = new HashSet<Coord>();
		
		Set<Coord> set_coord_white_stable = new HashSet<Coord>();
		
		mblt += set_coord_moves_black.size() * poid_mobilite;
		
		mblt -= set_coord_moves_white.size() * poid_mobilite;
		
		Coord c = new Coord(0,0);
		Coord d = c;
		
		//int discs_flank = 0;
		
		//List<Coord> list_temp = new LinkedList<Coord>();
		
		for(int row = 0; row < noeud.getSize(); row++) {
			for (int col = 0; col < noeud.getSize(); col++) {
				c = new Coord(row,col);
				
				if(noeud.getColor(c) == null) {
					; // faire rien
				}else {

					//void getFlanks(Node noeud, Coord init, String dir, Set<Coord> black_flank, Set<Coord> white_flank, Set<Coord> black_moves, Set<Coord> white_moves)
					getFlanks(noeud,c,"up",set_coord_black_flank,set_coord_white_flank,set_coord_moves_black,set_coord_moves_white);
					getFlanks(noeud,c,"down",set_coord_black_flank,set_coord_white_flank,set_coord_moves_black,set_coord_moves_white);
					getFlanks(noeud,c,"left",set_coord_black_flank,set_coord_white_flank,set_coord_moves_black,set_coord_moves_white);
					getFlanks(noeud,c,"rigth",set_coord_black_flank,set_coord_white_flank,set_coord_moves_black,set_coord_moves_white);
					getFlanks(noeud,c,"upRight",set_coord_black_flank,set_coord_white_flank,set_coord_moves_black,set_coord_moves_white);
					getFlanks(noeud,c,"upLeft",set_coord_black_flank,set_coord_white_flank,set_coord_moves_black,set_coord_moves_white);
					getFlanks(noeud,c,"downRight",set_coord_black_flank,set_coord_white_flank,set_coord_moves_black,set_coord_moves_white);
					getFlanks(noeud,c,"downLeft",set_coord_black_flank,set_coord_white_flank,set_coord_moves_black,set_coord_moves_white);
					
				}
			}
		}
		
		for(int row = 0; row < noeud.getSize(); row++) {
			for (int col = 0; col < noeud.getSize(); col++) {
				c = new Coord(row,col);
				if(noeud.getColor(c) == null) {
					; //faire rien
				}else if(noeud.getColor(c) == Color.BLACK && !set_coord_black_flank.contains(c)) {
					set_coord_black_stable.add(c);
				}else if(noeud.getColor(c) == Color.WHITE && !set_coord_white_flank.contains(c)) {
					set_coord_white_stable.add(c);
				}
			}
		}
		
		// discs stables = intersetion de flanks et tous les discs
		
		stbl += set_coord_black_stable.size() * poid_stabilite;
		
		stbl -= set_coord_white_stable.size() * poid_stabilite;
		
		stbl += set_coord_white_flank.size() * poid_stabilite;
		
		stbl -= set_coord_black_flank.size() * poid_stabilite;
		
		return mblt+stbl;
	}
	
	void getFlanks(Node noeud, Coord init, String dir, Set<Coord> black_flank, Set<Coord> white_flank, Set<Coord> black_moves, Set<Coord> white_moves) {
		
		List<Coord> list_temp = new LinkedList<Coord>();
		
		Coord move = new Coord(0,0);
		
		switch (dir) {
		case "up" : move = init.up();
		case "down" : move = init.down();
		case "right" : move = init.right();
		case "left" : move = init.left();
		case "upRight" : move = init.upRight();
		case "upLeft" : move = init.upLeft();
		case "downRight" : move = init.downRight();
		case "downLeft" : move = init.downLeft();
		}
		
//		Coord d = init.up();
		
		if(init.isInRect(move) && noeud.getColor(move) != noeud.getColor(init) && noeud.getColor(move) != null) {
			
			while(init.isInRect(move) && noeud.getColor(move) != noeud.getColor(init)) {
				
				list_temp.add(move);
				
				switch (dir) {
				case "up" : move = move.up();
				case "down" : move = move.down();
				case "right" : move = move.right();
				case "left" : move = move.left();
				case "upRight" : move = move.upRight();
				case "upLeft" : move = move.upLeft();
				case "downRight" : move = move.downRight();
				case "downLeft" : move = move.downLeft();
				}
				
				if(noeud.getColor(move) == null) {
					if(noeud.getColor(init) == Color.BLACK && black_moves.contains(init)) {
						for(Coord flank : list_temp ) {
							white_flank.add(flank);
						}
						break;
					}else if(noeud.getColor(init) == Color.WHITE && white_moves.contains(init)) {
						for(Coord flank : list_temp ) {
							black_flank.add(flank);
						}
						break;
					}
				}
			}
		}
	}
	

//	Boolean canmove(Node noeud, int row, int col) {
//		
//		Color current_color = noeud.getColor(new Coord(row,col));
//		
//		//up
//		for(int rowup = row-1; rowup < 1; rowup--) {
//			if(noeud.getColor(new Coord(rowup,col)) == current_color) {
//				break;
//			}else {
//				if(noeud.getColor(new Coord(rowup-1,col)) == null) {
//					return true;
//				}
//			}
//		}
//		
//		//down
//		for(int rowdown = row+1; rowdown < noeud.getSize()-1; rowdown++) {
//			if(noeud.getColor(new Coord(rowdown,col)) == current_color) {
//				break;
//			}else {
//				if(noeud.getColor(new Coord(rowdown+1,col)) == null) {
//					return true;
//				}
//			}
//		}
//		
//		//right
//		for(int colright = col+1; colright < noeud.getSize()-1; colright++) {
//			if(noeud.getColor(new Coord(row,colright)) == current_color) {
//				break;
//			}else {
//				if(noeud.getColor(new Coord(row,colright+1)) == null) {
//					return true;
//				}
//			}
//		}
//		
//		//left
//		for(int colleft = col-1; colleft < 1; colleft--) {
//			if(noeud.getColor(new Coord(row,colleft)) == current_color) {
//				break;
//			}else {
//				if(noeud.getColor(new Coord(row,colleft-1)) == null) {
//					return true;
//				}
//			}
//		}
//		
//		
//		
//		return false;
//	}
	
	
//	Boolean flank(Node noeud, int row, int col) {
//		
//		Color current_color = noeud.getColor(new Coord(row,col));
//		
//		//up
//		for(int rowup = row-1; rowup < 1; rowup--) {
//			if(noeud.getColor(new Coord(rowup,col)) == null) {
//				break;
//			}else {
//				if(noeud.getColor(new Coord(rowup-1,col)) != null) {
//					for(int rowdown = row+1; rowdown <
//				}
//			}
//		}
//		
//		return false;
//	}
	

public Double coin(Node noeud) {
//	if(a1,a8,h1,h8 in noeud.last_disc) {
//		if(noued.last_disc.color == Noir) {
//			return 100;
//		}else {
//			return -100;
//		}
//		
//	}else if(noeud.last_disc in around corners){
//		if(noued.last_disc.color == Noir) {
//			return -90;
//		}else {
//			return 90;
//		}
//		
//	}else{
//		return 0;
//	}
	return Double.POSITIVE_INFINITY;
}
	
	public static void main(String[] args) {
		//System.out.println("hello world");
		
		//hors de la class
		IBoard b = new Board(8);
		b.putDisk(new Coord(3,3), Color.BLACK);
		b.putDisk(new Coord(4,4), Color.BLACK);
		b.putDisk(new Coord(3,4), Color.WHITE);
		b.putDisk(new Coord(4,3), Color.WHITE);
		
		MinSpaceStrategyTree t = new MinSpaceStrategyTree(b, 20);
		
		int tree_depth = 2;
		
		Calcul_h h = new Calcul_h(t.getRoot(),tree_depth);

		//System.out.println("Value : "+h.getValue());
		
//		Node m = t.getRoot();
//		m.generateChildren();		
		
//		h_noeud test = new h_noeud(null,Status.alive,Double.POSITIVE_INFINITY);
//		
////		System.out.println(test.status);
//		
//		Comparator<h_noeud> valuecomp = new ValComp();
//		
//		PriorityQueue<h_noeud> G = new PriorityQueue<h_noeud>(100, valuecomp);
//		
//		G.add(new h_noeud(null,Status.resolved,(double)0));
//		
//		G.add(new h_noeud(null,Status.resolved,(double)-10));
//
//		G.add(new h_noeud(null,Status.resolved,(double)10));
//		
//		G.add(test);
//		
//		while(!G.isEmpty()) {
//			System.out.println(G.poll().value);
//		}
	}
	
}
