package com.polytech.atles.timer;

public class Compteur 
{
	private int id;
	private ChaineCompteur compteurs;
	private int valeur;
	private int borneInf;
	private int borneSup;
	private int pas;
	
	public Compteur(int v, int bi, int bs, int p, ChaineCompteur c)
	{
		valeur =  v;
		borneInf = bi;
		borneSup = bs;
		pas = p;
		compteurs = c;
	}
	
	public Compteur(int bs, ChaineCompteur c)
	{
		this(0, 0, bs, 1, c);
	}
	
	public Compteur(int id, int bs, ChaineCompteur c)
	{
		this(0, 0, bs, 1, c);
		this.id = id;
	}
	
	public Compteur(int id, int v, int bs, ChaineCompteur c)
	{
		this(v, 0, bs, 1, c);
		this.id = id;
	}
	
	public Compteur(int bs)
	{
		this(0, 0, bs, 1, null);
	}
	
	public void initCompteur(int id, ChaineCompteur c)
	{
		compteurs = c;
		setID(id);
	}
	
	public void setID(int id)
	{
		this.id = id;
	}
	
	public int getValeur()
	{
		return valeur;
	}
	
	public void incrementer()
	{
		valeur += pas;
		if(valeur >= borneSup)
			RAZAndUp();
	}
	
	public void RAZAndUp()
	{
		RAZ() ;
		compteurs.IncrCompteur(id);
	}

	public void RAZ() 
	{
		valeur = valeur - borneSup + borneInf;
		//compteurs.IncrCompteur(id);
	}
}
