package br.com.thiagopratti.gestao_vagas.modules.candidate.controller;

import org.springframework.web.bind.annotation.RestController;


import br.com.thiagopratti.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.thiagopratti.gestao_vagas.modules.candidate.usecases.CreateCandidateUseCase;
import br.com.thiagopratti.gestao_vagas.modules.candidate.usecases.ProfileCandidateUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/candidate")

public class CandidateControler {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;
    
    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity){
       try {
            var result = this.createCandidateUseCase.execute(candidateEntity);
            return ResponseEntity.ok().body(result);
       } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
          }
    }

    @GetMapping("/")
    
    public ResponseEntity<Object> get(HttpServletRequest request){
     var idCandidate = request.getAttribute("candidate_id");
     
     try {
          var profile = this.profileCandidateUseCase.execute(UUID.fromString(idCandidate.toString()));

          return ResponseEntity.ok().body(profile);

     } catch (Exception e) {

          return ResponseEntity.badRequest().body(e.getMessage());
     }
    }
}
