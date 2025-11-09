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

import larePlusProjetoJPA.entity.Apartamento;
import larePlusProjetoJPA.repository.ApartamentoRepository;


@CrossOrigin(origins = "*")
@RestController
public class ApartamentoController {
	
	@Autowired
	private ApartamentoRepository apartamentoRepository;
	
	@PostMapping("/apartamento")
	public String saveApartamento(@RequestBody Apartamento apartamento) {
		apartamentoRepository.save(apartamento);
		return "Apartamento adicionado com sucesso!";
	}
	
	@PostMapping("/apartamento/lista")
	public String saveApartamentos(@RequestBody List<Apartamento> apartamentos) {
		apartamentoRepository.saveAll(apartamentos);
		return "Apartamentos adicionados com sucesso!";
	}
	
	@GetMapping("/apartamento")
	public List<Apartamento> getApartamento() {
		return apartamentoRepository.findAll();
	}
	
	@GetMapping("/apartamento/{id}")
	public Apartamento getApartamentoById(@PathVariable Long id) {
		return apartamentoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Não foi possível encontrar o apartamento informado!"));
	}
	
	@PutMapping("/apartamento/{id}")
	public String updateApartamento(@PathVariable Long id, @RequestBody Apartamento novoApartamento) {
		Apartamento apartamentoSalvo = apartamentoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Não foi possível encontrar o apartamento informado!"));
		
		apartamentoSalvo.setNumero(novoApartamento.getNumero());
		apartamentoSalvo.setCondominio(novoApartamento.getCondominio());
		apartamentoSalvo.setRua(novoApartamento.getRua());
		
		apartamentoRepository.save(apartamentoSalvo);
		return "O apartamento foi atualizado!";
	}
	
	@DeleteMapping("/apartamento/{id}")
	public String deleteApartamento(@PathVariable Long id) {
		apartamentoRepository.deleteById(id);
		return "Apartamento apagado!";
	}

}
