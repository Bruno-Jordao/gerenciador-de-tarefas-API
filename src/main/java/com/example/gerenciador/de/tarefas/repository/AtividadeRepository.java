package com.example.gerenciador.de.tarefas.repository;

import com.example.gerenciador.de.tarefas.model.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
}
