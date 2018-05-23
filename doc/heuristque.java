import java.util.PriorityQueue;

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
 */

public enum Status{
	alive,resolved
}

public class h_noeud(MSNode noeud, Status status, int value) {
	//MSNode * noeud;
//	Status status;
//	int value;
	
	Boolean explored = false;
	int depth = noeud.depth;
}

public class ValComp implements Comparator<MSNode> {

    @Override
    public int compare (MSNode noeud1, MSNode noeud2) {
    	if(noeud1.value == noeud2.value) {
    		return 1;
    	}else {
    		if(noeud1.value > noeud2.value) {
    			return 1;
    		}else {
    			return -1;
    		}
    	}
    }
}

public MSNode sss_etoile(MSNode noeud_root){
	
	h_noeud root = new h_noeud(&noeud_root,alive,MAX);
	
	ValComp valuecomp = new ValComp();
	
	PriorityQueue<h_noeud> G = new PriorityQueue<h_noeud>(100, valuecomp);
	
//	root = noeud_root.id; //nom ou un pointeur ?
	
//	G = G.insert(root,alive,inf,G);
	
	G.add(root);
	
	h_noeud node = null;
	
	while( true ) {
		node = G.poll();
		
		if(node.status == resolved && node.name == root) {
			return node.value
		}else {
			if(node.status == alive) {
				if(node.depth >= tree.max_depth) {
					h = heuristique(noeud)
					G = G.insert(node.name,resolved, max(node.value,h), G)
				}else {
					if(color(node,tree) == Noir) {
						foreach(node,child in tree where child.parent == node.name){
							insert(child.name,alive,child.value,G)
						}
					}else {
						bro = get_bro(node,tree)
						insert(bro.name,alive,bro.value,G)
					}
				}
			}else {
				if(color(node,tree) == Blanc) {
					insert(node.parent.name,resolved,node.value,G)
					delete_childs(node.parent,G)
				}else {
					if(have_not_explored_bros(node,tree)) {
						bro = get_bro(node,tree)
						insert(bro.name,alive,node.value,G)
					}else {
						insert(node.parent.name,resolved,node.value,G)
					}
				}
			}
		}
	}
}



public int heuristique(MSNode noeud) {
	if(num_noeud(noeud.color) > num_noeud(noeud.color.neg)) {
		if(noeud.color == Noir)
			return MAX-1;
		else
			return MIN;
	}else if(num_noeud(noeud.color) == num_noeud(noeud.color.neg)){
		return 0;
	}else {

		int mob_stab = mobilite_stabilite(noeud);
		int coin = coin(noeud);
		
		return coin+mob_stab;
	}
}



public int mobilite_stabilite(MSNode noeud) {
	
	int mblt = 0;
	
	int stbl = 0;
	
	foreach(disc in noeud){
		case noeud.canmove(disc) && disc.color == Noir
				mblt += poid_mobilite;
		case noeud.canmove(disc) && disc.color == Blanc
				mblt -= poid_mobilite;
		case !noeud.canmove(disc) && disc.color == Noir
				mblt -= poid_mobilite;
		case !noeud.canmove(disc) && disc.color == Blanc
				mblt += poid_mobilite;
		
	
		case flank(disc) && disc.color == Noir
			stbl -= poid_stabilite;
		case flank(disc) && disc.color == Blanc
			stbl += poid_stabilite;
		case !flank(disc) && disc.color == Noir
			stbl += poid_stabilite;
		case !flank(disc) && disc.color == Blanc
			stbl -= poid_stabilite;
	
	}
	
	return stbl+mblt;
}

public int coin(MSNode noeud) {
	if(a1,a8,h1,h8 in noeud.last_disc) {
		if(noued.last_disc.color == Noir) {
			return 100;
		}else {
			return -100;
		}
		
	}else if(noeud.last_disc in around corners){
		if(noued.last_disc.color == Noir) {
			return -90;
		}else {
			return 90;
		}
		
	}else{
		return 0;
	}
}
