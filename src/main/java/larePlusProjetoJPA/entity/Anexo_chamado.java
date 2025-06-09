package larePlusProjetoJPA.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "anexo_chamado")//Tem relação com CHAMADO_SERVICO (1,1) e CHAMADO_SERVICO tem (1,n)
//As chaves estrangeiras são id_chamado
public class Anexo_chamado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_anexo;
	
	@Column(nullable = true)
	private String caminho_arquivo;

	@ManyToOne
	@JoinColumn(name="id_chamado")
	private Chamado_servico chamado_servico;

	public Long getId_anexo() {
		return id_anexo;
	}

	public void setId_anexo(Long id_anexo) {
		this.id_anexo = id_anexo;
	}

	public String getCaminho_arquivo() {
		return caminho_arquivo;
	}

	public void setCaminho_arquivo(String caminho_arquivo) {
		this.caminho_arquivo = caminho_arquivo;
	}

	public Chamado_servico getChamado_servico() {
		return chamado_servico;
	}

	public void setChamado_servico(Chamado_servico chamado_servico) {
		this.chamado_servico = chamado_servico;
	}
}
