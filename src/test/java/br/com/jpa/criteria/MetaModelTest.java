package br.com.jpa.criteria;

import br.com.jpa.EntityManagerTest;
import br.com.jpa.entity.Produto;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class MetaModelTest extends EntityManagerTest {

    @Test
    public void utilizarMetaModel(){

        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> root = criteriaQuery.from(Produto.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.
                      or(criteriaBuilder.like(root.get("nome"), "%Ps%"),
                        criteriaBuilder.like(root.get("descricao"), "%PS%")));


        TypedQuery<Produto> typedQuery = manager.createQuery(criteriaQuery);
        List<Produto> resultList = typedQuery.getResultList();


        Assert.assertTrue(!resultList.isEmpty());
    }

}
