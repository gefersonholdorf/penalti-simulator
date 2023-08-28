package com.gefersonholdorf.simuladorpenaltis.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gefersonholdorf.simuladorpenaltis.dto.JogadorDTO;
import com.gefersonholdorf.simuladorpenaltis.entities.Jogador;
import com.gefersonholdorf.simuladorpenaltis.repositories.JogadorRepository;
import com.gefersonholdorf.simuladorpenaltis.services.exceptions.DatabaseException;
import com.gefersonholdorf.simuladorpenaltis.services.exceptions.ResourceNotFoundException;

@Service
public class JogadorService {

    @Autowired
    JogadorRepository repository;

    @Transactional(readOnly = true)
    public Page<JogadorDTO> findAllPaged(PageRequest pageRequest) {
        Page<Jogador> list = repository.findAll(pageRequest);
        return list.map(x -> new JogadorDTO(x, x.getTime().getName()));
    }

    @Transactional(readOnly = true)
    public JogadorDTO findById(Long id) {
        Optional<Jogador> obj = repository.findById(id);
        Jogador jogador = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found!"));
        return new JogadorDTO(jogador, jogador.getTime().getName());
    }

    @Transactional
    public JogadorDTO insert(JogadorDTO dto) {
        Jogador jogador = new Jogador();
        copyDtoToEntity(dto, jogador);
        jogador = repository.save(jogador);
        return new JogadorDTO(jogador);
    }

    @Transactional
    public JogadorDTO update(Long id, JogadorDTO dto) {
        try {
            Jogador jogador = repository.getReferenceById(id);
            copyDtoToEntity(dto, jogador);
            jogador = repository.save(jogador);
            return new JogadorDTO(jogador);
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
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(JogadorDTO dto, Jogador jogador) {
        jogador.setNome(dto.getNome());
        jogador.setIdade(dto.getIdade());
        jogador.setImg(dto.getImg());
        jogador.setHab(dto.getHab());
        jogador.setPot(dto.getPot());
        jogador.setPosicao(dto.getPosicao());
    }
}
