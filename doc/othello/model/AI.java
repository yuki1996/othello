package othello.model;

import othello.util.Calcul_h;
import othello.util.Color;
import othello.util.Coord;
import othello.util.MinSpaceStrategyTree;
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
	
	//Constructeur
	AI(Color myColor, IBoard board, int niveau, String strategy){
		super(myColor, board);
		this.niveau = niveau;
		this.strategy = strategy;
		bestProba = getBestProba(niveau);
		maxDepth = getMaxDepth(niveau);
		sTree = new MinSpaceStrategyTree(board, maxDepth);
		calcul_h = new Calcul_h(sTree.getRoot(), getMaxDepth(niveau));
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
		if (board.getLastShot() != null) {
			sTree.move(board.getLastShot());
		}
		
		switch (strategy) {
			case Calcul_h.SSS_STAR:
				calcul_h.sss_etoile(sTree.getRoot());
				break;
			case Calcul_h.NEGA:
				// TODO
				break;
			default:
				throw new IllegalArgumentException();
		}
		
		Coord c = getChoice();
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
	
	private Coord getChoice() {
		return new Coord(0,0); // TODO
	}
}
