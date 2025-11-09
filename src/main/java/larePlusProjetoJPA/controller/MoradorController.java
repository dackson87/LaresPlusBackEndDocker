package larePlusProjetoJPA.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import larePlusProjetoJPA.entity.Morador;
import larePlusProjetoJPA.repository.MoradorRepository;

@CrossOrigin(origins = "*")
@RestController
public class MoradorController {
	@Autowired
	private MoradorRepository moradorRepository;
	
	@PostMapping("/cadastro_morador")
	public ResponseEntity<Map<String, Object>> cadastroMorador(@RequestBody Morador morador) {
	    Optional<Morador> moradorOptional = moradorRepository.findByEmail(morador.getEmail());

	    if (moradorOptional.isPresent()) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("mensagem", "Email já cadastrado, use outro!"));
	    }

	    Morador novoMorador = moradorRepository.save(morador);

	    Map<String, Object> response = new HashMap<>();
	    response.put("id_morador", novoMorador.getId_morador());
	    response.put("mensagem", "Usuário cadastrado com sucesso!");

	    return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PostMapping("/morador")
	public String saveMorador(@RequestBody Morador morador) {
		moradorRepository.save(morador);
		return "Morador adicionado no sistema!";
	}
	
	@PostMapping("/morador/lista")
	public String saveMoradores(@RequestBody List<Morador> moradores) {
		moradorRepository.saveAll(moradores);
		return "Moradores adicionado no sistema!";
	}
	
	@GetMapping("/validar_morador")
	public ResponseEntity<?> validarMorador(@RequestParam String email, @RequestParam String senha) {
	    Optional<Morador> moradorOptional = moradorRepository.findByEmailAndSenha(email, senha);

	    if (moradorOptional.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas!");
	    }

	    Morador morador = moradorOptional.get();

	    Map<String, Object> response = new HashMap<>();
	    response.put("id_morador", morador.getId_morador());
	    response.put("nome", morador.getNome());

	    return ResponseEntity.ok(response);
	}
	
	@GetMapping("/morador")
	public List<Morador> getMoradoresTESTE() {
		return moradorRepository.findAll();
	}
	
	@GetMapping("/morador/{id}")
	public Morador getMoradorById(@PathVariable Long id) {
		return moradorRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Não foi possível localizar o morador no sistema!"));
	}
	
	@PutMapping("/morador/{id}")
	public String updateMorador(@PathVariable Long id, @RequestBody Morador novoMorador) {
		Morador moradorSalvo = moradorRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Não foi possível localizar o morador no sistema!"));
		
		moradorSalvo.setNome(novoMorador.getNome());
		moradorSalvo.setEmail(novoMorador.getEmail());
		moradorSalvo.setSenha(novoMorador.getSenha());
		moradorSalvo.setApartamento(novoMorador.getApartamento());
		
		moradorRepository.save(moradorSalvo);
		return "O morador foi atualizado!";
	}
		
	@DeleteMapping("/morador/{id}")
	public String deleteMorador(@PathVariable Long id) {
		moradorRepository.deleteById(id);
		return "Morador foi excluído!";
	}

}
