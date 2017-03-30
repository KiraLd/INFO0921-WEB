package managedBeans;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class BonjourBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String nom;
	private String prenom;
	private String numero;
	private String adresse;
	private String ville;
	private String email;
	private String phone;
	private String pseudo;
	private String naissance;
	private String mdp;
	
	
	public String getNom()
	{
		return nom;
	}
	
	
	public String getPrenom()
	{
		return prenom;
	}
	
	public String getNumero()
	{
		return numero;
	}
	
	public String getAdresse()
	{
		return adresse;
	}
	
	public String getVille()
	{
		return ville;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public String getPhone()
	{
		return phone;
	}
	
	public String getPseudo()
	{
		return pseudo;
	}
	
	public String getNaissance()
	{
		return naissance;
	}
	
	public String getMdp()
	{
		return mdp;
	}
	
	public void setNom(String nom)
	{
		this.nom = nom;
	}
	
}
