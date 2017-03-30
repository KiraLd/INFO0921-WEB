package managedBeans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import entity.User;


@ManagedBean
@RequestScoped
public class ConnexionBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	private User u;
	
	
	public ConnexionBean()
	{
		u = new User();
	}
	
	public String connexion()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		UtilisateurBean ub = context.getApplication().evaluateExpressionGet(context, "#{utilisateurBean}", UtilisateurBean.class);
		ub.connecter();
		return "/index.xhtml?faces-redirect=true";
	}
	
	public User getU()
	{
		return u;
	}
	
	public void setU(User u)
	{
		this.u = u;
	}

}
