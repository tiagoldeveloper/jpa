package br.com.jpa.jpql;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import br.com.jpa.EntityManagerTest;

public class GroupByTest extends EntityManagerTest {

	@Test
	public void condicionarAgrupamentoComHaving() {

		StringBuilder jpql = new StringBuilder();

		jpql.append("select cat.nome, sum(ipe.precoProduto) ");
		jpql.append("from ItemPedido ipe ");
		jpql.append("inner join ipe.produto pro ");
		jpql.append("inner join pro.categoria cat ");
		jpql.append("group by cat.id ");
		jpql.append("having sum(ipe.precoProduto) > 100 ");

		TypedQuery<Object[]> query = manager.createQuery(jpql.toString(), Object[].class);

		List<Object[]> resultado = query.getResultList();

		Assert.assertTrue(resultado.size() > 0);
		
		resultado.forEach(p ->  System.out.println(p[0]));
	}

}
