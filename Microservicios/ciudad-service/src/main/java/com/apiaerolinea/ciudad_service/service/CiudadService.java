package com.apiaerolinea.ciudad_service.service;

import com.apiaerolinea.ciudad_service.dto.CiudadDTO;
import com.apiaerolinea.ciudad_service.dto.Pais;
import com.apiaerolinea.ciudad_service.entity.Ciudad;
import com.apiaerolinea.ciudad_service.repository.ICiudadRepository;
import com.apiaerolinea.ciudad_service.repository.PaisAPIClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CiudadService implements ICiudadService{

    @Autowired
    private ICiudadRepository ciuRep;

    @Autowired
    private PaisAPIClient paisapi;

    @Override
    public List<CiudadDTO> readCiudades() {

        //Se crea un List donde obtengo todas las ciudades con estado en true
        List<Ciudad> listaCiudades = ciuRep.findAllTrue();
        //Se crea un listado para ciudadesDTO para poder enviar las ciudades en el formato deseado
        List<CiudadDTO> listaCiudadesDTO = new ArrayList<>();

        //Se recorre el List con todas las ciudades con estado en true
        for(Ciudad c : listaCiudades){

            //Se crea un objeto CiudadDTO para almacenar la información de la ciudad
            CiudadDTO cDto = new CiudadDTO();
            //Se trae un objeto Pais con el id_Pa igual al id_Pa_Ciu de la ciudad
            Pais pa = paisapi.findPais(c.getId_Pa_Ciu());

            //Se setean los atributos de la CiudadDTO
            cDto.setId_Ciu(c.getId_Ciu());
            cDto.setId_Pa_Ciu(c.getId_Pa_Ciu());
            cDto.setNombre_Ciu(c.getNombre_Ciu());
            cDto.setNombre_Pa(pa.getNombre_Pa());
            cDto.setEstado_Ciu(c.getEstado_Ciu());

            //Se agrega la CiudadDTO al listado
            listaCiudadesDTO.add(cDto);

        }

        //Se retorna el listado de CiudadesDTO
        return listaCiudadesDTO;
    }

    @Override
    public CiudadDTO findCiudad(Long id) {

        //Se busca la ciudad con el id ingresado. Si no existe devuelve null
        Ciudad ciu = ciuRep.findById(id).orElse(null);

        //Si el objeto ciu es igual a null, quiere decir que no existe
        if(ciu == null)
            return null;

        //Se trae un objeto Pais con el id_Pa igual al id_Pa_Ciu de la ciudad
        Pais pa = paisapi.findPais(ciu.getId_Pa_Ciu());

        //Se retorna una CiudadDTO con todos los atributos asignados
        return new CiudadDTO(id, ciu.getNombre_Ciu(), ciu.getId_Pa_Ciu(), pa.getNombre_Pa(), ciu.getEstado_Ciu());

    }

    @Override
    public String createCiudad(Ciudad ci) {

        //Validación de campos vacíos
        if(ci.getNombre_Ciu() == null || ci.getId_Pa_Ciu() == null)
            return "Debe completar todos los campos";

        //Se trae un objeto Pais con el id_Pa igual al id_Pa_Ciu de la ciudad
        Pais pa = paisapi.findPais(ci.getId_Pa_Ciu());

        //Validación de existencia del id de país. No puede no existir ni estar dado de baja
        if(pa == null || !pa.getEstado_Pa())
            return "No existe el país ingresado";

        //Se asigna el estado en true
        ci.setEstado_Ciu(true);

        //Se intenta añadir la ciudad a la base de datos. Si no puede, arroja una excepción
        try{
            ciuRep.save(ci);
            return "Se ha guardado la ciudad exitosamente";
        }
        catch(Exception e){
            return "Error al crear la ciudad. No se pudo conectar con la base de datos " + e.getMessage();
        }

    }

    @Override
    public String updateCiudad(Ciudad ci, Long id) {

        //Validación de campos vacíos
        if(ci.getNombre_Ciu() == null || ci.getId_Pa_Ciu() == null)
            return "Debe completar todos los campos";

        //Se trae la ciudad buscada
        Ciudad ciudadBuscada = ciuRep.findById(id).orElse(null);

        //Validación de existencia del id de ciudad. No puede no existir ni estar dado de baja
        if(ciudadBuscada == null || !ciudadBuscada.getEstado_Ciu())
            return "No existe la ciudad ingresada";

        //Se trae un objeto Pais con el id_Pa igual al id_Pa_Ciu de la ciudad
        Pais pa = paisapi.findPais(ci.getId_Pa_Ciu());

        //Validación de existencia del id de país. No puede no existir ni estar dado de baja
        if(pa == null || !pa.getEstado_Pa())
            return "No existe el país ingresado";

        //Seteamos los atributos a modificar a la ciudad buscada
        ciudadBuscada.setNombre_Ciu(ci.getNombre_Ciu());
        ciudadBuscada.setId_Pa_Ciu(ci.getId_Pa_Ciu());

        //Se intenta modificar la ciudad a la base de datos. Si no puede, arroja una excepción
        try{
            ciuRep.save(ciudadBuscada);
            return "Se ha modificado la ciudad exitosamente";
        }
        catch(Exception e){
            return "Error al modificar la ciudad. No se pudo conectar con la base de datos " + e.getMessage();
        }

    }

    @Override
    public String deleteCiudad(Long id) {

        //Se trae la ciudad
        Ciudad ciu = ciuRep.findById(id).orElse(null);

        //Se verifica que la ciudad ingresada exista
        if(ciu == null || !ciu.getEstado_Ciu())
            return "La ciudad ingresada no existe";

        //Actualizo el estado a false
        ciu.setEstado_Ciu(false);

        //Intento actualizar en la base de datos. Caso contrario, arrojo una excepción
        try{
            ciuRep.save(ciu);
            return "Se ha eliminado la ciudad exitosamente";
        }
        catch(Exception e){
            return "Error al eliminar la ciudad. No se pudo conectar con la base de datos " + e.getMessage();
        }

    }
}
