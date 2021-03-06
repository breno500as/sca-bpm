package com.puc.sca.bpm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.puc.sca.bpm.entity.AtividadePerfuracao;

@RepositoryRestResource(exported = false)
public interface AtividadePerfuracaoRepository extends PagingAndSortingRepository<AtividadePerfuracao, Long> {
	
	public Page<AtividadePerfuracao> findAllByUsuarioMineradoraId(Long usuarioMineradoraId, Pageable pageable);

}
