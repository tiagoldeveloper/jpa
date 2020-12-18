package br.com.jpa.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.jpa.types.TipoPagamento;

@Entity
@Table(name = "TB_PAGAMENTO")
public class Pagamento extends PagamentoBase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PAG_ID")
	private Long id;

	@Column(name = "PAG_COD_BARRAS", length = 255)
	private String codigoBarras;

	@Temporal(TemporalType.DATE)
	@Column(name = "PAG_DATA_VENCIMENTO")
	private Date dataVencimento = Calendar.getInstance().getTime();

	@Column(name = "PAG_NUMERO_CARTAO", length = 50)
	private String numeroCartao;

	@Column(name = "PAG_NUMERO_CONTA", length = 50)
	private String numeroConta;
	
	@Column(name = "PAG_TIPO_PAGAMENTO", length = 10)
	@Enumerated(EnumType.STRING)
	private TipoPagamento tipoPagamento = TipoPagamento.DINHEIRO;
	
	public Pagamento() {}

	public Pagamento(Long id, String codigoBarras, Date dataVencimento, String numeroCartao,
			TipoPagamento tipoPagamento, String numeroConta) {
		this.id = id;
		this.codigoBarras = codigoBarras;
		this.dataVencimento = dataVencimento;
		this.numeroCartao = numeroCartao;
		this.tipoPagamento = tipoPagamento;
		this.numeroConta = numeroConta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	@Override
	public String toString() {
		return "Pagamento{" +
				"id=" + id +
				", codigoBarras='" + codigoBarras + '\'' +
				", dataVencimento=" + dataVencimento +
				", numeroCartao='" + numeroCartao + '\'' +
				", numeroConta='" + numeroConta + '\'' +
				", tipoPagamento=" + tipoPagamento +
				'}';
	}
}
