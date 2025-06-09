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


import larePlusProjetoJPA.entity.Rua;
import larePlusProjetoJPA.repository.RuaRepository;

@RestController
public class RuaController {
	@Autowired
	private RuaRepository ruaRepository;
	
	@PostMapping("/rua")
	public String saveRua(@RequestBody Rua rua) {
		ruaRepository.save(rua);
		return "Rua incluída com sucesso!";
	}
	
	@PostMapping("/rua/lista")
	public String saveRuas(@RequestBody List<Rua> ruas) {
		ruaRepository.saveAll(ruas);
		return "Ruas incluídas com sucesso!";
	}
	
	@GetMapping("/rua")
	public List<Rua> getRuas() {
		return ruaRepository.findAll();
	}
	
	@GetMapping("/rua/{id}")
	public Rua getRuaById(@PathVariable Long id) {
		return ruaRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Não foi possível localizar a rua!"));
	}
	
	@PutMapping("/rua/{id}")
	public String updateRua(@PathVariable Long id, @RequestBody Rua novaRua) {
		Rua ruaSalva = ruaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Não foi possível localizar a rua!"));
		
		ruaSalva.setNome(novaRua.getNome());
		
		ruaRepository.save(ruaSalva);
		return "A rua foi atualizada!";
	}
	
	@DeleteMapping("/rua/{id}")
	public String deleteRua(@PathVariable Long id) {
		ruaRepository.deleteById(id);
		return "Rua deletada!";
	}

}
