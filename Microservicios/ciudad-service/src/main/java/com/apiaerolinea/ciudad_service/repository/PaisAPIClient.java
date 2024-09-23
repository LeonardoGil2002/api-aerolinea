package com.apiaerolinea.ciudad_service.repository;

import com.apiaerolinea.ciudad_service.dto.Pais;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="pais-service")
public interface PaisAPIClient {
    @GetMapping("/paises/{id}")
    public Pais findPais(@PathVariable Long id);
}
