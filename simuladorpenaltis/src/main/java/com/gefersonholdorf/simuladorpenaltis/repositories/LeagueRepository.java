package com.gefersonholdorf.simuladorpenaltis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gefersonholdorf.simuladorpenaltis.entities.League;

@Repository
public interface LeagueRepository extends JpaRepository<League, Long>{
    
}
