package com.heinsohn.obl.model.dto.personas.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.ws.rs.FormParam;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequest {
    @FormParam("username")
    public String username;

    @FormParam("password")
    public String password;
}
