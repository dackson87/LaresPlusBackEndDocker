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
@Table(name="rua")
public class Rua {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_rua;
	
	@Column(nullable = false, length = 50)
	private String nome;
	
	@OneToMany(mappedBy="rua")
	Set<Apartamento> apartamentos = new HashSet<>();

	public Long getId_rua() {
		return id_rua;
	}

	public void setId_rua(Long id_rua) {
		this.id_rua = id_rua;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}