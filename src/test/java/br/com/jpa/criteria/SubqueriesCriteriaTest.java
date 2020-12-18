package br.com.jpa.criteria;

import br.com.jpa.EntityManagerTest;
import br.com.jpa.entity.Cliente;
import br.com.jpa.entity.ItemPedido;
import br.com.jpa.entity.Pedido;
import br.com.jpa.entity.Produto;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.List;

public class SubqueriesCriteriaTest extends EntityManagerTest {


    public void pesquisarComAllExercicio() {


        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
        Root<ItemPedido> root = criteriaQuery.from(ItemPedido.class);
        criteriaQuery.select(root.get("produto"));
        criteriaQuery.distinct(true);

        Subquery<BigDecimal> subquery = criteriaQuery.subquery(BigDecimal.class);
        Root<ItemPedido> itemPedidoRoot = subquery.from(ItemPedido.class);
        subquery.select(itemPedidoRoot.get("precoProduto"));
        subquery.where(
                criteriaBuilder.equal(itemPedidoRoot.get("produto"), root.get("produto")),
                criteriaBuilder.notEqual(itemPedidoRoot, root)
        );
        criteriaQuery.where(criteriaBuilder.equal(root.get("precoProduto"), criteriaBuilder.all(subquery)));
        TypedQuery<Produto> typedQuery = manager.createQuery(criteriaQuery);
        List<Produto> resultList = typedQuery.getResultList();
        resultList.forEach(umProduto -> System.out.println(umProduto.getNome()));
    }


    public void pesquisarComAny() {

        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> root = criteriaQuery.from(Produto.class);
        criteriaQuery.select(root);
        Subquery<BigDecimal> subquery = criteriaQuery.subquery(BigDecimal.class);
        Root<ItemPedido> rootItemPedido = subquery.from(ItemPedido.class);
        subquery.select(rootItemPedido.get("precoProduto"));
        subquery.where(criteriaBuilder.equal(rootItemPedido.get("produto"), root));
        criteriaQuery.where(criteriaBuilder.notEqual(root.get("preco"),
                criteriaBuilder.any(subquery)));
        TypedQuery<Produto> typedQuery = manager.createQuery(criteriaQuery);
        List<Produto> resultList = typedQuery.getResultList();
        resultList.forEach(p -> System.out.println(p.getNome()));
    }




    public void recuperaSimples(){

        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);
        criteriaQuery.select(root);
        Subquery<Long> subquery = criteriaQuery.subquery(Long.class);
        Root<Cliente> rootSubquery = subquery.from(Cliente.class);
        subquery.select(rootSubquery.get("id"));
        subquery.where(rootSubquery.get("id").in(1,10));
        criteriaQuery.where(root.get("cliente").in(subquery));
        TypedQuery<Pedido> typedQuery = manager.createQuery(criteriaQuery);
        List<Pedido> resultList = typedQuery.getResultList();
        resultList.forEach(pedido -> System.out.println(pedido.getId()));
    }



    public void recuperaSubquerys(){

        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
        Root<Cliente> root = criteriaQuery.from(Cliente.class);
        criteriaQuery.select(root);
        Subquery<BigDecimal> subquery = criteriaQuery.subquery(BigDecimal.class);
        Root<Pedido> rootSubQuery = subquery.from(Pedido.class);
        subquery.select(rootSubQuery.get("total"));
        subquery.where(criteriaBuilder.equal(root, rootSubQuery.get("cliente")));
        criteriaQuery.where(criteriaBuilder.greaterThan(subquery, new BigDecimal("3000")));
        TypedQuery<Cliente> typedQuery = manager.createQuery(criteriaQuery);
        typedQuery.getResultList();
    }

}























