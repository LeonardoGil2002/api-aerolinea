package com.apiaerolinea.pais_service.service;

import com.apiaerolinea.pais_service.entity.Pais;

import java.util.List;

public interface IPaisService {
    public List<Pais> readPaises();
    public Pais findPais(Long id);
    public String createPais(Pais pa);
    public String updatePais(Pais pa, Long id);
    public String deletePais(Long id);
}
