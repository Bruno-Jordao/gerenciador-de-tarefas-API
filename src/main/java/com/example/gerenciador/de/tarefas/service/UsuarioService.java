package com.example.gerenciador.de.tarefas.service;

import com.example.gerenciador.de.tarefas.exceptions.UsuarioNaoEncontradoException;
import com.example.gerenciador.de.tarefas.model.Usuario;
import com.example.gerenciador.de.tarefas.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos(){
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorIdDoUsuario(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário com ID "+id +" não encontrado"));
    }

    public Usuario atualizarUsuarioPorId(Long id, Usuario novoUsuario){
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setNome(novoUsuario.getNome());
                    usuario.setEmail(novoUsuario.getEmail());
                    usuario.setSenha(novoUsuario.getSenha());
                    return usuarioRepository.save(usuario);
                })
                .orElseThrow(() -> new RuntimeException("Não foi possível atualizar. Atividade com ID " + id + " não encontrado."));
    }

    public void deletarUsuario(Long id){
        usuarioRepository.deleteById(id);
    }

}
