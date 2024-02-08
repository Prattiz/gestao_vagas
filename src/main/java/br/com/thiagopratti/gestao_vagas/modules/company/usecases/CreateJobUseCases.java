package br.com.thiagopratti.gestao_vagas.modules.company.usecases;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.thiagopratti.gestao_vagas.exceptions.CompanyNotFoundException;
import br.com.thiagopratti.gestao_vagas.modules.company.entities.JobEntity;
import br.com.thiagopratti.gestao_vagas.modules.company.repositories.CompanyRepos;
import br.com.thiagopratti.gestao_vagas.modules.company.repositories.JobRepos;

@Service
public class CreateJobUseCases {

  @Autowired
  private JobRepos jobRepos;

  @Autowired
  private CompanyRepos companyRepos;


  public JobEntity execute(JobEntity jobEntity) {

    companyRepos.findById(jobEntity.getCompanyId()).orElseThrow(() -> {
      throw new CompanyNotFoundException();
    });
    
    return this.jobRepos.save(jobEntity);
  }
}
