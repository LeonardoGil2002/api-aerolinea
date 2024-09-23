package com.apiaerolineas.avion_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tipo {

    private Long id_Ti;
    private String nombre_Ti;
    private Boolean estado_Ti;

}
