package com.apiaerolineas.cliente_service.service;

import com.apiaerolineas.cliente_service.entity.Cliente;

import java.util.List;

public interface IClienteService {
    public List<Cliente> readClientes();
    public Cliente findCliente(Long id);
    public String createCliente(Cliente cl);
    public String deleteCliente(Long id);
    public String updateCliente(Cliente cl, Long id);
}
