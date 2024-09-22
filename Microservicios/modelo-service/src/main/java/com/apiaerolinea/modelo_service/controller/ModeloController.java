package com.apiaerolinea.modelo_service.controller;

import com.apiaerolinea.modelo_service.entity.Modelo;
import com.apiaerolinea.modelo_service.service.IModeloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modelos")
public class ModeloController {

    @Autowired
    private IModeloService modSer;

    @GetMapping("/")
    public List<Modelo> readModelos(){
        return modSer.readModelos();
    }

    @GetMapping("/{id}")
    public Modelo findModelo(@PathVariable Long id){
        return modSer.findModelo(id);
    }

    @PostMapping("/alta")
    public String createModelo(@RequestBody Modelo mod){
        return modSer.createModelo(mod);
    }

    @PutMapping("/modificacion/{id}")
    public String updateModelo(@RequestBody Modelo mod, @PathVariable Long id){
        return modSer.updateModelo(mod, id);
    }

    @DeleteMapping("/baja/{id}")
    public String deleteModelo(@PathVariable Long id){
        return modSer.deleteModelo(id);
    }

}
