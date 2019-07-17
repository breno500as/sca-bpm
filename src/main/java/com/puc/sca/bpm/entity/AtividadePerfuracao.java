package com.puc.sca.bpm.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class AtividadePerfuracao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private Long operadorMineiradoraId;

	@NotNull
	private Long gestorId;

	private Date dataAtividade = new Date();

	private Date dataPrevisaoTerminoAtividade = new Date();

	private String observacoes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOperadorMineiradoraId() {
		return operadorMineiradoraId;
	}

	public void setOperadorMineiradoraId(Long operadorMineiradoraId) {
		this.operadorMineiradoraId = operadorMineiradoraId;
	}

	public Date getDataAtividade() {
		return dataAtividade;
	}

	public void setDataAtividade(Date dataAtividade) {
		this.dataAtividade = dataAtividade;
	}

	public Long getGestorId() {
		return gestorId;
	}

	public void setGestorId(Long gestorId) {
		this.gestorId = gestorId;
	}

	public Date getDataPrevisaoTerminoAtividade() {
		return dataPrevisaoTerminoAtividade;
	}

	public void setDataPrevisaoTerminoAtividade(Date dataPrevisaoTerminoAtividade) {
		this.dataPrevisaoTerminoAtividade = dataPrevisaoTerminoAtividade;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

}
