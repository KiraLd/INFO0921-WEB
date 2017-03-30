package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.faces.validator.FacesValidator;


@FacesValidator(value="validerLabel")
public class ValiderLabel implements Validator {
	
	private static final String EMPTY = "Veuillez entrez les tags correspondant � l'image";
	

	
	@Override
    public void validate( FacesContext context, UIComponent component, Object value ) throws ValidatorException {
        /* R�cup�ration de la valeur � traiter depuis le param�tre value */
        String nom = (String) value;
            if ( nom.length() == 0) {
                throw new ValidatorException(new FacesMessage( FacesMessage.SEVERITY_ERROR, EMPTY, null ) );
            }

    }
	

}
