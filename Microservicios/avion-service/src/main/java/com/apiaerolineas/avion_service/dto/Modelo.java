package com.apiaerolineas.avion_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Modelo {

    private Long id_Mo;
    private String nombre_Mo;
    private Boolean estado_Mo;

}
