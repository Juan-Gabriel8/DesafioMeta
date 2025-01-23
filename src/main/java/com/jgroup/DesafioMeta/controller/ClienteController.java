package com.jgroup.DesafioMeta.controller;

import com.jgroup.DesafioMeta.domain.Cliente;
import com.jgroup.DesafioMeta.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;


    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente) {
      Cliente clienteSalvo = clienteService.criarCliente(cliente);
      return ResponseEntity.ok(clienteSalvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente clienteAtualizado = clienteService.atualizarCliente(id, cliente);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> excluirCliente(@PathVariable Long id) {
        clienteService.excluirCliente(id);
        return ResponseEntity.noContent().build();
    }
}
