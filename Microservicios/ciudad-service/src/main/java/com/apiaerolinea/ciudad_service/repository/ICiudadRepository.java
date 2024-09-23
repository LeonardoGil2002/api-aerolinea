package com.apiaerolinea.ciudad_service.repository;

import com.apiaerolinea.ciudad_service.entity.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICiudadRepository extends JpaRepository<Ciudad, Long> {

    @Query("SELECT ci FROM Ciudad ci WHERE ci.estado_Ciu=true")
    List<Ciudad> findAllTrue();

}
