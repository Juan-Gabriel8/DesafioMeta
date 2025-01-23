package com.jgroup.DesafioMeta.repository;

import com.jgroup.DesafioMeta.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
