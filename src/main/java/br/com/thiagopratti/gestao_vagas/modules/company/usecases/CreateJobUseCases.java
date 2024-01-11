package br.com.thiagopratti.gestao_vagas.modules.company.usecases;


import org.springframework.stereotype.Service;

import br.com.thiagopratti.gestao_vagas.modules.company.entities.JobEntity;
import br.com.thiagopratti.gestao_vagas.modules.company.repositories.JobRepos;

@Service
public class CreateJobUseCases {

    private JobRepos jobRepos;

    public JobEntity execute(JobEntity jobEntity){
        return this.jobRepos.save(jobEntity);
    }
}
