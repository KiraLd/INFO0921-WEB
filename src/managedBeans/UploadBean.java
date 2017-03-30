package managedBeans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import entity.Tache;

@ManagedBean
@RequestScoped
public class UploadBean {
	private Part file;
	
	public Part getFile()
	{
		return file;
	}
	
	public void setFile(Part file)
	{
		this.file = file;
	}
	
	public void save()
	{
		InputStream input = null;
		try
		{
			input = file.getInputStream();
			Reader reader = new InputStreamReader(input);
			BufferedReader br = new BufferedReader(reader);
			
			FacesContext context = FacesContext.getCurrentInstance();
			TacheBean tb = context.getApplication().evaluateExpressionGet(context, "#{tacheBean}", TacheBean.class);
			
			Tache t = null;
			String s = "";
			while((s = br.readLine())!=null)
			{
				t = new Tache();
				t.setUrl(s);
				tb.ajouterTache(t);
			}
			br.close();
			reader.close();
			input.close();
		}
		catch(IOException e)
		{
			
		}
	}
}
