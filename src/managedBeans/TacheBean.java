package managedBeans;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import entity.Projet;
import entity.Tache;
import managedBeans.UtilisateurBean;
import dao.TacheDAO;
import dao.ProjetDao;


@ManagedBean
@RequestScoped
public class TacheBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Tache t;
	
	@EJB
	private TacheDAO tdao;
	
	@EJB
	private ProjetDao pdao; 

	public TacheBean()
	{
		t = new Tache();
	}
	
	public String ajouterTache()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		UtilisateurBean ub = context.getApplication().evaluateExpressionGet(context, "#{utilisateurBean}", UtilisateurBean.class);
		if(ub.getP().getNbtache() < ub.getP().getN())
		{
			ub.getP().setNbTache(ub.getP().getNbtache()+1);
			t.setNomProjet(ub.getP().getNom());
			t.setfini(false);
			t.setIdmongo(ub.getP().getNbtache());
			t.setWorking(false);
			tdao.creer(t,t.getNomProjet(),t.getIdmongo(),t.getUrl());
			pdao.update(ub.getP());
		}
		return "/ajouterTaches.xhtml";
	}
	
	
	public String ajouterTache(Tache t)
	{
		FacesContext context = FacesContext.getCurrentInstance();
		UtilisateurBean ub = context.getApplication().evaluateExpressionGet(context, "#{utilisateurBean}", UtilisateurBean.class);
		if(ub.getP().getNbtache() < ub.getP().getN())
		{
			ub.getP().setNbTache(ub.getP().getNbtache()+1);
			t.setNomProjet(ub.getP().getNom());
			t.setfini(false);
			t.setIdmongo(ub.getP().getNbtache());
			t.setWorking(false);
			tdao.creer(t,t.getNomProjet(),t.getIdmongo(),t.getUrl());
			pdao.update(ub.getP());
		}
		
		return "/ajouterTaches.xhtml";
	}
	public Tache getT()
	{
		return t;
	}
	
	public void setT(Tache t)
	{
		this.t = t;
	}
	
	public String selectionner()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		UtilisateurBean ub = context.getApplication().evaluateExpressionGet(context, "#{utilisateurBean}", UtilisateurBean.class);
		Tache t = tdao.selectionner();
		Projet p = pdao.trouver(t.getNomProjet());
		ub.setP(p);
		ub.setUrl();
		t.setWorking(true);
		tdao.update(t);
		ub.setT(t);
		return "contribuer.xhtml?faces-redirect=true";
	}

}
