package br.com.jpa.jpql;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import javax.persistence.TypedQuery;

import org.junit.Test;

import br.com.jpa.EntityManagerTest;

public class FuncoesTest extends EntityManagerTest{
	
	@Test
	public void aplicarFuncaoAgregacao() {
		
		String jpql = "select sum(p.preco) from Produto p";
		
		TypedQuery<BigDecimal> query = manager.createQuery(jpql, BigDecimal.class);
		
		BigDecimal preco = query.getSingleResult();
		
		assertTrue(preco !=null);
		
		System.out.println(preco);
	}

}
