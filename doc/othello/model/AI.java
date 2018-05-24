package othello.model;

import othello.util.Color;
import othello.util.Coord;

public class AI extends AbstractPlayer{
		
	//Attributs
	private int niveau;
	private String strategy;
	
	//Constructeur
	AI(Color myColor, IBoard board, int niveau, String strategy){
		super(myColor, board);
		this.niveau = niveau;
		this.strategy = strategy;
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
	/**
	 * Change le niveau de l'IA
	 */
	public void setNiveau(int niv) {
		this.niveau = niv;
	}
	
	/**
	 * Change la stratégie de l'IA
	 */
	public void setStragegy(String strat) {
		this.strategy = strat;
	}
	@Override
	public void play(Coord xy) {
		System.out.println("mon tour");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}