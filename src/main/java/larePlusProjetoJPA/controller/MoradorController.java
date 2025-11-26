package larePlusProjetoJPA.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import larePlusProjetoJPA.entity.Morador;
import larePlusProjetoJPA.repository.MoradorRepository;

@CrossOrigin(origins = "*")
@RestController
public class MoradorController {

    @Autowired
    private MoradorRepository moradorRepository;

    // ============================
    // CADASTRO DE MORADOR
    // ============================
    @PostMapping("/cadastro_morador")
    public ResponseEntity<Map<String, Object>> cadastroMorador(@RequestBody Morador morador) {
        Optional<Morador> moradorOptional = moradorRepository.findByEmail(morador.getEmail());

        if (moradorOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("mensagem", "Email já cadastrado, use outro!"));
        }

        if (morador.getSenha() == null || morador.getSenha().length() < 4) {
            return ResponseEntity.badRequest()
                    .body(Map.of("mensagem", "Senha muito fraca!"));
        }

        Morador novoMorador = moradorRepository.save(morador);

        Map<String, Object> response = new HashMap<>();
        response.put("id_morador", novoMorador.getId_morador());
        response.put("mensagem", "Usuário cadastrado com sucesso!");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ============================
    // LOGIN DE MORADOR
    // ============================
    @PostMapping("/validar_morador")
    public ResponseEntity<?> validarMorador(@RequestBody Map<String, String> login) {
        String email = login.get("email");
        String senha = login.get("senha");

        Optional<Morador> moradorOptional = moradorRepository.findByEmailAndSenha(email, senha);

        if (moradorOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("mensagem", "Credenciais inválidas!"));
        }

        Morador morador = moradorOptional.get();

        // Aqui você pode gerar JWT real futuramente
        String token = "TOKEN_FICTICIO";

        Map<String, Object> response = new HashMap<>();
        response.put("id_morador", morador.getId_morador());
        response.put("nome", morador.getNome());
        response.put("tipo_usuario", morador.getTipo_usuario());
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

    // ============================
    // LISTAGEM E CONSULTA DE MORADORES
    // ============================
    @GetMapping("/morador")
    public List<Morador> getMoradores() {
        return moradorRepository.findAll();
    }

    @GetMapping("/morador/{id}")
    public Morador getMoradorById(@PathVariable Long id) {
        return moradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não foi possível localizar o morador no sistema!"));
    }

    // ============================
    // ATUALIZAÇÃO DE MORADOR
    // ============================
    @PutMapping("/morador/{id}")
    public ResponseEntity<Map<String, String>> updateMorador(@PathVariable Long id, @RequestBody Morador novoMorador) {
        Morador moradorSalvo = moradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não foi possível localizar o morador no sistema!"));

        moradorSalvo.setNome(novoMorador.getNome());
        moradorSalvo.setEmail(novoMorador.getEmail());
        moradorSalvo.setSenha(novoMorador.getSenha());
        moradorSalvo.setApartamento(novoMorador.getApartamento());

        moradorRepository.save(moradorSalvo);

        return ResponseEntity.ok(Map.of("mensagem", "O morador foi atualizado!"));
    }

    // ============================
    // EXCLUSÃO DE MORADOR
    // ============================
    @DeleteMapping("/morador/{id}")
    public ResponseEntity<Map<String, String>> deleteMorador(@PathVariable Long id) {
        moradorRepository.deleteById(id);
        return ResponseEntity.ok(Map.of("mensagem", "Morador foi excluído!"));
    }
}
