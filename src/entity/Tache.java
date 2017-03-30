package entity;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;

@Entity
public class Tache {
	
	@NotNull
	private String nomProjet;
	
	@NotNull
	private boolean fini;
	
	@NotNull
	private int idmongo;
	
	@NotNull(message="Entrez l'adresse de l'image")
	@Size(message="256 caractères maximum")
	@Pattern(regexp ="^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]", message="Adresse non valide")
	private String url;
	
	@Id 
	@GeneratedValue( strategy=GenerationType.IDENTITY )
	private int taskkey;
	
	@NotNull
	boolean working;
	
	public Tache()
	{
		nomProjet = "defaut";
		fini = true;
		working = true;
		idmongo = 1;
		url ="http://google.fr";
		//taskkey = 1;
	}
	
	public boolean getWorking()
	{
		return working;
	}
	
	public void setWorking(boolean working)
	{
		this.working = working;
	}
	
	public String getNomProjet()
	{
		return nomProjet;
	}
	
	public boolean getFini()
	{
		return fini;
	}
	
	public int getIdmongo()
	{
		return idmongo;
	}
	
	public String getUrl()
	{
		return url;
	}
	
	public int getTaskkey()
	{
		return taskkey;
	}
	
	public void setfini(boolean fini)
	{
		this.fini = fini;
	}
	
	public void setNomProjet(String nomProjet)
	{
		this.nomProjet = nomProjet;
	}
	
	public void setIdmongo(int idmongo)
	{
		this.idmongo = idmongo;
	}
	
	public void setUrl(String url)
	{
		this.url = url;
	}
	
	public void setTaskkey(int taskkey)
	{
		this.taskkey = taskkey;
	}
	
	
	
	
	

}
