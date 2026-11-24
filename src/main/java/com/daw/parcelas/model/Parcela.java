package com.daw.parcelas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Parcela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nombre;
    private String ubicacion;
    private Double superficie;

    @Enumerated(EnumType.STRING)
    private TipoCultivo TipoCultivo;



}
