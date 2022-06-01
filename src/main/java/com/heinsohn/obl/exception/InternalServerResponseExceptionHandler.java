package com.heinsohn.obl.exception;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR;

@Provider
@Slf4j
public class InternalServerResponseExceptionHandler implements ExceptionMapper<ApiRequestException> {


    @ConfigProperty(name="com.heinsohn.obl.model.error.unavailable")
    String unavailable;
    @ConfigProperty(name="com.heinsohn.obl.model.error.internal.server")
    String internalServerError;


    @Override
    public Response toResponse(ApiRequestException e) {
        log.error("InternalServerResponseExceptionHandler: " + e.getLocalizedMessage() + " causa: " + e.getCause());
        return Response.status(SC_INTERNAL_SERVER_ERROR)
                .entity(new ApiException(unavailable,
                        internalServerError,
                        SC_INTERNAL_SERVER_ERROR,
                        ZonedDateTime.now(ZoneId.of("Z"))))
                .build();
    }
}
