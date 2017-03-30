package dao;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.User;

@Stateless
public class ConnexionDao {
	public static final String SELECT_CONNEXION = "SELECT u FROM User u WHERE u.email=:email AND u.password=:password";
	public static final String PARAM_EMAIL = "email";
	public static final String PARAM_PASSWORD ="password";
	
	@PersistenceContext(unitName = "INFO0921-WEB")
	private EntityManager em;
	

	
	public User trouver(String email,String password)
	{
		Query  rq = em.createQuery(SELECT_CONNEXION);
		rq.setParameter(PARAM_EMAIL, email);
		rq.setParameter(PARAM_PASSWORD,password);
		if(!rq.getResultList().isEmpty())
		{
			User u = (User)rq.getResultList().get(0);
			return u;
		}
		return null;
	}
	
	
}
