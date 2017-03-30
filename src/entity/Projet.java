package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Projet {
	
	@Id
	@NotNull(message = "Veuillez entrer le nom du projet")
	@Size(max = 50, message="50 caractères maximum")
	private String nom;
	
	private float cout;
	
	
	private boolean fini;
	
	private String fkeyuser;
	
	private String type;
	
	
	@NotNull(message = "Veuillez entrer le nombre de tâches")
	private int n;

	
	
	@NotNull(message="Veuillez entrer la durée maximum dédiée à une tâche(en s)")
	private int duree;
	
	@NotNull
	private int nbtache;
	
	public int getNbtache()
	{
		return nbtache;
	}
	
	public void setNbTache(int nbtache)
	{
		this.nbtache = nbtache;
	}
	

	
	public int getDuree()
	{
		return duree;
	}
	
	public void setDuree(int duree)
	{
		this.duree = duree;
	}
	
	public String getNom()
	{
		return nom;
	}
	
	public void setNom(String nom)
	{
		this.nom = nom;
	}
	
	public float getCout()
	{
		return cout;
	}
	
	public void setCout(float cout)
	{
		this.cout = cout;
	}
	
	public boolean getFini()
	{
		return fini;
	}
	
	public void setFini(boolean fini)
	{
		this.fini = fini;
	}

	public String getFkeyuser()
	{
		return fkeyuser;
	}
	
	public void setFkeyuser(String fkeyuser)
	{
		this.fkeyuser = fkeyuser;
	}
	
	public String getType()
	{
		return type;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	

	
	public int getN()
	{
		return n;
	}
	
	public void setN(int n)
	{
		this.n = n;
	}


	
}
