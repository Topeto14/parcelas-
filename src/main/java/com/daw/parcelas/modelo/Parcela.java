package com.daw.parcelas.modelo;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.AnyDiscriminatorImplicitValues;

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
