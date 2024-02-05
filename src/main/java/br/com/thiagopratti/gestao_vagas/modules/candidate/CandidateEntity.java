package br.com.thiagopratti.gestao_vagas.modules.candidate;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "candidate")
public class CandidateEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Schema(example = "Daniel", requiredMode = RequiredMode.REQUIRED, description = "Candidate's Name")
    private String name;
    
    @NotBlank

    @Pattern(regexp = "\\S+", message = "The username field must not contain spaces")
    @Schema(example = "Dan", requiredMode = RequiredMode.REQUIRED, description = "Candidate's Username")
    private String username;


    @Email(message = "The E-mail field must have a valid e-mail")
    @Schema(example = "daniel@gmail.com", requiredMode = RequiredMode.REQUIRED, description = "E-mail do candidato")
    private String email;


    @Length(min = 8, max = 100, message = "The password must contain between 8 and 100 characters")
    @Schema(example = "********", requiredMode = RequiredMode.REQUIRED, description = "Candidate's Password")
    private String password;

    @Schema(example = "Say More about Yourself", description = "brief description of the candidate")
    private String description;

    private String curriculum;
    
    @CreationTimestamp
    private LocalDateTime createdAt;
}
