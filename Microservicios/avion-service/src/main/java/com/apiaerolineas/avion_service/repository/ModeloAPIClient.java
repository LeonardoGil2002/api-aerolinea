package com.apiaerolineas.avion_service.repository;

import com.apiaerolineas.avion_service.dto.Modelo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="modelo-service")
public interface ModeloAPIClient {
    @GetMapping("/modelos/{id}")
    public Modelo findModelo(@PathVariable Long id);
}
