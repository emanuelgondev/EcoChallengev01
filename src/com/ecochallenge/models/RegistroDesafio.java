package com.ecochallenge.models;

import java.time.LocalDateTime;

public class RegistroDesafio {
    private int id;
    private int usuarioId;
    private int desafioId;
    private String tituloDesafio;
    private int pontosGanhos;
    private LocalDateTime dataRealizacao;
    private boolean concluido;

    public RegistroDesafio(int id, int usuarioId, int desafioId, String tituloDesafio, int pontosGanhos, LocalDateTime dataRealizacao, boolean concluido) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.desafioId = desafioId;
        this.tituloDesafio = tituloDesafio;
        this.pontosGanhos = pontosGanhos;
        this.dataRealizacao = dataRealizacao;
        this.concluido = concluido;
    }

    public int obterId() { return id; }
    public int obterUsuarioId() { return usuarioId; }
    public int obterDesafioId() { return desafioId; }
    public String obterTituloDesafio() { return tituloDesafio; }
    public int obterPontosGanhos() { return pontosGanhos; }
    public LocalDateTime obterDataRealizacao() { return dataRealizacao; }
    public boolean isConcluido() { return concluido; }

    @Override
    public String toString() {
        return String.format("%s - %d pontos - %s", tituloDesafio, pontosGanhos, dataRealizacao);
    }
}

