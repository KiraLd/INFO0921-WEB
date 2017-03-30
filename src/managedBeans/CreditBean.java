package managedBeans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import dao.UserDao;
import entity.User;


@ManagedBean
@RequestScoped
public class CreditBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	private User u;
	
	@EJB
	private UserDao udao;
	
	
	public CreditBean()
	{
		u = new User();
	}
	
	public String ajouterCredit()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		UtilisateurBean ub = context.getApplication().evaluateExpressionGet(context, "#{utilisateurBean}", UtilisateurBean.class);
		ub.getU().setCredit(ub.getU().getCredit()+u.getCredit());
		udao.update(ub.getU());
		return "/index.xhtml";
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
