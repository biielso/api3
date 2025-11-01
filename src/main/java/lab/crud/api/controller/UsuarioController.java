package lab.crud.api.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lab.crud.api.model.Usuario;
import lab.crud.api.repository.UsuarioRepository;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;
    
    // curl -X POST http://localhost:8080/usuarios -H "Content-Type: application/json; Charset=utf-8" -d @usuario.json
    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) {
        Usuario salvo = repository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    
    @GetMapping("/usuarios")
    public ResponseEntity<Iterable<Usuario>> obterTodos() {
        
        List<Usuario> usuarios = (List<Usuario>) repository.findAll(); // ou repository.findByNomeLike("%a%") se quiser filtro
        
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(usuarios);
    }
    
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable Integer id) {
        
        Optional<Usuario> usuarioEncontrado = repository.findById(id);
        
        if (usuarioEncontrado.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Usuário não encontrado!");
        }
        
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(usuarioEncontrado.get());
    }
    
    // curl -X PUT http://localhost:8080/usuarios/1 -H "Content-Type: application/json; Charset=utf-8" -d @usuario-atualizado.json
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Object> atualizarUsuario(
            @PathVariable Integer id,
            @RequestBody Usuario usuario) {
        
        Optional<Usuario> usuarioExistente = repository.findById(id);
        
        if (usuarioExistente.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Usuário não encontrado!");
        }
        
        usuario.setId(id);
        usuario.setDataNascimento(usuarioExistente.get().getDataNascimento());
        repository.save(usuario);
        
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Usuário atualizado com sucesso!");
    }

    // curl -X DELETE http://localhost:8080/usuarios/1
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Object> apagarUsuario(@PathVariable Integer id) {
        
        Optional<Usuario> usuario = repository.findById(id);
        
        if (usuario.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Usuário não encontrado!");
        }
        
        repository.delete(usuario.get());
        
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Usuário apagado com sucesso!");
    }
}
