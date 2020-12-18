package br.com.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;

public class EntityManagerFactoryTest {

	protected static EntityManagerFactory entityManagerFactory;
	
	@BeforeClass
	public static void criandoEntityManagerFactoryBeforeClasse() {
		entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
	}

	@AfterClass
	public static void destruindoEntityManagerFactoryAfterClasse() {
		entityManagerFactory.close();
	}
}
