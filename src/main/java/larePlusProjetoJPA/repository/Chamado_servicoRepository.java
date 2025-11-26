package larePlusProjetoJPA.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import larePlusProjetoJPA.entity.Chamado_servico;

@Repository
public interface Chamado_servicoRepository extends JpaRepository<Chamado_servico, Long> {

    // Buscar todos os chamados de um morador específico
    @Query("SELECT c FROM Chamado_servico c WHERE c.morador.id_morador = :id")
    List<Chamado_servico> findByMoradorId(@Param("id") Long idMorador);
}
