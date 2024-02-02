package br.com.thiagopratti.gestao_vagas.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateJobDTO {

  @Schema(example = "Your Schema")
  private String description;
  
  @Schema(example = "Your Schema")
  private String benefits;

  @Schema(example = "Your Schema")
  private String level;
}