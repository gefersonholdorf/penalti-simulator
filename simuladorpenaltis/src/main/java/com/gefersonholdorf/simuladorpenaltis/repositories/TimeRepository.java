package com.gefersonholdorf.simuladorpenaltis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gefersonholdorf.simuladorpenaltis.entities.Time;

@Repository
public interface TimeRepository extends JpaRepository<Time, Long>{
    
}
