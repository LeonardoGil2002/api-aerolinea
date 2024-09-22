package com.apiaerolinea.tipo_service.controller;

import com.apiaerolinea.tipo_service.entity.Tipo;
import com.apiaerolinea.tipo_service.service.ITipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos")
public class TipoController {

    @Autowired
    private ITipoService tipSer;

    @GetMapping("/")
    public List<Tipo> readTipos(){
        return tipSer.readTipos();
    }

    @GetMapping("/{id}")
    public Tipo findTipo(@PathVariable Long id){
        return tipSer.findTipo(id);
    }

    @PostMapping("/alta")
    public String createTipo(@RequestBody Tipo tip){
        return tipSer.createTipo(tip);
    }

    @PutMapping("/modificacion/{id}")
    public String updateTipo(@RequestBody Tipo tip, @PathVariable Long id){
        return tipSer.updateTipo(tip, id);
    }

    @DeleteMapping("/baja/{id}")
    public String deleteTipo(@PathVariable Long id){
        return tipSer.deleteTipo(id);
    }

}
