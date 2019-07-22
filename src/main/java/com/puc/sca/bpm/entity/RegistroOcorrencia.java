package com.puc.sca.bpm.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.puc.sca.bpm.enums.Turno;

@Entity
public class RegistroOcorrencia {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String descricao;

	private Date dataOcorrencia;

	@ManyToOne(fetch = FetchType.LAZY)
	private AtividadePerfuracao atividadePerfuracao;

	@Enumerated(EnumType.STRING)
	private Turno turno;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataOcorrencia() {
		return dataOcorrencia;
	}

	public void setDataOcorrencia(Date dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public AtividadePerfuracao getAtividadePerfuracao() {
		return atividadePerfuracao;
	}

	public void setAtividadePerfuracao(AtividadePerfuracao atividadePerfuracao) {
		this.atividadePerfuracao = atividadePerfuracao;
	}

}
