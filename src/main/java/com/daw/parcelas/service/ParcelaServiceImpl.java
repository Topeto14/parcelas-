package com.daw.parcelas.service;

import com.daw.parcelas.model.Parcela;
import com.daw.parcelas.repository.ParcelaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ParcelaServiceImpl implements ParcelaService{

    private final ParcelaRepository parcelaRepository;

    public ParcelaServiceImpl(ParcelaRepository parcelaRepository) {
        this.parcelaRepository = parcelaRepository;
    }

    @Override
    public List<Parcela> listarTodas() {
        return parcelaRepository.findAll();
    }

    @Override
    public Parcela buscarPorId(Long id) {
        return parcelaRepository.findById(id).orElse(null);
    }

    @Override
    public Parcela guardar(Parcela parcela) {
        return parcelaRepository.save(parcela);
    }

    @Override
    public void eliminar(Long id) {
        parcelaRepository.deleteById(id);
    }
}
