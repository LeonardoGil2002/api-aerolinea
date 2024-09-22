package com.apiaerolineas.avion_service.repository;

import com.apiaerolineas.avion_service.entity.Avion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAvionRepository extends JpaRepository<Avion,Long> {

    @Query("SELECT av FROM Avion av WHERE av.estado_Av!='Fuera de servicio'")
    List<Avion> findAllNoFueraDeServicio();

}
