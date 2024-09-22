package com.apiaerolinea.modelo_service.service;

import com.apiaerolinea.modelo_service.entity.Modelo;
import com.apiaerolinea.modelo_service.repository.IModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModeloService implements IModeloService{

    @Autowired
    private IModeloRepository modRep;

    @Override
    public List<Modelo> readModelos() {
        return modRep.findAllTrue();
    }

    @Override
    public Modelo findModelo(Long id) {
        //Intenta traer un modelo con la id solicitada. Si no lo encuentra devuelve null
        return modRep.findById(id).orElse(null);
    }

    @Override
    public String createModelo(Modelo mod) {

        //Validación de que el campo a ingresar no esté vacío
        if(mod.getNombre_Mo() == null)
            return "Completar todos los campos";

        //Se setea el estado en true
        mod.setEstado_Mo(true);

        //Se intenta guardar el modelo en la base de datos. Si no se puede, devuelve una excepción
        try{
            modRep.save(mod);
            return "Modelo creado con éxito";
        }
        catch (Exception e){
            return "Error al crear el modelo: Problemas al conectar con la base de datos. " + e.getMessage();
        }

    }

    @Override
    public String updateModelo(Modelo mod, Long id) {

        //Traigo el modelo según el id ingresado
        Modelo mo = this.findModelo(id);

        //Si el modelo traído es null, quiere decir que no existe, y si tiene estado en false, quiere decir que fue eliminado
        if(mo == null || !mo.getEstado_Mo())
            return "El id de modelo ingresado no existe";

        //Validación de que el campo a ingresar no esté vacío
        if(mod.getNombre_Mo() == null)
            return "Completar todos los campos";

        //Le seteamos los atributos nuevos al modelo viejo (así conserva el estado y el ID)
        mo.setNombre_Mo(mod.getNombre_Mo());

        //Se intenta guardar el modelo en la base de datos. Si no se puede, devuelve una excepción
        try{
            modRep.save(mod);
            return "Modelo modificado con éxito";
        }
        catch (Exception e){
            return "Error al modificar el modelo: Problemas al conectar con la base de datos. " + e.getMessage();
        }

    }

    @Override
    public String deleteModelo(Long id) {

        //Traigo el modelo según el id ingresado
        Modelo mo = this.findModelo(id);

        //Si el modelo traído es null, quiere decir que no existe, y si tiene estado en false, quiere decir que fue eliminado
        if(mo == null || !mo.getEstado_Mo())
            return "El id de modelo ingresado no existe";

        //Ponemos el estado en falso (baja lógica)
        mo.setEstado_Mo(false);

        //Se intenta guardar el modelo en la base de datos. Si no se puede, devuelve una excepción
        try{
            modRep.save(mo);
            return "Modelo eliminado con éxito";
        }
        catch (Exception e){
            return "Error al eliminar el modelo: Problemas al conectar con la base de datos. " + e.getMessage();
        }

    }

}
