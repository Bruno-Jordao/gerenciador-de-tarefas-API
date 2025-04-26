package com.example.gerenciador.de.tarefas.controller;

import com.example.gerenciador.de.tarefas.dto.AtividadeDTO;
import com.example.gerenciador.de.tarefas.service.AtividadeService;
import jakarta.validation.Valid;
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
    public ResponseEntity<AtividadeDTO> criar(@Valid @RequestBody AtividadeDTO atividadeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(atividadeService.salvar(atividadeDTO));
    }

    @GetMapping
    public ResponseEntity<Page<AtividadeDTO>> listarTodas(Pageable pageable) {
        return ResponseEntity.ok(atividadeService.listarTodas(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtividadeDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(atividadeService.buscarPorID(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtividadeDTO> atualizar(@PathVariable Long id,@Valid @RequestBody AtividadeDTO atividadeDTO) {
        return ResponseEntity.ok(atividadeService.atualizar(id, atividadeDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        atividadeService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
