package larePlusProjetoJPA.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import larePlusProjetoJPA.entity.Chamado_servico;
import larePlusProjetoJPA.repository.Chamado_servicoRepository;

class ChamadoDTO {
    public Long id_chamado;
    public String descricao_chamado;
    public String status;
    public String data_solicitacao;

    public ChamadoDTO(Long id, String descricao, String status, String data_solicitacao) {
        this.id_chamado = id;
        this.descricao_chamado = descricao;
        this.status = status;
        this.data_solicitacao = data_solicitacao;
    }
}

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/chamado_servico")
public class Chamado_servicoController {

    @Autowired
    private Chamado_servicoRepository chamado_servicoRepository;

    @GetMapping("/morador/{idMorador}")
    public List<ChamadoDTO> getChamadosPorMorador(@PathVariable Long idMorador) {
        List<Chamado_servico> chamados = chamado_servicoRepository.findByMoradorId(idMorador);

        return chamados.stream()
                .map(c -> new ChamadoDTO(
                        c.getId_chamado(),
                        c.getDescricao_chamado(),
                        c.getStatus() != null ? c.getStatus().name() : "PENDENTE",
                        c.getData_abertura() != null ? c.getData_abertura().toString() : ""
                ))
                .collect(Collectors.toList());
    }

    @PostMapping
    public Chamado_servico save(@RequestBody Chamado_servico chamado) {
        return chamado_servicoRepository.save(chamado);
    }

    @GetMapping("/{id}")
    public Chamado_servico getById(@PathVariable Long id) {
        return chamado_servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chamado não encontrado"));
    }

    @PutMapping("/{id}")
    public Chamado_servico update(@PathVariable Long id, @RequestBody Chamado_servico novoChamado) {
        Chamado_servico chamado = chamado_servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chamado não encontrado"));

        chamado.setDescricao_chamado(novoChamado.getDescricao_chamado());
        chamado.setStatus(novoChamado.getStatus());
        chamado.setAtendente(novoChamado.getAtendente());

        return chamado_servicoRepository.save(chamado);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        chamado_servicoRepository.deleteById(id);
        return "Chamado deletado!";
    }
    
    @GetMapping
    public List<Chamado_servico> getAllChamados() {
        return chamado_servicoRepository.findAll();
    }

}
