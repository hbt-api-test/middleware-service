package com.heinsohn.obl.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
      private String token;
}
