package br.com.thiagopratti.gestao_vagas.modules.company.repositories;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.thiagopratti.gestao_vagas.modules.company.entities.JobEntity;


public interface JobRepos extends JpaRepository<JobEntity, UUID> {
}
