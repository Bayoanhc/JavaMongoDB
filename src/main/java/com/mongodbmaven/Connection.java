package com.mongodbmaven;

import java.util.Arrays;

import org.bson.Document;
import org.bson.conversions.*;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class Connection {

	MongoDatabase DataBase;
	MongoCollection<Document> collectionDB;
	Document doc = new Document();
	
	public Connection() {
		try {
			
			MongoClient mongoClient = MongoClients.create();
			
			/*MongoClient mongoClient = MongoClients.create(
			        MongoClientSettings.builder()
			                .applyToClusterSettings(builder ->
			                        builder.hosts(Arrays.asList(new ServerAddress("localhost", 27017))))
			                .build());*/
			
			DataBase = mongoClient.getDatabase("ToDo");
			collectionDB = DataBase.getCollection("ToDo");
			
			System.out.println("Connected to database");
		
		} catch(Exception e) {
			System.out.print(e);
			System.out.println("CATCH CATCH CATCH");
		}
		
		System.out.println("Server is ready");
	}
	
	//String
	public boolean createSingle(String task) {
		doc.put("task", task);
		collectionDB.insertOne(doc);
		
		return true;
	}
	
	//Document
	public boolean createMany(Document doc) {
		//doc.put("task", task);
		collectionDB.insertOne(doc);
		
		return true;
	}
	
	public void read() {
		MongoCursor<Document> cursor = collectionDB.find().iterator();
		while(cursor.hasNext()) {
			System.out.print(cursor.next());
		}
	}
	
	public boolean update(String oldField, String newField) {
		Document found = (Document) collectionDB.find(new Document("task", oldField)).first();
		
		if(found != null) {
			Bson updatedValue = new Document("task", newField);
			Bson updateOperation = new Document("$set", updatedValue);
			
			collectionDB.updateOne(found, updateOperation);
		}
		
		return true;
	}
	
	public boolean delete(String task) {
		doc.put("task", task);
		collectionDB.deleteOne(doc);
		
		return true;
	}
	
	public Document createJson(String name, String task, int days, String date) {
		
		Document docJson = new Document("name", name)
	             .append("task", task)
	             .append("days completation", days)
	             .append("start date", date);
		
		return docJson;
	}
	
}
