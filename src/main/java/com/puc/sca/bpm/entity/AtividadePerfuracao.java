package com.puc.sca.bpm.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class AtividadePerfuracao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private Long usuarioMineradoraId;

	private Long gestorId;

	private Date dataInicioAtividade = new Date();

	private Date dataPrevisaoTerminoAtividade = new Date();

	private String observacoes;

	@OneToMany(mappedBy = "atividadePerfuracao")
	private List<RegistroOcorrencia> ocorrencias;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUsuarioMineradoraId() {
		return usuarioMineradoraId;
	}

	public void setUsuarioMineradoraId(Long usuarioMineradoraId) {
		this.usuarioMineradoraId = usuarioMineradoraId;
	}

	public Date getDataInicioAtividade() {
		return dataInicioAtividade;
	}

	public void setDataInicioAtividade(Date dataInicioAtividade) {
		this.dataInicioAtividade = dataInicioAtividade;
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

	public List<RegistroOcorrencia> getOcorrencias() {
		return ocorrencias;
	}

	public void setOcorrencias(List<RegistroOcorrencia> ocorrencias) {
		this.ocorrencias = ocorrencias;
	}

}
