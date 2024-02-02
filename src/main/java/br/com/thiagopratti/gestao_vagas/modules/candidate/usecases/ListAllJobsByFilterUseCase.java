package br.com.thiagopratti.gestao_vagas.modules.candidate.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.thiagopratti.gestao_vagas.modules.company.entities.JobEntity;
import br.com.thiagopratti.gestao_vagas.modules.company.repositories.JobRepos;

@Service
public class ListAllJobsByFilterUseCase {
    
    @Autowired
    private JobRepos jobRepos; 

    public List<JobEntity> execute(String filter){
        return this.jobRepos.findByDescriptionContaining(filter);
    }
}
