/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import beans.Produto;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Luis
 */
public class ProdutoDAO {

    private Conexao conexao;
    private Connection conn;

    public ProdutoDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public boolean inserir(Produto produto) {

        String sql = "INSERT INTO produtos(nomeproduto, quantidade, valor) VALUES " + "(?, ?, ?)";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, produto.getNomeProduto());
            stmt.setInt(2, produto.getQuantidade());
            stmt.setDouble(3, produto.getValorProduto());
            stmt.execute();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO - inserir(Produtos) " + e.getMessage());
            return false;
        }
    }

    public boolean editar(Produto produto){
        String sql = "UPDATE produtos SET nomeproduto=?, quantidade=?, valor=? WHERE id=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, produto.getNomeProduto());
            stmt.setInt(2, produto.getQuantidade());
            stmt.setDouble(3, produto.getValorProduto());
            stmt.setInt(4, produto.getId());
            stmt.execute();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR - Editar: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM produtos WHERE id=?";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR - Excluir(Produtos)" + e.getMessage());
            return false;
        }
    }

//    public Produto getProdutos(String nomeproduto) {
//        String sql = "SELECT * FROM produtos WHERE nomeproduto=?";
//
//        try {
//            PreparedStatement stmt = this.conn.prepareStatement(sql);
//            stmt.setString(1, nomeproduto);
//            ResultSet rs = stmt.executeQuery();
//            Produto produto = new Produto();
//            rs.first();
//            produto.setId(rs.getInt("id"));
//            produto.setNomeProduto(nomeproduto);
//            produto.setQuantidade(rs.getInt("quantidade"));
//            produto.setValorProduto(rs.getInt("valor"));
//            return produto;
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Erro - GetProdutos(Produtos)" + e.getMessage());
//            return null;
//        }
//    }

    public List<Produto> getProdutos(String produtos) {
        String sql = "SELECT * FROM produtos WHERE nomeproduto LIKE ?";
        List<Produto> lista = new ArrayList<>();

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, "%" + produtos + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();

                produto.setId(rs.getInt("id"));
                produto.setNomeProduto(rs.getString("nomeproduto"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setValorProduto(rs.getInt("valor"));

                lista.add(produto);
            }
            return lista;
        } catch (Exception e) {
            return null;
        }
    }

}
