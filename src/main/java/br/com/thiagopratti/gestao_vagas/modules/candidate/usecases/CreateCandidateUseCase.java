package br.com.thiagopratti.gestao_vagas.modules.candidate.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.thiagopratti.gestao_vagas.exceptions.UserFoundExceptions;
import br.com.thiagopratti.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.thiagopratti.gestao_vagas.modules.candidate.CandidateRepos;


@Service
public class CreateCandidateUseCase {
    
    @Autowired
    private CandidateRepos candidateRepos;
    
    public CandidateEntity execute(CandidateEntity candidateEntity){

         this.candidateRepos
            .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
            .ifPresent((user) -> {
                throw new UserFoundExceptions();
            });

        return this.candidateRepos.save(candidateEntity);
    }
}
