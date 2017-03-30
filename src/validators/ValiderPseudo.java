package validators;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.faces.validator.FacesValidator;

import dao.UserDao;

@FacesValidator(value="validerPseudo")
public class ValiderPseudo implements Validator {
	
	private static final String PSEUDO_EXIST = "Pseudo déjà utilisé";
	
	@EJB
	private UserDao udao;
	
	@Override
    public void validate( FacesContext context, UIComponent component, Object value ) throws ValidatorException {
        /* Récupération de la valeur à traiter depuis le paramètre value */
        String pseudo = (String) value;
            if ( udao.trouverPseudo( pseudo ) != null ) {
                throw new ValidatorException(new FacesMessage( FacesMessage.SEVERITY_ERROR, PSEUDO_EXIST, null ) );
            }

    }
	

}
