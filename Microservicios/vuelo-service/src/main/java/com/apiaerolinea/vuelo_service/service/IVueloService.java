package com.apiaerolinea.vuelo_service.service;

import com.apiaerolinea.vuelo_service.dto.VueloDTO;
import com.apiaerolinea.vuelo_service.entity.Vuelo;

import java.util.List;

public interface IVueloService {
    public List<VueloDTO> readVuelos();
    public VueloDTO findVuelo(Long id);
    public String createVuelo(Vuelo vu);
    public String updateVuelo(Vuelo vu, Long id);
    public String deleteVuelo(Long id);
}
