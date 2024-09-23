package com.apiaerolineas.avion_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class AvionDTO {

    private Long id_Av;
    private Long id_Mo_Av;
    private String nombre_Mo;
    private Long id_Ti_Av;
    private String nombre_Ti;
    private Integer capacidadTotal_Av;
    private String estado_Av;

}
