package com.jgroup.DesafioMeta.service;

import com.jgroup.DesafioMeta.domain.Cliente;
import com.jgroup.DesafioMeta.repository.ClienteRepository;
import com.jgroup.DesafioMeta.service.exception.ClienteNaoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final EnderecoService enderecoService;

    public ClienteService(ClienteRepository clienteRepository, EnderecoService enderecoService) {
        this.clienteRepository = clienteRepository;
        this.enderecoService = enderecoService;
    }

    public Cliente criarCliente(Cliente cliente) {
        if (cliente.getEndereco() != null && cliente.getEndereco().getCep() != null) {
            cliente.setEndereco(enderecoService.buscarEnderecoPorCep(cliente.getEndereco().getCep()));
        }
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .map(Optional::of)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado com ID: " + id));
    }

    public Cliente atualizarCliente(Long id, Cliente clienteAtualizado) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado com ID: " + id));

        clienteAtualizado.setId(clienteExistente.getId());

        if (clienteAtualizado.getEndereco() != null && clienteAtualizado.getEndereco().getCep() != null) {
            clienteAtualizado.setEndereco(enderecoService.buscarEnderecoPorCep(clienteAtualizado.getEndereco().getCep()));
        }
        return clienteRepository.save(clienteAtualizado);
    }

    public void excluirCliente(Long id) {
     if (!clienteRepository.existsById(id)) {
         throw new ClienteNaoEncontradoException("Cliente não encontrado com ID: " + id);
     }
     clienteRepository.deleteById(id);
    }
}
