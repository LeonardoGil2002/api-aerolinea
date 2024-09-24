package com.apiaerolinea.vuelo_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VueloDTO {

    private Long id_Vu;
    private Long id_Av_Vu;
    private Long destino_Vu;
    private String destino_nombre;
    private Long origen_Vu;
    private String origen_nombre;
    private Double precio_Vu;
    private Integer capacidadDisponible_Vu;
    private LocalDate fechaVuelo_Vu;
    private LocalTime horaVuelo_Vu;
    private String estado_Vu;

}
