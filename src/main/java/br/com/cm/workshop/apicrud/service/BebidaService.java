package br.com.cm.workshop.apicrud.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

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

    public Optional<Bebida> findById(Long id){     
        return repository.findById(id);
    }

    public List<Bebida> buscaPorNome(String nome){
        return repository.findByNomeContains(nome);
    }

    public List<Bebida> buscarPorDescricao(String descricao){
        return repository.findByDescricao(descricao);
    }

    public List<Bebida> buscarPorMarca(String marca){
        return repository.findByMarca(marca);
    }

    public Bebida save(Bebida bebida) {
        return repository.saveAndFlush(bebida);
    }

    public void remove(Long id) {
        repository.deleteById(id);
    }

    public Bebida update(Long id, Bebida bebida){
        if(repository.existsById(id)) {
            if(id.equals(bebida.getId())) {
                bebida.setAlteracao(new Date());
                return repository.saveAndFlush(bebida);
            }
              
            else
                throw new UnsupportedOperationException("Id informado é diferente do id da bebida");
        } else
            throw new EntityNotFoundException("Bebida não encontrada");
    }

}
