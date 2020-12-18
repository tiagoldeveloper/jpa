package br.com.jpa.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_ITEM_PEDIDO")
public class ItemPedido extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IPE_ID")
	private Long id;

	@ManyToOne(targetEntity = Pedido.class, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "IPE_PEDID", nullable = false)
	private Pedido pedido;

	@ManyToOne(targetEntity = Produto.class, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "IPE_PROID", nullable = false)
	private Produto produto;

	@Column(name = "IPE_PRECO", nullable = false)
	private BigDecimal precoProduto;

	@Column(name = "IPE_QUANTIDADE", nullable = false)
	private Integer quantidade;

	public ItemPedido() {
	}

	public ItemPedido(Long id, Pedido pedido, Produto produto, BigDecimal precoProduto, Integer quantidade) {
		this.id = id;
		this.pedido = pedido;
		this.produto = produto;
		this.precoProduto = precoProduto;
		this.quantidade = quantidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public BigDecimal getPrecoProduto() {
		return precoProduto;
	}

	public void setPrecoProduto(BigDecimal precoProduto) {
		this.precoProduto = precoProduto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public BigDecimal valorTotal() {
		return this.precoProduto.multiply(new BigDecimal(this.quantidade.intValue()));
	}


	@Override
	public String toString() {
		return "ItemPedido{" +
				"id=" + id +
				", pedido=" + pedido +
				", produto=" + produto +
				", precoProduto=" + precoProduto +
				", quantidade=" + quantidade +
				'}';
	}
}
