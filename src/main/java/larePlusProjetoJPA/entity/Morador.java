package larePlusProjetoJPA.entity;

import java.time.LocalDateTime;
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
@Table(name = "morador")
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

    @Column(nullable = false, length = 20)
    private String tipo_usuario = "MORADOR";

    @ManyToOne
    @JoinColumn(name = "id_apartamento")
    private Apartamento apartamento;

    @OneToMany(mappedBy = "morador")
    Set<Telefone_morador> telefones = new HashSet<>();

    @OneToMany(mappedBy = "morador")
    Set<Agendamento> agendamentos = new HashSet<>();

    @OneToMany(mappedBy = "morador")
    Set<Chamado_servico> chamados = new HashSet<>();

    // ----------------- NOVOS CAMPOS -----------------
    @Column(nullable = false)
    private int tentativasLogin = 0;

    @Column
    private LocalDateTime bloqueioAte;

    // ----------------- GETTERS E SETTERS -----------------
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

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public Apartamento getApartamento() {
        return apartamento;
    }

    public void setApartamento(Apartamento apartamento) {
        this.apartamento = apartamento;
    }

    public int getTentativasLogin() {
        return tentativasLogin;
    }

    public void setTentativasLogin(int tentativasLogin) {
        this.tentativasLogin = tentativasLogin;
    }

    public LocalDateTime getBloqueioAte() {
        return bloqueioAte;
    }

    public void setBloqueioAte(LocalDateTime bloqueioAte) {
        this.bloqueioAte = bloqueioAte;
    }
}
