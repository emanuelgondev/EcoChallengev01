package com.ecochallenge.models;

public class Desafio {
    private int id;
    private String titulo;
    private String descricao;
    private int pontosRecompensa;
    private String categoria;
    private String dicaEcologica;
    private boolean ativo;

    public Desafio(int id, String titulo, String descricao, int pontosRecompensa, String categoria, String dicaEcologica, boolean ativo) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.pontosRecompensa = pontosRecompensa;
        this.categoria = categoria;
        this.dicaEcologica = dicaEcologica;
        this.ativo = ativo;
    }

    public int obterId() { return id; }
    public String obterTitulo() { return titulo; }
    public String obterDescricao() { return descricao; }
    public int obterPontosRecompensa() { return pontosRecompensa; }
    public String obterCategoria() { return categoria; }
    public String obterDicaEcologica() { return dicaEcologica; }
    public boolean isAtivo() { return ativo; }

    @Override
    public String toString() {
        return String.format("%s (%s)\n%s\nPontos: %d\nDica: %s",
                titulo, categoria, descricao, pontosRecompensa, dicaEcologica);
    }
}

