package larePlusProjetoJPA.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import larePlusProjetoJPA.entity.Morador;
import larePlusProjetoJPA.repository.MoradorRepository;

@Service
public class MoradorService {

    @Autowired
    private MoradorRepository moradorRepository;

    public Optional<Morador> validarLogin(String email, String senha) {
        return moradorRepository.findByEmailAndSenha(email, senha);
    }

    public Morador cadastrarMorador(Morador morador) {
        return moradorRepository.save(morador);
    }

    public Optional<Morador> buscarPorEmail(String email) {
        return moradorRepository.findByEmail(email);
    }

    public List<Morador> listarTodos() {
        return moradorRepository.findAll();
    }

    public Morador buscarPorId(Long id) {
        return moradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Morador não encontrado!"));
    }

    public Morador atualizarMorador(Long id, Morador novoMorador) {
        Morador morador = buscarPorId(id);

        morador.setNome(novoMorador.getNome());
        morador.setEmail(novoMorador.getEmail());
        morador.setSenha(novoMorador.getSenha());
        morador.setApartamento(novoMorador.getApartamento());

        return moradorRepository.save(morador);
    }

    public void deletarMorador(Long id) {
        moradorRepository.deleteById(id);
    }
}
