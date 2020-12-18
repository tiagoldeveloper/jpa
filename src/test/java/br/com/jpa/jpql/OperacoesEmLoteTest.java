package br.com.jpa.jpql;

import static org.junit.Assert.assertFalse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Query;

import org.junit.Test;

import br.com.jpa.EntityManagerTest;
import br.com.jpa.entity.Endereco;
import br.com.jpa.entity.Produto;

public class OperacoesEmLoteTest extends EntityManagerTest {
	private static final int LIMITE_INSERCOES = 4;

	public void gravar() {

		Endereco endereco1 = new Endereco();
		endereco1.setCep("8787878");
		endereco1.setLogradouro("teste");
		endereco1.setBairro("teste");
		endereco1.setCidade("teste");
		endereco1.setNumero("teste");

		Endereco endereco2 = new Endereco();
		endereco2.setCep("8787878");
		endereco2.setLogradouro("teste");
		endereco2.setBairro("teste");
		endereco2.setCidade("teste");
		endereco2.setNumero("teste");

		List<Endereco> enderecos = new ArrayList<Endereco>();

		enderecos.add(endereco1);
		enderecos.add(endereco2);

		manager.getTransaction().begin();

		enderecos.forEach((p) -> {
			manager.merge(p);
		});

		manager.getTransaction().commit();

	}

	public void deletar() {

		manager.getTransaction().begin();
		String sql = "delete from Endereco p where p.id between 37 and 39";
		Query query = manager.createQuery(sql);
		query.executeUpdate();

		manager.getTransaction().commit();

		Endereco endereco = manager.find(Endereco.class, 37L);

		assertFalse(endereco == null);

	}

	public void atualiza() {

		manager.getTransaction().begin();
		String sql = "update Endereco e set e.complemento =:complemento where e.id in (41,42) ";

		Query query = manager.createQuery(sql);

		query.setParameter("complemento", "99999");

		query.executeUpdate();

		manager.getTransaction().commit();

	}

	@Test
	public void inserirEmLote() throws IOException {
		InputStream in = OperacoesEmLoteTest.class.getClassLoader().getResourceAsStream("produtos/importar.txt");

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
			manager.getTransaction().begin();

			int contadorInsercoes = 0;

			for (String linha : reader.lines().collect(Collectors.toList())) {
				if (linha.isBlank()) {
					continue;
				}

				String[] produtoColuna = linha.split(";");
				Produto produto = new Produto();
				produto.setNome(produtoColuna[0]);
				produto.setDescricao(produtoColuna[1]);
				produto.setPreco(new BigDecimal(produtoColuna[2]));
				produto.setDataCriacao(new Date());

				manager.persist(produto);

				if (++contadorInsercoes == LIMITE_INSERCOES) {
					manager.flush();
					manager.clear();

					contadorInsercoes = 0;

					System.out.println("---------------------------------");
				}
			}
		}

		manager.getTransaction().commit();
	}

}
