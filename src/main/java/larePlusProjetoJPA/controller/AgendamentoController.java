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
import java.util.List;


import larePlusProjetoJPA.entity.Agendamento;
import larePlusProjetoJPA.repository.AgendamentoRepository;

@CrossOrigin(origins = "*")
@RestController
public class AgendamentoController {
	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	@PostMapping("/agendamento")
	public String saveAgendamento(@RequestBody Agendamento agendamento) {
		agendamentoRepository.save(agendamento);
		return "Agendamento salvo com sucesso!";
	}
	
	@GetMapping("/agendamento")
	public java.util.List<Agendamento> getAgendamentos() {
		return agendamentoRepository.findAll();
	}
	
	@GetMapping("/agendamento/morador/{idMorador}")
	public List<Agendamento> getAgendamentosPorMorador(@PathVariable Long idMorador) {
	    return agendamentoRepository.findAll()
	        .stream()
	        .filter(a -> a.getMorador() != null && a.getMorador().getId_morador().equals(idMorador))
	        .toList();
	}

	@GetMapping("/agendamento/{id}")
	public Agendamento getAgendamentoById(@PathVariable Long id) {
	return agendamentoRepository.findById(id)
			.orElseThrow(()-> new RuntimeException("Não foi possível encontrar o agendamento!"));
	}
	
	@PutMapping("/agendamento/{id}")
	public String updateAgendamento(@PathVariable Long id, @RequestBody Agendamento novoAgendamento) {
		Agendamento agendamentoSalvo = agendamentoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Não foi possível encontrar o agendamento!"));
		
		agendamentoSalvo.setDia(novoAgendamento.getDia());
		agendamentoSalvo.setHorario(novoAgendamento.getHorario());
		agendamentoSalvo.setStatus(novoAgendamento.getStatus());
		agendamentoSalvo.setArea_comum(novoAgendamento.getArea_comum());
		
		agendamentoRepository.save(agendamentoSalvo);
		return "O agendamento foi atualizado!";
	}
	
	@DeleteMapping("/agendamento/{id}")
	public String deleteAgendamento(@PathVariable Long id) {
		agendamentoRepository.deleteById(id);
		return "Agendamento cancelado!";
	}
}
