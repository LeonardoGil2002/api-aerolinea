package com.apiaerolinea.modelo_service.repository;

import com.apiaerolinea.modelo_service.entity.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IModeloRepository extends JpaRepository<Modelo, Long> {
    //Método para que se traigan de la base de datos todos los modelos de avión en estado true
    @Query("SELECT mod FROM Modelo mod WHERE mod.estado_Mo=true")
    List<Modelo> findAllTrue();
}
