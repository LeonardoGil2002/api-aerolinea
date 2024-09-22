package com.apiaerolinea.pais_service.controller;

import com.apiaerolinea.pais_service.entity.Pais;
import com.apiaerolinea.pais_service.service.IPaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paises")
public class PaisController {

    @Autowired
    private IPaisService paiSer;

    @GetMapping("/")
    public List<Pais> readPaises(){
        return paiSer.readPaises();
    }

    @GetMapping("/{id}")
    public Pais findPais(@PathVariable Long id){
        return paiSer.findPais(id);
    }

    @PostMapping("/alta")
    public String createPais(@RequestBody Pais pa){
        return paiSer.createPais(pa);
    }

    @PutMapping("/modificacion/{id}")
    public String updatePais(@RequestBody Pais pa, @PathVariable Long id){
        return paiSer.updatePais(pa, id);
    }

    @DeleteMapping("/baja/{id}")
    public String deletePais(@PathVariable Long id){
        return paiSer.deletePais(id);
    }

}
