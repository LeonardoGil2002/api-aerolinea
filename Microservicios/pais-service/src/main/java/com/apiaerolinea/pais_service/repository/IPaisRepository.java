package com.apiaerolinea.pais_service.repository;

import com.apiaerolinea.pais_service.entity.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPaisRepository extends JpaRepository<Pais, Long> {
    @Query("SELECT pa FROM Pais pa WHERE pa.estado_Pa=true")
    List<Pais> findAllTrue();
}
