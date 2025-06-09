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
@Table(name = "apartamento")//Tem relação (1,1) com RUA, e RUA tem relação (1,n) // Tem relação (1,1) com CONDOMINIO, e CONDOMINIO tem relação (1,n)
//As chaves estrangeiras são id_rua e id_condominio
public class Apartamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_apartamento;
	
	@Column(nullable = false, length = 50)
	private String numero;
	
	@ManyToOne
	@JoinColumn(name="id_rua")
	private Rua rua;
	
	@ManyToOne
	@JoinColumn(name="id_condominio")
	private Condominio condominio;
	
	@OneToMany(mappedBy="apartamento")
	Set<Morador> moradores = new HashSet<>();

	public Long getId_apartamento() {
		return id_apartamento;
	}

	public void setId_apartamento(Long id_apartamento) {
		this.id_apartamento = id_apartamento;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Rua getRua() {
		return rua;
	}

	public void setRua(Rua rua) {
		this.rua = rua;
	}

	public Condominio getCondominio() {
		return condominio;
	}

	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
	}
	
}
