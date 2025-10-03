package com.ecochallenge.models;

public class Usuario {
    private int id;
    private String nomeCompleto;
    private int idade;
    private String email;
    private int pontosTotais;
    private String nivelSustentabilidade;

    public Usuario(int id, String nomeCompleto, int idade, String email) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.idade = idade;
        this.email = email;
        this.pontosTotais = 0;
        this.nivelSustentabilidade = "Iniciante";
    }

    public Usuario(String nomeCompleto, int idade, String email) {
        this(0, nomeCompleto, idade, email);
    }

    public int obterId() {
        return id;
    }
    public void definirId(int id) {
        this.id = id;
    }
    public String obterNomeCompleto() {
        return nomeCompleto;
    }
    public void definirNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }
    public int obterIdade() {
        return idade;
    }
    public void definirIdade(int idade) {
        this.idade = idade;
    }
    public String obterEmail() {
        return email;
    }
    public void definirEmail(String email) {
        this.email = email;
    }
    public int obterPontosTotais() {
        return pontosTotais;
    }
    public void definirPontosTotais(int pontosTotais) {
        this.pontosTotais = pontosTotais;
    }
    public String obterNivelSustentabilidade() {
        return nivelSustentabilidade;
    }
    public void definirNivelSustentabilidade(String nivel) {
        this.nivelSustentabilidade = nivel;
    }
    @Override
    public String toString() {
        return String.format("Usuário: %s (%d anos) - %s - Pontos: %d - Nível: %s",
                nomeCompleto, idade, email, pontosTotais, nivelSustentabilidade);
    }
}
