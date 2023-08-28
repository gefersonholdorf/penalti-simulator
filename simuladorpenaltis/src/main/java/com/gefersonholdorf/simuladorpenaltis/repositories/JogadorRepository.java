package com.gefersonholdorf.simuladorpenaltis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gefersonholdorf.simuladorpenaltis.entities.Jogador;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Long> {
    
}
