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
	private static final String TOO_BIG = "Veuillez entrer un nombre inf�rieur ou �gale � 5";
		

		@Override
	    public void validate( FacesContext context, UIComponent component, Object value ) throws ValidatorException {
	        /* R�cup�ration de la valeur � traiter depuis le param�tre value */
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
