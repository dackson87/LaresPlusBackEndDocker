package larePlusProjetoJPA.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "area_comum")
public class Area_comum {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_area;
	
	@Column(nullable = false, length = 100)
	private String nome;
	
	@OneToMany(mappedBy="area_comum")
	Set<Agendamento> agendamentos = new HashSet<>();
	
	@OneToMany(mappedBy="area_comum")
	Set<Chamado_servico> Chamados = new HashSet<>();

	public Long getId_area() {
		return id_area;
	}

	public void setId_area(Long id_area) {
		this.id_area = id_area;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
