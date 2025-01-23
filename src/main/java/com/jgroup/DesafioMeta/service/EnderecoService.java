package com.jgroup.DesafioMeta.service;

import com.jgroup.DesafioMeta.domain.Endereco;
import com.jgroup.DesafioMeta.integration.response.ViaCepResponse;
import com.jgroup.DesafioMeta.integration.viacep.ViaCepClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    ViaCepClient viaCepClient;

    public EnderecoService(ViaCepClient viaCepClient) {
        this.viaCepClient = viaCepClient;
    }

    public Endereco buscarEnderecoPorCep(String cep) {
        ViaCepResponse response = viaCepClient.buscarEnderecoPorCep(cep);

        if (response == null || response.getCep() == null) {
            throw new IllegalArgumentException("CEP inválido ou não encontrado: " + cep);
        }

        Endereco endereco = new Endereco();
        endereco.setCep(response.getCep());
        endereco.setLogradouro(response.getLogradouro());
        endereco.setBairro(response.getBairro());
        endereco.setCidade(response.getLocalidade());
        endereco.setEstado(response.getUf());
        return endereco;
    }
}
