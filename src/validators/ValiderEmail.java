package validators;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.faces.validator.FacesValidator;

import dao.UserDao;

@FacesValidator(value="validerEmail")
public class ValiderEmail implements Validator {
	
	private static final String EMAIL_EXIST = "Cette adresse email est d�j� utilis�e";
	
	@EJB
	private UserDao udao;
	
	@Override
    public void validate( FacesContext context, UIComponent component, Object value ) throws ValidatorException {
        /* R�cup�ration de la valeur � traiter depuis le param�tre value */
        String email = (String) value;
            if ( udao.trouver( email ) != null ) {
                throw new ValidatorException(new FacesMessage( FacesMessage.SEVERITY_ERROR, EMAIL_EXIST, null ) );
            }

    }
	

}
