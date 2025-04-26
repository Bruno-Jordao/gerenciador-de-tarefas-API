package com.example.gerenciador.de.tarefas.controller;

import com.example.gerenciador.de.tarefas.model.Atividade;
import com.example.gerenciador.de.tarefas.service.AtividadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/atividades")
public class AtividadeController {

    private final AtividadeService atividadeService;

    @PostMapping
    public Atividade criar(@RequestBody Atividade atividade) {
        return atividadeService.salvar(atividade);
    }

    @GetMapping
    public List<Atividade> listarTodas() {
        return atividadeService.listarTodas();
    }

    @GetMapping("/{id}")
    public Atividade buscarPorId(@PathVariable Long id) {
        return atividadeService.buscarPorID(id);
    }

    @PutMapping("/{id}")
    public Atividade atualizar(@PathVariable Long id, @RequestBody Atividade atividadeAtualizada) {
        return atividadeService.atualizar(id, atividadeAtualizada);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        atividadeService.excluir(id);
    }
}
