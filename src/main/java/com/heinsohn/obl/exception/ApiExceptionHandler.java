package com.heinsohn.obl.exception;


import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.client.exception.ResteasyWebApplicationException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.time.ZoneId;
import java.time.ZonedDateTime;


import static org.apache.http.HttpStatus.*;


@Provider
@Slf4j
public class ApiExceptionHandler implements ExceptionMapper<ResteasyWebApplicationException> {

    @ConfigProperty(name="com.heinsohn.obl.model.error.unavailable")
    String unavailable;
    @ConfigProperty(name="com.heinsohn.obl.model.error.forbidden")
    String forbidden;
    @ConfigProperty(name="com.heinsohn.obl.model.error.not-found")
    String notFound;
    @ConfigProperty(name="com.heinsohn.obl.model.error.internal.server")
    String internalServerError;



    private String title = "";
    private String type = "";


    @Override
    public Response toResponse(ResteasyWebApplicationException e) {
        log.error("ApiExceptionHandler: " + e.getMessage());
        type = unavailable;

        switch (e.getResponse().getStatus()){
            case SC_NOT_FOUND:
                title = notFound;
                break;
            case SC_FORBIDDEN:
                title = forbidden;
                break;
            case SC_INTERNAL_SERVER_ERROR:
                title = internalServerError;
                break;
            default:
                title = e.getLocalizedMessage();
                break;
        }


        return Response.status(e.getResponse().getStatus())
                .entity(new ApiException(type, title,
                        e.getResponse().getStatus(),
                        ZonedDateTime.now(ZoneId.of("Z"))))
                .build();
    }
}
