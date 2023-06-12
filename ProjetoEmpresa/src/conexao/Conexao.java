/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Luis
 */
public class Conexao {
    
    public Connection getConexao()
    {
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/empresa?serverTimezone=UTC",
                    "root",
                    ""
            );
            Statement stmt = conn.createStatement();
            return conn;
        } catch (Exception e) {
            System.out.println("Erro ao conectar ao servidor de Banco de Dados: " + e.getMessage());
            return null;
        }
    }
}
