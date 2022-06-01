package com.heinsohn.obl.service;


import com.heinsohn.obl.exception.ApiRequestException;
import com.heinsohn.obl.model.dto.empleador.request.DataCertificationInDTO;
import com.heinsohn.obl.model.dto.empleador.request.DataEmployerInDto;
import com.nimbusds.jose.shaded.json.JSONObject;

public interface IEmpleadorService {

    JSONObject getEmpleador(DataEmployerInDto dataEmployerInDto) throws Exception;
    JSONObject getCertificacionEmpleador(DataCertificationInDTO dataCertificationInDTO) throws ApiRequestException;
    JSONObject getVersionServicioEmpleador() throws ApiRequestException;
    String getToken() throws Exception;

}
