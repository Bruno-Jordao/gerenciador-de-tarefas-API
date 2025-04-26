package com.example.gerenciador.de.tarefas.service;

import com.example.gerenciador.de.tarefas.exceptions.UsuarioNaoEncontradoException;
import com.example.gerenciador.de.tarefas.model.Usuario;
import com.example.gerenciador.de.tarefas.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario salvarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Page<Usuario> listarTodos(Pageable pageable){
        return usuarioRepository.findAll(pageable);
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
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Não foi possível atualizar. Usuário com ID " + id + " não encontrado."));
    }

    public void deletarUsuario(Long id){
        usuarioRepository.deleteById(id);
    }

}
