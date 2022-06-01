package com.heinsohn.obl.model.dto.empleador.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name= "Certificacion Empleador", description = "Data Certificacion Empleador")
public class DataCertificationInDTO {

    @Schema (required = true)
    private String tipoCertificacion;
    @Schema (required = true)
    private String tipoIdentificacion;
    @Schema (required = true)
    private String numeroIdentificacion;
    private String entidad;
	private Integer da;
    private Integer ue;
}
