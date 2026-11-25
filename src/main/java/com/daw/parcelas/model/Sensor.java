package com.daw.parcelas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity(name = "sensors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{sensor.nombre.obligatorio}")
    private String name;

    @NotBlank(message = "{sensor.modelo.obligatorio}")
    private String model;

    @NotNull(message = "{sensor.tipo.obligatorio}")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "tipo_sensor")
    private TipoSensor tipo;

    @Column(name = "unidad_medida")
    private String unidadMedida;

    @NotBlank(message = "{sensor.estado.obligatorio}")
    private String estado;

    private Double latitud;

    private Double longitud;

    @CreationTimestamp
    private LocalDateTime creadoEn;

    @UpdateTimestamp
    private LocalDateTime actualizadoEn;
}
