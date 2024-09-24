package com.apiaerolinea.vuelo_service.service;

import com.apiaerolinea.vuelo_service.dto.Avion;
import com.apiaerolinea.vuelo_service.dto.Ciudad;
import com.apiaerolinea.vuelo_service.dto.VueloDTO;
import com.apiaerolinea.vuelo_service.entity.Vuelo;
import com.apiaerolinea.vuelo_service.repository.AvionAPIClient;
import com.apiaerolinea.vuelo_service.repository.CiudadAPIClient;
import com.apiaerolinea.vuelo_service.repository.IVueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class VueloService implements IVueloService{

    @Autowired
    private IVueloRepository vueRep;

    @Autowired
    private AvionAPIClient avionapi;

    @Autowired
    private CiudadAPIClient ciudadapi;

    @Override
    public List<VueloDTO> readVuelos() {

        //Traigo el listado de vuelos con estado en true
        List<Vuelo> listadoVuelos = vueRep.findAllVuelos();
        //Creo una lista vacía de vuelos con el formato a mostrar deseado
        List<VueloDTO> listadoVuelosDto = new ArrayList<>();

        //Recorro el listado de vuelos
        for(Vuelo v : listadoVuelos){

            //Creo un objeto VueloDTO que tiene el formato a mostrar deseado
            VueloDTO vDto = new VueloDTO();
            //Traigo el avión, ciudad de origen y ciudad de destino según el id del vuelo
            Avion avion = avionapi.findAvion(v.getId_Av_Vu());
            Ciudad ciudadOrigen = ciudadapi.findCiudad(v.getOrigen_Vu());
            Ciudad ciudadDestino = ciudadapi.findCiudad(v.getDestino_Vu());
            //Creo el formato deseado para mostrar el origen y el destino ('ciudad', 'país') con los datos recolectados
            String origen = ciudadOrigen.getNombre_Ciu() + ", " + ciudadOrigen.getNombre_Pa();
            String destino = ciudadDestino.getNombre_Ciu() + ", " + ciudadDestino.getNombre_Pa();

            //Asigno todos los valores a vDTO
            vDto.setId_Vu(v.getId_Vu());
            vDto.setId_Av_Vu(v.getId_Av_Vu());
            vDto.setDestino_Vu(v.getDestino_Vu());
            vDto.setDestino_nombre(destino);
            vDto.setOrigen_Vu(v.getOrigen_Vu());
            vDto.setOrigen_nombre(origen);
            vDto.setPrecio_Vu(v.getPrecio_Vu());
            vDto.setCapacidadDisponible_Vu(v.getCapacidadDisponible_Vu());
            vDto.setFechaVuelo_Vu(v.getFechaVuelo_Vu());
            vDto.setHoraVuelo_Vu(v.getHoraVuelo_Vu());
            vDto.setEstado_Vu(v.getEstado_Vu());

            //Agrego el vueloDto al listado de vuelos dto
            listadoVuelosDto.add(vDto);

        }

        //Retorno el listado de vuelos en el formato deseado
        return listadoVuelosDto;

    }

    @Override
    public VueloDTO findVuelo(Long id) {

        //Validación de vuelo inexistente
        Vuelo vueloBuscado = vueRep.findById(id).orElse(null);
        if(vueloBuscado == null)
            return null;

        //Creo un objeto VueloDTO que tiene el formato a mostrar deseado
        VueloDTO vDto = new VueloDTO();
        //Traigo el avión, ciudad de origen y ciudad de destino según el id del vuelo
        Avion avion = avionapi.findAvion(vueloBuscado.getId_Av_Vu());
        Ciudad ciudadOrigen = ciudadapi.findCiudad(vueloBuscado.getOrigen_Vu());
        Ciudad ciudadDestino = ciudadapi.findCiudad(vueloBuscado.getDestino_Vu());
        //Creo el formato deseado para mostrar el origen y el destino ('ciudad', 'país') con los datos recolectados
        String origen = ciudadOrigen.getNombre_Ciu() + ", " + ciudadOrigen.getNombre_Pa();
        String destino = ciudadDestino.getNombre_Ciu() + ", " + ciudadDestino.getNombre_Pa();

        //Asigno todos los valores a vDTO
        vDto.setId_Vu(vueloBuscado.getId_Vu());
        vDto.setId_Av_Vu(vueloBuscado.getId_Av_Vu());
        vDto.setDestino_Vu(vueloBuscado.getDestino_Vu());
        vDto.setDestino_nombre(destino);
        vDto.setOrigen_Vu(vueloBuscado.getOrigen_Vu());
        vDto.setOrigen_nombre(origen);
        vDto.setPrecio_Vu(vueloBuscado.getPrecio_Vu());
        vDto.setCapacidadDisponible_Vu(vueloBuscado.getCapacidadDisponible_Vu());
        vDto.setFechaVuelo_Vu(vueloBuscado.getFechaVuelo_Vu());
        vDto.setHoraVuelo_Vu(vueloBuscado.getHoraVuelo_Vu());
        vDto.setEstado_Vu(vueloBuscado.getEstado_Vu());

        //Retorno el vuelo en el formato deseado
        return vDto;

    }

    @Override
    public String createVuelo(Vuelo vu) {

        //Validación campos vacíos
        if(!validarCamposVaciosAlta(vu))
            return "Debe completar todos los campos";

        //Validación de fecha de vuelo anterior a la actual
        if (!validarFechaMenorALaActual(vu.getFechaVuelo_Vu()))
            return "La fecha del vuelo debe ser mayor a la fecha actual";

        //Validación de mismo destino y origen
        if(validarMismoDestinoYOrigen(vu.getOrigen_Vu(), vu.getDestino_Vu()))
            return "El destino no puede ser igual al origen";

        //Validación avion inexistente
        Avion av = avionapi.findAvion(vu.getId_Av_Vu());
        if(!validarAvionInexistente(av))
            return "El avión ingresado no existe";

        //Validación ciudad origen inexistente
        if(!validarCiudadInexistente(vu.getOrigen_Vu()))
            return "La ciudad de origen ingresada no existe";

        //Validación ciudad destino inexistente
        if(!validarCiudadInexistente(vu.getDestino_Vu()))
            return "La ciudad de destino ingreda no existe";

        //Le asigno al vuelo el estado y la cantidad disponible
        vu.setEstado_Vu("Disponible");
        vu.setCapacidadDisponible_Vu(av.getCapacidadTotal_Av());

        //Intento guardar el vuelo en la base de datos. En caso de no poder, arroja una excepción
        try{
            vueRep.save(vu);
            return "Se ha guardado el vuelo con éxito";
        }
        catch(Exception e){
            return "Error: no se ha podido conectar con la base de datos. " + e.getMessage();
        }

    }

    @Override
    public String updateVuelo(Vuelo vu, Long id) {

        //Validación vuelo inexistente
        Vuelo vueloBuscado = vueRep.findById(id).orElse(null);
        if(vueloBuscado == null || vueloBuscado.getEstado_Vu().equals("Cancelado"))
            return "El vuelo ingresado no existe";

        //Validación campos vacíos
        if(!validarCamposVaciosModificacion(vu))
            return "Debe completar todos los campos";

        //Validación de fecha de vuelo anterior a la actual
        if (!validarFechaMenorALaActual(vu.getFechaVuelo_Vu()))
            return "La fecha del vuelo debe ser mayor a la fecha actual";

        //Validación de mismo destino y origen
        if(validarMismoDestinoYOrigen(vu.getOrigen_Vu(), vu.getDestino_Vu()))
            return "El destino no puede ser igual al origen";

        //Validación avion inexistente
        Avion av = avionapi.findAvion(vu.getId_Av_Vu());
        if(!validarAvionInexistente(av))
            return "El avión ingresado no existe";

        //Validación ciudad origen inexistente
        if(!validarCiudadInexistente(vu.getOrigen_Vu()))
            return "La ciudad de origen ingresada no existe";

        //Validación ciudad destino inexistente
        if(!validarCiudadInexistente(vu.getDestino_Vu()))
            return "La ciudad de destino ingreda no existe";

        //Se le asignan los campos clave al vuelo ingresado
        vu.setId_Vu(vueloBuscado.getId_Vu());
        vu.setEstado_Vu(vueloBuscado.getEstado_Vu());

        //Intento actualizar el vuelo en la base de datos. En caso de no poder, arroja una excepción
        try{
            vueRep.save(vu);
            return "Se ha modificado el vuelo con éxito";
        }
        catch(Exception e){
            return "Error: no se ha podido conectar con la base de datos. " + e.getMessage();
        }

    }

    @Override
    public String deleteVuelo(Long id) {

        //Validación vuelo inexistente
        Vuelo vueloBuscado = vueRep.findById(id).orElse(null);
        if(vueloBuscado == null || vueloBuscado.getEstado_Vu().equals("Cancelado"))
            return "El vuelo ingresado no existe";

        //Se le cambia el estado al vuelo a "Cancelado"
        vueloBuscado.setEstado_Vu("Cancelado");

        //Intento actualizar el vuelo en la base de datos. En caso de no poder, arroja una excepción
        try{
            vueRep.save(vueloBuscado);
            return "Se ha eliminado el vuelo con éxito";
        }
        catch(Exception e){
            return "Error: no se ha podido conectar con la base de datos. " + e.getMessage();
        }

    }

    private Boolean validarCamposVaciosAlta(Vuelo v){
        return v.getId_Av_Vu() != null && v.getOrigen_Vu() != null && v.getDestino_Vu() != null &&
                v.getPrecio_Vu() != null && v.getFechaVuelo_Vu() != null && v.getHoraVuelo_Vu() != null;
    }

    private Boolean validarCamposVaciosModificacion(Vuelo v){
        return v.getId_Av_Vu() != null && v.getOrigen_Vu() != null && v.getDestino_Vu() != null &&
                v.getPrecio_Vu() != null && v.getFechaVuelo_Vu() != null && v.getHoraVuelo_Vu() != null &&
                v.getCapacidadDisponible_Vu() != null;
    }

    private Boolean validarAvionInexistente(Avion av){
        return av != null && !av.getEstado_Av().equals("Fuera de servicio");
    }

    private Boolean validarCiudadInexistente(Long id){
        Ciudad ci = ciudadapi.findCiudad(id);
        return ci != null && ci.getEstado_Ciu();
    }

    private Boolean validarFechaMenorALaActual(LocalDate fecha){
        return LocalDate.now().isBefore(fecha);
    }

    private Boolean validarMismoDestinoYOrigen(Long origen, Long destino){
        return Objects.equals(origen, destino);
    }

}
