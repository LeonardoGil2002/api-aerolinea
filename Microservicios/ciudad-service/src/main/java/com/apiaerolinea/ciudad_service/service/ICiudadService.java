package com.apiaerolinea.ciudad_service.service;

import com.apiaerolinea.ciudad_service.dto.CiudadDTO;
import com.apiaerolinea.ciudad_service.entity.Ciudad;

import java.util.List;

public interface ICiudadService {
    public List<CiudadDTO> readCiudades();
    public CiudadDTO findCiudad(Long id);
    public String createCiudad(Ciudad ci);
    public String updateCiudad(Ciudad ci, Long id);
    public String deleteCiudad(Long id);
}
