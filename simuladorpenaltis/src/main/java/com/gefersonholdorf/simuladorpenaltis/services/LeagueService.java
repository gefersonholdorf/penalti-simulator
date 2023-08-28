package com.gefersonholdorf.simuladorpenaltis.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gefersonholdorf.simuladorpenaltis.dto.LeagueDTO;
import com.gefersonholdorf.simuladorpenaltis.entities.League;
import com.gefersonholdorf.simuladorpenaltis.repositories.LeagueRepository;
import com.gefersonholdorf.simuladorpenaltis.services.exceptions.DatabaseException;
import com.gefersonholdorf.simuladorpenaltis.services.exceptions.ResourceNotFoundException;

@Service
public class LeagueService {
    
    @Autowired
    private LeagueRepository repository;

    @Transactional(readOnly = true) 
    public Page<LeagueDTO> findAllPaged(PageRequest pageRequest) {
        Page<League> listPage = repository.findAll(pageRequest);
        return listPage.map(x -> new LeagueDTO(x, x.getTeams()));
    }

    @Transactional(readOnly = true)
    public LeagueDTO findById(Long id) {
        Optional<League> obj = repository.findById(id);
        League league = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found!"));
        return new LeagueDTO(league, league.getTeams());
    }

    @Transactional
    public LeagueDTO insert(LeagueDTO dto) {
        League league = new League();
        copyDtoToEntity(dto, league);
        league = repository.save(league);
        return new LeagueDTO(league);
    }

    @Transactional
    public LeagueDTO update(Long id, LeagueDTO dto) {
        try {
            League league = repository.getReferenceById(id);
            copyDtoToEntity(dto, league);
            league = repository.save(league);
            return new LeagueDTO(league);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Entity not found!");
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Entity not found!");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation!");
        }
    }

    private void copyDtoToEntity(LeagueDTO dto, League league) {
        league.setName(dto.getName());
    }
}
