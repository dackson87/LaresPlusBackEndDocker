package larePlusProjetoJPA.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name= "morador")//Tem relação (1,1) com APARTAMENTO, e APARTAMENTO tem relação (1,n)
//A chave estrangeira é id_apartamento
public class Morador {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_morador;
	
	@Column(nullable = false, length = 100)
	private String nome;
	
	@Column(nullable = false, length = 100, unique = true)
	private String email;

	@Column(nullable = false, length = 50)
	private String senha;
	
	@ManyToOne
	@JoinColumn(name="id_apartamento")
	private Apartamento apartamento;
	
	@OneToMany(mappedBy="morador")
	Set<Telefone_morador> telefones = new HashSet<>();
	
	@OneToMany(mappedBy="morador")
	Set<Agendamento> agendamentos = new HashSet<>();
	
	@OneToMany(mappedBy="morador")
	Set<Chamado_servico> chamados = new HashSet<>();

	public Long getId_morador() {
		return id_morador;
	}

	public void setId_morador(Long id_morador) {
		this.id_morador = id_morador;
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

	public Apartamento getApartamento() {
		return apartamento;
	}

	public void setApartamento(Apartamento apartamento) {
		this.apartamento = apartamento;
	}
}
