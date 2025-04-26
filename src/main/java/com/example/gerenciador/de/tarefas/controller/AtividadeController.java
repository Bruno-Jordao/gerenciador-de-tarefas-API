package com.example.gerenciador.de.tarefas.controller;

import com.example.gerenciador.de.tarefas.model.Atividade;
import com.example.gerenciador.de.tarefas.service.AtividadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/atividades")
public class AtividadeController {

    private final AtividadeService atividadeService;

    @PostMapping
    public ResponseEntity<Atividade> criar(@RequestBody Atividade atividade) {
        return ResponseEntity.status(HttpStatus.CREATED).body(atividadeService.salvar(atividade));
    }

    @GetMapping
    public ResponseEntity<Page<Atividade>> listarTodas(Pageable pageable) {
        return ResponseEntity.ok(atividadeService.listarTodas(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atividade> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(atividadeService.buscarPorID(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atividade> atualizar(@PathVariable Long id, @RequestBody Atividade atividadeAtualizada) {
        return ResponseEntity.ok(atividadeService.atualizar(id, atividadeAtualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        atividadeService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
