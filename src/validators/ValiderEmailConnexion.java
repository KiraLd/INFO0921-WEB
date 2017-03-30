package validators;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.faces.validator.FacesValidator;

import dao.UserDao;

@FacesValidator(value="validerEmailConnexion")
public class ValiderEmailConnexion implements Validator {
	
	private static final String EMAIL_EXIST = "Adresse email inconnue";
	
	@EJB
	private UserDao udao;
	
	@Override
    public void validate( FacesContext context, UIComponent component, Object value ) throws ValidatorException {
        String email = (String) value;
            if ( udao.trouver( email ) == null ) {
                throw new ValidatorException(new FacesMessage( FacesMessage.SEVERITY_ERROR, EMAIL_EXIST, null ) );
            }

    }
	

}
