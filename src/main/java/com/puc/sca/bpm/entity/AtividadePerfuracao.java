package com.puc.sca.bpm.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

 

@Entity
public class AtividadePerfuracao {
	
	
	public static final String ATIVIDADE_NAO_ENCONTRADA = "Atividade de perfuração não encontrada!";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private Long usuarioMineradoraId;

	private Long gestorId;

	private LocalDateTime dataInicioAtividade = LocalDateTime.now();

	private LocalDateTime dataTerminoAtividade = LocalDateTime.now();

	private String orientacoes;

	@OneToMany(mappedBy = "atividadePerfuracao")
	private List<RegistroOcorrencia> ocorrencias;

	@Transient
	private Long totalElementos;
	
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

	public LocalDateTime getDataInicioAtividade() {
		return dataInicioAtividade;
	}

	public void setDataInicioAtividade(LocalDateTime dataInicioAtividade) {
		this.dataInicioAtividade = dataInicioAtividade;
	}

	public Long getGestorId() {
		return gestorId;
	}

	public void setGestorId(Long gestorId) {
		this.gestorId = gestorId;
	}

	public LocalDateTime getDataTerminoAtividade() {
		return dataTerminoAtividade;
	}
	
	public void setDataTerminoAtividade(LocalDateTime dataTerminoAtividade) {
		this.dataTerminoAtividade = dataTerminoAtividade;
	}

	public String getOrientacoes() {
		return orientacoes;
	}
	
	public void setOrientacoes(String orientacoes) {
		this.orientacoes = orientacoes;
	}

	public List<RegistroOcorrencia> getOcorrencias() {
		return ocorrencias;
	}

	public void setOcorrencias(List<RegistroOcorrencia> ocorrencias) {
		this.ocorrencias = ocorrencias;
	}
	
	public Long getTotalElementos() {
		return totalElementos;
	}
	
	public void setTotalElementos(Long totalElementos) {
		this.totalElementos = totalElementos;
	}

}
