package com.apiaerolinea.modelo_service.service;

import com.apiaerolinea.modelo_service.entity.Modelo;

import java.util.List;

public interface IModeloService {
    public List<Modelo> readModelos();
    public Modelo findModelo(Long id);
    public String createModelo(Modelo mod);
    public String updateModelo(Modelo mod, Long id);
    public String deleteModelo(Long id);
}
