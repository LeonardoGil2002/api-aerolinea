package com.apiaerolineas.cliente_service.controller;

import com.apiaerolineas.cliente_service.entity.Cliente;
import com.apiaerolineas.cliente_service.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private IClienteService cliSer;

    @GetMapping("/")
    public List<Cliente> readClientes(){
        return cliSer.readClientes();
    }

    @GetMapping("/{id}")
    public Cliente findCliente(@PathVariable Long id){
        return cliSer.findCliente(id);
    }

    @PostMapping("/alta")
    public String createCliente(@RequestBody Cliente cl){
        return cliSer.createCliente(cl);
    }

    @DeleteMapping("/baja/{id}")
    public String deleteCliente(@PathVariable Long id){
        return cliSer.deleteCliente(id);
    }

    @PutMapping("/modificacion/{id}")
    public String updateCliente(@RequestBody Cliente cl, @PathVariable Long id){
        return cliSer.updateCliente(cl, id);
    }

}
