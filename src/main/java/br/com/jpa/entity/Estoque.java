package br.com.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_ESTOQUE")
public class Estoque {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EST_ID")
	private Long id;

	@OneToOne(targetEntity = Produto.class, fetch = FetchType.LAZY,  mappedBy = "estoque")
	@JoinColumn(name = "EST_PROID")
	private Produto produto;
	
	@Column(name = "EST_QUANTIDADE")
	private Integer quantidade;
	
	@Embedded
	private EstoqueAtributos atributos;
	

	public Estoque() {
	}

	public Estoque(Long id, Produto produto, Integer quantidade, EstoqueAtributos atributos) {
		this.id = id;
		this.produto = produto;
		this.quantidade = quantidade;
		this.setAtributos(atributos);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public EstoqueAtributos getAtributos() {
		return atributos;
	}

	public void setAtributos(EstoqueAtributos atributos) {
		this.atributos = atributos;
	}


	@Override
	public String toString() {
		return "Estoque{" +
				"id=" + id +
				", produto=" + produto +
				", quantidade=" + quantidade +
				", atributos=" + atributos +
				'}';
	}
}
