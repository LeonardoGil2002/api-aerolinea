package com.apiaerolinea.ciudad_service.controller;

import com.apiaerolinea.ciudad_service.dto.CiudadDTO;
import com.apiaerolinea.ciudad_service.entity.Ciudad;
import com.apiaerolinea.ciudad_service.service.ICiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ciudades")
public class CiudadController {

    @Autowired
    private ICiudadService ciuSer;

    @GetMapping("/")
    public List<CiudadDTO> readCiudades(){
        return ciuSer.readCiudades();
    }

    @GetMapping("/{id}")
    public CiudadDTO findCiudad(@PathVariable Long id){
        return ciuSer.findCiudad(id);
    }

    @PostMapping("/alta")
    public String createCiudad(@RequestBody Ciudad ciu){
        return ciuSer.createCiudad(ciu);
    }

    @PutMapping("/modificacion/{id}")
    public String updateCiudad(@RequestBody Ciudad ciu, @PathVariable Long id){
        return ciuSer.updateCiudad(ciu, id);
    }

    @DeleteMapping("/baja/{id}")
    public String deleteCiudad(@PathVariable Long id){
        return ciuSer.deleteCiudad(id);
    }

}
