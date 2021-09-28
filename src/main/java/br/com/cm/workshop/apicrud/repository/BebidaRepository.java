package br.com.cm.workshop.apicrud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cm.workshop.apicrud.model.Bebida;

@Repository
public interface BebidaRepository extends JpaRepository<Bebida, Long> {

    List<Bebida> findByNomeContains(String nome);

    @Query("SELECT b FROM Bebida b where b.descricao like %?1% ")
    List<Bebida> findByDescricao(String descricao);

    @Query("SELECT b FROM Bebida b where b.marca like %?1%")
    List<Bebida> findByMarca(String marca);
}
