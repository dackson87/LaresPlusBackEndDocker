package larePlusProjetoJPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import larePlusProjetoJPA.entity.Anexo_chamado;

public interface Anexo_chamadoRepository extends JpaRepository<Anexo_chamado, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Anexo_chamado a WHERE a.chamado_servico.id_chamado = :idAgendamento")
    void deleteByIdAgendamento(@Param("idAgendamento") Long idAgendamento);

}
