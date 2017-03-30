package entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

@Entity
public class User {
	

	@Id
	@NotNull( message ="Veuillez entrer votre adresse email")
	@Pattern( regexp = "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)", message = "Email non valide" )
	@Size(max = 50, message ="50 caractères maximum")
	private String email;
	
	@NotNull( message ="Veuillez entrer votre nom utilisateur")
	@Size(max = 25, message ="25 caractères maximum")
	private String pseudo;
	
	@NotNull( message ="Veuillez entrer votre mot de passe")
	@Size(max = 25, message ="25 caractères maximum")
	@Pattern(regexp = ".*(?=.{8,})(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).*", message = "8 caractères minimum, une minuscule, une mascule et un chiffre")
	private String password;
	
	@NotNull(message ="Veuillez entrer le montant que vous souhaitez ajouter")
	private float credit;
	
	
	private boolean type;	
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}


	public String getPseudo()
	{
		return pseudo;
	}
	
	public void setPseudo(String pseudo)
	{
		this.pseudo = pseudo;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public float getCredit()
	{
		return credit;
	}
	
	public void setCredit(float credit)
	{
		this.credit = credit;
	}
	
	public boolean getType()
	{
		return type;
	}
	
	public void setType(boolean type)
	{
		this.type = type;
	}
	
}
