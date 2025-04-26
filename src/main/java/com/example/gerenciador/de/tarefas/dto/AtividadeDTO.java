package com.example.gerenciador.de.tarefas.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record AtividadeDTO(
        Long id,
        String titulo,
        String descricao,
        String grauNecessidade,
        String diaSemana,
        String mes,
        String status,
        LocalDate dataInicio,
        LocalDate dataLimite,
        LocalTime horario,
        Long usuarioId
) { }

