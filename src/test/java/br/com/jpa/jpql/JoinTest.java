package br.com.jpa.jpql;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import br.com.jpa.EntityManagerTest;
import br.com.jpa.entity.Pedido;

public class JoinTest extends EntityManagerTest {

	public void joinFetch() {

		StringBuilder jpql = new StringBuilder();

		jpql.append("select ped from Pedido ped ");
		jpql.append("left join fetch ped.pagamento pag ");
		jpql.append("left join ped.cliente cli ");
		jpql.append("left join fetch ped.notaFiscal nfe ");

		TypedQuery<Pedido> query = manager.createQuery(jpql.toString(), Pedido.class);

		List<Pedido> pedidos = query.getResultList();
		assertTrue(!pedidos.isEmpty());
		pedidos.forEach(p -> System.out.println(p.toString()));

	}

	public void leftJoin() {
		StringBuilder jpql = new StringBuilder();

		jpql.append("select ped from Pedido ped ");
		jpql.append("left join ped.pagamento pag ");
		jpql.append("where pag.status in('PROCESSANDO') ");

		TypedQuery<Pedido> query = manager.createQuery(jpql.toString(), Pedido.class);

		List<Pedido> pedidos = query.getResultList();

		assertTrue(pedidos.isEmpty());

		pedidos.forEach(p -> System.out.println(p.getStatus()));
	}

	@Test
	public void join() {
		String jpql = "select p from Pedido p join p.pagamento pag";

		TypedQuery<Object[]> typedQuery = manager.createQuery(jpql, Object[].class);

		List<Object[]> lista = typedQuery.getResultList();
		Assert.assertFalse(lista.isEmpty());
	}
}
