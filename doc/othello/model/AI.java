package othello.model;

import java.util.Map.Entry;
import java.util.SortedSet;

import othello.util.Calcul_h;
import othello.util.Color;
import othello.util.Coord;
import othello.util.MinSpaceStrategyTree;
import othello.util.NodeComparator;
import othello.util.StrategyTree;
import othello.util.StrategyTree.Node;

public class AI extends AbstractPlayer{
		
	//Attributs
	private final int niveau; // de 0 à 100
	private final String strategy; // valeurs dans Calcul_h.java
	private final StrategyTree sTree;
	private final Calcul_h calcul_h;
	private final double bestProba;
	private final int maxDepth;
	private final NodeComparator comp;
	
	//Constructeur
	AI(Color myColor, IBoard board, int niveau, String strategy){
		super(myColor, board);
		this.niveau = niveau;
		this.strategy = strategy;
		bestProba = getBestProba(niveau);
		maxDepth = getMaxDepth(niveau);
		sTree = new MinSpaceStrategyTree(board, maxDepth);
		calcul_h = new Calcul_h(sTree.getRoot(), getMaxDepth(niveau));
		comp = new NodeComparator();
	}

	//Requêtes
	/**
	 * Retourne le niveau de l'IA
	 */
	public int getNiveau() {
		return this.niveau;
	}
	
	/**
	 * Retourne la stratégie de l'IA
	 */
	public String getStrategy() {
		return this.strategy;
	}
	
	//Méthodes
	@Override
	public void play(Coord xy) {
		sTree.move(board.getLastShot());
		
		Coord c = getChoice(Calcul_h.SSS_STAR);
		choose(c);
		sTree.move(c);
	}
	
//	@Override
//	public void play(Coord xy) {
//		Set<Coord> set = getBoard().getValidMoves(getColor());
//		if (!set.isEmpty()) {
//			Iterator<Coord> it = set.iterator();
//			Coord coord = (Coord) it.next();
//			choose(coord);
//		}
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	// OUTILS
	private double getBestProba(int level) {
		return ((double) level) / 100.0;
	}
	
	private int getMaxDepth(int level) {
		return level / 5;
	}
	
	private int probaChoice(int size) {
		return (int) (size * Math.pow(Math.random(), - (Math.log(bestProba) / Math.log(size))));
	}
	
	private Coord getChoice(String strategy) {
		
		for (Node s : sTree.getRoot()) {
			s.setEval(calcul_h.getValue(strategy));
		}

		sTree.getRoot().children().sort(comp);
		int ind = bestProba == 1.0 ? 0 : probaChoice(sTree.getRoot().children().size());
	
		return sTree.getRoot().children().get(ind).getMove();
	}
	
}
