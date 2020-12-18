package br.com.jpa.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@MappedSuperclass
public class EntityBase {

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_ULT_ALTERACAO")
	private Date dataUtilAtualizacao = Calendar.getInstance().getTime();

	@Column(name = "ATIVO")
	private Integer ativo = Integer.valueOf(1);

	@Version
	@Column(name = "VERSAO")
	private Integer versao;

	public Date getDataUtilAtualizacao() {
		return dataUtilAtualizacao;
	}

	public void setDataUtilAtualizacao(Date dataUtilAtualizacao) {
		this.dataUtilAtualizacao = dataUtilAtualizacao;
	}

	public Integer getAtivo() {
		return ativo;
	}

	public void setAtivo(Integer ativo) {
		this.ativo = ativo;
	}

	public Integer getVersao() {
		return versao;
	}

	public void setVersao(Integer versao) {
		this.versao = versao + 1;
	}

}
