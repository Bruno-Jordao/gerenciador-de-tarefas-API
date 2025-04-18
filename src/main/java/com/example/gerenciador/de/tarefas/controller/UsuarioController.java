package com.example.gerenciador.de.tarefas.controller;

import com.example.gerenciador.de.tarefas.model.Usuario;
import com.example.gerenciador.de.tarefas.repository.UsuarioRepository;
import com.example.gerenciador.de.tarefas.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario){
        return usuarioService.salvarUsuario(usuario);
    }

    @GetMapping
    public List<Usuario> listarUsuarios(){
        return usuarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public Usuario buscarUsuario(@PathVariable Long id){
        return usuarioService.buscarPorIdDoUsuario(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado){
        return usuarioService.atualizarUsuarioPorId(id, usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        usuarioService.deletarUsuario(id);
    }
}
