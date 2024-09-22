package com.apiaerolineas.avion_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Avion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Av;

    private String modelo_Av;
    private int capacidadTotal_Av;
    private String tipo_Av;
    private String estado_Av;
}
