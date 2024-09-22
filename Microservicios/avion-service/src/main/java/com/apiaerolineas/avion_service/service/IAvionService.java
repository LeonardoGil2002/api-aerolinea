package com.apiaerolineas.avion_service.service;

import com.apiaerolineas.avion_service.entity.Avion;

import java.util.List;

public interface IAvionService {
    public List<Avion> readAviones();
    public Avion findAvion(Long id);
    public String createAvion(Avion av);
    public String deleteAvion(Long id);
    public String updateAvion(Avion av, Long id);
}
