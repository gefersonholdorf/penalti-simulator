package com.gefersonholdorf.simuladorpenaltis.dto;

import java.io.Serializable;

import com.gefersonholdorf.simuladorpenaltis.entities.Jogador;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JogadorDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private Integer idade;
    private String img;
    private Double hab;
    private Double pot;
    private String posicao;

    private String time;
    
    public JogadorDTO(Jogador jogador) {
        this.id = jogador.getId();
        this.nome = jogador.getNome();
        this.idade = jogador.getIdade();
        this.img = jogador.getImg();
        this.hab = jogador.getHab();
        this.pot = jogador.getPot();
        this.posicao = jogador.getPosicao();
    }

    public JogadorDTO(Jogador jogador, String time) {
        this(jogador);
        this.time = jogador.getTime().getName();
    }
}
