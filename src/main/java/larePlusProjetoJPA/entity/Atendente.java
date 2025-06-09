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
@Table(name = "atendente")
public class Atendente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_atendente;
	
	@Column(nullable = false, length = 100)
	private String nome;
	
	@Column(nullable = false, length = 100, unique = true)
	private String email;
	
	@Column(nullable = false, length = 20)
	private String senha;
	
	@OneToMany(mappedBy="atendente")
	Set<Chamado_servico> chamados = new HashSet<>();
	
	@OneToMany(mappedBy="atendente")
	Set<Telefone_atendente> telefones = new HashSet<>();

	public Long getId_atendente() {
		return id_atendente;
	}

	public void setId_atendente(Long id_atendente) {
		this.id_atendente = id_atendente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
