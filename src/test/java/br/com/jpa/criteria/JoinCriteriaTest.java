package br.com.jpa.criteria;

import br.com.jpa.EntityManagerTest;
import br.com.jpa.entity.ItemPedido;
import br.com.jpa.entity.Pagamento;
import br.com.jpa.entity.Pedido;
import br.com.jpa.entity.Produto;
import br.com.jpa.types.TipoPagamento;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

public class JoinCriteriaTest  extends EntityManagerTest {



    public void buscarPedidosComProdutoEspecifico(){


        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);
        Join<Pedido, ItemPedido> joinItemPedido = root.join("itens");
        Join<ItemPedido, Produto> joinProduto = joinItemPedido.join("produto");
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(joinProduto.get("id"), 5L));
        TypedQuery<Pedido> typedQuery = manager.createQuery(criteriaQuery);
        List<Pedido> resultList = typedQuery.getResultList();
        Assert.assertTrue(!resultList.isEmpty());
        resultList.forEach(p -> System.out.println(p.getTotal()));
    }


    public void usarJoinFetch(){

        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);
        root.fetch("cliente", JoinType.LEFT);
        root.fetch("pagamento", JoinType.LEFT);
        root.fetch("notaFiscal");
        criteriaQuery.select(root);
        TypedQuery<Pedido> typedQuery = manager.createQuery(criteriaQuery);

        List<Pedido> resultList = typedQuery.getResultList();
        Assert.assertTrue(!resultList.isEmpty());

    }

    @Test
    public void fazerJoinComOn(){

        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);
        Join<Pedido, Pagamento> joinPagamento = root.join("pagamento");
        joinPagamento.on(criteriaBuilder.equal(joinPagamento.get("tipoPagamento"), TipoPagamento.CARTAO));
        criteriaQuery.select(root);
        TypedQuery<Pedido> typedQuery = manager.createQuery(criteriaQuery);

        List<Pedido> resultList = typedQuery.getResultList();
        Assert.assertTrue(!resultList.isEmpty());
    }

}
















