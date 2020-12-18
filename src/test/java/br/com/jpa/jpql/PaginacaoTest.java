package br.com.jpa.jpql;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Test;

import br.com.jpa.EntityManagerTest;
import br.com.jpa.entity.Produto;

public class PaginacaoTest extends EntityManagerTest{

	
	@Test
	public void paginacao() {
		
		String jpql = "select p from Produto p order by p.nome";
		
		TypedQuery<Produto> query = manager.createQuery(jpql, Produto.class);
		
		
		query.setFirstResult(2);
		query.setMaxResults(2);
		
		
		List<Produto> produtos = query.getResultList();
		
		
		assertTrue(produtos.isEmpty());
		produtos.forEach(p -> System.out.println(p.getDescricao()));
		
	}
	
}
