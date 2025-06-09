package larePlusProjetoJPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import larePlusProjetoJPA.entity.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {}