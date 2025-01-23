package com.jgroup.DesafioMeta.controller;

import com.jgroup.DesafioMeta.domain.Cliente;
import com.jgroup.DesafioMeta.domain.Endereco;
import com.jgroup.DesafioMeta.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    void deveCriarCliente() throws Exception {
        String clienteJson = """
            {
                "nome": "João Silva",
                "cpf": "12345678901",
                "endereco": {
                    "cep": "01001-000"
                }
            }
        """;

        mockMvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clienteJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("João Silva"))
                .andExpect(jsonPath("$.cpf").value("12345678901"));
    }

    @Test
    void deveRetornarClientePorId() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNome("Maria Silva");
        cliente.setCpf("98765432100");

        Endereco endereco = new Endereco();
        endereco.setCep("22222-222");
        cliente.setEndereco(endereco);

        clienteRepository.save(cliente);

        mockMvc.perform(get("/api/clientes/{id}", cliente.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Maria Silva"))
                .andExpect(jsonPath("$.cpf").value("98765432100"));
    }
}
