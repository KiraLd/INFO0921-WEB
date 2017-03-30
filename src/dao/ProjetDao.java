package dao;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Projet;
import entity.Tache;

@Stateless
public class ProjetDao {
	
	public static final String SELECT_NAME = "SELECT p FROM Projet p WHERE p.nom=:nom";
	public static final String PARAM_NAME = "nom";
	
	public static final String SELECT_ALL = "SELECT p FROM Projet p WHERE p.fkeyuser=:fkeyuser";
	public static final String PARAM_EMAIL = "fkeyuser";
	
	public static final String SELECT_ALL_TASK = "SELECT t FROM Tache t WHERE t.nomProjet=:nomProjet AND t.fini = 1";
	public static final String PARAM_NOM_PROJET = "nomProjet";
	
	@PersistenceContext(unitName = "INFO0921-WEB")
	private EntityManager em;
	
	public void creer(Projet p)
	{
		
		em.persist(p);
		
	}
	
	public void update(Projet p)
	{
		em.merge(p);
	}
	
	public Projet trouver(String nom)
	{
		Projet p =  null;
		Query  rq = em.createQuery(SELECT_NAME);
		rq.setParameter(PARAM_NAME, nom);
		if(!rq.getResultList().isEmpty())
		{
			p = (Projet)rq.getSingleResult();
		}
		
		return p;
	}
	
	public boolean projetTermine(String nom, int n)
	{
		Query rq = em.createQuery(SELECT_ALL_TASK);
		rq.setParameter(PARAM_NOM_PROJET, nom);
		@SuppressWarnings("unchecked")
		List<Tache> l = rq.getResultList();
		if(l.size() < n)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public List<Projet> trouverProjets(String email)
	{
		Query rq = em.createQuery(SELECT_ALL);
		rq.setParameter(PARAM_EMAIL, email);
		@SuppressWarnings("unchecked")
		List<Projet> l = rq.getResultList();
		if(!l.isEmpty())
		{
			for(Projet p: l)
			{
				p.setFini(this.projetTermine(p.getNom(),p.getN()));
			}
		}
		return l;
	}

}
