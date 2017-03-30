package dao;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.User;

@Stateless
public class UserDao {
	public static final String SELECT_EMAIL = "SELECT u FROM User u WHERE u.email=:email";
	public static final String PARAM_EMAIL = "email";
	
	public static final String SELECT_PSEUDO = "SELECT u FROM User u WHERE u.pseudo=:pseudo";
	public static final String PARAM_PSEUDO = "pseudo";
	
	@PersistenceContext(unitName = "INFO0921-WEB")
	private EntityManager em;
	
	public void creer(User u)
	{
		em.persist(u);
	}
	
	public void update(User u)
	{
		em.merge(u);
	}
	
	public User trouver(String email)
	{
		User u =  null;
		Query  rq = em.createQuery(SELECT_EMAIL);
		rq.setParameter(PARAM_EMAIL, email);
		if(!rq.getResultList().isEmpty())
		{
			u = (User)rq.getSingleResult();
		}
		
		return u;
	}
	
	public User trouverPseudo(String pseudo)
	{
		User u =  null;
		Query  rq = em.createQuery(SELECT_PSEUDO);
		rq.setParameter(PARAM_PSEUDO, pseudo);
		if(!rq.getResultList().isEmpty())
		{
			u = (User)rq.getSingleResult();
		}
		
		return u;
	}
	

}
