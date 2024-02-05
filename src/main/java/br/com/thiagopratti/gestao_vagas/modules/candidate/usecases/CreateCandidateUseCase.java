package br.com.thiagopratti.gestao_vagas.modules.candidate.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.thiagopratti.gestao_vagas.exceptions.UserFoundExceptions;
import br.com.thiagopratti.gestao_vagas.modules.candidate.entity.CandidateEntity;
import br.com.thiagopratti.gestao_vagas.modules.candidate.repository.CandidateRepos;


@Service
public class CreateCandidateUseCase {
    
    @Autowired
    private CandidateRepos candidateRepos;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public CandidateEntity execute(CandidateEntity candidateEntity){

         this.candidateRepos
            .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
            .ifPresent((user) -> {
                throw new UserFoundExceptions();
            });

            var password = passwordEncoder.encode(candidateEntity.getPassword());
            candidateEntity.setPassword(password);

        return this.candidateRepos.save(candidateEntity);
    }
}
