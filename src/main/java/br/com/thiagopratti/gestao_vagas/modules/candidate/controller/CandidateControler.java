package br.com.thiagopratti.gestao_vagas.modules.candidate.controller;

import org.springframework.web.bind.annotation.RestController;


import br.com.thiagopratti.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.thiagopratti.gestao_vagas.modules.candidate.usecases.CreateCandidateUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/candidate")

public class CandidateControler {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;
    
    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity){
       try {
            var result = this.createCandidateUseCase.execute(candidateEntity);
            return ResponseEntity.ok().body(result);
       } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
    }
    }
}
