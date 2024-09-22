package com.apiaerolineas.cliente_service.service;

import com.apiaerolineas.cliente_service.entity.Cliente;
import com.apiaerolineas.cliente_service.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ClienteService implements IClienteService{

    @Autowired
    private IClienteRepository cliRep;

    @Override
    public List<Cliente> readClientes() {
        //Se trae a todos los clientes con estado true de la base de datos
        return cliRep.findAllTrue();
    }

    @Override
    public Cliente findCliente(Long id) {
        //Trae el cliente con el id especificado, por más que esté dado de baja
        return cliRep.findById(id).orElse(null);
    }

    @Override
    public String createCliente(Cliente cl) {

        //Validación de que los campos no estén vacíos
        if(!validarCamposVaciosAgregar(cl))
            return "Completar todos los campos";

        //Validación de que no se repita el DNI
        if(!validarDniRepetido(cl))
            return "Error: el dni ya se ha registrado anteriormente";

        //Se setea el estado en true
        cl.setEstado_Cli(true);

        //Se intenta guardar el cliente en la base de datos. Si no se puede, se arroja un mensaje de que hay un problema en la bd
        try {
            cliRep.save(cl);
            return "Cliente creado con éxito";
        }
        catch (Exception e){
            return "Error al crear el cliente: Problemas al conectar con la base de datos. " + e.getMessage();
        }

    }

    @Override
    public String deleteCliente(Long id) {

        Cliente cli = this.findCliente(id);

        //Si cli es null, significa que no existe
        //Si cli tiene estado false, quiere decir que fue eliminado anteriormente
        if(cli == null || !cli.getEstado_Cli()){
            return "El cliente ingresado no existe";
        }

        //Se intenta guardar el cliente en la base de datos. Si no se puede, se arroja un mensaje de que hay un problema en la bd
        cli.setEstado_Cli(false);
        try {
            cliRep.save(cli);
            return "Cliente eliminado con éxito";
        }
        catch(Exception e){
            return "Error al eliminar el cliente: Problema con la base de datos. " + e.getMessage();
        }
    }

    @Override
    public String updateCliente(Cliente cl, Long id) {

        //Se trae al cliente. Si no trae nada, quiere decir que el cliente no existe
        Cliente cliente = this.findCliente(id);
        if(cliente == null || !cliente.getEstado_Cli()){
            return "El cliente ingresado no existe";
        }

        //Validación de que los campos no estén vacíos
        if(!validarCamposVaciosModificar(cl))
            return "Completar todos los campos";

        //Asignamos las variables nuevas al cliente viejo
        //El dni no se lo asignamos porque no lo debe poder cambiar
        cliente.setEmail_Cli(cl.getEmail_Cli());
        cliente.setNombre_Cli(cl.getNombre_Cli());

        //Se intenta guardar el cliente en la base de datos. Si no se puede, se arroja un mensaje de que hay un problema en la bd
        try {
            cliRep.save(cliente);
            return "Cliente modificado con éxito";
        }
        catch(Exception e){
            return "Error al modificar el cliente: Problema con la base de datos. " + e.getMessage();
        }
    }

    public Boolean validarCamposVaciosAgregar(Cliente cl){
        return cl.getEmail_Cli() != null && cl.getDni_Cli() != null && cl.getNombre_Cli() != null;
    }

    public Boolean validarCamposVaciosModificar(Cliente cl){
        return cl.getEmail_Cli() != null && cl.getNombre_Cli() != null;
    }

    public Boolean validarDniRepetido(Cliente cl){
        return cliRep.findClienteByDni(cl.getDni_Cli()) == null;
    }

}
