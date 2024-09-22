package com.apiaerolinea.tipo_service.service;

import com.apiaerolinea.tipo_service.entity.Tipo;

import java.util.List;

public interface ITipoService {
    public List<Tipo> readTipos();
    public Tipo findTipo(Long id);
    public String createTipo(Tipo tip);
    public String deleteTipo(Long id);
    public String updateTipo(Tipo tip, Long id);
}
