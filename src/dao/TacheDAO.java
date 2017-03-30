package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import entity.Tache;

@Stateless
public class TacheDAO {
	
	public static final String SELECT_TACHE = "SELECT t FROM Tache t WHERE t.fini = 0 AND t.working = 0 ";

	@PersistenceContext(unitName = "INFO0921-WEB")
	private EntityManager em;
	
	public void creer(Tache t, String nom, int n, String url)
	{
		em.persist(t);
		
		/*
		try
		{
			MongoClient mongoClient = new MongoClient("127.0.0.1",27017);
			MongoDatabase db= mongoClient.getDatabase("info0921");
			MongoCollection<Document> col = db.getCollection("task");
			Document doc = new Document("key",nom+Integer.toString(n)).append("nom", nom).append("id", n).append("url", url);
			col.insertOne(doc);
			mongoClient.close();
			
		}
		catch(Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}*/
		
	}
	
	public void update(Tache t)
	{
		em.merge(t);
	}
	
	public Tache selectionner()
	{
		Query rq = em.createQuery(SELECT_TACHE);
		
		@SuppressWarnings("unchecked")
		List<Tache> l = rq.setMaxResults(1).getResultList();
		Tache t = new Tache();
		if(!l.isEmpty())
		{
			t = (Tache)l.get(0);
		}
		return t;
	}
}
