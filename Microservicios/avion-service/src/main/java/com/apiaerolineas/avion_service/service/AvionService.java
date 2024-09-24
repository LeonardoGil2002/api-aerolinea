package com.apiaerolineas.avion_service.service;

import com.apiaerolineas.avion_service.dto.AvionDTO;
import com.apiaerolineas.avion_service.dto.Modelo;
import com.apiaerolineas.avion_service.dto.Tipo;
import com.apiaerolineas.avion_service.entity.Avion;
import com.apiaerolineas.avion_service.repository.IAvionRepository;
import com.apiaerolineas.avion_service.repository.ModeloAPIClient;
import com.apiaerolineas.avion_service.repository.TipoAPIClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AvionService implements IAvionService{

    @Autowired
    private IAvionRepository avRep;

    @Autowired
    private ModeloAPIClient modeloapi;

    @Autowired
    private TipoAPIClient tipoapi;

    @Override
    public List<AvionDTO> readAviones() {

        //Creo una lista con todos los aviones que no están fuera de servicio
        List<Avion> listaAviones = avRep.findAllNoFueraDeServicio();

        //Creo una lista vacía de aviones con el formato solicitado. Luego serán rellenados
        List<AvionDTO> listaAvionesDTO = new ArrayList<>();

        //Recorro la lista de aviones
        for(Avion a : listaAviones){

            //Creamos un avionDTO que tiene el formato que deseamos para mostrar
            AvionDTO aDTO = new AvionDTO();

            //Traemos los objetos tipo y modelo de avión según los id almacenados en el avión
            Tipo ti = tipoapi.findTipo(a.getId_Ti_Av());
            Modelo mo = modeloapi.findModelo(a.getId_Mo_Av());

            //Seteamos todos los campos
            aDTO.setId_Av(a.getId_Av());
            aDTO.setId_Mo_Av(a.getId_Mo_Av());
            aDTO.setId_Ti_Av(a.getId_Ti_Av());
            aDTO.setNombre_Mo(mo.getNombre_Mo());
            aDTO.setNombre_Ti(ti.getNombre_Ti());
            aDTO.setCapacidadTotal_Av(a.getCapacidadTotal_Av());
            aDTO.setEstado_Av(a.getEstado_Av());

            //Agregamos el avionDTO a la lista a mostrar
            listaAvionesDTO.add(aDTO);

        }

        return listaAvionesDTO;

    }

    @Override
    public AvionDTO findAvion(Long id) {

        //Traemos el avión de la base de datos
        Avion a = avRep.findById(id).orElse(null);

        //Validación de avión inexistentetr
        if(a == null)
            return null;

        //Creamos un avionDTO que tiene el formato que deseamos para mostrar
        AvionDTO aDTO = new AvionDTO();

        //Traemos los objetos tipo y modelo de avión según los id almacenados en el avión
        Tipo ti = tipoapi.findTipo(a.getId_Ti_Av());
        Modelo mo = modeloapi.findModelo(a.getId_Mo_Av());

        //Seteamos todos los campos
        aDTO.setId_Av(a.getId_Av());
        aDTO.setId_Mo_Av(a.getId_Mo_Av());
        aDTO.setId_Ti_Av(a.getId_Ti_Av());
        aDTO.setNombre_Mo(mo.getNombre_Mo());
        aDTO.setNombre_Ti(ti.getNombre_Ti());
        aDTO.setCapacidadTotal_Av(a.getCapacidadTotal_Av());
        aDTO.setEstado_Av(a.getEstado_Av());

        return aDTO;

    }

    @Override
    public String createAvion(Avion av) {

        //Validación de campos vacíos
        if(!validarCamposVacios(av))
            return "Debe completar todos los campos";

        //Validación tipo existente
        if(!validarTipoExistente(av.getId_Ti_Av()))
            return "No existe el tipo de avión ingresado";

        //Validación modelo existente
        if(!validarModeloExistente(av.getId_Mo_Av()))
            return "No existe el modelo de avión ingresado";

        //Seteamos el estado en "Disponible"
        av.setEstado_Av("Disponible");

        //Intentamos subirlo a la base de datos. Si no se puede, arrojará una excepción
        try{
            avRep.save(av);
            return "Se ha guardado el avión exitosamente";
        }
        catch (Exception e){
            return "Error: no se ha podido conectar con la base de datos. " + e.getMessage();
        }

    }

    @Override
    public String deleteAvion(Long id) {

        //Validación avión existente
        Avion avionEliminar = avRep.findById(id).orElse(null);
        if(avionEliminar == null || avionEliminar.getEstado_Av().equals("Fuera de servicio"))
            return "No existe el avión ingresado";

        //Seteamos el estado en "Fuera de servicio"
        avionEliminar.setEstado_Av("Fuera de servicio");

        //Intentamos actualizarlo en la base de datos. Si no se puede, arrojará una excepción
        try{
            avRep.save(avionEliminar);
            return "Se ha eliminado el avión exitosamente";
        }
        catch (Exception e){
            return "Error: no se ha podido conectar con la base de datos. " + e.getMessage();
        }

    }

    @Override
    public String updateAvion(Avion av, Long id) {

        //Validación de campos vacíos
        if(!validarCamposVacios(av))
            return "Debe completar todos los campos";

        //Validación avión existente
        Avion avionEditar = avRep.findById(id).orElse(null);
        if(avionEditar == null || avionEditar.getEstado_Av().equals("Fuera de servicio"))
            return "No existe el avión ingresado";

        //Validación tipo existente
        if(!validarTipoExistente(av.getId_Ti_Av()))
            return "No existe el tipo de avión ingresado";

        //Validación modelo existente
        if(!validarModeloExistente(av.getId_Mo_Av()))
            return "No existe el modelo de avión ingresado";

        //Le asignamos al avión que se ingresó los datos clave del avión a editar
        av.setId_Av(avionEditar.getId_Av());
        av.setEstado_Av(avionEditar.getEstado_Av());

        //Intentamos actualizarlo en la base de datos. Si no se puede, arrojará una excepción
        try{
            avRep.save(av);
            return "Se ha modificado el avión exitosamente";
        }
        catch (Exception e){
            return "Error: no se ha podido conectar con la base de datos. " + e.getMessage();
        }

    }

    private Boolean validarCamposVacios(Avion av){
        return av.getId_Mo_Av() != null && av.getId_Ti_Av() != null && av.getCapacidadTotal_Av() != null;
    }

    private Boolean validarTipoExistente(Long id){
        Tipo tip = tipoapi.findTipo(id);
        return tip != null && tip.getEstado_Ti();
    }

    private Boolean validarModeloExistente(Long id){
        Modelo mod = modeloapi.findModelo(id);
        return mod != null && mod.getEstado_Mo();
    }

}
