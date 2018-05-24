package othello.util;

import java.util.Comparator;

public class ValComp implements Comparator<h_noeud> {

    @Override
    public int compare (h_noeud noeud1, h_noeud noeud2) {
    	if(noeud1.noeud.getEval() == noeud2.noeud.getEval()) {
    		return 0;
    	}else {
    		if(noeud1.noeud.getEval() > noeud2.noeud.getEval()) {
    			return -1;
    		}else {
    			return 1;
    		}
    	}
    }
}