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
@Table(name = "condominio")
public class Condominio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_condominio;
	
	@Column(nullable = false, length = 50)
	private String nome;

	@OneToMany(mappedBy="condominio")
	Set<Apartamento> apartamentos = new HashSet<>();

	public Long getId_condominio() {
		return id_condominio;
	}

	public void setId_condominio(Long id_condominio) {
		this.id_condominio = id_condominio;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
