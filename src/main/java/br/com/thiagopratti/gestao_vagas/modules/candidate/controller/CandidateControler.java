package br.com.thiagopratti.gestao_vagas.modules.candidate.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.thiagopratti.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.thiagopratti.gestao_vagas.modules.candidate.CandidateRepos;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/candidate")

public class CandidateControler {

    @Autowired
    private CandidateRepos candidateRepos;
    
    @PostMapping("/")
    public CandidateEntity create(@Valid @RequestBody CandidateEntity candidateEntity){
        return this.candidateRepos.save(candidateEntity);
    }
}
