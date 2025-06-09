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
@Table(name = "telefone_morador")//Tem relação (1,1) com MORADOR, e MORADOR tem relação (1,n)
//A chave estrangeira é id_morador
public class Telefone_morador {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_telefone_morador;
	
	@Size(min = 9, max = 9)
	@Column(nullable = false, length = 9)
	private String telefone;
	
	@ManyToOne
	@JoinColumn(name="id_morador")
	private Morador morador;

	public Long getId_telefone_morador() {
		return id_telefone_morador;
	}

	public void setId_telefone_morador(Long id_telefone_morador) {
		this.id_telefone_morador = id_telefone_morador;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Morador getMorador() {
		return morador;
	}

	public void setMorador(Morador morador) {
		this.morador = morador;
	}
}
