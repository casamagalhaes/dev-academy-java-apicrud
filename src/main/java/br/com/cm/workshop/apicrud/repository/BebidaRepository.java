package br.com.cm.workshop.apicrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cm.workshop.apicrud.model.Bebida;

@Repository
public interface BebidaRepository extends JpaRepository<Bebida, Long> {}
