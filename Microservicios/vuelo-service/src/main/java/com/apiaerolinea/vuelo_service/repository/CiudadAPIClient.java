package com.apiaerolinea.vuelo_service.repository;

import com.apiaerolinea.vuelo_service.dto.Ciudad;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="ciudad-service")
public interface CiudadAPIClient {
    @GetMapping("/ciudades/{id}")
    public Ciudad findCiudad(@PathVariable Long id);
}
