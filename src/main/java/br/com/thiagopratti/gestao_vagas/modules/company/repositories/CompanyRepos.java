package br.com.thiagopratti.gestao_vagas.modules.company.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.thiagopratti.gestao_vagas.modules.company.entities.CompanyEntity;

public interface CompanyRepos extends JpaRepository<CompanyEntity, UUID>{

    Optional<CompanyEntity> findByUsernameOrEmail(String username, String e_mail);

}
