package managedBeans;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.bson.Document;
import dao.TacheDAO;
import dao.UserDao;
import dao.MongoDAO;

@ManagedBean
@RequestScoped
public class ImageLabelBean {
	
	private String text;
	
	@EJB
	private TacheDAO tdao;
	
	@EJB
	private MongoDAO mdao;
	
	@EJB
	private UserDao udao;
	
	public String getText()
	{
		return text;
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
	
	public String contribuer()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		UtilisateurBean ub = context.getApplication().evaluateExpressionGet(context, "#{utilisateurBean}", UtilisateurBean.class);
		ub.getT().setfini(true);
		ub.getT().setWorking(true);
		tdao.update(ub.getT());
		
		ub.getU().setCredit((float)(ub.getU().getCredit()+0.25));
		udao.update(ub.getU());
		
		Document doc = new Document("key",ub.getT().getNomProjet()+Integer.toString(ub.getT().getIdmongo()))
				.append("nom", ub.getT().getNomProjet())
				.append("id", ub.getT().getIdmongo())
				.append("url", ub.getT().getUrl())
				.append("resultat", text);
		mdao.ajouterResultat(doc);
		
		mdao.close();
		TacheBean tb = context.getApplication().evaluateExpressionGet(context, "#{tacheBean}", TacheBean.class);
		return tb.selectionner();
	}

}
