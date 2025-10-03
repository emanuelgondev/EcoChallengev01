package com.ecochallenge.managers;

import com.ecochallenge.models.Desafio;
import com.ecochallenge.models.RegistroDesafio;
import com.ecochallenge.models.Usuario;
import com.ecochallenge.dao.DesafioDAO;
import com.ecochallenge.dao.RegistroDesafioDAO;
import com.ecochallenge.dao.UsuarioDAO;
import java.util.List;
import java.time.LocalDateTime;

public class GerenciadorDados {
    private static GerenciadorDados instancia;
    private final UsuarioDAO usuarioDAO;
    private final DesafioDAO desafioDAO;
    private final RegistroDesafioDAO registroDesafioDAO;

    private GerenciadorDados() {
        usuarioDAO = new UsuarioDAO();
        desafioDAO = new DesafioDAO();
        registroDesafioDAO = new RegistroDesafioDAO();
    }

    public static GerenciadorDados getInstance() {
        if (instancia == null) {
            instancia = new GerenciadorDados();
        }
        return instancia;
    }

    public Usuario carregarUsuario() {
        return usuarioDAO.carregarUsuario();
    }

    public void salvarUsuario(Usuario usuario) {
        usuarioDAO.salvarUsuario(usuario);
    }

    public List<Desafio> obterDesafiosDisponiveis() {
        return desafioDAO.obterDesafiosDisponiveis();
    }

    public void completarDesafio(Desafio desafio, Usuario usuario) {
        // Cria um registro de desafio concluído
        RegistroDesafio registro = new RegistroDesafio(
            0, // id será gerado pelo banco
            usuario.obterId(),
            desafio.obterId(),
            desafio.obterTitulo(),
            desafio.obterPontosRecompensa(),
            LocalDateTime.now(),
            true
        );
        registroDesafioDAO.salvarRegistroDesafio(registro, usuario.obterId(), desafio.obterId());
        // Atualiza os pontos do usuário
        usuario.definirPontosTotais(usuario.obterPontosTotais() + desafio.obterPontosRecompensa());
        usuarioDAO.salvarUsuario(usuario);
    }

    public List<RegistroDesafio> carregarHistorico(Usuario usuario) {
        return registroDesafioDAO.carregarHistoricoPorUsuario(usuario.obterId());
    }

    public int obterTotalDesafiosCompletos(Usuario usuario) {
        return registroDesafioDAO.contarRegistrosPorUsuario(usuario.obterId());
    }
}
