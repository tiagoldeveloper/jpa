package br.com.jpa.temp;

import br.com.jpa.EntityManagerTest;
import br.com.jpa.entity.Cliente;
import br.com.jpa.entity.ItemPedido;
import br.com.jpa.entity.Pedido;
import br.com.jpa.entity.Produto;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.time.Duration;
import java.time.Instant;


public class Exemplos extends EntityManagerTest {

    //jpql = 61000600

    @Test
    public void testeCriteria() {

        Instant inicio = Instant.now();
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);

        Root<Pedido> root = criteriaQuery.from(Pedido.class);
        Join<Pedido, ItemPedido> joinItemPedido = root.join("itens");
        Join<ItemPedido, Produto> joinProduto = joinItemPedido.join("produto");
        Join<Pedido, Cliente> joinClientePedido = root.join("cliente");
        criteriaQuery.select(root);

        TypedQuery<Pedido> typedQuery = manager.createQuery(criteriaQuery);
        typedQuery.getResultList();

        Instant fim = Instant.now();

        Duration between = Duration.between(inicio, fim);

        System.out.println(between.getNano());
    }
}
