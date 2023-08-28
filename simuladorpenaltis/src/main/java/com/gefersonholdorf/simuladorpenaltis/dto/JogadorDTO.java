package com.gefersonholdorf.simuladorpenaltis.dto;

import java.io.Serializable;

import com.gefersonholdorf.simuladorpenaltis.entities.Jogador;
import com.gefersonholdorf.simuladorpenaltis.entities.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JogadorDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private Integer idade;
    private String img;
    private Double hab;
    private Double pot;
    private String posicao;

    private TimeDTO time;
    
    public JogadorDTO(Jogador jogador) {
        this.id = jogador.getId();
        this.nome = jogador.getNome();
        this.idade = jogador.getIdade();
        this.img = jogador.getImg();
        this.hab = jogador.getHab();
        this.pot = jogador.getPot();
        this.posicao = jogador.getPosicao();
    }

    public JogadorDTO(Jogador jogador, Time time) {
        this(jogador);
        this.time = new TimeDTO(jogador.getTime());
    }
}