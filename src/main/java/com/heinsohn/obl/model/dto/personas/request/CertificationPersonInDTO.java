package com.heinsohn.obl.model.dto.personas.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name= "Certificacion Persona", description = "Data Certificacion Persona")
public class CertificationPersonInDTO {

        @Schema (required = true)
        private String tipoCertificacion;
        @Schema (required = true)
        private String nroDocumento;
        private String complemento;
        private String fechaNacimiento;
}
