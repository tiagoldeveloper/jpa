package br.com.jpa.criteria;

import br.com.jpa.EntityManagerTest;
import br.com.jpa.entity.Pedido;
import br.com.jpa.types.TipoStatusPedido;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

public class OperadoresLogicosCriteriaTest  extends EntityManagerTest {

    @Test
    public void usarOperadores(){

        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);
        criteriaQuery.select(root);
        criteriaQuery.where(
                criteriaBuilder.or(
                        criteriaBuilder.equal(root.get("status"), TipoStatusPedido.AGUARDANDO),
                        criteriaBuilder.equal(root.get("status"), TipoStatusPedido.PAGO)
                ),
                criteriaBuilder.greaterThan(root.get("total"), new BigDecimal("1000"))
        );

        TypedQuery<Pedido> typedQuery = manager.createQuery(criteriaQuery);
        List<Pedido> resultList = typedQuery.getResultList();

        Assert.assertFalse(resultList.isEmpty());

    }


}
