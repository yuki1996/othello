package othello.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import othello.model.Board;
import othello.model.IBoard;
//import othello.util.MinSpaceStrategyTree.MSNode;
import othello.util.StrategyTree.Node;

enum Status{
	alive,resolved;
}

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
	
	private Node noeud_root;
	//int depth;
	int max_depth;
	
	Calcul_h(Node init_root, int max_depth){
		this.noeud_root = init_root;
		this.max_depth = max_depth;
	}
	
	public Double getValue() {
		
		
		this.noeud_root.generateChildren();
		double h_value = Double.POSITIVE_INFINITY;
		
		
		if(this.max_depth == 1) {
			
			for(Node n : this.noeud_root) {
				System.out.println("Children de profondeur 1: "+n);
				
//				h_value = Double.min(h_value, heuristique(n));
			}
			
			return Double.POSITIVE_INFINITY;
			
		}else {
						
			for(Node n : this.noeud_root) {
				//System.out.println(n);
				h_value = Double.min(h_value, sss_etoile(n));
			}
			
			//double sss_etoile = sss_etoile();
			System.out.println("Value SSS* : "+h_value);
		
			return Double.POSITIVE_INFINITY;
		}
	}
	
	Double sss_etoile(Node root) {
		
		Comparator<h_noeud> valuecomp = new ValComp();
		
		PriorityQueue<h_noeud> G = new PriorityQueue<h_noeud>(100, valuecomp);
		
		h_noeud h_root = new h_noeud(root,Status.alive,Double.POSITIVE_INFINITY,1,null);
		
		G.add(h_root);
		
		h_noeud node = null;
		
		System.out.println("Value dans sss pour "+root.hashCode()+": "+G.peek().value);
		
		while( true ) {
			node = G.poll();
			
			if(node.status == Status.resolved && node.noeud == root) {
				return node.value;
			}else {
				if(node.status == Status.alive) {
					if(node.depth >= max_depth) {
//						h = heuristique(node)
//						G.add(node,Status.resolved,min(node.value,h),node.depth,node.parent);
//					}else {
//						if(color(node,tree) == Noir) {
//							foreach(node,child in tree where child.parent == node.name){
//								insert(child.name,alive,child.value,G)
//							}
//						}else {
//							bro = get_bro(node,tree)
//							insert(bro.name,alive,bro.value,G)
//						}
//					}
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
			}
		
		
		return Double.NEGATIVE_INFINITY;
		}
	}
	
//	Double heuristique(Node noeud) {
//		if(num_noeud(noeud.color) > num_noeud(noeud.color.neg)) {
//			if(noeud.color == Noir)
//				return MAX-1;
//			else
//				return MIN;
//		}else if(num_noeud(noeud.color) == num_noeud(noeud.color.neg)){
//			return 0;
//		}else {
//
//			int mob_stab = mobilite_stabilite(noeud);
//			int coin = coin(noeud);
//			
//			return coin+mob_stab;
//		}
//	}
	
	public static void main(String[] args) {
		//System.out.println("hello world");
		
		//hors de la class
		IBoard b = new Board(8);
		b.putDisk(new Coord(3,3), Color.BLACK);
		b.putDisk(new Coord(4,4), Color.BLACK);
		b.putDisk(new Coord(3,4), Color.WHITE);
		b.putDisk(new Coord(4,3), Color.WHITE);
		
		MinSpaceStrategyTree t = new MinSpaceStrategyTree(b);
		
		int tree_depth = 2;
		
		Calcul_h h = new Calcul_h(t.getRoot(),tree_depth);

		System.out.println("Value : "+h.getValue());
		
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

class h_noeud implements Iterable<h_noeud> {
	
	Boolean explored;
	Node noeud;
	Status status;
	Double value;
	int depth;
	h_noeud parent;
	
	private List<h_noeud> children;
	
	//CONSTRUCTEUR
	h_noeud (Node noeud, Status init_status, Double init_value, int init_depth,h_noeud pere){
		explored = false;
		status = init_status;
		value = init_value;
		depth = init_depth;
		parent = pere;
	}
	
	public Iterator<h_noeud> iterator() {
		return children.iterator();
	}
}


class ValComp implements Comparator<h_noeud> {

    @Override
    public int compare (h_noeud noeud1, h_noeud noeud2) {
    	if(noeud1.value == noeud2.value) {
    		return 0;
    	}else {
    		if(noeud1.value > noeud2.value) {
    			return -1;
    		}else {
    			return 1;
    		}
    	}
    }
}


//
//
//

//
//
//
//public int mobilite_stabilite(MSNode noeud) {
//	
//	int mblt = 0;
//	
//	int stbl = 0;
//	
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
//}
//
//public int coin(MSNode noeud) {
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
//}
