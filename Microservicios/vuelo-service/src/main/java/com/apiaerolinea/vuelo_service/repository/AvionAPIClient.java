package com.apiaerolinea.vuelo_service.repository;

import com.apiaerolinea.vuelo_service.dto.Avion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="avion-service")
public interface AvionAPIClient {
    @GetMapping("/aviones/{id}")
    public Avion findAvion(@PathVariable Long id);
}
