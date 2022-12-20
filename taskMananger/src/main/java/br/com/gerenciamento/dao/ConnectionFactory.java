package br.com.gerenciamento.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {
	
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("taskMananger");
	
	public static EntityManager getEntityMananger() {
		return factory.createEntityManager();
	}
	
}
