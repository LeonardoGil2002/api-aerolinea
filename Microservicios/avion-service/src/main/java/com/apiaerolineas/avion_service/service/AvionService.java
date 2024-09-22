package com.apiaerolineas.avion_service.service;

import com.apiaerolineas.avion_service.entity.Avion;
import com.apiaerolineas.avion_service.repository.IAvionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvionService implements IAvionService{

    @Autowired
    private IAvionRepository avRep;

    @Override
    public List<Avion> readAviones() {
        //Trae todos los aviones que no estén fuera de servicio
        return avRep.findAllNoFueraDeServicio();
    }

    @Override
    public Avion findAvion(Long id) {
        //Trae un avión específico por más que esté dado de baja
        return avRep.findById(id).orElse(null);
    }

    @Override
    public String createAvion(Avion av) {




        return "";
    }

    @Override
    public String deleteAvion(Long id) {
        return "";
    }

    @Override
    public String updateAvion(Avion av, Long id) {
        return "";
    }
}
