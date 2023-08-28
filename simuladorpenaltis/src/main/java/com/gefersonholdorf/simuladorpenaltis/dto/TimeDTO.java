package com.gefersonholdorf.simuladorpenaltis.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.gefersonholdorf.simuladorpenaltis.entities.Jogador;
import com.gefersonholdorf.simuladorpenaltis.entities.League;
import com.gefersonholdorf.simuladorpenaltis.entities.Time;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TimeDTO implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String name;
    private String logo_img;
    private Double pot;

    private List<JogadorDTO> players = new ArrayList<>();

    private String league;

    public TimeDTO(Time time) {
        this.id = time.getId();
        this.name = time.getName();
        this.logo_img = time.getLogo_img();
        this.pot = time.getPot();
    }

    public TimeDTO(Time time, League league, List<Jogador> players) {
        this(time);
        this.league = league.getName();
        players.forEach(cat -> this.players.add(new JogadorDTO(cat, cat.getNome())));
    }
}
