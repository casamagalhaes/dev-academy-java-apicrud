package br.com.cm.workshop.apicrud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.cm.workshop.apicrud.model.Bebida;
import br.com.cm.workshop.apicrud.service.BebidaService;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Bebida")
@RestController
@RequestMapping(path = "/bebidas",  
  consumes = MediaType.APPLICATION_JSON_VALUE, 
  produces = MediaType.APPLICATION_JSON_VALUE)
public class BebidaController {
    
    @Autowired
    private BebidaService service;
    
    @GetMapping
    public List<Bebida> listaTodos(){
        return service.listaTodos();
    }

    @GetMapping("/{id}")
    public Optional<Bebida> getById(@PathVariable Long id){
        return service.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/buscaPorNome")
    public List<Bebida> listarPorNome(@RequestParam String nome ){
        return service.buscaPorNome(nome);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/buscaPorDescricao")
    public List<Bebida> listarPorDescricao(@RequestParam String descricao){
        return service.buscarPorDescricao(descricao);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/buscaPorMarcaESituacao")
    public List<Bebida> listarPorMarca(@RequestParam String marca){
        return service.buscarPorMarca(marca) ;
    }
    
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Bebida salvar(@Valid @RequestBody Bebida bebida) {
        return service.save(bebida);
    }

    @PutMapping("/{id}")
    public Bebida atualiza(@PathVariable Long id, @RequestBody Bebida bebida) {
        return service.update(id, bebida);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        service.remove(id);
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public void unsupported() {}

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public void notFound() {}

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}