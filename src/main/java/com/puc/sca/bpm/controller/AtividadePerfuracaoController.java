package com.puc.sca.bpm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puc.sca.bpm.entity.AtividadePerfuracao;
import com.puc.sca.bpm.repository.AtividadePerfuracaoRepository;

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
	public String startaProcessoAtividadePerfuracao(@RequestBody AtividadePerfuracao atividadePerfuracao, HttpServletRequest request) {

		this.atividadePerfuracaoRepository.save(atividadePerfuracao);
		
		Map<String, Object> variables = new HashMap<String, Object>();
		
		variables.put("id", atividadePerfuracao.getId());
	 
		variables.put("gestorId", request.getParameter("idUsuarioLogado"));
		variables.put("operadorMineiradoraId", atividadePerfuracao.getOperadorMineiradoraId());
		variables.put("dataAtividade", atividadePerfuracao.getDataAtividade());
		variables.put("dataPrevisaoTerminoAtividade", atividadePerfuracao.getDataPrevisaoTerminoAtividade());
		variables.put("observacoes", atividadePerfuracao.getObservacoes());
		
	 
		this.runtimeService.startProcessInstanceByKey("atividadePerfuracao", variables);
		
		return "OK";
	}
	
	@GetMapping("{operadorMineiradoraId}")
	public List<String> getTarefasPorOperadorMineiradora(@PathVariable("operadorMineiradoraId") String operadorMineiradoraId) {
		
	
		return this.taskService
                .createTaskQuery()
                .taskAssignee(operadorMineiradoraId)
                .list().stream().map(task -> task.getName()).collect(Collectors.toList());
		
		 
	}
	
	@PutMapping("{operadorMineiradoraId}")
	public String atualizaAtividadePerfuracao(@PathVariable("operadorMineiradoraId") String operadorMineiradoraId, @RequestBody Map<String, Object> data) {

		Task task = this.taskService.createTaskQuery().taskAssignee(operadorMineiradoraId).singleResult();
		
		Boolean complete = (Boolean) data.get("complete");
		
		if (complete) {
			String actionType = (String) data.get("actionType");
			HashMap<String, Object> variables = new HashMap<String, Object>();
			variables.put("actionType", actionType);
			this.taskService.complete(task.getId(), variables);
		}
		
		return "OK";
	}
}
