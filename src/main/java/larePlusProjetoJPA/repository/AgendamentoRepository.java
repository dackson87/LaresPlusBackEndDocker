package larePlusProjetoJPA.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import larePlusProjetoJPA.entity.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    @Query("SELECT a FROM Agendamento a " +
           "WHERE a.area_comum.id_area = :idArea " +
           "AND a.dia = :dia " +
           "AND a.horario = :horario")
    List<Agendamento> findConflito(
            @Param("idArea") Long idArea,
            @Param("dia") LocalDate dia,
            @Param("horario") LocalTime horario
    );


}
