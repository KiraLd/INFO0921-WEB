package validators;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.faces.validator.FacesValidator;

import dao.ProjetDao;

@FacesValidator(value="validerProjet")
public class ValiderProjet implements Validator {
	
	private static final String PROJET_EXIST = "Nom d�j� utilis�";
	
	@EJB
	private ProjetDao pdao;
	
	@Override
    public void validate( FacesContext context, UIComponent component, Object value ) throws ValidatorException {
        /* R�cup�ration de la valeur � traiter depuis le param�tre value */
        String nom = (String) value;
            if ( pdao.trouver( nom ) != null ) {
                throw new ValidatorException(new FacesMessage( FacesMessage.SEVERITY_ERROR, PROJET_EXIST, null ) );
            }

    }
	

}
