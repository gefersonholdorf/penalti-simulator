package com.gefersonholdorf.simuladorpenaltis.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.gefersonholdorf.simuladorpenaltis.entities.League;
import com.gefersonholdorf.simuladorpenaltis.entities.Time;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LeagueDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    private List<TimeDTO> teams = new ArrayList<>();

    public LeagueDTO(League league) {
        this.id = league.getId();
        this.name = league.getName();
    }

    public LeagueDTO(League league, List<Time> teams) {
        this(league);
        teams.forEach(cat -> this.teams.add(new TimeDTO(cat)));
    }
    
}
