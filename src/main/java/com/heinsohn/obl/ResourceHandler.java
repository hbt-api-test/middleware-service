package com.heinsohn.obl;

import com.heinsohn.obl.exception.ApiRequestException;
import com.heinsohn.obl.model.dto.empleador.request.DataCertificationInDTO;
import com.heinsohn.obl.model.dto.empleador.request.DataEmployerInDto;
import com.heinsohn.obl.model.dto.personas.request.CertificationPersonInDTO;
import com.heinsohn.obl.model.dto.personas.request.DataPersonInDto;
import com.heinsohn.obl.service.IEmpleadorService;
import com.heinsohn.obl.service.IPersonaService;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@ApplicationScoped
@Path("/segip")
@Slf4j
@Tag(name="Resource Handler", description="Servicio para la gestion de consulta de Persona y Empleador de la Gestora")
public class ResourceHandler {


    @Inject
    IPersonaService personaService;

    @Inject
    IEmpleadorService empleadorService;


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/consultar/persona")
    @Operation(
            operationId = "getPerson",
            summary = "Estado De La Persona",
            description = "Consulta que muestra el estado de la Persona en la Gestora"
    )
    @APIResponse(
            responseCode ="200",
            description = "Ejecutado exitosamente"
    )
    public Response getPerson(@RequestBody(
            description="Muestra los resultados obtenidos de consultar una persona por medio del servicio de la Gestora",
            required = true,
            content = @Content(schema = @Schema(implementation = DataPersonInDto.class))
            )@Valid DataPersonInDto dataPersonInDto) throws Exception {


            return Response.ok(new String(Base64.getUrlDecoder()
                    .decode(personaService.getPersona(dataPersonInDto).get("payload").toString()),
                    StandardCharsets.UTF_8)).build();


    }


    @POST
    @Path("/certificacion/persona")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            operationId = "getCertificationPerson",
            summary = "Certificacion Persona",
            description = "Muestra el tipo de archivo de la certificacion y la relacion de su identificacion en archivoBase64"
    )
    @APIResponse(
            responseCode ="200",
            description = "Ejecutado exitosamente"
    )
    public Response getCertificationPerson(@RequestBody(
            description="Muestra el tipo de archivo de la certificacion de la Persona y la relacion de su identificacion en archivoBase64",
            required = true,
            content = @Content(schema = @Schema(implementation = CertificationPersonInDTO.class))
    )@Valid CertificationPersonInDTO certificationPersonInDTO) throws Exception {

            return Response
                    .ok(personaService.getCertificacionPersona(certificationPersonInDTO))
                    .build();

    }

    @GET
    @Path("/info/persona")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            operationId = "getVersionServicePerson",
            summary = "Estado Del Servicio De Persona",
            description = "Estado de la disponibilidad del servicio, que gestiona la consulta de Persona"
    )
    @APIResponse(
            responseCode ="200",
            description = "Ejecutado exitosamente"
    )
    public Response getVersionServicePerson() throws Exception {
        return Response.ok(personaService.getVersionServicePersona()).build();
    }



    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/empleador")
    @Operation(
            operationId = "getEmployer",
            summary = "Estado Del Empleador",
            description = "Consulta que muestra el estado del Empleador en la Gestora"
    )
    @APIResponse(
            responseCode ="200",
            description = "Ejecutado exitosamente"
    )
    public Response getEmployer(@RequestBody(
            description="Muestra los resultados obtenidos de consultar un empleador por medio del servicio de la Gestora",
            required = true,
            content = @Content(schema = @Schema(implementation = DataEmployerInDto.class))
            )@Valid DataEmployerInDto dataEmployerInDto) throws Exception {

              /*return  Response.ok(new String(Base64.getUrlDecoder()
                      .decode(empleadorService.getEmpleador(dataEmployerInDto)
                              .get("payload").toString()
                      ),
                      StandardCharsets.UTF_8)).build();*/
        return Response.ok(empleadorService.getEmpleador(dataEmployerInDto)).build();

    }



    @POST
    @Path("/certificacion/empleador")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            operationId = "getCertificationEmployer",
            summary = "Certificacion Empleador",
            description = "Muestra el tipo de archivo de la certificacion y la relacion de su identificacion en archivoBase64"
    )
    @APIResponse(
            responseCode ="200",
            description = "Ejecutado exitosamente"
    )
    public Response getCertificationEmployer(@RequestBody(
            description="Muestra el tipo de archivo de la certificacion del Empleador y la relacion de su identificacion en archivoBase64",
            required = true,
            content = @Content(schema = @Schema(implementation = DataCertificationInDTO.class))
            )@Valid DataCertificationInDTO dataCertificationInDTO) throws ApiRequestException {

            return Response.ok(empleadorService.getCertificacionEmpleador(dataCertificationInDTO)).build();

    }



    @GET
    @Path("/info/empleador")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            operationId = "getVersionServiceEmployer",
            summary = "Estado Del Servicio Del Empleador",
            description = "Estado de la disponibilidad del servicio, que gestiona la consulta del Empleador"
    )
    @APIResponse(
            responseCode ="200",
            description = "Ejecutado exitosamente"
    )
    public Response getVersionServiceEmployer() throws ApiRequestException {
         return Response.ok(empleadorService.getVersionServicioEmpleador()).build();
    }


}