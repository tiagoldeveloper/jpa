package br.com.jpa.criteria;

import br.com.jpa.EntityManagerTest;
import br.com.jpa.entity.NotaFiscal;
import org.junit.Test;

import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PassandoParametrosCriteriaTest extends EntityManagerTest {


     public void passarParametroDate(){

        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<NotaFiscal> criteriaQuery = criteriaBuilder.createQuery(NotaFiscal.class);

        Root<NotaFiscal> root = criteriaQuery.from(NotaFiscal.class);
        criteriaQuery.select(root);

        var dateParameterExpression = criteriaBuilder.parameter(Date.class, "dataEmissao");

        criteriaQuery.where(criteriaBuilder.greaterThan(root.get("dataEmissao"), dateParameterExpression));

        TypedQuery<NotaFiscal> typedQuery = manager.createQuery(criteriaQuery);

        Calendar dataInicial = Calendar.getInstance();
        dataInicial.set(Calendar.DATE, -3);

        typedQuery.setParameter("dataEmissao", dataInicial.getTime(), TemporalType.TIMESTAMP);

        List<NotaFiscal> resultList = typedQuery.getResultList();

        assert resultList ==null;

    }

    @Test
    public void passarParametro(){

        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<NotaFiscal> criteriaQuery = criteriaBuilder.createQuery(NotaFiscal.class);

        Root<NotaFiscal> root = criteriaQuery.from(NotaFiscal.class);
        criteriaQuery.select(root);

        ParameterExpression<Long> parameterExpression = criteriaBuilder.parameter(Long.class, "id");
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), parameterExpression));

        TypedQuery<NotaFiscal> typedQuery = manager.createQuery(criteriaQuery);
        typedQuery.setParameter("id", 9L);


        List<NotaFiscal> resultList = typedQuery.getResultList();


        assert !resultList.isEmpty();

        resultList.forEach(p -> System.out.println(p.getId()));

    }

}
















































