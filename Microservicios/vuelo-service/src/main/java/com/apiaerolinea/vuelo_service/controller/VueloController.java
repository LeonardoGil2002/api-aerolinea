package com.apiaerolinea.vuelo_service.controller;

import com.apiaerolinea.vuelo_service.dto.VueloDTO;
import com.apiaerolinea.vuelo_service.entity.Vuelo;
import com.apiaerolinea.vuelo_service.service.IVueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vuelos")
public class VueloController {

    @Autowired
    private IVueloService vueSer;

    @GetMapping("/")
    public List<VueloDTO> readVuelo(){
        return vueSer.readVuelos();
    }

    @GetMapping("/{id}")
    public VueloDTO findVuelo(@PathVariable Long id){
        return vueSer.findVuelo(id);
    }

    @PostMapping("/alta")
    public String createVuelo(@RequestBody Vuelo vu){
        return vueSer.createVuelo(vu);
    }

    @PutMapping("/modificacion/{id}")
    public String updateVuelo(@RequestBody Vuelo vu, @PathVariable Long id){
        return vueSer.updateVuelo(vu, id);
    }

    @DeleteMapping("/baja/{id}")
    public String deleteVuelo(@PathVariable Long id){
        return vueSer.deleteVuelo(id);
    }

}