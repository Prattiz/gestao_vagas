package br.com.thiagopratti.gestao_vagas.modules.candidate;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepos extends JpaRepository<CandidateEntity, UUID> {

}