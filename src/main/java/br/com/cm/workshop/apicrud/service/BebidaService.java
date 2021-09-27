package br.com.cm.workshop.apicrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cm.workshop.apicrud.model.Bebida;
import br.com.cm.workshop.apicrud.repository.BebidaRepository;

@Service
public class BebidaService {
    
    @Autowired
    private BebidaRepository repository;

    public List<Bebida> listaTodos(){
        return repository.findAll();
    }
    
}
