package br.com.jpa.criteria;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Test;

import br.com.jpa.EntityManagerTest;
import br.com.jpa.dto.ClienteDTO;
import br.com.jpa.entity.Cliente;
import br.com.jpa.entity.Endereco;
import br.com.jpa.entity.Produto;

public class BasicoCriteriaTest extends EntityManagerTest {

	public void distinct() {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Endereco> query = builder.createQuery(Endereco.class);
		Root<Endereco> root = query.from(Endereco.class);
		query.select(root);
		query.distinct(true);
		TypedQuery<Endereco> typedQuery = manager.createQuery(query);
		List<Endereco> enderecos = typedQuery.getResultList();

		assertFalse(enderecos.isEmpty());
		enderecos.forEach(p -> System.out.println(p.getComplemento()));
	}

	public void ordenarResultados() {

		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Endereco> criteriaQuery = criteriaBuilder.createQuery(Endereco.class);
		Root<Endereco> root = criteriaQuery.from(Endereco.class);
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("bairro")));
		TypedQuery<Endereco> typedQuery = manager.createQuery(criteriaQuery);
		List<Endereco> enderecos = typedQuery.getResultList();
		enderecos.forEach(p -> System.out.println(p.toString()));
	}

	public void projetarOResultadoDTO() {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<ClienteDTO> criteriaQuery = criteriaBuilder.createQuery(ClienteDTO.class);
		Root<Cliente> root = criteriaQuery.from(Cliente.class);
		criteriaQuery.select(criteriaBuilder.construct(ClienteDTO.class, root.get("id"), root.get("nome")));
		TypedQuery<ClienteDTO> typedQuery = manager.createQuery(criteriaQuery);
		List<ClienteDTO> clienteDTO = typedQuery.getResultList();
		clienteDTO.forEach(p -> System.out.println(p.getNome()));
	}

	public void projetarOResultadoTuple() {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
		Root<Cliente> root = criteriaQuery.from(Cliente.class);
		criteriaQuery.select(criteriaBuilder.tuple(root.get("id").alias("id"), root.get("nome").alias("nome")));
		TypedQuery<Tuple> typedQuery = manager.createQuery(criteriaQuery);
		List<Tuple> resultList = typedQuery.getResultList();

		assertTrue(resultList.get(0) !=null);
		resultList.forEach(p -> System.out.println(p.get(0)));
	}

	public void projetarOResultado() {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<ClienteDTO> criteriaQuery = criteriaBuilder.createQuery(ClienteDTO.class);
		Root<Cliente> root = criteriaQuery.from(Cliente.class);
		criteriaQuery.multiselect(root.get("id"), root.get("nome"));
		TypedQuery<ClienteDTO> typedQuery = manager.createQuery(criteriaQuery);
		List<ClienteDTO> clientes = typedQuery.getResultList();
		clientes.forEach(p -> System.out.println(p.getNome()));

		assertFalse(clientes.isEmpty());
	}

	
	public void retornarTodosOsProdutosExercicio() {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
		Root<Produto> root = criteriaQuery.from(Produto.class);
		criteriaQuery.select(root);
		TypedQuery<Produto> typedQuery = manager.createQuery(criteriaQuery);
		List<Produto> produtos = typedQuery.getResultList();

		assertNotNull(produtos);
		produtos.forEach(p -> System.out.println(p.toString()));
	}
	
	
	public void teste() {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Endereco> criteriaQuery = criteriaBuilder.createQuery(Endereco.class);
		Root<Endereco> root = criteriaQuery.from(Endereco.class);
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("bairro")));
		TypedQuery<Endereco> typedQuery = manager.createQuery(criteriaQuery);
		List<Endereco> enderecos = typedQuery.getResultList();

		enderecos.forEach(p -> System.out.println(p.toString()));
	}
	
	@Test
	public void selecionarUmAtributoParaRetorno() {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<BigDecimal> criteriaQuery = criteriaBuilder.createQuery(BigDecimal.class);
		Root<Produto> root = criteriaQuery.from(Produto.class);
		criteriaQuery.multiselect(root.get("preco")).where(criteriaBuilder.equal(root.get("id"), 5));
		TypedQuery<BigDecimal> typedQuery = manager.createQuery(criteriaQuery);
		BigDecimal total = typedQuery.getSingleResult();

		assertTrue(total !=null);
		System.out.println(total);
	}
}