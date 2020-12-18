package br.com.jpa.jpql;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Test;

import br.com.jpa.EntityManagerTest;
import br.com.jpa.entity.Pedido;
import br.com.jpa.types.TipoStatusPedido;

public class NamedQueryTest extends EntityManagerTest {

	public void namedQuery() {
		TypedQuery<Pedido> query = manager.createNamedQuery(Pedido.recuperaPedidos, Pedido.class);
		
		List<Pedido> pedidos = query.getResultList();
		
		assertTrue(pedidos.get(0).getId() != null);
		
		pedidos.forEach(p -> System.out.println(p.getDataConclusao()));

	}
	
	
	public void namedQueryUltimoPedidoCadastrado() {
		TypedQuery<Long> query = manager.createNamedQuery(Pedido.recuperaMaiorPedidoCadastrado, Long.class);
		
		List<Long> maiorPedidoCadastrado = query.getResultList();
		
		assertTrue(maiorPedidoCadastrado != null);
		
		 System.out.println(maiorPedidoCadastrado);

	}
	
	
	@Test
	public void namedQueryPedidoPorStatus() {
		TypedQuery<Pedido> query = manager.createNamedQuery(Pedido.recuperaPedidoPorStatus, Pedido.class);
		query.setParameter("status", TipoStatusPedido.AGUARDANDO);
		List<Pedido> maiorPedidoCadastrado = query.getResultList();
		
		assertTrue(maiorPedidoCadastrado != null);
		
		 System.out.println(maiorPedidoCadastrado);

	}
	
	
	

}
