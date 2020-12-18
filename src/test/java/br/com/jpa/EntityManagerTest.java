package br.com.jpa;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;

import java.util.List;

public class EntityManagerTest extends EntityManagerFactoryTest {

	protected EntityManager manager;

	@Before
	public void iniciarEntityManager() {
		manager = entityManagerFactory.createEntityManager();
	}

	@After
	public void fechandoEntityManager() {
		manager.close();
	}


	public static void imprimirObjects(List<Object[]> objects){
		for (Object[] umObjeto : objects){
			System.out.println("-------------------------------------------");
			for (int i =0; i<umObjeto.length; i++){
				System.out.println(umObjeto[i].toString());
			}
		}
	}


}
