package larePlusProjetoJPA.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import larePlusProjetoJPA.entity.Morador;

public interface MoradorRepository extends JpaRepository<Morador, Long>{
	Optional<Morador> findByEmailAndSenha(String email, String senha);
	Optional<Morador> findByEmail(String email);
}
