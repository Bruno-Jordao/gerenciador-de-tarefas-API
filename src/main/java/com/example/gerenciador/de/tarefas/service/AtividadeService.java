package com.example.gerenciador.de.tarefas.service;

import com.example.gerenciador.de.tarefas.exceptions.AtividadeNaoEncontradaException;
import com.example.gerenciador.de.tarefas.model.Atividade;
import com.example.gerenciador.de.tarefas.repository.AtividadeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AtividadeService {

    private final AtividadeRepository atividadeRepository;

    public AtividadeService(AtividadeRepository atividadeRepository) {
        this.atividadeRepository = atividadeRepository;
    }
    public Atividade salvar(Atividade atividade){
        return atividadeRepository.save(atividade);
    }
    public List<Atividade> listarTodas() {
        List<Atividade> atividades = atividadeRepository.findAll();
        LocalDate hoje = LocalDate.now();

        for (Atividade atividade : atividades) {
            if (atividade.getDataLimite() != null) {
                if (atividade.getStatus() == null || !atividade.getStatus().equals("FEITA")) {
                    if (atividade.getDataLimite().isBefore(hoje)) {
                        atividade.setStatus("ATRASADA");
                    } else {
                        atividade.setStatus("A FAZER");
                    }
                    atividadeRepository.save(atividade);
                }
            }
        }

        return atividades;
    }
    public Atividade buscarPorID(Long id){
        return atividadeRepository.findById(id)
                .orElseThrow(() -> new AtividadeNaoEncontradaException("Atividade com ID "+id+ " não encontrada"));
    }
    public Atividade atualizar(Long id, Atividade novaAtividade) {
        return atividadeRepository.findById(id)
                .map(atividade -> {
                    atividade.setTitulo(novaAtividade.getTitulo());
                    atividade.setDescricao(novaAtividade.getDescricao());
                    atividade.setGrauNecessidade(novaAtividade.getGrauNecessidade());
                    atividade.setDataInicio(novaAtividade.getDataInicio());
                    atividade.setDataLimite(novaAtividade.getDataLimite());
                    atividade.setHorario(novaAtividade.getHorario());
                    atividade.setDiaSemana(novaAtividade.getDiaSemana());
                    atividade.setMes(novaAtividade.getMes());
                    atividade.setStatus(novaAtividade.getStatus());
                    return atividadeRepository.save(atividade);
                })
                .orElseThrow(() -> new AtividadeNaoEncontradaException("Não foi possível atualizar. Atividade com ID " + id + " não encontrado."));
    }
    public void excluir(Long id){
        atividadeRepository.deleteById(id);
    }

}
