package br.com.thiagopratti.gestao_vagas.modules.candidate.UseCase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import br.com.thiagopratti.gestao_vagas.exceptions.JobNotFoundException;
import br.com.thiagopratti.gestao_vagas.exceptions.UserNotFoundException;
import br.com.thiagopratti.gestao_vagas.modules.candidate.entity.ApplyJobEntity;
import br.com.thiagopratti.gestao_vagas.modules.candidate.entity.CandidateEntity;
import br.com.thiagopratti.gestao_vagas.modules.candidate.repository.ApplyJobRepos;
import br.com.thiagopratti.gestao_vagas.modules.candidate.repository.CandidateRepos;
import br.com.thiagopratti.gestao_vagas.modules.candidate.usecases.ApplyJobCandidateUseCase;
import br.com.thiagopratti.gestao_vagas.modules.company.entities.JobEntity;
import br.com.thiagopratti.gestao_vagas.modules.company.repositories.JobRepos;


@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private CandidateRepos candidateRepos;

    @Mock
    private JobRepos jobRepos;

    @Mock
    private ApplyJobRepos applyJobRepos;

    @Test
    @DisplayName("Should not be able to apply for a job if candidate does not found")
    public void should_not_be_apply_jon_with_candidate_not_found() {
        try{
            applyJobCandidateUseCase.execute(null, null);
        } catch (Exception e){
            assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
    }

    @Test
    public void should_not_be_able_to_apply_job_with_job_not_found(){
        var idCandidate = UUID.randomUUID();

        var candidate = new CandidateEntity();
        candidate.setId(idCandidate);

        when(candidateRepos.findById(idCandidate)).thenReturn(Optional.of(candidate));

        try{
            applyJobCandidateUseCase.execute(idCandidate, null);
        } catch (Exception e){
            assertThat(e).isInstanceOf(JobNotFoundException.class);
        }
    }

    @Test
    public void should_be_able_to_create_a_new_apply_job() {
        var idCandidate = UUID.randomUUID();
        var idJob = UUID.randomUUID();

        var applyJob = ApplyJobEntity.builder().candidateId(idCandidate)
                .jobId(idJob).build();

        var applyJobCreated = ApplyJobEntity.builder().id(UUID.randomUUID()).build();

        when(candidateRepos.findById(idCandidate)).thenReturn(Optional.of(new CandidateEntity()));
        when(jobRepos.findById(idJob)).thenReturn(Optional.of(new JobEntity()));

        when(applyJobRepos.save(applyJob)).thenReturn(applyJobCreated);

        var result = applyJobCandidateUseCase.execute(idCandidate, idJob);

        assertThat(result).hasFieldOrProperty("id");
        assertNotNull(result.getId());
    }
}