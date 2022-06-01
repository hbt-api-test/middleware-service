package com.heinsohn.obl.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.http.HttpStatus;

import javax.ws.rs.core.Response;
import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiException {

    private String type;
    private String title;
    private int httpStatus;
    //private Throwable throwable;
    private ZonedDateTime zonedDateTime;

}
