package com.mongodbmaven;

import org.bson.Document;

public class MongoDBMain {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Connection connection = new Connection();
		
		//connection.create("create CRUD");

		connection.createMany(connection.createJson("Bayoan", "Create App for Biri El Loco", 2, "01/24/2020"));
		
		connection.read();
	}

}
