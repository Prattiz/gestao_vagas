package br.com.thiagopratti.gestao_vagas.modules.candidate.usecases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.thiagopratti.gestao_vagas.exceptions.UserNotFoundException;
import br.com.thiagopratti.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.thiagopratti.gestao_vagas.modules.candidate.repository.CandidateRepos;


@Service
public class ProfileCandidateUseCase {

  @Autowired
  private CandidateRepos candidateRepos;


  public ProfileCandidateResponseDTO execute(UUID idCandidate) {

    var candidate = this.candidateRepos.findById(idCandidate)
        .orElseThrow(() -> {
          throw new UserNotFoundException();
        });

    var candidateDTO = ProfileCandidateResponseDTO.builder()
        .description(candidate.getDescription())
        .username(candidate.getUsername())
        .email(candidate.getEmail())
        .name(candidate.getName())
        .id(candidate.getId())
        .build();


    return candidateDTO;
  }
}