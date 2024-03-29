package br.com.thiagopratti.gestao_vagas.modules.candidate.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.thiagopratti.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.thiagopratti.gestao_vagas.modules.candidate.entity.CandidateEntity;
import br.com.thiagopratti.gestao_vagas.modules.candidate.usecases.ApplyJobCandidateUseCase;
import br.com.thiagopratti.gestao_vagas.modules.candidate.usecases.CreateCandidateUseCase;
import br.com.thiagopratti.gestao_vagas.modules.candidate.usecases.ListAllJobsByFilterUseCase;
import br.com.thiagopratti.gestao_vagas.modules.candidate.usecases.ProfileCandidateUseCase;
import br.com.thiagopratti.gestao_vagas.modules.company.entities.JobEntity;

import java.util.List;
import java.util.UUID;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidate", description = "Candidate's Information")
public class CandidateControler {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @Autowired
    private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

    @Autowired
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;
    
    @PostMapping("/")
    @Operation(
     summary = "Register candidate",

     description = "This function is is responsible for registering the candidate"
     )
     @ApiResponses({
          @ApiResponse(responseCode = "200", content = {
              @Content(schema = @Schema(implementation = CandidateEntity.class))
          }),
          @ApiResponse(responseCode = "400", description = "User already exists")
      })
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity){
       try {
            var result = this.createCandidateUseCase.execute(candidateEntity);
            return ResponseEntity.ok().body(result);
       } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
          }
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(
     summary = "Candidate's profile",

     description = "This function is is responsible for searching for candidate informations"
     )
     @ApiResponses({
          @ApiResponse(responseCode = "200", content = {
              @Content(schema = @Schema(implementation = ProfileCandidateResponseDTO.class))
          }),
          @ApiResponse(responseCode = "400", description = "User not found")
      })
     @SecurityRequirement(name = "jtw_auth")
    public ResponseEntity<Object> get(HttpServletRequest request){
     var idCandidate = request.getAttribute("candidate_id");
     
     try {
          var profile = this.profileCandidateUseCase.execute(UUID.fromString(idCandidate.toString()));

          return ResponseEntity.ok().body(profile);

     } catch (Exception e) {

          return ResponseEntity.badRequest().body(e.getMessage());
     }
    }

    @GetMapping("/job")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(
     summary = "Filtered list of vacancies for the candidate",
     description = "This function is responsable to filter all the jobs in the list"
     )
     @ApiResponses(
          @ApiResponse(responseCode = "200", content = {
               @Content(
                    array = @ArraySchema(
                         schema = @Schema(implementation = JobEntity.class)
               ))
          })
     )
     @SecurityRequirement(name = "jtw_auth")
    public List<JobEntity> findJobByFilter(@RequestParam String filter){

     return this.listAllJobsByFilterUseCase.execute(filter);
    }


    @PostMapping("/job/apply")
    @PreAuthorize("hasRole('CANDIDATE')")
    @SecurityRequirement(name = "jtw_auth")
    @Operation(
     summary = "registration of a candidate for a vacancy",
     description = "This function is responsible for registering the candidate for a vacancy"
     )
    public ResponseEntity<Object> applyJob(HttpServletRequest request, @RequestBody UUID idJob){

     var idCandidate = request.getAttribute("candidate_id");

     try {

          var result = this.applyJobCandidateUseCase.execute(UUID.fromString(idCandidate.toString()), idJob);
          
          return ResponseEntity.ok().body(result);

     } catch (Exception e) {
          return ResponseEntity.badRequest().body(e.getMessage());
     }


    }
}
