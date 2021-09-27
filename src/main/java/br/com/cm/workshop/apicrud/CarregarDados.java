package br.com.cm.workshop.apicrud;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.cm.workshop.apicrud.enums.TipoBebida;
import br.com.cm.workshop.apicrud.model.Bebida;
import br.com.cm.workshop.apicrud.repository.BebidaRepository;

@Configuration
public class CarregarDados {
    
    private static final Logger log = 
        LoggerFactory.getLogger(CarregarDados.class);

    @Bean
    CommandLineRunner initDB(BebidaRepository repository) {
        return args -> {
            Bebida b;
            for (int i = 0; i < 25; i++) {
                b = new Bebida();
                b.setNome("Bebida " + i);
                b.setDescricao("Descricao " + i);
                b.setMarca("Marca " + i % 5);
                b.setAtivo(true);
                b.setTipoBebida(TipoBebida.randomTipoBebida());
                b.setAlteracao(new Date());
                b.setPreco(Math.round(Math.random() * 100) / 10.0);
                log.info("Cadastrando produtos... " + repository.save(b));
            }
        };
    }
}