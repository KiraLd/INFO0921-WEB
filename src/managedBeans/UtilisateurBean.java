package managedBeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;

import entity.Projet;
import entity.Tache;
import entity.User;
import dao.TacheDAO;

@ManagedBean
@SessionScoped
public class UtilisateurBean {
	private boolean connexion;
	private boolean type;
	private User u;
	private Projet p;
	private List<Projet> pl;
	private Tache t;
	private String url;
	private boolean download;
	
	@EJB
	TacheDAO tdao;
	
	@PreDestroy
	public void destroy()
	{
		if(t != null)
		{
			t.setWorking(false);
			tdao.update(t);
		}
	}
	
	public boolean getDownload()
	{
		return download;
	}
	
	public void setDownload(boolean download)
	{
		this.download = download;
	}
	
	public String getUrl()
	{
		return url;
	}
	
	public void setUrl(String url)
	{
		this.url = url;
	}
	
	public void setUrl()
	{
		if(type)
		{
			url = contributionURL();
		}
		else
		{
			url = addTaskURL();
		}
	}
	
	public String addTaskURL()
	{
		if(p != null)
		{
			if(p.getType().equals("imagelabeling"))
			{
				return "addImageTask.xhtml";
			}
		}
		
		return "content.xhtml";
	}
	
	public String contributionURL()
	{
		if(p != null)
		{
			if(p.getType().equals("imagelabeling"))
			{
				return "contributionImageLabel.xhtml";
			}
		}
		
		return "content.xhtml";
	}
	
	
	
	
	public Tache getT()
	{
		return t;
	}
	
	public void setT(Tache t)
	{
		this.t = t;
	}
	
	public List<Projet> getPl()
	{
		return pl;
	}
	
	public void setPl(List<Projet> pl)
	{
		this.pl = pl;
	}
	
	public User getU()
	{
		return u;
	}
	
	public void setU(User u)
	{
		this.u = u;
	}
	
	public String projetCourant()
	{
		return "projet.xhtml?faces-redirect=true";
	}
	
	public UtilisateurBean()
	{
		connexion = false;
		p = null;
		type = false;
		u = null;
	}
	
	public Projet getP()
	{
		return p;
	}
	public void setP(Projet p)
	{
		this.p = p;
	}
	
	
	public void setConnexion(boolean b)
	{
		connexion = b;
	}
	
	public boolean getConnexion()
	{
		return connexion;
	}
	
	public void setType(boolean type)
	{
		this.type = type;
	}
	
	public boolean getType()
	{
		return type;
	}
	
	public void connecter()
	{
		connexion = true;
		download = false;
	}
	
	public String deconnecter()
	{
		connexion = false;
		type = false;
		download = false;
		u = null;
		p = null;
		return "/index.xhtml";
	}


}
