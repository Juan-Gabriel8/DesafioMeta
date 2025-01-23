package com.jgroup.DesafioMeta.service;

import com.jgroup.DesafioMeta.domain.Cliente;
import com.jgroup.DesafioMeta.domain.Endereco;
import com.jgroup.DesafioMeta.repository.ClienteRepository;
import com.jgroup.DesafioMeta.service.exception.ClienteNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ClienteServiceTest {

    private ClienteService clienteService;
    private ClienteRepository clienteRepository;
    private EnderecoService enderecoService;

    @BeforeEach
    void setUp() {
        clienteRepository = Mockito.mock(ClienteRepository.class);
        enderecoService = Mockito.mock(EnderecoService.class);
        clienteService = new ClienteService(clienteRepository, enderecoService);
    }

    @Test
    void deveCriarClienteComEnderecoValido() {
        Endereco enderecoMock = new Endereco();
        enderecoMock.setCep("01001-000");

        Cliente clienteMock = new Cliente();
        clienteMock.setNome("João Silva");
        clienteMock.setCpf("12345678901");
        clienteMock.setEndereco(enderecoMock);

        when(enderecoService.buscarEnderecoPorCep("01001-000")).thenReturn(enderecoMock);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteMock);

        Cliente clienteSalvo = clienteService.criarCliente(clienteMock);

        assertNotNull(clienteSalvo);
        assertEquals("João Silva", clienteSalvo.getNome());
        verify(clienteRepository, times(1)).save(clienteMock);
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoForEncontrado() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.empty());

        ClienteNaoEncontradoException exception = assertThrows(ClienteNaoEncontradoException.class, () -> {
            clienteService.buscarPorId(1L);
        });

        assertEquals("Cliente não encontrado com ID: 1", exception.getMessage());
    }
}
