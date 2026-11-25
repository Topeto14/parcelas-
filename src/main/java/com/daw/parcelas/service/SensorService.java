package com.daw.parcelas.service;

import com.daw.parcelas.model.Sensor;
import com.daw.parcelas.model.TipoSensor;

import java.util.List;

public interface SensorService {
    List<Sensor> listarTodos();
    Sensor buscarPorId(Long id);
    Sensor guardar(Sensor sensor);
    void eliminar(Long id);
    List<Sensor> buscar(String texto, TipoSensor tipo);

}
