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
import com.puc.sca.integration.util.Constants;

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
		
		atividadePerfuracao.setGestorId(Long.parseLong(idUsuarioLogado));
		AtividadePerfuracao atv = this.atividadePerfuracaoRepository.save(atividadePerfuracao);
		
		Map<String, Object> variables = new HashMap<String, Object>();
		
		variables.put("id", atividadePerfuracao.getId());
		variables.put("gestorId", idUsuarioLogado);
		variables.put("usuarioMineradoraId", atividadePerfuracao.getUsuarioMineradoraId());
		variables.put("dataAtividade", atividadePerfuracao.getDataInicioAtividade());
		variables.put("dataPrevisaoTerminoAtividade", atividadePerfuracao.getDataPrevisaoTerminoAtividade());
		variables.put("observacoes", atividadePerfuracao.getObservacoes());
		
	 
		this.runtimeService.startProcessInstanceByKey("atividadePerfuracao", variables);
		
		return atv;
		
	}
	
	@GetMapping("{id}")
	public AtividadePerfuracao findById(@PathVariable(value = "id") Long id) {
		return this.atividadePerfuracaoRepository.findById(id).get();
	}
	
	@GetMapping("tarefas-por-operador/{usuarioMineiradoraId}")
	public List<String> getTarefasPorOperadorMineiradora(@PathVariable("usuarioMineiradoraId") String operadorMineiradoraId) {
		return this.taskService
                .createTaskQuery()
                .taskAssignee(operadorMineiradoraId)
                .list().stream().map(task -> task.getName()).collect(Collectors.toList());
	}
	
	@PutMapping("{operadorMineiradoraId}")
	public void atualizaAtividadePerfuracao(@PathVariable("operadorMineiradoraId") String operadorMineiradoraId, @RequestBody Map<String, Object> data) {

		Task task = this.taskService.createTaskQuery().taskAssignee(operadorMineiradoraId).singleResult();
		
		Boolean complete = (Boolean) data.get("complete");
		
		if (complete) {
			String actionType = (String) data.get("actionType");
			HashMap<String, Object> variables = new HashMap<String, Object>();
			variables.put("actionType", actionType);
			this.taskService.complete(task.getId(), variables);
		}
	}
}
