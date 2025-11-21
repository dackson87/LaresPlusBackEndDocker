package larePlusProjetoJPA.dto;

import larePlusProjetoJPA.entity.Chamado_servico.Status;

public class ChamadoDTO {
    private Long id_chamado;
    private String descricao_chamado;
    private Status status;
    private String data_solicitacao;

    public ChamadoDTO(Long id_chamado, String descricao_chamado, Status status2, String data_solicitacao) {
        this.id_chamado = id_chamado;
        this.descricao_chamado = descricao_chamado;
        this.status = status2;
        this.data_solicitacao = data_solicitacao;
    }

    public Long getId_chamado() { return id_chamado; }
    public String getDescricao_chamado() { return descricao_chamado; }
    public Status getStatus() { return status; }
    public String getData_solicitacao() { return data_solicitacao; }
}
