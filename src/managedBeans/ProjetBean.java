package managedBeans;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import entity.Projet;
import dao.ProjetDao;
import dao.UserDao;
import managedBeans.UtilisateurBean;



@ManagedBean
@RequestScoped
public class ProjetBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private Projet p;
	
	@EJB
	private ProjetDao pdao;
	
	@EJB 
	private UserDao udao;
	
	public ProjetBean()
	{
		p = new Projet();
	}
	
	public String ajouterProjet()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		UtilisateurBean ub = context.getApplication().evaluateExpressionGet(context, "#{utilisateurBean}", UtilisateurBean.class);
		ub.setP(p);
		ub.setUrl();
		ub.getU().setCredit((float)(ub.getU().getCredit()-p.getN()*0.5));
		udao.update(ub.getU());
		p.setFini(false);
		p.setFkeyuser(ub.getU().getEmail());
		if(p.getType().equals("image"))
		{
			p.setCout(p.getN()*0.5f);
		}
		pdao.creer(p);
		return "/projet.xhtml?faces-redirect=true";
	}
	
	public String listerProjets()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		UtilisateurBean ub = context.getApplication().evaluateExpressionGet(context, "#{utilisateurBean}", UtilisateurBean.class);
		ub.setPl(pdao.trouverProjets(ub.getU().getEmail()));
		return "listeProjet.xhtml?faces-redirect=true";
	}
	
	
	
	public Projet getP()
	{
		return p;
	}
	
	public void setP(Projet p)
	{
		this.p = p;
	}

}
