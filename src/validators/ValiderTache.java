package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import managedBeans.UtilisateurBean;

import javax.faces.validator.FacesValidator;

@FacesValidator(value="validerTache")
public class ValiderTache implements Validator{

private static final String NOT_POSITIF = "Veuillez entrer un nombre positif";
private static final String TOO_BIG = "Veuillez entrer un nombre inf�rieur ou �gale � 1000";
	

	@Override
    public void validate( FacesContext context, UIComponent component, Object value ) throws ValidatorException {
        /* R�cup�ration de la valeur � traiter depuis le param�tre value */
        int n = (int) value;
        
		UtilisateurBean ub = context.getApplication().evaluateExpressionGet(context, "#{utilisateurBean}", UtilisateurBean.class);
        
        if(n<=0)
        {
            throw new ValidatorException(new FacesMessage( FacesMessage.SEVERITY_ERROR, NOT_POSITIF, null ) );
        }
        else if(n > 1000)
        {
        	throw new ValidatorException(new FacesMessage( FacesMessage.SEVERITY_ERROR, TOO_BIG, null ) );
        }
        else if(n*0.5 > ub.getU().getCredit())
        {
        	String credit = Float.toString((float)(n*0.5))+" cr�dits requis. Il vous en reste "+Float.toString(ub.getU().getCredit());
        	throw new ValidatorException(new FacesMessage( FacesMessage.SEVERITY_ERROR, credit, null ) );
        }
            

    }
}
