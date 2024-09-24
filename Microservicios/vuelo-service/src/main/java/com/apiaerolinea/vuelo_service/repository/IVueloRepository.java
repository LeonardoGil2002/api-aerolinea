package com.apiaerolinea.vuelo_service.repository;

import com.apiaerolinea.vuelo_service.entity.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVueloRepository extends JpaRepository<Vuelo, Long> {
    @Query("SELECT vu FROM Vuelo vu WHERE vu.estado_Vu!='Cancelado'")
    public List<Vuelo> findAllVuelos();
}
