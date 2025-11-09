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

import larePlusProjetoJPA.entity.Anexo_chamado;
import larePlusProjetoJPA.repository.Anexo_chamadoRepository;

@CrossOrigin(origins = "*")
@RestController
public class Anexo_chamadoController {@Autowired
	private Anexo_chamadoRepository anexo_chamadoRepository;
	
	@PostMapping("/anexo_chamado")
	public String saveAnexo_chamado(@RequestBody Anexo_chamado anexo_chamado) {
		anexo_chamadoRepository.save(anexo_chamado);
		return "Anexo incluído com sucesso!";
	}
	
	@GetMapping("/anexo_chamado")
	public List<Anexo_chamado> getAnexo_chamado() {
		return anexo_chamadoRepository.findAll();
	}
	
	@GetMapping("/anexo_chamado/{id}")
	public Anexo_chamado getAnexo_chamadoById(@PathVariable Long id) {
		return anexo_chamadoRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Não foi possível encontrar o anexo!"));
	}
	
	@PutMapping("/anexo_chamado/{id}")
	public String updateAnexo_chamado(@PathVariable Long id, @RequestBody Anexo_chamado novoAnexo_chamado) {
		Anexo_chamado anexo_chamadoSalvo = anexo_chamadoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Não foi possível encontrar o anexo!"));
		//caminho_arquivo id_chamado
		anexo_chamadoSalvo.setCaminho_arquivo(novoAnexo_chamado.getCaminho_arquivo());
		anexo_chamadoSalvo.setChamado_servico(novoAnexo_chamado.getChamado_servico());
		
		anexo_chamadoRepository.save(anexo_chamadoSalvo);
		return "O anexo foi atualizado!";
	}
	
	@DeleteMapping("/anexo_chamado/{id}")
	public String deleteAnexo_chamado(@PathVariable Long id) {
		anexo_chamadoRepository.deleteById(id);
		return "Anexo deletado!";
	}

}
