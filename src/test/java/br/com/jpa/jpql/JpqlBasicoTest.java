package br.com.jpa.jpql;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import br.com.jpa.EntityManagerTest;
import br.com.jpa.dto.ClienteDTO;
import br.com.jpa.entity.Cliente;
import br.com.jpa.entity.Pedido;
import br.com.jpa.entity.Produto;

public class JpqlBasicoTest extends EntityManagerTest {

	public void recuperaTodosClientes() {
		String jhql = "select p from Cliente p";
		TypedQuery<Cliente> query = manager.createQuery(jhql, Cliente.class);
		List<Cliente> clientes = query.getResultList();
		assertTrue(clientes.size() > 0);
		clientes.forEach(p -> System.out.println(p.getNome()));
	}

	public void recuperaTodosCliente() {
		String jhql = "select p from Cliente p where p.id =:id";
		TypedQuery<Cliente> query = manager.createQuery(jhql, Cliente.class);
		query.setParameter("id", 27L);
		Cliente cliente = query.getSingleResult();
		assertTrue(cliente.getId() == 27);
	}

	public void recuperaClientesOrdenado() {
		String jhql = "select p from Cliente p order by p.nome";
		TypedQuery<Cliente> query = manager.createQuery(jhql, Cliente.class);
		List<Cliente> clientes = query.getResultList();
		Assert.assertFalse(clientes.isEmpty());

	}

	public void recuperaClienteDTO() {
		String jpql = "select new br.com.jpa.dto.ClienteDTO(id, nome) from Cliente ";
		TypedQuery<ClienteDTO> query = manager.createQuery(jpql, ClienteDTO.class);
		List<ClienteDTO> resultList = query.getResultList();
		Assert.assertFalse(resultList.isEmpty());
	}

	public void pedidoDistinct() {
		StringBuilder jpql = new StringBuilder();
		jpql.append("select distinct ped from Pedido ped ");
		jpql.append("inner join ped.itens ite ");
		jpql.append("inner join ite.produto pro ");
		jpql.append("where pro.id in(5,13) ");
		TypedQuery<Pedido> query = manager.createQuery(jpql.toString(), Pedido.class);
		List<Pedido> resultList = query.getResultList();
		Assert.assertFalse(resultList.isEmpty());
	}

	public void recuperaProduto() {
		String jpql = "select id, nome from Produto";
		TypedQuery<Object[]> query = manager.createQuery(jpql, Object[].class);
		List<Object[]> result = query.getResultList();
		Assert.assertTrue(result != null);
	}

	public void recuperaUmAtributoProduto() {
		String jpql = "select p.nome from Produto p";
		TypedQuery<String> query = manager.createQuery(jpql, String.class);
		List<String> nomes = query.getResultList();
		assertTrue(!nomes.isEmpty());
		nomes.forEach(n -> System.out.println(n));
	}

	 
	public void recuperaProdutoPorId() {
		String jpql = "select p from Produto p where p.id = 5";
		TypedQuery<Produto> query = manager.createQuery(jpql, Produto.class);
		Produto produto = query.getSingleResult();
		Assert.assertNotNull(produto);
	}
	
	
	@Test
	public void recuperaTuple() {
		
		String jpql = "select p.id, p.nome from Produto p";
		Query createQuery =  manager.createQuery(jpql);
		 
		List resultList = createQuery.getResultList();
		
		
		//tuples.stream().map(t -> new MyClass(t.get(idExpr), t.get(priceExpr), ...)).collect(Collectors.toList())

		 System.out.println(resultList);
	}
	
	
	 

}





