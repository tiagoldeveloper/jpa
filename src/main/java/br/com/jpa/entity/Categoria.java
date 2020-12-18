package br.com.jpa.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_CATEGORIA")
public class Categoria extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CAT_ID")
	private Long id;

	@Column(name = "CAT_NOME", length = 100, nullable = false)
	private String nome;

	@OneToMany(targetEntity = Produto.class, fetch = FetchType.LAZY, mappedBy = "categoria")
	private List<Produto> produtos;

	public Categoria() {
	}

	public Categoria(Long id) {
		this.id = id;
	}

	public Categoria(Long id, String nome, List<Produto> produtos) {
		this.id = id;
		this.nome = nome;
		this.produtos = produtos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	@Override
	public String toString() {
		return "Categoria{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", produtos=" + produtos +
				'}';
	}
}
