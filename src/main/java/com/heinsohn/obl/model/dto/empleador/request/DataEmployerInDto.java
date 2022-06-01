package com.heinsohn.obl.model.dto.empleador.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name= "Empleador", description = "Data Empleador")
public class DataEmployerInDto {

    @Schema (required = true)
    private String tipoProceso;
    @Schema (required = true)
    private String tipoIdentificacion;
    @Schema (required = true)
    private String numeroIdentificacion;
    private String entidad;
    private int da;
    private int ue;
}
