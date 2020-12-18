package br.com.jpa.jpql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.jpa.EntityManagerTest;
import br.com.jpa.entity.Produto;

public class JpqlNamedQueryDinamica extends EntityManagerTest{

	@BeforeClass
	public static void criarNamedQuery() {
		entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
		EntityManager manager = entityManagerFactory.createEntityManager();
		String jpql = "select p from Produto p";
		TypedQuery<Produto> query = manager.createQuery(jpql, Produto.class);
		entityManagerFactory.addNamedQuery("Produto.recuperaTotos", query);
	}
	
	@Test
	public void recuperaProdutoPorNamedQueryDinamica() {
		TypedQuery<Produto> query = manager.createNamedQuery("Produto.recuperaTotos", Produto.class);
		List<Produto> produtos = query.getResultList();
		Assert.assertTrue(!produtos.isEmpty());
	}
	
}
