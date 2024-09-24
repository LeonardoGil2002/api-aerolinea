package com.apiaerolinea.vuelo_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ciudad {
    private Long id_Ciu;
    private String nombre_Ciu;
    private Long id_Pa_Ciu;
    private String nombre_Pa;
    private Boolean estado_Ciu;
}
