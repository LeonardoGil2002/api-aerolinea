package com.apiaerolinea.vuelo_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Vuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Vu;

    private Long id_Av_Vu;
    private Long destino_Vu;
    private Long origen_Vu;
    private Double precio_Vu;
    private Integer capacidadDisponible_Vu;
    private LocalDate fechaVuelo_Vu;
    private LocalTime horaVuelo_Vu;
    private String estado_Vu;

}
