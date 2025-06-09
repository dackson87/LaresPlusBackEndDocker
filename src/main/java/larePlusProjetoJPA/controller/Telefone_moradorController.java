package larePlusProjetoJPA.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import larePlusProjetoJPA.entity.Telefone_morador;
import larePlusProjetoJPA.repository.Telefone_moradorRepository;

@RestController
public class Telefone_moradorController {
	@Autowired
	private Telefone_moradorRepository telefone_moradorRepository;
		
	@PostMapping("/telefone_morador")
	public String saveTelefone_morador(@RequestBody Telefone_morador telefone_morador) {
		telefone_moradorRepository.save(telefone_morador);
		return "Telefone adicionado ao morador!";
	}
	
	@GetMapping("/telefone_morador")
	public List<Telefone_morador> getTelefone_moradores() {
		return telefone_moradorRepository.findAll();
	}
	
	@GetMapping("/telefone_morador/{id}")
	public Telefone_morador getTelefone_moradorById(@PathVariable Long id) {
		return telefone_moradorRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Não foi possível encontrar esse número de contato!"));
	}
	
	@PutMapping("/telefone_morador/{id}")
	public String updateTelefone_morador(@PathVariable Long id, @RequestBody Telefone_morador novoTelefone_morador) {
		Telefone_morador telefone_moradorSalvo = telefone_moradorRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Não foi possível encontrar esse número de contato!"));
			
		telefone_moradorSalvo.setTelefone(novoTelefone_morador.getTelefone());
		
		
		telefone_moradorRepository.save(telefone_moradorSalvo);
		return "O telefone foi atualizado!";
	}
		
	@DeleteMapping("/telefone_morador/{id}")
	public String deleteTelefone_morador(@PathVariable Long id) {
		telefone_moradorRepository.deleteById(id);
		return "Telefone foi excluído!";
	}

}
