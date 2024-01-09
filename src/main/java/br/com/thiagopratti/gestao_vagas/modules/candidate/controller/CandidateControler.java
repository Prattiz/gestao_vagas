package br.com.thiagopratti.gestao_vagas.modules.candidate.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.thiagopratti.gestao_vagas.modules.candidate.CandidateEntity;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/candidate")

public class CandidateControler {
    
    @PostMapping("/")
    public void create(@Valid @RequestBody CandidateEntity candidateEntity){
        System.err.println("Candidato" + candidateEntity.getEmail());
    }
}
