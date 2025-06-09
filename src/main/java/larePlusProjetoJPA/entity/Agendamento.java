package larePlusProjetoJPA.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "agendamento")//Tem relação (1,1) com MORADOR, e MORADOR tem relação (1,n) // Tem relação (1,1) com AREA_COMUM, e AREA_COMUM tem relação (1,n)
//As chaves estrangeiras são id_morador e id_area
public class Agendamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_agendamento;
	
	@Column(nullable = false)
	private LocalDate dia;
	
	@Column(nullable = false)
	private LocalTime horario;
	
	@Column(nullable = false)
	private LocalDateTime dia_solicitado;
	
	@PrePersist
	public void prePersist() {
		this.dia_solicitado = LocalDateTime.now();
		
		if (this.status == null) {
	        this.status = Status.SOLICITADO;
	    }
	}
	
	public enum Status {
		SOLICITADO, CANCELADO, AGENDADO
	}
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, columnDefinition = "varchar(30) default 'Solicitado'")
	private Status status;
	
	@ManyToOne
	@JoinColumn(name="id_morador")
	private Morador morador;
	
	@ManyToOne
	@JoinColumn(name="id_area")
	private Area_comum area_comum;

	public Long getId_agendamento() {
		return id_agendamento;
	}

	public void setId_agendamento(Long id_agendamento) {
		this.id_agendamento = id_agendamento;
	}

	public LocalDate getDia() {
		return dia;
	}

	public void setDia(LocalDate dia) {
		this.dia = dia;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	public LocalDateTime getDia_solicitado() {
		return dia_solicitado;
	}

	public void setDia_solicitado(LocalDateTime dia_solicitado) {
		this.dia_solicitado = dia_solicitado;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Morador getMorador() {
		return morador;
	}

	public void setMorador(Morador morador) {
		this.morador = morador;
	}

	public Area_comum getArea_comum() {
		return area_comum;
	}

	public void setArea_comum(Area_comum area_comum) {
		this.area_comum = area_comum;
	}
}
	
