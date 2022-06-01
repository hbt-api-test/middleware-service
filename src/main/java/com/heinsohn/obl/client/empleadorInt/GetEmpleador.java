package com.heinsohn.obl.client.empleadorInt;


import com.heinsohn.obl.model.dto.AuthResponse;
import com.heinsohn.obl.model.dto.empleador.request.DataCertificationInDTO;
import com.heinsohn.obl.model.dto.empleador.request.DataEmployerInDto;
import com.heinsohn.obl.model.dto.personas.request.AuthRequest;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
@RegisterRestClient
@Path("/api")
public interface GetEmpleador {

    @POST
    @Path("/v1/login/token")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    AuthResponse getJwt(@BeanParam AuthRequest authRequest);

    @POST
    @Path("/v1/consulta")
    @Produces(MediaType.APPLICATION_JSON)
    JSONObject getEmployer(@Valid DataEmployerInDto dataEmployerInDto,
                           @HeaderParam(HttpHeaders.AUTHORIZATION) String authorization);


    @POST
    @Path("/v1/certificacion")
    @Produces(MediaType.APPLICATION_JSON)
    JSONObject getCertification(@Valid DataCertificationInDTO dataCertificationInDTO,
                                @HeaderParam(HttpHeaders.AUTHORIZATION) String authorization);


    @GET
    @Path("/info")
    @Produces(MediaType.APPLICATION_JSON)
    JSONObject getVersionServiceEmployer();


}
