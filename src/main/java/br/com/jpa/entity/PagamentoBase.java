package br.com.jpa.entity;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import br.com.jpa.types.TipoStatusPagamento;

@MappedSuperclass
public abstract class PagamentoBase extends EntityBase {

	@OneToOne(optional = false)
	@JoinColumn(name = "PAG_PEDID")
	private Pedido pedido;

	@Column(name = "PAG_STATUS", length = 10, nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoStatusPagamento status = TipoStatusPagamento.PROCESSANDO;

	public PagamentoBase() {
	}

	public PagamentoBase(Pedido pedido, TipoStatusPagamento status) {
		this.pedido = pedido;
		this.status = status;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public TipoStatusPagamento getStatus() {
		return status;
	}

	public void setStatus(TipoStatusPagamento status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "PagamentoBase{" +
				"pedido=" + pedido +
				", status=" + status +
				'}';
	}
}
