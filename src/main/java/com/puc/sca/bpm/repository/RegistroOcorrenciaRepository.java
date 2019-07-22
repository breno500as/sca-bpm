package com.puc.sca.bpm.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.puc.sca.bpm.entity.RegistroOcorrencia;

@RepositoryRestResource(path = "registros-ocorrencia", collectionResourceRel ="registros-ocorrencia")
public interface RegistroOcorrenciaRepository extends PagingAndSortingRepository<RegistroOcorrencia, Long> {

}
