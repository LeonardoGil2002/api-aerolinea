package com.apiaerolineas.avion_service.repository;

import com.apiaerolineas.avion_service.dto.Tipo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="tipo-service")
public interface TipoAPIClient {
    @GetMapping("/tipos/{id}")
    public Tipo findTipo(@PathVariable Long id);
}
