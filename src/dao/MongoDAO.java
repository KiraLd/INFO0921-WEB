package dao;
import javax.ejb.Stateless;
import org.bson.Document;


import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

@Stateless
public class MongoDAO {
	
	private MongoClient mongoClient;
	
	
	public void ajouterResultat(Document doc)
	{
		mongoClient = new MongoClient("127.0.0.1",27017);
		MongoDatabase db= mongoClient.getDatabase("info0921");
		MongoCollection<Document> col = db.getCollection("task");
		col.insertOne(doc);
	}
	
	public FindIterable<Document> trouver(String nomProjet)
	{
		mongoClient =  new MongoClient("127.0.0.1",27017);
		MongoDatabase db= mongoClient.getDatabase("info0921");
		MongoCollection<Document> col = db.getCollection("task");
		FindIterable<Document> res = col.find(Filters.eq("nom", nomProjet));
		return res;
	}
	
	public void close()
	{
		mongoClient.close();
	}
}
