package com.polytech.atles.timer;
public abstract class ChaineCompteur 
{
	public Compteur[] compteurs;
	
	public ChaineCompteur()
	{}
	
	public ChaineCompteur(Compteur[] c)
	{
		for(int i = 0; i < c.length; i++)
		{
			c[i].initCompteur(i, this);
		}
		compteurs = c;
	}
	
	public ChaineCompteur(int bs[], int v[])
	{
		int l = bs.length;
		compteurs = new Compteur[l];
		for(int i = 0; i < l; i++)
		{
			compteurs[i] = new Compteur(i, v[i], bs[i], this);
		}
	}
	
	protected void incrementer()
	{
		compteurs[0].incrementer();
	}
	
	public void IncrCompteur(int id) 
	{
		if(id >= compteurs.length-1)
		{
			RAZ();
		}
		else
		{
			compteurs[id+1].incrementer();
		}
	}

	public void RAZ() 
	{
		for(int i = 0; i < compteurs.length; i++)
		{
			compteurs[i].RAZ();
		}
	}
	
	public void afficher()
	{
		String s = ":";
		for(int i = compteurs.length-1; i >= 0; i--)
		{
			s += compteurs[i].getValeur() + ":";
		}
		System.out.println(s);
	}
	
	
	@Override
	public String toString()
	{
		String s = "";
		for(int i = compteurs.length-1; i >= 0; i--)
		{
			int val = compteurs[i].getValeur();
			if(val < 10)
				s+= "0";
			s += val;
			if(i >= 1)
				s += " : ";
		}
		return s;
	}
}
