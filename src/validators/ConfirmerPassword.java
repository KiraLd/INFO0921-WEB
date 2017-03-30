package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "confirmerPassword")
public class ConfirmerPassword implements Validator{
	private static final String ATTRIBUT_MDP = "cMDP";
	private static final String MSG = "Le mot de passe et la confirmation doivent �tre identiques.";
	
	 @Override
	    public void validate( FacesContext context, UIComponent component, Object value ) throws ValidatorException {
	        /*
	         * R�cup�ration de l'attribut mot de passe parmi la liste des attributs
	         * du composant confirmation
	         */
	        UIInput composantMotDePasse = (UIInput) component.getAttributes().get( ATTRIBUT_MDP );
	        /*
	         * R�cup�ration de la valeur du champ, c'est-�-dire le mot de passe
	         * saisi
	         */
	        String motDePasse = (String) composantMotDePasse.getValue();
	        /* R�cup�ration de la valeur du champ confirmation */
	        String confirmation = (String) value;

	        if ( confirmation != null && !confirmation.equals( motDePasse ) ) {
	            /*
	             * Envoi d'une exception contenant une erreur de validation JSF
	             * initialis�e avec le message destin� � l'utilisateur, si les mots
	             * de passe sont diff�rents
	             */
	            throw new ValidatorException(
	                    new FacesMessage( FacesMessage.SEVERITY_ERROR, MSG, null ) );
	        }
	    }

}
