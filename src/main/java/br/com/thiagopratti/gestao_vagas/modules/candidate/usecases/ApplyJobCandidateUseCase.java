package br.com.thiagopratti.gestao_vagas.modules.candidate.usecases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.thiagopratti.gestao_vagas.exceptions.JobNotFoundException;
import br.com.thiagopratti.gestao_vagas.exceptions.UserNotFoundException;
import br.com.thiagopratti.gestao_vagas.modules.candidate.CandidateRepos;
import br.com.thiagopratti.gestao_vagas.modules.company.repositories.JobRepos;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private CandidateRepos candidateRepos;

    @Autowired
    private JobRepos jobRepos;

    
    public void execute(UUID idCandidate, UUID idJob){

        this.candidateRepos.findById(idCandidate).orElseThrow(() -> {
            throw new UserNotFoundException();
        });

        this.jobRepos.findById(idJob).orElseThrow(() -> {
            throw new JobNotFoundException();
        });
    }
}
