package com.jgroup.DesafioMeta.controller;

import com.jgroup.DesafioMeta.domain.Endereco;
import com.jgroup.DesafioMeta.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {

    @Autowired
    EnderecoService enderecoService;

    @GetMapping("/cep/{cep}")
    public ResponseEntity<Endereco> buscarEnderecoPorCep(@PathVariable String cep) {
        Endereco endereco = enderecoService.buscarEnderecoPorCep(cep);
        return ResponseEntity.ok(endereco);
    }
}
