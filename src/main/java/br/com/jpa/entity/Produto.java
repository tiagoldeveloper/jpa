package br.com.jpa.entity;

import java.math.BigDecimal;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "TB_PRODUTO")
public class Produto extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PRO_ID")
	private Long id;

	@Column(name = "PRO_NOME")
	private String nome;

	@Column(name = "PRO_DESCRICAO")
	private String descricao;

	@Column(name = "PRO_PRECO")
	private BigDecimal preco;

	@Lob
	@Type(type = "org.hibernate.type.BinaryType")
	@Column(name = "PRO_FOTO")
	private byte[] foto;

	@Column(name = "PRO_TAGS")
	private String tags;

	@Temporal(TemporalType.DATE)
	@Column(name = "PRO_DATA_CRIACAO")
	private Date dataCriacao = Calendar.getInstance().getTime();

	@OneToOne(targetEntity = Estoque.class, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "PRO_ESTID")
	private Estoque estoque;

	@ManyToOne(targetEntity = Categoria.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "PRO_CATID")
	private Categoria categoria;

	public Produto() {
	}

	public Produto(Long id, String nome, String descricao, BigDecimal preco, byte[] foto, String tags, Date dataCriacao,
			Estoque estoque, Categoria categoria) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.foto = foto;
		this.tags = tags;
		this.dataCriacao = dataCriacao;
		this.estoque = estoque;
		this.categoria = categoria;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Produto{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", descricao='" + descricao + '\'' +
				", preco=" + preco +
				", foto=" + Arrays.toString(foto) +
				", tags='" + tags + '\'' +
				", dataCriacao=" + dataCriacao +
				", estoque=" + estoque +
				", categoria=" + categoria +
				'}';
	}
}
