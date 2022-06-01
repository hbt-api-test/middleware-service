package com.heinsohn.obl.service;

import com.heinsohn.obl.model.dto.personas.request.CertificationPersonInDTO;
import com.heinsohn.obl.model.dto.personas.request.DataPersonInDto;
import com.nimbusds.jose.shaded.json.JSONObject;

public interface IPersonaService {

    JSONObject getPersona(DataPersonInDto dataPersonInDto) throws Exception;
    JSONObject getCertificacionPersona(CertificationPersonInDTO certificationPersonInDTO) throws Exception;
    JSONObject getVersionServicePersona() throws Exception;
    String getToken() throws Exception;

}
