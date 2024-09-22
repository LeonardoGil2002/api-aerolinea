package com.apiaerolinea.pais_service.service;

import com.apiaerolinea.pais_service.entity.Pais;
import com.apiaerolinea.pais_service.repository.IPaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaisService implements IPaisService{

    @Autowired
    private IPaisRepository paiRep;

    @Override
    public List<Pais> readPaises() {
        return paiRep.findAllTrue();
    }

    @Override
    public Pais findPais(Long id) {
        return paiRep.findById(id).orElse(null);
    }

    @Override
    public String createPais(Pais pa) {

        //Validación de campos nulos
        if(pa.getNombre_Pa() == null){
            return "Debe completar todos los campos";
        }

        //Seteo el estado en true
        pa.setEstado_Pa(true);

        //Se intenta cargar el país a la base de datos. Si no puede tira una excepción
        try{
            paiRep.save(pa);
            return "País creado con éxito";
        }
        catch(Exception e){
            return "Error al crear el país: Problemas al conectar con la base de datos. " + e.getMessage();
        }

    }

    @Override
    public String updatePais(Pais pa, Long id) {

        //Buscamos el país con la id ingresada
        Pais paisBuscado = this.findPais(id);

        //Si el paisBuscado es null, quiere decir que no existe, y si tiene estado false, que ya se ha borrado
        if(paisBuscado == null || !paisBuscado.getEstado_Pa())
            return "El pais ingresado no existe";

        //Validación de campos vacíos
        if(pa.getNombre_Pa() == null)
            return "Debe completar todos los campos";


        //Se actualizan los campos de paisBuscado con los atributos a modificar
        paisBuscado.setNombre_Pa(pa.getNombre_Pa());

        //Se intenta modificar el país a la base de datos. Si no puede tira una excepción
        try{
            paiRep.save(paisBuscado);
            return "País modificado con éxito";
        }
        catch(Exception e){
            return "Error al modificar el país: Problemas al conectar con la base de datos. " + e.getMessage();
        }

    }

    @Override
    public String deletePais(Long id) {

        //Buscamos el país con la id ingresada
        Pais paisBuscado = this.findPais(id);

        //Si el paisBuscado es null, quiere decir que no existe, y si tiene estado false, que ya se ha borrado
        if(paisBuscado == null || !paisBuscado.getEstado_Pa())
            return "El pais ingresado no existe";

        //Se actualiza el campo del país a false
        paisBuscado.setEstado_Pa(false);

        //Se intenta actualizar el país a la base de datos. Si no puede tira una excepción
        try{
            paiRep.save(paisBuscado);
            return "País eliminado con éxito";
        }
        catch(Exception e){
            return "Error al eliminar el país: Problemas al conectar con la base de datos. " + e.getMessage();
        }

    }
}
