package br.com.thiagopratti.gestao_vagas.modules.candidate.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.thiagopratti.gestao_vagas.modules.candidate.entity.CandidateEntity;

public interface CandidateRepos extends JpaRepository<CandidateEntity, UUID> {
    
    Optional<CandidateEntity> findByUsernameOrEmail(String username, String e_mail);

    Optional<CandidateEntity> findByUsername(String username);
}