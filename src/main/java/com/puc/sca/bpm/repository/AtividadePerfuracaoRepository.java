package com.puc.sca.bpm.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.puc.sca.bpm.entity.AtividadePerfuracao;

@RepositoryRestResource(path = "atividades-perfuracao", collectionResourceRel = "atividades-perfuracao", exported = false)
public interface AtividadePerfuracaoRepository extends PagingAndSortingRepository<AtividadePerfuracao, Long> {

}
