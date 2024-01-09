package br.com.thiagopratti.gestao_vagas.modules.candidate;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CandidateEntity {
    
    private UUID id;
    private String name;
    
    @NotBlank

    @Pattern(regexp = "\\S+", message = "The username field must not contain spaces")
    private String username;


    @Email(message = "The E-mail field must have a valid e-mail")
    private String email;


    @Length(min = 8, max = 100, message = "The password must contain between 8 and 100 characters")
    private String password;

    private String description;
    private String curriculum;
}
