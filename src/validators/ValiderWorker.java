package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.faces.validator.FacesValidator;

@FacesValidator(value="validerWorker")
public class ValiderWorker implements Validator{
	
	private static final String NOT_POSITIF = "Veuillez entrer un nombre positif";
	private static final String TOO_BIG = "Veuillez entrer un nombre inférieur ou égale à 5";
		

		@Override
	    public void validate( FacesContext context, UIComponent component, Object value ) throws ValidatorException {
	        /* Récupération de la valeur à traiter depuis le paramètre value */
	        int n = (int) value;

	        if(n<=0)
	        {
	            throw new ValidatorException(new FacesMessage( FacesMessage.SEVERITY_ERROR, NOT_POSITIF, null ) );
	        }
	        else if(n > 5)
	        {
	        	throw new ValidatorException(new FacesMessage( FacesMessage.SEVERITY_ERROR, TOO_BIG, null ) );
	        }
	            

	    }

}
