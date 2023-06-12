/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Usuario;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Luis
 */
public class UsuarioDAO {

    private Conexao conexao;
    private Connection conn;

    public UsuarioDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public void inserir(Usuario usuario) {
        String sql = "INSERT INTO usuarios(usuario, senha) VALUES " + "(?, ?)";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getSenha());
            stmt.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error - Inserir(Usuarios) " + e.getMessage());
        }
    }

    public boolean login(Usuario usuario) {
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND senha = ?";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getSenha());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Login bem-sucedido
                JOptionPane.showMessageDialog(null, "Login realizado com sucesso.", "Login", JOptionPane.INFORMATION_MESSAGE);

                // Resto do código após o login
            } else {
                // Login inválido
                JOptionPane.showMessageDialog(null, "Nome de usuário ou senha incorretos.", "Login inválido", JOptionPane.ERROR_MESSAGE);
            }
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error - Login(Usuarios) " + e.getMessage());
            return false;
        }
    }

    public void excluir(Usuario usuario) {
        String sql = "DELETE FROM usuarios WHERE usuario=?";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, usuario.getUsuario());
            stmt.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error - Excluir(Usuarios)" + e.getMessage());
        }
    }

    public void editarSenha(Usuario usuario) {
        String sql = "UPDATE usuarios SET senha=? WHERE usuario=?";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, usuario.getSenha());
            stmt.setString(2, usuario.getUsuario());
            stmt.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error - EditarSenha(Usuarios) " + e.getMessage());
        }
    }
}
