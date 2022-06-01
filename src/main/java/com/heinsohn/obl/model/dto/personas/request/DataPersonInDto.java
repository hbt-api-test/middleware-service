package com.heinsohn.obl.model.dto.personas.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name= "Persona", description = "Data Persona")
public class DataPersonInDto implements Serializable {

    @Schema (required = true)
    private String tipoProceso;
    @Schema (required = true)
    private String tipoDocumento;
    @Schema (required = true)
    private String nroDocumento;
    private String complemento;
    private String fechaNacimiento;
    private String primerApellido;
    private String segundoApellido;
    private String nombres;
}
