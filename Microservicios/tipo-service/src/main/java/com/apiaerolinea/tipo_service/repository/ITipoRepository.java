package com.apiaerolinea.tipo_service.repository;

import com.apiaerolinea.tipo_service.entity.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITipoRepository extends JpaRepository<Tipo, Long> {
    //Traigo de la base de datos todos los tipos de avión con estado en true en una lista
    @Query("SELECT ti FROM Tipo ti WHERE ti.estado_Ti=true")
    List<Tipo> findAllTrue();
}
