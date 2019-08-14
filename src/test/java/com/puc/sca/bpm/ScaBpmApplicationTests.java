package com.puc.sca.bpm;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.puc.sca.bpm.entity.AtividadePerfuracao;
import com.puc.sca.bpm.repository.AtividadePerfuracaoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScaBpmApplicationTests {

	@Autowired
	private AtividadePerfuracaoRepository atividadePerfuracaoRepository;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;
	
	private Map<String, Object> variables;
	
	private static final Long USUARIO_MINEIRADORA_ID = 7L;

	@Test
	public void testBpm() {

		// Mock
		AtividadePerfuracao atividadePerfuracao = new AtividadePerfuracao();
		atividadePerfuracao.setGestorId(1L);
		atividadePerfuracao.setUsuarioMineradoraId(USUARIO_MINEIRADORA_ID);
		atividadePerfuracao.setObservacoes("Na área de extração devem ser realizados instalações de cabos elétricos");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, 10);
		atividadePerfuracao.setDataPrevisaoTerminoAtividade(c.getTime());
		this.atividadePerfuracaoRepository.save(atividadePerfuracao);

		// Cria o processo
		this.variables = new HashMap<String, Object>();
		this.variables.put("id", atividadePerfuracao.getId());
		this.variables.put("operadorMineiradoraId", atividadePerfuracao.getUsuarioMineradoraId());
		this.variables.put("gestorId", atividadePerfuracao.getGestorId());
		this.variables.put("dataAtividade", atividadePerfuracao.getDataInicioAtividade());
		this.variables.put("dataPrevisaoTerminoAtividade", atividadePerfuracao.getDataPrevisaoTerminoAtividade());
		this.variables.put("observacoes", atividadePerfuracao.getObservacoes());
		
		this.runtimeService.startProcessInstanceByKey("atividadePerfuracao", this.variables);

		
		
		// Tarefa 1 - organizaLocalExtracao
		Task taskOrganizarLocal = this.taskService
				                        .createTaskQuery()
				                        .taskAssignee(USUARIO_MINEIRADORA_ID.toString())
				                        .singleResult();

		Assert.assertEquals("Organizar local para extração do minério", taskOrganizarLocal.getName());

		this.taskService.complete(taskOrganizarLocal.getId());

		// Tarefa 2 - identificaFalhaNasRochas
		Task taskFalhasRochas = this.taskService
				                    .createTaskQuery()
				                    .taskAssignee(USUARIO_MINEIRADORA_ID.toString())
			   	                    .singleResult();

		Assert.assertEquals("Identificar falhas nas rochas para remoção", taskFalhasRochas.getName());

		this.variables = new HashMap<String, Object>();
		this.variables.put("actionType", "explosivos");
		
		this.taskService.complete(taskFalhasRochas.getId(),this.variables);
		
		
		// Tarefa 2 - taskDesmontarExplosivos
		Task taskDesmontarExplosivos = this.taskService
				                               .createTaskQuery()
				                               .taskAssignee(USUARIO_MINEIRADORA_ID.toString())
				                               .singleResult();
		 
        Assert.assertEquals("Desmontar rochas com explosivos", taskDesmontarExplosivos.getName());
        

        this.variables = new HashMap<String, Object>();
		this.variables.put("actionType", "fimAtividadePerfuracao");
		
        this.taskService.complete(taskDesmontarExplosivos.getId(), this.variables);
        
    
	}

}
