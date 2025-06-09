package larePlusProjetoJPA.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name= "telefone_atendente")//Tem relação com ATENDENTE (1,1) e ATENDENTE tem (1,n)
//A chave estrangeira é id_atendente
public class Telefone_atendente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_telefone_atendente;
	
	@Size(min = 9, max = 9)
	@Column(nullable = false, length = 9)
	private String telefone;
	
	@ManyToOne
	@JoinColumn(name="id_atendente")
	private Atendente atendente;

	public Long getId_telefone_atendente() {
		return id_telefone_atendente;
	}

	public void setId_telefone_atendente(Long id_telefone_atendente) {
		this.id_telefone_atendente = id_telefone_atendente;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Atendente getAtendente() {
		return atendente;
	}

	public void setAtendente(Atendente atendente) {
		this.atendente = atendente;
	}
}
