package managedBeans;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import dao.UserDao;
import entity.User;

@ManagedBean
@RequestScoped
public class InscrireBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private User u;
	
	@EJB
	private UserDao udao;
	
	public InscrireBean()
	{
		u = new User();
	}
	
	public String inscrire() throws NoSuchAlgorithmException
	{
		u.setCredit(0.f);
		udao.creer(u);
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
