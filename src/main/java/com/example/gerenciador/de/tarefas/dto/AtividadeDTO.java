package com.example.gerenciador.de.tarefas.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalTime;

public record AtividadeDTO(
        Long id,
        @NotBlank(message = "O título é obrigatório.")
        String titulo,

        @NotBlank(message = "A descrição é obrigatória.")
        String descricao,

        @NotBlank(message = "O grau de necessidade é obrigatório.")
        String grauNecessidade,

        @NotBlank(message = "O dia da semana é obrigatório.")
        String diaSemana,

        @NotBlank(message = "O mês é obrigatório.")
        String mes,

        @NotBlank(message = "O status é obrigatório.")
        String status,

        @NotNull(message = "A data de início é obrigatória.")
        LocalDate dataInicio,

        @NotNull(message = "A data limite é obrigatória.")
        LocalDate dataLimite,

        @NotNull(message = "O horário é obrigatório.")
        LocalTime horario,

        @NotNull(message = "O ID do usuário é obrigatório.")
        Long usuarioId
) { }

