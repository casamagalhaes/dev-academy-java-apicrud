package br.com.cm.workshop.apicrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cm.workshop.apicrud.model.Bebida;
import br.com.cm.workshop.apicrud.service.BebidaService;

@RestController
@RequestMapping("/bebidas")
public class BebidaController {
    
    @Autowired
    private BebidaService service;
    
    @GetMapping
    public List<Bebida> listaTodos(){
        return service.listaTodos();
    }
}