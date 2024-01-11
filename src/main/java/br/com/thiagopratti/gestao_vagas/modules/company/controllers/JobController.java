package br.com.thiagopratti.gestao_vagas.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thiagopratti.gestao_vagas.modules.company.entities.JobEntity;
import br.com.thiagopratti.gestao_vagas.modules.company.usecases.CreateJobUseCases;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/job")
public class JobController {
    
    @Autowired
    private CreateJobUseCases createJobUseCases;

    @PostMapping("/")
    public JobEntity create(@Valid @RequestBody JobEntity jobEntity){
        return this.createJobUseCases.execute(jobEntity);
    }
}