/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Clientes;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Luis
 */
public class ClienteDAO {

    private Conexao conexao;
    private Connection conn;

    public ClienteDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public Boolean inserir(Clientes cliente) {

        String sql = "INSERT INTO cliente(nomecliente, cpfcliente, contatocliente, cidade, cep, veiculo) VALUES " + "(?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNomeCliente());
            stmt.setString(2, cliente.getCpfCliente());
            stmt.setString(3, cliente.getContato());
            stmt.setString(4, cliente.getCidade());
            stmt.setString(5, cliente.getCep());
            stmt.setString(6, cliente.getVeiculo());
            stmt.execute();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR - inserir" + e.getMessage());
            return false;
        }
    }

    public boolean editar(Clientes cliente){
        String sql = "UPDATE cliente SET nomecliente=?, cpfcliente=?, contatocliente=?, cidade=?, cep=?, veiculo=? WHERE id=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNomeCliente());
            stmt.setString(2, cliente.getCep());
            stmt.setString(3, cliente.getContato());
            stmt.setString(4, cliente.getCidade());
            stmt.setString(5, cliente.getCep());
            stmt.setString(6, cliente.getVeiculo());
            stmt.setInt(7, cliente.getId());
            stmt.execute();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR - Editar: " + e.getMessage());
            return false;
        }
        
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM cliente WHERE id=?";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR - excluir" + e.getMessage());
            return false;
        }
    }

//    public Clientes getClientes(String nomecliente) {
//        String sql = "SELECT * FROM cliente WHERE nomecliente=?";
//
//        try {
//            PreparedStatement stmt = this.conn.prepareStatement(sql);
//            stmt.setString(1, nomecliente);
//            ResultSet rs = stmt.executeQuery();
//            Clientes cliente = new Clientes();
//            rs.first();
//            cliente.setId(rs.getInt("id"));
//            cliente.setNomeCliente(nomecliente);
//            cliente.setCpfCliente(rs.getString("cpfcliente"));
//            cliente.setContato(rs.getString("contatocliente"));
//            cliente.setCidade(rs.getString("cidade"));
//            cliente.setCep(rs.getString("cep"));
//            cliente.setVeiculo(rs.getString("veiculo"));
//            return cliente;
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "ERROR - getClientes" + e.getMessage());
//            return null;
//        }
//    }

    public List<Clientes> getClientes(String nome) {
        String sql = "SELECT * FROM cliente WHERE nomecliente LIKE ?";
        List<Clientes> lista = new ArrayList<>();

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Clientes cliente = new Clientes();

                cliente.setId(rs.getInt("id"));
                cliente.setNomeCliente(rs.getString("nomecliente"));
                cliente.setCpfCliente(rs.getString("cpfcliente"));
                cliente.setContato(rs.getString("contatocliente"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setCep(rs.getString("cep"));
                cliente.setVeiculo(rs.getString("veiculo"));

                lista.add(cliente);
            }
            return lista;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
