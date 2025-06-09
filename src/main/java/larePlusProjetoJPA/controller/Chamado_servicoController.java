package larePlusProjetoJPA.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import larePlusProjetoJPA.entity.Chamado_servico;
import larePlusProjetoJPA.repository.Chamado_servicoRepository;

@RestController
public class Chamado_servicoController {
	@Autowired
	private Chamado_servicoRepository chamado_servicoRepository;
	
	@PostMapping("/chamado_servico")
	public ResponseEntity<?> saveChamado_servico(@RequestBody Chamado_servico chamado_servico) {
		Chamado_servico salvo = chamado_servicoRepository.save(chamado_servico);
		Map<String, Object> response = new HashMap<>();
		response.put("mensagem", "Chamado realizado com sucesso!");
		response.put("id_chamado", salvo.getId_chamado());
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping("/chamado_servico")
	public List<Chamado_servico> getChamado_servicos() {
		return chamado_servicoRepository.findAll();
	}
	
	@GetMapping("/chamado_servico/{id}")
	public Chamado_servico getChamado_servicoById(@PathVariable Long id) {
		return chamado_servicoRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Não foi possível localizar o chamado!"));
	}
	
	@PutMapping("/chamado_servico/{id}")
	public String updateChamado_servico(@PathVariable Long id, @RequestBody Chamado_servico novoChamado_servico) {
		Chamado_servico chamado_servicoSalvo = chamado_servicoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Não foi possível localizar o chamado!"));
		
		chamado_servicoSalvo.setDescricao_chamado(novoChamado_servico.getDescricao_chamado());
		chamado_servicoSalvo.setStatus(novoChamado_servico.getStatus());
		chamado_servicoSalvo.setAtendente(novoChamado_servico.getAtendente());
		
		chamado_servicoRepository.save(chamado_servicoSalvo);
		return "O chamado foi atualizado!";
	}
	
	@DeleteMapping("/chamado_servico/{id}")
	public String deleteChamado_servico(@PathVariable Long id) {
		chamado_servicoRepository.deleteById(id);
		return "Chamado deletado!";
	}

}
