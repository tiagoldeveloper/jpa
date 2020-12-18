package br.com.jpa.entity;

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
@Table(name = "TB_ENDERECO")
public class Endereco extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "END_ID")
	private Long id;

	@Column(name = "END_CEP", length = 9)
	private String cep;

	@Column(name = "END_LOGRADOURO", length = 100)
	private String logradouro;

	@Column(name = "END_NUMERO", length = 10)
	private String numero;

	@Column(name = "END_COMPLEMENTO", length = 50)
	private String complemento;

	@Column(name = "END_BAIRRO", length = 50)
	private String bairro;

	@Column(name = "END_cidade", length = 50)
	private String cidade;

	@Column(name = "END_ESTADO", length = 2)
	private String estado;

	@ManyToOne(targetEntity = Cliente.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "END_CLIID")
	private Cliente cliente;

	public Endereco() {
	}

	public Endereco(Long id, String cep, String logradouro, String numero, String complemento, String bairro,
			String cidade, String estado, Cliente cliente) {
		super();
		this.id = id;
		this.cep = cep;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", cep=" + cep + ", logradouro=" + logradouro + ", numero=" + numero
				+ ", complemento=" + complemento + ", bairro=" + bairro + ", cidade=" + cidade + ", estado=" + estado
				+ ", cliente=" + cliente + "]";
	}

}
