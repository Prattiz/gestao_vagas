package br.com.thiagopratti.gestao_vagas.modules.company.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.thiagopratti.gestao_vagas.exceptions.UserFoundExceptions;
import br.com.thiagopratti.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.thiagopratti.gestao_vagas.modules.company.repositories.CompanyRepos;


@Service
public class CreateCompanyUseCases {

    @Autowired
    private CompanyRepos companyRepos;
    
    public CompanyEntity execute(CompanyEntity companyEntity){
        this.companyRepos.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
        .ifPresent((user) -> {
            throw new UserFoundExceptions();
        });

        return this.companyRepos.save(companyEntity);
    }
}
