package br.com.jpa.criteria;

import br.com.jpa.EntityManagerTest;
import br.com.jpa.entity.Endereco;
import org.junit.Test;

import javax.persistence.Query;
import javax.persistence.criteria.*;


public class OperacoesEmLoteCriteriaTest extends EntityManagerTest {

    public void removerEmLoteExercicio(){

        manager.getTransaction().begin();
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaDelete<Endereco> criteriaDelete = criteriaBuilder.createCriteriaDelete(Endereco.class);
        Root<Endereco> root = criteriaDelete.from(Endereco.class);
        criteriaDelete.where(criteriaBuilder.between(root.get("id"), 41L,42L));
        Query query = manager.createQuery(criteriaDelete);
        query.executeUpdate();
        manager.getTransaction().commit();
    }

    @Test
    public void atualizarEmLote(){

        manager.getTransaction().begin();
        CriteriaBuilder  criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaUpdate<Endereco> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Endereco.class);
        Root<Endereco> root = criteriaUpdate.from(Endereco.class);
        criteriaUpdate.set("bairro", "xxxxxxxxxxxxxxxxx");
        criteriaUpdate.where(criteriaBuilder.equal(root.get("id"), 28L));

        Query query = manager.createQuery(criteriaUpdate);
        query.executeUpdate();
        manager.getTransaction().commit();
    }
}





















