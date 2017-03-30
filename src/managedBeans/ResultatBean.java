package managedBeans;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.nio.file.Files;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoIterable;

import entity.Projet;
import entity.Tache;
import managedBeans.UtilisateurBean;
import dao.TacheDAO;
import dao.ProjetDao;
import dao.MongoDAO;


@ManagedBean
@RequestScoped
public class ResultatBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EJB
	private TacheDAO tdao;
	
	@EJB
	private ProjetDao pdao; 
	
	@EJB
	private MongoDAO mdao; 
	
	public String genererResultat()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		UtilisateurBean ub = context.getApplication().evaluateExpressionGet(context, "#{utilisateurBean}", UtilisateurBean.class);
		FindIterable<Document> res = mdao.trouver(ub.getP().getNom());
		
		try {
			
			ub.setUrl("\\temp\\"+ub.getP().getNom()+".json");
			File f = new File(ub.getUrl());
			if(!f.exists())
			{
				f.createNewFile();
			}
			
			FileOutputStream out = new FileOutputStream(f);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
			MongoCursor<Document> it = res.iterator();
			while(it.hasNext())
			{
				Document doc = it.next();
				bw.write(doc.toJson());
				bw.newLine();
			}
			bw.close();
			out.close();
			ub.setDownload(true);
			
			mdao.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			context.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,e.toString(),null));
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			context.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,e.toString(),null));
			e.printStackTrace();
		}
		return "projet.xhtml";
	}
	
	public void download() throws IOException
	{
		FacesContext context = FacesContext.getCurrentInstance();
	    ExternalContext ec = context.getExternalContext();
		UtilisateurBean ub = context.getApplication().evaluateExpressionGet(context, "#{utilisateurBean}", UtilisateurBean.class);

		File file = new File(ub.getUrl());
	    String fileName = file.getName();
	    String contentType = ec.getMimeType(fileName); 
	    int contentLength = (int) file.length();
		
	    ec.responseReset(); 
	    ec.setResponseContentType("application/json"); 
	    ec.setResponseContentType(contentType);
	    ec.setResponseContentLength(contentLength); 
	    ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + ub.getP().getNom()+".json" + "\""); 
	    OutputStream output = ec.getResponseOutputStream();
	    
	    Files.copy(file.toPath(), output);
	    context.responseComplete();
	}
	

	

}
