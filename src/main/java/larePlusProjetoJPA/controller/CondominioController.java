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

import larePlusProjetoJPA.entity.Condominio;
import larePlusProjetoJPA.repository.CondominioRepository;

@CrossOrigin(origins = "*")
@RestController
public class CondominioController {
	@Autowired
	private CondominioRepository condominioRepository;
	
	@PostMapping("/condominio")
	public String saveCondominio(@RequestBody Condominio condominio) {
		condominioRepository.save(condominio);
		return "Condominio incluído com sucesso!";
	}
	
	@PostMapping("/condominio/lista")
	public String saveCondominios(@RequestBody List<Condominio> condominios) {
		condominioRepository.saveAll(condominios);
		return "Condominios incluídos com sucesso!";
	}
	
	@GetMapping("/condominio")
	public List<Condominio> getCondominios() {
		return condominioRepository.findAll();
	}
	
	@GetMapping("/condominio/{id}")
	public Condominio getCondominioById(@PathVariable Long id) {
		return condominioRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Não foi possível localizar o condomínio"));
	}
	
	@PutMapping("/condominio/{id}")
	public String updateCondominio(@PathVariable Long id, @RequestBody Condominio novoCondominio) {
		Condominio condominioSalvo = condominioRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Não foi possível localizar o condominio!"));
		
		condominioSalvo.setNome(novoCondominio.getNome());
		
		condominioRepository.save(condominioSalvo);
		return "O condominio foi atualizado!";
	}
	
	@DeleteMapping("/condominio/{id}")
	public String deleteCondominio(@PathVariable Long id) {
		condominioRepository.deleteById(id);
		return "Condominio deletado!";
	}

}
