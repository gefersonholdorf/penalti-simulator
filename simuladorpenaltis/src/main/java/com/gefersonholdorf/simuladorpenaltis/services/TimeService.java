package com.gefersonholdorf.simuladorpenaltis.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gefersonholdorf.simuladorpenaltis.dto.TimeDTO;
import com.gefersonholdorf.simuladorpenaltis.entities.Time;
import com.gefersonholdorf.simuladorpenaltis.repositories.TimeRepository;
import com.gefersonholdorf.simuladorpenaltis.services.exceptions.DatabaseException;
import com.gefersonholdorf.simuladorpenaltis.services.exceptions.ResourceNotFoundException;

@Service
public class TimeService {
    
    @Autowired
    private TimeRepository repository;

    @Transactional(readOnly = true)
    public Page<TimeDTO> findAllPaged(PageRequest pageRequest) {
        Page<Time> list = repository.findAll(pageRequest);
        return list.map(x -> new TimeDTO(x, x.getLeague(), x.getPlayers()));
    }

    @Transactional(readOnly = true)
    public TimeDTO findById(Long id) {
        Optional<Time> obj = repository.findById(id);
        Time time = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found!"));
        return new TimeDTO(time, time.getLeague(), time.getPlayers());
    }

    @Transactional
    public TimeDTO insert(TimeDTO dto) {
        Time time = new Time();
        copyDtoToEntity(dto, time);
        time = repository.save(time);
        return new TimeDTO(time);
    }

    @Transactional
    public TimeDTO update(Long id, TimeDTO dto) {
        try {
            Time time = repository.getReferenceById(id);
            copyDtoToEntity(dto, time);
            time = repository.save(time);
            return new TimeDTO(time);
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

    private void copyDtoToEntity(TimeDTO dto, Time time) {
        time.setName(dto.getName());
        time.setLogo_img(dto.getLogo_img());
        time.setPot(dto.getPot());
    }
}
