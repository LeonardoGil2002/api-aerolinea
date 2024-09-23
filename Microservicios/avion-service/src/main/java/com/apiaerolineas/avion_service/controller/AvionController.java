package com.apiaerolineas.avion_service.controller;

import com.apiaerolineas.avion_service.dto.AvionDTO;
import com.apiaerolineas.avion_service.entity.Avion;
import com.apiaerolineas.avion_service.service.IAvionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aviones")
public class AvionController {

    @Autowired
    private IAvionService avSer;

    @GetMapping("/")
    public List<AvionDTO> readAviones(){
        return avSer.readAviones();
    }

    @GetMapping("/{id}")
    public AvionDTO findAvion(@PathVariable Long id){
        return avSer.findAvion(id);
    }

    @PostMapping("/alta")
    public String createAvion(@RequestBody Avion av){
        return avSer.createAvion(av);
    }

    @PutMapping("/modificacion/{id}")
    public String updateAvion(@RequestBody Avion av, @PathVariable Long id){
        return avSer.updateAvion(av, id);
    }

    @DeleteMapping("/baja/{id}")
    public String deleteAvion(@PathVariable Long id){
        return avSer.deleteAvion(id);
    }

}
