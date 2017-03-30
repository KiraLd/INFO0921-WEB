package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.faces.validator.FacesValidator;

@FacesValidator(value="validerDuree")
public class ValiderDuree implements Validator{
	
	private static final String NOT_POSITIF = "30 secondes minimum";
	private static final String TOO_BIG = "Veuillez entrer un nombre inférieur ou égale à 300";
		

		@Override
	    public void validate( FacesContext context, UIComponent component, Object value ) throws ValidatorException {
	        int n = (int) value;

	        
	        if(n<=30)
	        {
	            throw new ValidatorException(new FacesMessage( FacesMessage.SEVERITY_ERROR, NOT_POSITIF, null ) );
	        }
	        else if(n > 300)
	        {
	        	throw new ValidatorException(new FacesMessage( FacesMessage.SEVERITY_ERROR, TOO_BIG, null ) );
	        }
	            

	    }

}
