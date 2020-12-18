package br.com.jpa.criteria;

import br.com.jpa.EntityManagerTest;
import br.com.jpa.entity.Pedido;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class FuncoesCriteriaTest extends EntityManagerTest {

    @DisplayName("Executa query no banco caso for verdadeira")
    @Test
    public void aplicarFuncaoAgregacao(){

        CriteriaBuilder  criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);
        criteriaQuery.multiselect(criteriaBuilder.avg(root.get("total")),
                criteriaBuilder.sum(root.get("total")),
                criteriaBuilder.min(root.get("total")),
                criteriaBuilder.max(root.get("total")));
        TypedQuery<Object[]> typedQuery = manager.createQuery(criteriaQuery);
        List<Object[]> objects = typedQuery.getResultList();

        objects.forEach(p -> System.out.println(p[0]));
        objects.forEach(p -> System.out.println(p[1]));
        objects.forEach(p -> System.out.println(p[2]));
        objects.forEach(p -> System.out.println(p[3]));

    }

}
