package br.com.jpa.entity;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.jpa.types.TipoStatusPedido;

@Entity
@Table(name = "TB_PEDIDO")
@NamedQuery(name = Pedido.recuperaPedidos, query = "select p from Pedido p ")
@NamedQuery(name = Pedido.recuperaMaiorPedidoCadastrado, query = "select max(p.id) from Pedido p ")
@NamedQuery(name = Pedido.recuperaPedidoPorStatus, query = "select p from Pedido p where p.status = :status ")
public class Pedido extends EntityBase {

	public static final String recuperaPedidos = "recuperaTodosPedidos";
	public static final String recuperaMaiorPedidoCadastrado = "recuperaPedidoCadastrado";
	public static final String recuperaPedidoPorStatus = "recuperaPedidoPorStatus"; 
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PED_ID")
	private Long id;

	@ManyToOne(targetEntity = Cliente.class, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "PED_CLIID")
	private Cliente cliente;

	@Temporal(TemporalType.DATE)
	@Column(name = "PED_DATA_CRIACAO")
	private Date dataCriacao = Calendar.getInstance().getTime();

	@Temporal(TemporalType.DATE)
	@Column(name = "PED_DATA_CONCLUSAO")
	private Date dataConclusao = Calendar.getInstance().getTime();

	@Column(name = "PED_TOTAL", nullable = false)
	private BigDecimal total = BigDecimal.ZERO;

	@Enumerated(EnumType.STRING)
	@Column(name = "PED_STATUS", length = 10)
	private TipoStatusPedido status = TipoStatusPedido.AGUARDANDO;

	@OneToOne(targetEntity = Pagamento.class, fetch = FetchType.LAZY, mappedBy = "pedido")
	@JoinColumn(name = "PED_PAGID")
	private Pagamento pagamento;

	@OneToOne(targetEntity = NotaFiscal.class, fetch = FetchType.LAZY, mappedBy = "pedido")
	@JoinColumn(name = "PED_NOTA_FISCAL")
	private NotaFiscal notaFiscal;

	@OneToMany(targetEntity = ItemPedido.class, fetch = FetchType.LAZY, mappedBy = "pedido")
	private List<ItemPedido> itens;

	public Pedido() {
	}

	public Pedido(Long id, Cliente cliente, Date dataCriacao, Date dataConclusao, BigDecimal total,
			TipoStatusPedido status, Pagamento pagamento, NotaFiscal notaFiscal, List<ItemPedido> itens) {
		this.id = id;
		this.cliente = cliente;
		this.dataCriacao = dataCriacao;
		this.dataConclusao = dataConclusao;
		this.total = total;
		this.status = status;
		this.pagamento = pagamento;
		this.notaFiscal = notaFiscal;
		this.itens = itens;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public TipoStatusPedido getStatus() {
		return status;
	}

	public void setStatus(TipoStatusPedido status) {
		this.status = status;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

}
