package br.com.jpa.jpql;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.persistence.TypedQuery;
import org.junit.Assert;
import org.junit.Test;
import br.com.jpa.EntityManagerTest;
import br.com.jpa.entity.Cliente;
import br.com.jpa.entity.Pagamento;
import br.com.jpa.entity.Pedido;
import br.com.jpa.entity.Produto;

public class ExpressoesCondicionaisTest extends EntityManagerTest {

	public void expressaoIN() {

		Cliente cliente1 = manager.find(Cliente.class, 1L);

		Cliente cliente2 = manager.find(Cliente.class, 10L);

		List<Cliente> clientes = Arrays.asList(cliente1, cliente2);

		String jpql = "select p from Pedido p where p.cliente (:clientes)";

		TypedQuery<Pedido> query = manager.createQuery(jpql, Pedido.class);
		query.setParameter("clientes", clientes);

		List<Pedido> pedidos = query.getResultList();

		Assert.assertFalse(pedidos.isEmpty());

		pedidos.forEach(p -> System.out.println(p.getTotal()));

	}

	
	public void expressaoCase() {
		StringBuilder jpql = new StringBuilder();

		jpql.append("select cli.nome, ped.total, ped.status, ");
		jpql.append("(case type(pag.tipoPagamento) ");
		jpql.append("when  CARTAO then 'Pagamento realizado com Cart�o' ");
		jpql.append("when  BOLETO then 'Pagamento realizado com Boleto' ");
		jpql.append("when  DINHEIRO then 'Pagamento realizado com Dinheiro' ");
		jpql.append("when  DEPOSITO then 'Pagamento realizado com deposito' ");
		jpql.append("else 'Pagamento n�o pago' ");
		jpql.append("end) as status ");
		jpql.append("from Pedido ped ");
		jpql.append("inner join ped.pagamento pag ");
		jpql.append("inner join ped.cliente cli ");

		TypedQuery<Object[]> query = manager.createQuery(jpql.toString(), Object[].class);

		List<Object[]> resultList = query.getResultList();

		Assert.assertNotNull(resultList);
	}

	
	public void expressaoDiferente() {
		
		String jpql = "select p from Pedido p where p.total <> 100 ";
		
		TypedQuery<Pedido> query = manager.createQuery(jpql, Pedido.class);
		List<Pedido> pedidos = query.getResultList();
		
		Assert.assertTrue(!pedidos.isEmpty());
		
		pedidos.forEach(p -> System.out.println(p.getCliente()));
		
	}
	
	
	public void between() {
		String jpql = "select p from Pedido p where p.dataConclusao between :dataInicio  and :dataFim";
		
		TypedQuery<Pedido> query = manager.createQuery(jpql, Pedido.class);
		
		query.setParameter("dataInicio", new Date());
		query.setParameter("dataFim", new Date());
		
		List<Pedido> pedidos = query.getResultList();
		
		Assert.assertTrue(pedidos.get(0).getTotal() !=null);
		pedidos.forEach(p -> System.out.println(p.getTotal()));
		
		
	}
	
	
	public void maiorMenorComDatas() {
		String jpql = "select p from Pedido p where p.dataConclusao < :data";
		
		TypedQuery<Pedido> query = manager.createQuery(jpql, Pedido.class);
		
		query.setParameter("data", new Date());
		
		List<Pedido> pedidos = query.getResultList();
		
		Assert.assertTrue(!pedidos.isEmpty());
		pedidos.forEach(p -> System.out.println(p.getStatus()));
		
		
	}
	
	
	public void maiorMenor() {
		String jpql = "select p from Pedido p where p.id >= :idInicicio and p.id <= :idFim";
		
		TypedQuery<Pedido> query = manager.createQuery(jpql, Pedido.class);
		query.setParameter("idInicicio", 14L);
		query.setParameter("idFim", 23L);
		
		List<Pedido> pedidos = query.getResultList();
		
		Assert.assertFalse(pedidos.isEmpty());
	}
	
	
	public void isNull() {
		
		String jpql = "select p from Pagamento p where p.numeroCartao is null";
		
		TypedQuery<Pagamento> query = manager.createQuery(jpql, Pagamento.class);
		
		List<Pagamento> pagamentos = query.getResultList();
		
		Assert.assertTrue(!pagamentos.isEmpty());
		pagamentos.forEach(p -> System.out.println(p.getId()));
		
	}
	
	
	
	public void isEmpty() {
		String jpql = "select p from Produto p where p.categoria is empty";
		
		TypedQuery<Produto> query = manager.createQuery(jpql, Produto.class);
		
		List<Produto> produtos = query.getResultList();
		
		assertFalse(produtos.isEmpty());
	}
	
	
	@Test
	public void expressaoCondicionalLike() {
		 String jpql = "select p from Produto p where p.nome like concat('%', :nome, '%')";

		 TypedQuery<Produto> query = manager.createQuery(jpql, Produto.class);
		 
		 String nome = "9";
		 query.setParameter("nome", nome);
		 
		 List<Produto> produtos = query.getResultList();
		 
		 assertTrue(produtos.get(0).getId() !=null);
		 
		 produtos.forEach(p -> System.out.println(p.getNome()));
	}
	
	
}








