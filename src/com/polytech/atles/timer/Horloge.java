package com.polytech.atles.timer;

public class Horloge extends ChaineCompteur
{
	// private Compteur heures, minutes, secondes;

	public Horloge(int h, int m, int s)
	{
		super(new int[] { 60, 60, 24 }, new int[] { s, m, h });
		/*
		 * this.compteurs = new Compteur[3]; heures = new Compteur(2, h, 24,
		 * this); minutes = new Compteur(1, m, 60, this); secondes = new
		 * Compteur(0, s, 60, this); compteurs[0] = secondes; compteurs[1] =
		 * minutes; compteurs[2] = heures;
		 */
	}

	public Horloge()
	{
		this(0, 0, 0);
	}

	public void nextSeconde()
	{
		this.incrementer();
	}

	public int[] getHeure()
	{
		return new int[] { compteurs[2].getValeur(), compteurs[1].getValeur(), compteurs[0].getValeur() };
	}
}
