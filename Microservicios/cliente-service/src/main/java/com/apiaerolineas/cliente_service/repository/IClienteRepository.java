package com.apiaerolineas.cliente_service.repository;

import com.apiaerolineas.cliente_service.entity.Cliente;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT cl FROM Cliente cl WHERE cl.dni_Cli=:dni")
    Cliente findClienteByDni(@Param("dni") String dni);

    @Query("SELECT cl FROM Cliente cl WHERE cl.estado_Cli=true")
    List<Cliente> findAllTrue();

}
