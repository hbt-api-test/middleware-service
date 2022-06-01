package com.heinsohn.obl.service.empleador;

import com.heinsohn.obl.client.empleadorInt.GetEmpleador;
import com.heinsohn.obl.exception.ApiRequestException;
import com.heinsohn.obl.model.dto.empleador.request.DataCertificationInDTO;
import com.heinsohn.obl.model.dto.empleador.request.DataEmployerInDto;
import com.heinsohn.obl.model.dto.personas.request.AuthRequest;
import com.heinsohn.obl.service.IEmpleadorService;
import com.heinsohn.obl.service.config.Utils;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.client.exception.ResteasyWebApplicationException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@Slf4j
public class EmpleadorService implements IEmpleadorService {

    @Inject
    @RestClient
    GetEmpleador getEmpleador;

    @ConfigProperty(name="com.heinsohn.obl.model.dto.request.username")
    String username;
    @ConfigProperty(name="com.heinsohn.obl.model.dto.request.password")
    String password;


    @Override
    public JSONObject getEmpleador(DataEmployerInDto dataEmployerInDto) throws Exception {
        try {
            log.info("Obteniendo el empleador, metodo getEmpleador() " + this.getClass().getSimpleName() + " datos " + dataEmployerInDto);
            Utils.disableSSLVerification();
            String token = getToken();
            //log.info("Token: " + token);
            JSONObject employer = getEmpleador.getEmployer(dataEmployerInDto, token);
            log.info("Empleador: " + employer);
            return employer;
        } catch (ResteasyWebApplicationException e) {
            String errorMesage = "Error al intentar obtener al empleador. "
                    /*.concat(dataEmployerInDto.toString())
                    .concat(" -> ")
                    .concat(e.getLocalizedMessage())*/;
            log.error(errorMesage);
            throw new ResteasyWebApplicationException(e);
        } catch (Exception ue){
            String errorMessage = "Error Exception: ".concat(ue.getLocalizedMessage());
            log.error(errorMessage);
            throw new ApiRequestException(errorMessage, ue);
        }
    }

    @Override
    public JSONObject getCertificacionEmpleador(DataCertificationInDTO dataCertificationInDTO) throws ApiRequestException {
        try{
            log.info("Obteniendo el certificado del empleador, metodo getCertificacionEmpleador() " + this.getClass().getSimpleName());
            Utils.disableSSLVerification();
            return getEmpleador.getCertification(dataCertificationInDTO, getToken());
        } catch (ResteasyWebApplicationException e) {
            String errorMesage = "Error al intentar obtener la certificación. "
                    .concat(dataCertificationInDTO.toString())
                    .concat(" -> ")
                    .concat(e.getLocalizedMessage());
            log.error(errorMesage);
            throw new ResteasyWebApplicationException(e);
        } catch (Exception ue){
            String errorMessage = "Error Exception: ".concat(ue.getLocalizedMessage());
            log.error(errorMessage);
            throw new ApiRequestException(errorMessage, ue);
        }
    }

    @Override
    public JSONObject getVersionServicioEmpleador() throws ApiRequestException {
        try {
            Utils.disableSSLVerification();
            return getEmpleador.getVersionServiceEmployer();
        }catch (ResteasyWebApplicationException e) {
            String errorMesage = "Error al intentar obtener la versión del servicio. "
                    .concat(" -> ")
                    .concat(e.getLocalizedMessage());
            log.error(errorMesage);
            throw new ResteasyWebApplicationException(e);
        } catch (Exception ue){
            String errorMessage = "Error Exception: ".concat(ue.getLocalizedMessage());
            log.error(errorMessage);
            throw new ApiRequestException(errorMessage, ue);
        }
    }

    @Override
    public String getToken() throws Exception {
        try {
            log.info("Obteniendo el token getToken() empleador " + this.getClass().getSimpleName());
            log.info("username: " + username + " password: " + password);
            return getEmpleador.getJwt(
                            new AuthRequest(username, password))
                    .getToken();
        } catch (ResteasyWebApplicationException e) {
            String errorMesage = "Error al intentar obtener el token del servicio. "
                    .concat(" -> ")
                    .concat(e.getLocalizedMessage());
            log.error(errorMesage);
            throw new ResteasyWebApplicationException(e);
        } catch (Exception ue){
            String errorMessage = "Error Exception: ".concat(ue.getLocalizedMessage());
            log.error(errorMessage);
            throw new ApiRequestException(errorMessage, ue);
        }
    }
}
