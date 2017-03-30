package validators;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import dao.ConnexionDao;
import entity.User;
import managedBeans.UtilisateurBean;

@FacesValidator(value = "validerConnexion")
public class ValiderConnexion implements Validator{
	private static final String ATTRIBUT_LOGIN = "cLOGIN";
	private static final String MSG = "Login/mot de passe inconnu";
	
	@EJB
	private ConnexionDao connexion;
	
	 @Override
	    public void validate( FacesContext context, UIComponent component, Object value ) throws ValidatorException {

	        UIInput composantLogin = (UIInput) component.getAttributes().get( ATTRIBUT_LOGIN );
	        String login = (String) composantLogin.getValue();
	        String password = (String) value;

	        if (login != null && password != null && !login.equals("") && !password.equals("")) 
	        {

	        	User u = connexion.trouver(login, password);
	        	if(u != null)
	        	{
	        		UtilisateurBean ub = context.getApplication().evaluateExpressionGet(context, "#{utilisateurBean}", UtilisateurBean.class);
					ub.setType(u.getType());
					ub.setU(u);
	        	}
	        	else
	        	{
		            throw new ValidatorException(new FacesMessage( FacesMessage.SEVERITY_ERROR, MSG, null ) );
	        	}	
	        }
	        
	    }

}
