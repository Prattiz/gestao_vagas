package br.com.thiagopratti.gestao_vagas.modules.candidate.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.thiagopratti.gestao_vagas.modules.candidate.entity.ApplyJobEntity;

public interface ApplyJobRepos extends JpaRepository<ApplyJobEntity, UUID> {
}