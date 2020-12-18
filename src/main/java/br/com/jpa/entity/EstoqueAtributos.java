package br.com.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EstoqueAtributos {

	@Column(name = "EST_DESCRICAO")
	private String descricao;

	@Column(name = "EST_PRATELEIRA")
	private String prateleira;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPrateleira() {
		return prateleira;
	}

	public void setPrateleira(String prateleira) {
		this.prateleira = prateleira;
	}


	@Override
	public String toString() {
		return "EstoqueAtributos{" +
				"descricao='" + descricao + '\'' +
				", prateleira='" + prateleira + '\'' +
				'}';
	}
}
