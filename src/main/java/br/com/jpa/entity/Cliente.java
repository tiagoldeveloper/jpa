package br.com.jpa.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_CLIENTE")
public class Cliente extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CLI_ID")
	private Long id;

	@Column(name = "CLI_NOME", length = 100, nullable = false)
	private String nome;

	@Column(name = "CLI_CPF", length = 14, nullable = false)
	private String cpf;

	@OneToMany(targetEntity = Pedido.class, fetch = FetchType.LAZY, mappedBy = "cliente")
	private List<Pedido> pedidos;

	@OneToMany(targetEntity = Endereco.class, fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Endereco> enderecos;

	public Cliente() {
	}

	public Cliente(Long id, String nome, String cpf, List<Pedido> pedidos, List<Endereco> enderecos) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.pedidos = pedidos;
		this.enderecos = enderecos;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

}
