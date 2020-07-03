package com.puc.sca.bpm.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.puc.sca.bpm.dto.TaskDTO;
import com.puc.sca.bpm.entity.AtividadePerfuracao;
import com.puc.sca.bpm.repository.AtividadePerfuracaoRepository;
import com.puc.sca.util.pojo.Constants;


/**
 * Rest controller para bpm de atividades de perfuração.
 * @author breno
 *
 */

@RestController
@RequestMapping("atividades-perfuracao")
public class AtividadePerfuracaoController {

	@Autowired
    private RuntimeService runtimeService;
	
	@Autowired
	private AtividadePerfuracaoRepository atividadePerfuracaoRepository;
	
	@Autowired
	private TaskService taskService;
	
	
	@PostMapping
	public AtividadePerfuracao startaProcessoAtividadePerfuracao(@RequestBody AtividadePerfuracao atividadePerfuracao, HttpServletRequest request) {

		String idUsuarioLogado = request.getParameter(Constants.ID_USUARIO_LOGADO);
		
		List<TaskDTO>  tasks =  this.getTarefasPorOperadorMineiradora(atividadePerfuracao.getUsuarioMineradoraId().toString());
		
		if (tasks != null && !tasks.isEmpty()) {
			throw new RuntimeException("Atenção, já existe uma atividade cadastrada para esse operador, conclua a mesma antes iniciar uma nova.");
		}
		
		atividadePerfuracao.setGestorId(Long.parseLong(idUsuarioLogado));
		AtividadePerfuracao atv = this.atividadePerfuracaoRepository.save(atividadePerfuracao);
		
		Map<String, Object> variables = new HashMap<String, Object>();
		
		variables.put("id", atividadePerfuracao.getId());
		variables.put("gestorId", idUsuarioLogado);
		variables.put("usuarioMineradoraId", atividadePerfuracao.getUsuarioMineradoraId());
		variables.put("dataInicioAtividade", atividadePerfuracao.getDataInicioAtividade());
		variables.put("dataTerminoAtividade", atividadePerfuracao.getDataTerminoAtividade());
		variables.put("orientacoes", atividadePerfuracao.getOrientacoes());
		
	 
		this.runtimeService.startProcessInstanceByKey("atividadePerfuracao", variables);
		
		return atv;
		
	}
	
	@GetMapping
	public Iterable<AtividadePerfuracao> findAll(@RequestParam("page") Integer page, 
			                        @RequestParam("size") Integer size,
			                        @RequestParam(value = "usuarioMineradoraId", required = false) Long usuarioMineradoraId,
			                        @RequestParam(name = "sort", defaultValue = "id") String sort) {
		Pageable pageable = PageRequest.of(page -1, size, Sort.by(sort));
		
		Page<AtividadePerfuracao> result =  null;
		
		if (usuarioMineradoraId != null) {
			result = this.atividadePerfuracaoRepository.findAllByUsuarioMineradoraId(usuarioMineradoraId, pageable);
		} else {
			result =  this.atividadePerfuracaoRepository.findAll(pageable);
		}
		
		if (result.get() != null) {
			List<AtividadePerfuracao> atividadesPerfuracao = result.get().collect(Collectors.toList());
			if (atividadesPerfuracao.size() > 0) {
				atividadesPerfuracao.get(0).setTotalElementos(result.getTotalElements());
			}
			
			return atividadesPerfuracao;
		}
		
		return Collections.emptyList();
		
	}
	
	@GetMapping("{id}")
	public AtividadePerfuracao findById(@PathVariable(value = "id") Long id) {
		 Optional<AtividadePerfuracao> oAtv = this.atividadePerfuracaoRepository.findById(id);
		return  oAtv.isPresent() ? oAtv.get() : null;
	}
	
	@GetMapping("/tarefas-por-operador/{usuarioMineradoraId}")
	public List<TaskDTO> getTarefasPorOperadorMineiradora(@PathVariable("usuarioMineradoraId") String operadorMineiradoraId) {
		return this.taskService
                .createTaskQuery()
                .taskAssignee(operadorMineiradoraId)
                .list().stream().map(t -> new TaskDTO(t.getId(),t.getTaskDefinitionKey(), t.getName())).collect(Collectors.toList());
	}
	
	@PutMapping("{taskId}")
	public void concluiAtividadePerfuracao(@PathVariable("taskId") String taskId, @RequestBody TaskDTO taskDTO) {

		HashMap<String, Object> variables = new HashMap<String, Object>();
		if (taskDTO.getActionType() != null) {
			variables.put("actionType", taskDTO.getActionType());
		}
		
		this.taskService.complete(taskId, variables);

	}
}
