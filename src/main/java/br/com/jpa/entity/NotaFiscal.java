package br.com.jpa.entity;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "TB_NOTA_FISCAL")
public class NotaFiscal extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "NFI_ID")
	private Long id;

	@OneToOne(targetEntity = Pedido.class, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "NFI_PEDID")
	private Pedido pedido;

	@Lob
	@Type(type = "org.hibernate.type.BinaryType")
	@Column(name = "NFI_XML", nullable = false)
	private byte[] xml;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "NFI_DATA_EMISSAO", nullable = false)
	private Date dataEmissao = Calendar.getInstance().getTime();

	public NotaFiscal() {
	}

	public NotaFiscal(Long id, Pedido pedido, byte[] xml, Date dataEmissao) {
		this.id = id;
		this.pedido = pedido;
		this.xml = xml;
		this.dataEmissao = dataEmissao;
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

	public byte[] getXml() {
		return xml;
	}

	public void setXml(byte[] xml) {
		this.xml = xml;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}


	@Override
	public String toString() {
		return "NotaFiscal{" +
				"id=" + id +
				", pedido=" + pedido +
				", xml=" + Arrays.toString(xml) +
				", dataEmissao=" + dataEmissao +
				'}';
	}
}
