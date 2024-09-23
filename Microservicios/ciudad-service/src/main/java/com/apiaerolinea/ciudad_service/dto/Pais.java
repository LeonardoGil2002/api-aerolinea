package com.apiaerolinea.ciudad_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Pais {

    private Long id_Pa;
    private String nombre_Pa;
    private Boolean estado_Pa;

}
