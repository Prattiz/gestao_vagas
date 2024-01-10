package br.com.thiagopratti.gestao_vagas.modules.company.repositories;

import br.com.thiagopratti.gestao_vagas.modules.company.entities.JobEntity;

public interface JobRepos {

    JobEntity save(JobEntity jobEntity);
    
}
