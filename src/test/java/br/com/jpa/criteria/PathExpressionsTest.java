package br.com.jpa.criteria;

import br.com.jpa.EntityManagerTest;
import br.com.jpa.entity.ItemPedido;
import br.com.jpa.entity.Pedido;
import br.com.jpa.entity.Produto;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

public class PathExpressionsTest extends EntityManagerTest {



    public void buscarPedidosComProdutoDeIDIgual1Exercicio(){

        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);
        Join<Pedido, ItemPedido> joinItemPedido = root.join("itens");
        Join<ItemPedido, Produto> joinProduto = joinItemPedido.join("produto");
        criteriaQuery.select(root);

        criteriaQuery.where(criteriaBuilder.equal(joinItemPedido.get("produto").get("id"), 5L));

        TypedQuery<Pedido> typedQuery = manager.createQuery(criteriaQuery);

        List<Pedido> resultList = typedQuery.getResultList();

        assert !resultList.isEmpty();


    }

    @Test
    public void usarPathExpression(){

        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.like(root.get("cliente").get("nome"), "O%"));
        TypedQuery<Pedido> typedQuery = manager.createQuery(criteriaQuery);
        List<Pedido> resultList = typedQuery.getResultList();

        resultList.forEach(p -> System.out.println(p.getCliente().getNome()));

        assert !resultList.isEmpty();
    }
}




















