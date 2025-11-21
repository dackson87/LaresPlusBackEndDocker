package larePlusProjetoJPA.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import larePlusProjetoJPA.entity.Agendamento;
import larePlusProjetoJPA.repository.AgendamentoRepository;
import larePlusProjetoJPA.repository.Anexo_chamadoRepository;

@CrossOrigin(origins = "*")
@RestController
public class AgendamentoController {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private Anexo_chamadoRepository anexoChamadoRepository;

    @PostMapping("/agendamento")
    public ResponseEntity<?> saveAgendamento(@RequestBody Agendamento agendamento) {
        Long idArea = agendamento.getArea_comum().getId_area();
        LocalDate dia = agendamento.getDia();
        LocalTime horario = agendamento.getHorario();

        List<Agendamento> conflito = agendamentoRepository.findConflito(idArea, dia, horario);

        if (!conflito.isEmpty()) {
            return ResponseEntity
                    .status(409) 
                    .body("Erro: Já existe um agendamento para esta área neste dia e horário.");
        }

        agendamentoRepository.save(agendamento);
        return ResponseEntity.ok("Agendamento realizado com sucesso!");
    }

    @GetMapping("/agendamento")
    public List<Agendamento> getAgendamentos() {
        return agendamentoRepository.findAll();
    }

    @GetMapping("/agendamento/morador/{idMorador}")
    public List<Agendamento> getAgendamentosPorMorador(@PathVariable Long idMorador) {
        return agendamentoRepository.findAll()
                .stream()
                .filter(a -> a.getMorador() != null &&
                        a.getMorador().getId_morador().equals(idMorador))
                .toList();
    }

    @GetMapping("/agendamento/{id}")
    public ResponseEntity<?> getAgendamentoById(@PathVariable Long id) {
        return ResponseEntity.of(agendamentoRepository.findById(id));
    }

    @PutMapping("/agendamento/{id}")
    public ResponseEntity<?> updateAgendamento(
            @PathVariable Long id,
            @RequestBody Agendamento novoAgendamento) {

        Agendamento agendamentoSalvo = agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

        agendamentoSalvo.setDia(novoAgendamento.getDia());
        agendamentoSalvo.setHorario(novoAgendamento.getHorario());
        agendamentoSalvo.setStatus(novoAgendamento.getStatus());
        agendamentoSalvo.setArea_comum(novoAgendamento.getArea_comum());

        agendamentoRepository.save(agendamentoSalvo);
        return ResponseEntity.ok("Agendamento atualizado com sucesso!");
    }

    @DeleteMapping("/agendamento/{id}")
    @Transactional
    public ResponseEntity<?> deleteAgendamento(@PathVariable Long id) {
        if (!agendamentoRepository.existsById(id)) {
            return ResponseEntity
                    .status(404)
                    .body("Erro: Agendamento com ID " + id + " não encontrado para excluir.");
        }

        try {
            anexoChamadoRepository.deleteByIdAgendamento(id);

            agendamentoRepository.deleteById(id);

            return ResponseEntity.ok("Agendamento cancelado com sucesso!");

        } catch (Exception e) {
            System.err.println("### ERRO AO DELETAR AGENDAMENTO ###");
            System.err.println("ID: " + id);
            e.printStackTrace();
            System.err.println("#####################################");

            return ResponseEntity
                    .status(500)
                    .body("Erro interno no servidor ao tentar excluir o agendamento. Causa: " + e.getMessage());
        }
    }

}
