package com.daw.parcelas.service;


import com.daw.parcelas.model.Sensor;
import com.daw.parcelas.model.TipoSensor;
import com.daw.parcelas.repository.SensorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Locale;

@Service
public class SensorServiceImpl implements SensorService {

    private final SensorRepository sensorRepository;

    public SensorServiceImpl(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public List<Sensor> listarTodos() {
        return sensorRepository.findAll();
    }

    @Override
    public Sensor buscarPorId(Long id) {
        return sensorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No existe el sensor con id " + id));
    }

    @Override
    public Sensor guardar(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    @Override
    public void eliminar(Long id) {
        sensorRepository.deleteById(id);
    }

    @Override
    public List<Sensor> buscar(String texto, TipoSensor tipo) {
        String busqueda = texto == null
                ? ""
                : texto.trim().toLowerCase(Locale.ROOT);

        return sensorRepository.findAll()
                .stream()
                .filter(sensor ->
                        busqueda.isEmpty()
                                || (sensor.getName() != null
                                && sensor.getName()
                                .toLowerCase(Locale.ROOT)
                                .contains(busqueda))
                                || (sensor.getModel() != null
                                && sensor.getModel()
                                .toLowerCase(Locale.ROOT)
                                .contains(busqueda)))
                .filter(sensor ->
                        tipo == null || sensor.getTipo() == tipo)
                .toList();
    }

}
