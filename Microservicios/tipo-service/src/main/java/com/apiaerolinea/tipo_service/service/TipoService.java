package com.apiaerolinea.tipo_service.service;

import com.apiaerolinea.tipo_service.entity.Tipo;
import com.apiaerolinea.tipo_service.repository.ITipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoService implements ITipoService{

    @Autowired
    private ITipoRepository tipRep;

    @Override
    public List<Tipo> readTipos() {
        return tipRep.findAllTrue();
    }

    @Override
    public Tipo findTipo(Long id) {
        return tipRep.findById(id).orElse(null);
    }

    @Override
    public String createTipo(Tipo tip) {

        //Validación de campos vacíos
        if(tip.getNombre_Ti() == null){
            return "Debe completar todos los campos";
        }

        //Seteo el estado en true
        tip.setEstado_Ti(true);

        //Se intenta guardar el tipo en la base de datos. Si no se puede, devuelve una excepción
        try{
            tipRep.save(tip);
            return "Tipo de avión creado con éxito";
        }
        catch (Exception e){
            return "Error al crear el tipo de avión: Problemas al conectar con la base de datos. " + e.getMessage();
        }

    }

    @Override
    public String deleteTipo(Long id) {

        //Traigo el tipo de avión con el id ingresado
        Tipo tip = this.findTipo(id);

        //Verifico si el tipo de avión ingresado existe o ya está eliminado
        if(tip == null || !tip.getEstado_Ti()){
            return "El tipo de avión ingresado no existe";
        }

        //Seteo el estado en false
        tip.setEstado_Ti(false);

        //Se intenta guardar el tipo en la base de datos. Si no se puede, devuelve una excepción
        try{
            tipRep.save(tip);
            return "Tipo de avión eliminado con éxito";
        }
        catch (Exception e){
            return "Error al eliminar el tipo de avión: Problemas al conectar con la base de datos. " + e.getMessage();
        }

    }

    @Override
    public String updateTipo(Tipo tip, Long id) {

        //Traigo el tipo de avión con el id ingresado
        Tipo tipo = this.findTipo(id);

        //Verifico si el tipo de avión ingresado existe o ya está eliminado
        if(tipo == null || !tipo.getEstado_Ti()){
            return "El tipo de avión ingresado no existe";
        }

        //Validación de campos vacíos
        if(tip.getNombre_Ti() == null){
            return "Debe completar todos los campos";
        }

        //Seteo los atributos del tipo de avión viejo con los del tipo nuevo así se conservan los atributos clave
        tipo.setNombre_Ti(tip.getNombre_Ti());

        //Se intenta guardar el tipo en la base de datos. Si no se puede, devuelve una excepción
        try{
            tipRep.save(tip);
            return "Tipo de avión modificado con éxito";
        }
        catch (Exception e){
            return "Error al modificar el tipo de avión: Problemas al conectar con la base de datos. " + e.getMessage();
        }


    }
}
