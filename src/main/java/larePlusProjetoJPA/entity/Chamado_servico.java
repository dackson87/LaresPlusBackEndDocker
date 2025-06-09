package larePlusProjetoJPA.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;


@Entity
@Table(name = "chamado_servico")//Tem relação com MORADOR (1,1) e MORADOR tem relação (1,n) // relação com ATENDENTE (1,1) e ATENDENTE tem relação (1,n)
//As chaves estrangeiras são id_morador e id_atendente
public class Chamado_servico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_chamado;
	
	@Column(nullable = false)
	private LocalDateTime data_abertura;
	
	@PrePersist
	public void prePersist() {
		this.data_abertura = LocalDateTime.now();
		
		if (this.status == null) {
	        this.status = Status.PENDENTE;
	    }
	}
	
	@Lob
	@Column(nullable = false)
	private String descricao_chamado;
	
	public enum Status {
		PENDENTE, EM_ANDAMENTO, CONCLUIDO 
	}
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, columnDefinition = "varchar(30) default 'Pendente'")
	private Status status;
	
	public enum Motivo {
		MANUTENCAO, LIMPEZA, SEGURANCA, INFRAESTRUTURA, ILUMINACAO, PROBLEMA_COM_PORTARIA, OUTROS
	}
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, columnDefinition = "varchar(30)")
	private Motivo motivo;
	
	public enum Situacao {
		URGENTE, MODERADO, LEVE, INFORMATIVO
	}
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, columnDefinition = "varchar(30)")
	private Situacao situacao;
	
	@ManyToOne
	@JoinColumn(name="id_morador")
	private Morador morador;
	
	@ManyToOne
	@JoinColumn(name="id_atendente")
	private Atendente atendente;
	
	@ManyToOne
	@JoinColumn(name="id_area")
	private Area_comum area_comum;
	
	@OneToMany(mappedBy="chamado_servico")
	Set<Anexo_chamado> anexos = new HashSet<>();

	public Long getId_chamado() {
		return id_chamado;
	}

	public void setId_chamado(Long id_chamado) {
		this.id_chamado = id_chamado;
	}

	public LocalDateTime getData_abertura() {
		return data_abertura;
	}

	public void setData_abertura(LocalDateTime data_abertura) {
		this.data_abertura = data_abertura;
	}

	public String getDescricao_chamado() {
		return descricao_chamado;
	}

	public void setDescricao_chamado(String descricao_chamado) {
		this.descricao_chamado = descricao_chamado;
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

	public Atendente getAtendente() {
		return atendente;
	}

	public void setAtendente(Atendente atendente) {
		this.atendente = atendente;
	}

	public Area_comum getArea_comum() {
		return area_comum;
	}

	public void setArea_comum(Area_comum area_comum) {
		this.area_comum = area_comum;
	}

	public Motivo getMotivo() {
		return motivo;
	}

	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
																									
}

