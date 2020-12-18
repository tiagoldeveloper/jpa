package br.com.jpa.criteria;

import br.com.jpa.EntityManagerTest;
import br.com.jpa.entity.*;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

public class GroupByCriteriaTest extends EntityManagerTest {


    public void condicionarAgrupamentoComHaving(){

        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<ItemPedido> root = criteriaQuery.from(ItemPedido.class);
        Join<ItemPedido, Produto> joinProduto = root.join("produto");
        Join<Produto, Categoria> joinCategoria = joinProduto.join("categoria");
        criteriaQuery.multiselect(joinCategoria.get("nome"),
                criteriaBuilder.sum(root.get("precoProduto")),
                criteriaBuilder.avg(root.get("precoProduto")));
        criteriaQuery.groupBy(joinCategoria.get("id"));
        criteriaQuery.having(criteriaBuilder.greaterThan(
           criteriaBuilder.avg(
                   root.get("precoProduto")).as(BigDecimal.class),
                new BigDecimal(700)));

        TypedQuery<Object[]> typedQuery = manager.createQuery(criteriaQuery);
        List<Object[]> resultList = typedQuery.getResultList();
        resultList.forEach(p -> System.out.println("Categoria: "+ p[0] + " Sum: "+p[1] + " Avg: "+ p[2]));
    }


    public void agruparResultado(){

        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<ItemPedido> root = criteriaQuery.from(ItemPedido.class);
        Join<ItemPedido, Pedido> joinPedido = root.join("pedido");
        Join<Pedido, Cliente> joinCliente = joinPedido.join("cliente");
        criteriaQuery.multiselect(joinCliente.get("nome"), criteriaBuilder.sum(root.get("precoProduto")));
        criteriaQuery.groupBy(joinCliente.get("id"));
        TypedQuery<Object[]> typedQuery = manager.createQuery(criteriaQuery);
        List<Object[]> resultList = typedQuery.getResultList();

        Assert.assertTrue(!resultList.isEmpty());
        imprimirObjects(resultList);
    }


    public void agrupaResultadoPorCategoria(){

        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<ItemPedido> root = criteriaQuery.from(ItemPedido.class);
        Join<ItemPedido, Produto> joinProduto = root.join("produto");
        Join<Produto, Categoria> joinCategoria = joinProduto.join("categoria");
        criteriaQuery.multiselect(joinCategoria.get("nome"), criteriaBuilder.sum(root.get("precoProduto")));
        criteriaQuery.groupBy(joinCategoria.get("id"));
        TypedQuery<Object[]> typedQuery = manager.createQuery(criteriaQuery);

        List<Object[]> resultList = typedQuery.getResultList();
        Assert.assertTrue(!resultList.isEmpty());
        imprimirObjects(resultList);
    }

    @Test
    public void agrupaResultado(){

        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Produto> root = criteriaQuery.from(Produto.class);
        Join<Produto, Categoria> joinCategoria = root.join("categoria");
        criteriaQuery.multiselect(
                joinCategoria.get("nome"),
                criteriaBuilder.count(root.get("id")));

        criteriaQuery.groupBy(joinCategoria.get("id"));
        TypedQuery<Object[]> typedQuery = manager.createQuery(criteriaQuery);

        List<Object[]> resultList = typedQuery.getResultList();
        imprimirObjects(resultList);
    }

}