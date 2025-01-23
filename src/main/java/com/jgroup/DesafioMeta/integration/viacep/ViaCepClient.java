package com.jgroup.DesafioMeta.integration.viacep;

import com.jgroup.DesafioMeta.integration.response.ViaCepResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ViaCepClient {

    private static final String BASE_URL = "https://viacep.com.br/ws/{cep}/json/";

    public ViaCepResponse buscarEnderecoPorCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(BASE_URL, ViaCepResponse.class, cep);
    }
}
