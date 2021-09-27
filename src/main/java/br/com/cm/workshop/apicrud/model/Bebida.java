package br.com.cm.workshop.apicrud.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import br.com.cm.workshop.apicrud.enums.TipoBebida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bebida implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    
    @NotEmpty(message = "nome é obrigatório")
    private String nome;
     
    private String descricao;

    private String marca;

    @Enumerated(EnumType.STRING)
    private TipoBebida tipoBebida;
   
    private boolean ativo;
     
    @Temporal(TemporalType.TIMESTAMP)
    private Date alteracao; 
     
    private Double preco;
    
}
