package com.example.gerenciador.de.tarefas.service;

import com.example.gerenciador.de.tarefas.dto.AtividadeDTO;
import com.example.gerenciador.de.tarefas.exceptions.AtividadeNaoEncontradaException;
import com.example.gerenciador.de.tarefas.mapper.AtividadeMapper;
import com.example.gerenciador.de.tarefas.model.Atividade;
import com.example.gerenciador.de.tarefas.repository.AtividadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AtividadeService {

    private final AtividadeRepository atividadeRepository;

    public AtividadeDTO salvar(AtividadeDTO atividadeDTO){
        Atividade atividade = AtividadeMapper.toEntity(atividadeDTO);
        return AtividadeMapper.toDTO(atividadeRepository.save(atividade));
    }

    public Page<AtividadeDTO> listarTodas(Pageable pageable) {
        Page<Atividade> atividades = atividadeRepository.findAll(pageable);
        atualizarStatusAtividades(atividades.getContent());
        atividadeRepository.saveAll(atividades.getContent());
        return atividades.map(AtividadeMapper::toDTO);
    }

    public AtividadeDTO buscarPorID(Long id){
        Atividade atividade = atividadeRepository.findById(id)
                .orElseThrow(() -> new AtividadeNaoEncontradaException("Atividade com ID "+id+ " não encontrada"));
        return AtividadeMapper.toDTO(atividade);
    }

    public AtividadeDTO atualizar(Long id, AtividadeDTO novaAtividade) {
        return atividadeRepository.findById(id)
                .map(atividade -> {
                    atividade.setTitulo(novaAtividade.titulo());
                    atividade.setDescricao(novaAtividade.descricao());
                    atividade.setGrauNecessidade(novaAtividade.grauNecessidade());
                    atividade.setDataInicio(novaAtividade.dataInicio());
                    atividade.setDataLimite(novaAtividade.dataLimite());
                    atividade.setHorario(novaAtividade.horario());
                    atividade.setDiaSemana(novaAtividade.diaSemana());
                    atividade.setMes(novaAtividade.mes());
                    atividade.setStatus(novaAtividade.status());
                    atividadeRepository.save(atividade);
                    return AtividadeMapper.toDTO(atividade);
                })
                .orElseThrow(() -> new AtividadeNaoEncontradaException(
                        "Não foi possível atualizar. Atividade com ID " + id + " não encontrado."
                ));
    }

    public void excluir(Long id){
        atividadeRepository.deleteById(id);
    }

    private void atualizarStatusAtividades(List<Atividade> atividades) {
        LocalDate hoje = LocalDate.now();

        for (Atividade atividade : atividades) {
            if (atividade.getDataLimite() != null) {
                if (atividade.getStatus() == null || !atividade.getStatus().equals("FEITA")) {
                    if (atividade.getDataLimite().isBefore(hoje)) {
                        atividade.setStatus("ATRASADA");
                    } else {
                        atividade.setStatus("A FAZER");
                    }
                }
            }
        }
    }


}
