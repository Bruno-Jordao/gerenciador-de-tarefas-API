package com.example.gerenciador.de.tarefas.mapper;

import com.example.gerenciador.de.tarefas.dto.AtividadeDTO;
import com.example.gerenciador.de.tarefas.model.Atividade;

public final class AtividadeMapper {

    private AtividadeMapper() {}

    public static AtividadeDTO toDTO(Atividade atividade) {
        if (atividade == null) {
            return null;
        }

        return new AtividadeDTO(
                atividade.getId(),
                atividade.getTitulo(),
                atividade.getDescricao(),
                atividade.getGrauNecessidade(),
                atividade.getDiaSemana(),
                atividade.getMes(),
                atividade.getStatus(),
                atividade.getDataInicio(),
                atividade.getDataLimite(),
                atividade.getHorario(),
                atividade.getUsuario() != null ? atividade.getUsuario().getId() : null
        );
    }

    public static Atividade toEntity(AtividadeDTO dto) {
        if (dto == null) {
            return null;
        }

        Atividade atividade = new Atividade();
        atividade.setId(dto.id());
        atividade.setTitulo(dto.titulo());
        atividade.setDescricao(dto.descricao());
        atividade.setGrauNecessidade(dto.grauNecessidade());
        atividade.setDiaSemana(dto.diaSemana());
        atividade.setMes(dto.mes());
        atividade.setStatus(dto.status());
        atividade.setDataInicio(dto.dataInicio());
        atividade.setDataLimite(dto.dataLimite());
        atividade.setHorario(dto.horario());

        return atividade;
    }
}
