package com.apiaerolinea.vuelo_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Avion {
    private Long id_Av;
    private Long id_Mo_Av;
    private Long id_Ti_Av;
    private Integer capacidadTotal_Av;
    private String estado_Av;
}
