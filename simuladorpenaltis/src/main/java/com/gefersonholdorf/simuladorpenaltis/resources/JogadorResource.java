package com.gefersonholdorf.simuladorpenaltis.resources;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gefersonholdorf.simuladorpenaltis.dto.JogadorDTO;
import com.gefersonholdorf.simuladorpenaltis.services.JogadorService;

@RestController
@RequestMapping(value = "/jogadores")
public class JogadorResource {
    
    @Autowired
    private JogadorService services;

    @GetMapping
    public ResponseEntity<Page<JogadorDTO>> findAllPaged(
        @RequestParam(value = "page", defaultValue = "0") Integer page,
        @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
        @RequestParam(value = "direction", defaultValue = "ASC") String direction,
        @RequestParam(value = "orderBy", defaultValue = "posicao") String orderBy
    ) {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        Page<JogadorDTO> list = services.findAllPaged(pageRequest);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = ("{id}"))
    public ResponseEntity<JogadorDTO> findById(@PathVariable Long id) {
        JogadorDTO dto = services.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<JogadorDTO> insert(@RequestBody JogadorDTO dto) {
        dto = services.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = ("{id}"))
    public ResponseEntity<JogadorDTO> update(@PathVariable Long id, @RequestBody JogadorDTO dto) {
        dto = services.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = ("{id}"))
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        services.delete(id);
        return ResponseEntity.noContent().build();
    }
}
