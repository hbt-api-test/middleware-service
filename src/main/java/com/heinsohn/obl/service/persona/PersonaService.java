package com.heinsohn.obl.service.persona;

import com.heinsohn.obl.client.personaInt.GetPersona;
import com.heinsohn.obl.exception.ApiRequestException;
import com.heinsohn.obl.model.dto.personas.request.AuthRequest;
import com.heinsohn.obl.model.dto.personas.request.CertificationPersonInDTO;
import com.heinsohn.obl.model.dto.personas.request.DataPersonInDto;
import com.heinsohn.obl.service.IPersonaService;
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
public class PersonaService implements IPersonaService {

    @RestClient
    @Inject
    GetPersona getPersona;

    @ConfigProperty(name="com.heinsohn.obl.model.dto.request.username")
    String username;
    @ConfigProperty(name="com.heinsohn.obl.model.dto.request.password")
    String password;


    @Override
    public String getToken() throws Exception {
        try {
            log.info("Obteniendo el token getToken() personas " + this.getClass().getSimpleName());
            return getPersona.getJwt(
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


    @Override
    public JSONObject getPersona(DataPersonInDto dataPersonInDto) throws Exception {
        try {
            log.info("Obteniendo la persona, metodo getPersona() " + this.getClass().getSimpleName());
            Utils.disableSSLVerification();
            return getPersona.getPerson(dataPersonInDto, getToken());
        } catch (ResteasyWebApplicationException e) {
            String errorMesage = "Error al intentar obtener la persona. "
                    .concat(dataPersonInDto.toString())
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
    public JSONObject getCertificacionPersona(CertificationPersonInDTO certificationPersonInDTO) throws Exception {
        try{
            log.info("Obteniendo el certificado de la persona, metodo getCertificacionPersona() " + this.getClass().getSimpleName());
            Utils.disableSSLVerification();
            return getPersona.getCertificationPerson(certificationPersonInDTO, getToken());
        } catch (ResteasyWebApplicationException e) {
            String errorMesage = "Error al intentar obtener la certificación. "
                    .concat(certificationPersonInDTO.toString())
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
    public JSONObject getVersionServicePersona() throws Exception {
        try {
            Utils.disableSSLVerification();
            return getPersona.GetVersionServicePerson();
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


}
