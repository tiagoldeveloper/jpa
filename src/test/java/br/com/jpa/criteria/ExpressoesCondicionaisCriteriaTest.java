package br.com.jpa.criteria;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.jpa.entity.Endereco;
import org.junit.Test;

import br.com.jpa.EntityManagerTest;
import br.com.jpa.entity.Cliente;
import br.com.jpa.entity.Pedido;

public class ExpressoesCondicionaisCriteriaTest extends EntityManagerTest {

	public void usarExpressaoIN1() {

		Cliente cliente1 = manager.find(Cliente.class, 6L);
		Cliente cliente2 = manager.find(Cliente.class, 14L);
		List<Cliente> clientes = Arrays.asList(cliente1, cliente2);
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
		Root<Pedido> root = criteriaQuery.from(Pedido.class);
		criteriaQuery.select(root);
		criteriaQuery.where(root.get("cliente").in(clientes));
		TypedQuery<Pedido> typedQuery = manager.createQuery(criteriaQuery);
		List<Pedido> pedidos = typedQuery.getResultList();

		assertTrue(pedidos != null);
		pedidos.forEach(p -> System.out.println(p.toString()));
	}

	public void usarExpressaoIN2() {

		List<Long> ids = Arrays.asList(23L, 14L);
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
		Root<Pedido> root = criteriaQuery.from(Pedido.class);
		criteriaQuery.select(root);
		criteriaQuery.where(root.get("id").in(ids));
		TypedQuery<Pedido> typedQuery = manager.createQuery(criteriaQuery);
		List<Pedido> pedidos = typedQuery.getResultList();

		assertTrue(!pedidos.isEmpty());
		pedidos.forEach(p -> System.out.println(p.toString()));
	}

	public void usarExpressaoCase() {
		try {

			CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
			CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
			Root<Pedido> root = criteriaQuery.from(Pedido.class);
			criteriaQuery.multiselect(root.get("total"), criteriaBuilder.selectCase(root.get("status"))
					.when("AGUARDANDO", "Aguarde até o pagamento ser efetuado").when("CANCELADO", "Pagamento cancelado")
					.when("PAGO", "Pagamento realizado!").otherwise("Status desconhecido"));
			TypedQuery<Object[]> typedQuery = manager.createQuery(criteriaQuery);
			List<Object[]> resultList = typedQuery.getResultList();

			assertTrue(resultList != null);

			resultList.forEach(p -> {
				System.out.println(p[0].toString());
				System.out.println(p[1].toString());
			});

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void usarBetween() {

		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
		Root<Pedido> root = criteriaQuery.from(Pedido.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.between(root.get("id"), 14, 23));
		TypedQuery<Pedido> typedQuery = manager.createQuery(criteriaQuery);
		List<Pedido> pedidos = typedQuery.getResultList();

		assertTrue(!pedidos.isEmpty());
		pedidos.forEach(p -> System.out.println(p.toString()));

	}

	public void usarExpressaoDiferente() {

		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
		Root<Pedido> root = criteriaQuery.from(Pedido.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.notEqual(root.get("total"), new BigDecimal("10000")));
		TypedQuery<Pedido> typedQuery = manager.createQuery(criteriaQuery);
		List<Pedido> pedidos = typedQuery.getResultList();
		
		assertTrue(!pedidos.isEmpty());
		pedidos.forEach(p -> System.out.println(p.getTotal()));
	}


	public void usarMaiorMenor() {
		
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
		Root<Pedido> root = criteriaQuery.from(Pedido.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.greaterThanOrEqualTo(root.get("id"), 14L),
				criteriaBuilder.lessThanOrEqualTo(root.get("id"), 23L));
		TypedQuery<Pedido> typedQuery = manager.createQuery(criteriaQuery);
		List<Pedido> pedidos = typedQuery.getResultList();

		assertFalse(pedidos.isEmpty());
		pedidos.forEach(pedido -> System.out.println(pedido.toString()));
	}



	public void usarIsEmpty(){

		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Endereco> criteriaQuery = criteriaBuilder.createQuery(Endereco.class);
		Root<Endereco> root = criteriaQuery.from(Endereco.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.isNull(root.get("complemento")));
		TypedQuery<Endereco> typedQuery = manager.createQuery(criteriaQuery);
		List<Endereco> enderecos = typedQuery.getResultList();

		assertTrue(!enderecos.isEmpty());
		enderecos.forEach(p -> System.out.println(p.toString()));
	}


	@Test
	public void usarExpressaoCondicionalLike(){

		CriteriaBuilder  criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Endereco> criteriaQuery = criteriaBuilder.createQuery(Endereco.class);
		Root<Endereco> root = criteriaQuery.from(Endereco.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.like(root.get("bairro"), "%A%"));
		TypedQuery<Endereco> typedQuery = manager.createQuery(criteriaQuery);
		List<Endereco> enderecos = typedQuery.getResultList();

		assertTrue(!enderecos.isEmpty());
		enderecos.forEach(p -> System.out.println(p.getBairro()));
	}

}
