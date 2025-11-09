package larePlusProjetoJPA.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import larePlusProjetoJPA.entity.Atendente;
import larePlusProjetoJPA.repository.AtendenteRepository;

@CrossOrigin(origins = "*")
@RestController
public class AtendenteController {
	@Autowired
	private AtendenteRepository atendenteRepository;
	
	@PostMapping("/atendente")
	public String saveAtendente(@RequestBody Atendente atendente) {
		atendenteRepository.save(atendente);
		return "Atendente inserido com sucesso!";
	}
	
	@PostMapping("/atendente/lista")
	public String saveAtendentes(@RequestBody List<Atendente> atendentes) {
		atendenteRepository.saveAll(atendentes);
		return "Atendentes inseridos com sucesso!";
	}
	
	@GetMapping("/atendente")
	public List<Atendente> getAtendente() {
		return atendenteRepository.findAll();
	}
	
	@GetMapping("/atendente/{id}")
	public Atendente getAtendenteById(@PathVariable Long id) {
		return atendenteRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Não foi possível encontrar o atendente!"));
	}
	
	@PutMapping("/atendente/{id}")
	public String updateAtendente(@PathVariable Long id, @RequestBody Atendente novoAtendente) {
		Atendente atendenteSalvo = atendenteRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Não foi possível encontrar o atendente!"));
		
		atendenteSalvo.setNome(novoAtendente.getNome());
		atendenteSalvo.setEmail(novoAtendente.getEmail());
		atendenteSalvo.setSenha(novoAtendente.getSenha());
		
		atendenteRepository.save(atendenteSalvo);
		return "O atendente foi atualizado no cadastro!";
	}
	
	@DeleteMapping("/atendente/{id}")
	public String deleteAtendente(@PathVariable Long id) {
		atendenteRepository.deleteById(id);
		return "Atendente apagado!";
	}

}

