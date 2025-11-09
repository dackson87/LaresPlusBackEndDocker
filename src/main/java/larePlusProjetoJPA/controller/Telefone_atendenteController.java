package larePlusProjetoJPA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import larePlusProjetoJPA.entity.Telefone_atendente;
import larePlusProjetoJPA.repository.Telefone_atendenteRepository;

@CrossOrigin(origins = "*")
@RestController
public class Telefone_atendenteController {
	@Autowired
	private Telefone_atendenteRepository telefone_atendenteRepository;
	
	@PostMapping("telefone_atendente")
	public String savetTelefone_atendente(@RequestBody Telefone_atendente telefone_atendente) {
		telefone_atendenteRepository.save(telefone_atendente);
		return "Telefone incluído com sucesso!";
	}
	
	@GetMapping("/telefone_atendente")
	public java.util.List<Telefone_atendente> getTelefone_atendentes() {
		return telefone_atendenteRepository.findAll();
	}
	
	@GetMapping("/telefone_atendente/{id}")
	public Telefone_atendente getTelefone_atendenteById(@PathVariable Long id) {
		return telefone_atendenteRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Não foi possível localizar o número telefonico!"));
	}
	
	@PutMapping("/telefone_atendente/{id}")
	public String updateTelefone_atendente(@PathVariable Long id, @RequestBody Telefone_atendente novoTelefone_atendente) {
		Telefone_atendente telefone_atendenteSalvo = telefone_atendenteRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Não foi possível localizar a número telefonico!"));
		
		telefone_atendenteSalvo.setTelefone(novoTelefone_atendente.getTelefone());
		
		
		telefone_atendenteRepository.save(telefone_atendenteSalvo);
		return "O número foi atualizado!";
	}
	
	@DeleteMapping("/telefone_atendente/{id}")
	public String deleteTelefone_atendente(@PathVariable Long id) {
		telefone_atendenteRepository.deleteById(id);
		return "Telefone excluído!";
	}

}

